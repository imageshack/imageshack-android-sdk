package com.imageshack.utility;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.model.BasicImageModel;
import com.imageshack.model.ImageModel;
import com.imageshack.model.ImagesModel;
import com.imageshack.model.MiniImageModel;
import com.imageshack.model.SimpleAlbumModel;
import com.imageshack.model.SimpleImageModel;
import com.imageshack.model.SimpleUserModel;
import com.imageshack.model.UploadModel;

public class ImagesJsonParser {

	public static ImageModel getImage(String jsonString) {
		ImageModel image = new ImageModel();
		JSONObject imageJson, json;

		try {
			imageJson = new JSONObject(jsonString);

			image.setProcessTime(imageJson.getInt(Const.PROCESS_TIME));

			if (!imageJson.getBoolean(Const.SUCCESS)) {
				setError(image, imageJson, false);
				return image;
			}

			json = new JSONObject(imageJson.getString(Const.RESULT));
			buildImageFromJson(json, image);

		} catch (JSONException e) {
			setError(image, null, true);
		}

		return image;
	}

	public static ImagesModel getImages(String jsonString) {
		ImagesModel images = new ImagesModel();
		ArrayList<BasicImageModel> imageList = new ArrayList<BasicImageModel>();
		JSONObject imagesJson, json;

		try {
			imagesJson = new JSONObject(jsonString);

			images.setProcessTime(imagesJson.getInt(Const.PROCESS_TIME));

			if (!imagesJson.getBoolean(Const.SUCCESS)) {
				setError(images, imagesJson, false);
				return images;
			}

			json = new JSONObject(imagesJson.getString(Const.RESULT));
			images.setLimit(json.getInt(Const.LIMIT));
			images.setOffset(json.getInt(Const.OFFSET));
			images.setTotal(json.getInt(Const.TOTAL));

			buildListOfBasicImagesFromJson(json.getJSONArray(Const.IMAGES),
					imageList);
			images.setImages(imageList);

		} catch (JSONException e) {
			setError(images, null, true);
		}

		return images;
	}

	public static void buildListOfBasicImagesFromJson(
			JSONArray imagesJsonArray, ArrayList<BasicImageModel> images) {
		try {
			for (int i = 0; i < imagesJsonArray.length(); i++) {
				BasicImageModel image = new BasicImageModel();
				buildBasicImageFromJson(imagesJsonArray.getJSONObject(i), image);
				
				if (image.getServer() != 0) {
					images.add(image);
				}
			}
		} catch (JSONException e) {
			// skip bad json, this should never happen
		}
	}

	public static UploadModel uploadImage(String jsonString) {
		UploadModel upload = new UploadModel();

		JSONObject uploadJson, json;
		JSONArray imagesArray;

		try {
			uploadJson = new JSONObject(jsonString);

			upload.setProcessTime(uploadJson.getInt(Const.PROCESS_TIME));

			if (!uploadJson.getBoolean(Const.SUCCESS)) {
				setError(upload, uploadJson, false);
				return upload;
			}

			json = new JSONObject(uploadJson.getString(Const.RESULT));
			upload.setPassed(json.getInt(Const.PASSED));
			upload.setFailed(json.getInt(Const.FAILED));
			upload.setTotal(json.getInt(Const.TOTAL));

			imagesArray = json.getJSONArray(Const.IMAGES);

			for (int i = 0; i < imagesArray.length(); i++) {
				ImageModel image = new ImageModel();
				buildImageFromJson(imagesArray.getJSONObject(i), image);
				if (image != null) {
					upload.add(image);
				}
			}

		} catch (JSONException e) {
			setError(upload, null, true);
		}

		return upload;

	}

	public static void buildSimpleImageArrayFromJson(JSONArray imageJsonArray,
			ArrayList<SimpleImageModel> images) {
		JSONObject imageJson;

		try {
			for (int i = 0; i < imageJsonArray.length(); i++) {
				SimpleImageModel image = new SimpleImageModel();
				imageJson = imageJsonArray.getJSONObject(i);
				buildSimpleImageFromJson(imageJson, image);
				images.add(image);
			}
		} catch (JSONException e) {
			// skip bad imageJson
		}
	}

	public static void buildMiniImageFromJson(JSONObject imageJson,
			MiniImageModel image) {
		try {
			image.setServer(imageJson.getInt(Const.SERVER));
			image.setFilename(imageJson.getString(Const.FILENAME));
		} catch (JSONException e) {
			image.setServer(0);
		}
	}

	public static void buildSimpleImageFromJson(JSONObject imageJson,
			SimpleImageModel image) {

		buildMiniImageFromJson(imageJson, image);
		try {
			image.setId(imageJson.getString(Const.ID));
			image.setBucket(imageJson.getInt(Const.BUCKET));
			image.setWidth(imageJson.getInt(Const.WIDTH));
			image.setHeight(imageJson.getInt(Const.HEIGHT));
		} catch (JSONException e) {
			image = null;
		}
	}

	public static void buildBasicImageFromJson(JSONObject json,
			BasicImageModel image) {
		SimpleAlbumModel album = new SimpleAlbumModel();
		SimpleUserModel owner = new SimpleUserModel();

		buildSimpleImageFromJson(json, image);

		try {
			image.setOriginalFilename(json.getString(Const.ORIGINAL_FILENAME));
			image.setTitle(json.getString(Const.TITLE));
			image.setLikes(json.getInt(Const.LIKES));
			image.setFilesize(json.getInt(Const.FILESIZE));
			image.setCreationDate(json.getInt(Const.CREATION_DATE));
			image.setPublic(json.getBoolean(Const.PUBLIC));
			image.setOwner(json.getBoolean(Const.IS_OWNER));

			AlbumsJsonParser.buildSimpleAlbumFromJson(
					new JSONObject(json.getString(Const.ALBUM)), album);

			if ("".equals(album.getId())) {
				album = null;
			}

			image.setAlbum(album);

			UserJsonParser.buildSimpleUserFromJson(
					new JSONObject(json.getString(Const.OWNER)), owner);
			image.setOwner(owner);

		} catch (JSONException e) {
			image = null;
		}
	}

	private static void buildImageFromJson(JSONObject json, ImageModel image) {
		ArrayList<SimpleImageModel> prev = new ArrayList<SimpleImageModel>(), next = new ArrayList<SimpleImageModel>();

		buildBasicImageFromJson(json, image);

		try {
			image.setDirectLink(json.getString(Const.DIRECT_LINK));
			image.setDescription(json.getString(Const.DESCRIPTION));

			image.setTags(getTagsFromString(json.getString(Const.TAGS)));

			image.setLiked(json.getBoolean(Const.LIKED));
			image.setViews(json.getInt(Const.VIEWS));
			image.setFilter(json.getInt(Const.FILTER));
			image.setCommentsCount(json.getInt(Const.COMMENTS_COUNT));
			image.setCommentsDisabled(json.getBoolean(Const.COMMENTS_DISABLED));

			buildSimpleImageArrayFromJson(json.getJSONArray(Const.PREV_IMAGES),
					prev);
			image.setPrev(prev);

			buildSimpleImageArrayFromJson(json.getJSONArray(Const.NEXT_IMAGES),
					next);
			image.setNext(next);

		} catch (JSONException e) {
			image = null;
		}
	}

	private static ArrayList<String> getTagsFromString(String tagsArray) {
		ArrayList<String> tags = new ArrayList<String>();

		// strip '["' '"]'
		String tmp = tagsArray.substring(2, tagsArray.length() - 2);

		// split string by '","'
		String[] array = tmp.split("\",\"");

		// strip quotes and add to list
		for (String s : array) {
			tags.add(s);
		}

		return tags;
	}

	public static void setError(ImageModel model, JSONObject json,
			boolean isException) {
		model.setSuccess(false);

		if (isException) {
			model.setError(Const.JSON_PARSING_EXCEPTION);
		} else {
			try {
				model.setError(json.getJSONObject(Const.ERROR).getString(
						Const.ERROR_MESSAGE));
			} catch (JSONException e) {
				model.setError(Const.JSON_PARSING_EXCEPTION);
			}
		}
	}

	public static void setError(ImagesModel model, JSONObject json,
			boolean isException) {
		model.setSuccess(false);

		if (isException) {
			model.setError(Const.JSON_PARSING_EXCEPTION);
		} else {
			try {
				model.setError(json.getJSONObject(Const.ERROR).getString(
						Const.ERROR_MESSAGE));
			} catch (JSONException e) {
				model.setError(Const.JSON_PARSING_EXCEPTION);
			}
		}
	}

	public static void setError(UploadModel model, JSONObject json,
			boolean isException) {
		model.setSuccess(false);

		if (isException) {
			model.setError(Const.JSON_PARSING_EXCEPTION);
		} else {
			try {
				model.setError(json.getJSONObject(Const.ERROR).getString(
						Const.ERROR_MESSAGE));
			} catch (JSONException e) {
				model.setError(Const.JSON_PARSING_EXCEPTION);
			}
		}
	}

}