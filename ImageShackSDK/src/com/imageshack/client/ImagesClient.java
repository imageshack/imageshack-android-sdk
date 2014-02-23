package com.imageshack.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.listener.ResponseListener;
import com.imageshack.model.ImageModel;
import com.imageshack.model.ImagesModel;
import com.imageshack.model.SimpleModel;
import com.imageshack.model.UploadModel;
import com.imageshack.utility.ImagesJsonParser;
import com.imageshack.utility.SimpleResponseJsonParser;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ImagesClient extends ImageShackAbstractClient {

	private static final String API_ENDPOINT = ROUTE + Const.IMAGES;
	private String authToken;

	public ImagesClient(String apiKey, String authToken) {
		super(apiKey);
		this.authToken = authToken;
	}

	/**
	 * Fetch image data
	 * 
	 * @param imageId
	 *            the image id
	 * @param listener
	 *            the listener for async http response
	 */
	public void get(String imageId, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (imageId != null) {
			String url = String.format("%s/%s", API_ENDPOINT, imageId);
			responseListener = listener;
			client.get(url, params, new ImageResponseHandler());
		} else {
			returnInvalidCall(listener, false);
		}
	}

	/**
	 * Fetch user images
	 * 
	 * @param limit
	 *            the max number of images to return per request
	 * @param offset
	 *            the offset for pagination
	 * @param listener
	 *            the listener for async http response
	 */
	public void get(Integer limit, Integer offset, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (limit != null) {
			params.put(Const.LIMIT, limit.intValue());
		}

		if (offset != null) {
			params.put(Const.OFFSET, offset.intValue());
		}

		responseListener = listener;
		client.get(API_ENDPOINT, params, new ImagesResponseHandler());
	}

	/**
	 * Delete images
	 * 
	 * @param imageIds
	 *            list of image ids
	 * @param listener
	 */
	public void delete(ArrayList<String> imageIds, ResponseListener listener) {
		StringBuilder ids = new StringBuilder();
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (imageIds != null) {
			for (String id : imageIds) {
				ids.append(id).append(",");
			}
			params.put(Const.IDS, ids.toString());
		} else {
			returnInvalidCall(listener, false);
		}

		responseListener = listener;
		client.delete(null, API_ENDPOINT, null, params,
				new SimpleResponseHandler());
	}

	/**
	 * Upload image
	 * 
	 * @param imageURI
	 *            the location of the image in your device, required
	 * @param tags
	 *            array of tags, optional
	 * @param album
	 *            the album id or title to upload the image into, optional
	 * @param title
	 *            image title, optional
	 * @param commentsDisabled
	 *            , optional
	 * @param isPublic
	 *            false will make the image private
	 * @param listener
	 *            the listener for async http response
	 */
	public void upload(String imageURI, String[] tags, String album,
			String title, Boolean commentsDisabled, Boolean isPublic,
			ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		try {
			params.put("file", new File(imageURI));
		} catch (FileNotFoundException e) {
			// this should never happen
		}

		if (tags != null) {
			StringBuilder tagsCsv = new StringBuilder();

			for (int i = 0; i < tags.length; i++) {
				tagsCsv.append(tags[i]);
				if (i < tags.length - 1) {
					tagsCsv.append(",");
				}
			}

			params.put(Const.TAGS, tagsCsv.toString());
		}

		if (album != null) {
			params.put(Const.ALBUM, album);
		}

		if (title != null) {
			params.put(Const.TITLE, title);
		}

		if (commentsDisabled != null) {
			params.put(Const.COMMENTS_DISABLED, commentsDisabled.toString());
		}

		if (isPublic != null) {
			params.put(Const.PUBLIC, isPublic.toString());
		}

		responseListener = listener;
		client.post(API_ENDPOINT, params, new UploadImageResponseHandler());
	}

	/**
	 * Like an image
	 * 
	 * @param imageId
	 *            the image id
	 * @param listener
	 */
	public void like(String imageId, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (imageId != null) {
			String url = String.format("%s/%s/%s", API_ENDPOINT, imageId,
					Const.LIKES);
			responseListener = listener;
			client.post(url, params, new SimpleResponseHandler());
		} else {
			returnInvalidCall(listener, true);
		}
	}

	/**
	 * Unlike image
	 * 
	 * @param imageId
	 *            the image id
	 * @param listener
	 */
	public void unlike(String imageId, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (imageId != null) {
			String url = String.format("%s/%s/%s", API_ENDPOINT, imageId,
					Const.UNLIKE);
			responseListener = listener;
			client.post(url, params, new SimpleResponseHandler());
		} else {
			returnInvalidCall(listener, true);
		}
	}

	/**
	 * Rotates an image by a given angle
	 * 
	 * @param imageId
	 *            the image id
	 * @param angle
	 *            the angle to rotate; accepted values are 90, 180 and 270
	 * @param listener
	 */
	public void rotate(String imageId, Integer angle, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (imageId != null && angle != null) {
			params.put(Const.ID, imageId);
			params.put(Const.ANGLE, angle);

			String url = String.format("%s/%s", API_ENDPOINT, Const.ROTATE);
			responseListener = listener;
			client.post(url, params, new ImageResponseHandler());
		} else {
			returnInvalidCall(listener, false);
		}
	}

	/**
	 * Update image properties
	 * 
	 * @param imageId
	 *            the image id to be updated, required
	 * @param title
	 *            new title, optional
	 * @param description
	 *            new description, optional
	 * @param tags
	 *            new tags, optional
	 * @param originalFilename
	 *            new original filename, optional
	 * @param isPublic
	 *            set to public/private, optional
	 * @param isCommentsDisabled
	 *            disable/enable comments, optional
	 * @param listener
	 */
	public void update(String imageId, String title, String description,
			ArrayList<String> tags, String originalFilename, Boolean isPublic,
			Boolean isCommentsDisabled, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (imageId != null) {
			if (title != null) {
				params.put(Const.TITLE, title);
			}

			if (description != null) {
				params.put(Const.DESCRIPTION, description);
			}

			if (tags != null) {
				StringBuilder tagsCsv = new StringBuilder();

				for (int i = 0; i < tags.size(); i++) {
					tagsCsv.append(tags.get(i));
					if (i < tags.size() - 1) {
						tagsCsv.append(",");
					}
				}

				params.put(Const.TAGS, tagsCsv.toString());
			}

			if (originalFilename != null) {
				params.put(Const.ORIGINAL_FILENAME, originalFilename);
			}

			if (isPublic != null) {
				params.put(Const.PUBLIC, isPublic.toString());
			}

			if (isCommentsDisabled != null) {
				params.put(Const.COMMENTS_DISABLED,
						isCommentsDisabled.toString());
			}

			responseListener = listener;
			client.put(API_ENDPOINT + "/" + imageId, params,
					new ImageResponseHandler());
		} else {
			returnInvalidCall(listener, false);
		}
	}

	private class SimpleResponseHandler extends AsyncHttpResponseHandler {

		public void onSuccess(String result) {
			SimpleModel model = SimpleResponseJsonParser.parse(result);
			responseListener.onResponse(model);
		}

		public void onFailure(Throwable arg0, String errorMsg) {
			SimpleModel model = SimpleResponseJsonParser.parse(errorMsg);

			try {
				SimpleResponseJsonParser.setError(model, new JSONObject(
						errorMsg), false);
			} catch (JSONException e) {
				model.setSuccess(false);
				model.setError(Const.GENERIC_ERROR);
			}

			responseListener.onResponse(model);
		}

	}

	private class ImageResponseHandler extends AsyncHttpResponseHandler {

		public void onSuccess(String result) {
			ImageModel image = ImagesJsonParser.getImage(result);
			responseListener.onResponse(image);
		}

		public void onFailure(Throwable arg0, String errorMsg) {
			ImageModel image = new ImageModel();

			try {
				ImagesJsonParser.setError(image, new JSONObject(errorMsg),
						false);
			} catch (JSONException e) {
				image.setSuccess(false);
				image.setError(Const.IMAGE_ERROR);
			}

			responseListener.onResponse(image);
		}

	}

	private class ImagesResponseHandler extends AsyncHttpResponseHandler {

		public void onSuccess(String result) {
			ImagesModel images = ImagesJsonParser.getImages(result);
			responseListener.onResponse(images);
		}

		public void onFailure(Throwable arg0, String errorMsg) {
			ImagesModel images = new ImagesModel();

			try {
				ImagesJsonParser.setError(images, new JSONObject(errorMsg),
						false);
			} catch (JSONException e) {
				images.setSuccess(false);
				images.setError(Const.IMAGE_ERROR);
			}

			responseListener.onResponse(images);
		}

	}

	private class UploadImageResponseHandler extends AsyncHttpResponseHandler {

		public void onSuccess(String result) {
			UploadModel upload = ImagesJsonParser.uploadImage(result);
			responseListener.onResponse(upload);
		}

		public void onFailure(Throwable arg0, String errorMsg) {
			UploadModel upload = new UploadModel();

			try {
				ImagesJsonParser.setError(upload, new JSONObject(errorMsg),
						false);
			} catch (JSONException e) {
				upload.setSuccess(false);
				upload.setError(Const.UPLOAD_ERROR);
			}
		}

	}

	private void returnInvalidCall(ResponseListener listener, boolean isSimple) {
		if (isSimple) {
			SimpleModel model = new SimpleModel();
			model.setSuccess(false);
			model.setProcessTime(0);
			model.setError(Const.MISSING_PARAMS);
			listener.onResponse(model);
		} else {
			ImageModel image = new ImageModel();
			image.setSuccess(false);
			image.setProcessTime(0);
			image.setError(Const.MISSING_PARAMS);
			listener.onResponse(image);
		}
	}

}