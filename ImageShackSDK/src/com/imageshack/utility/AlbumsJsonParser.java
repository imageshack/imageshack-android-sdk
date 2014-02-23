package com.imageshack.utility;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.model.AlbumModel;
import com.imageshack.model.AlbumsModel;
import com.imageshack.model.BasicImageModel;
import com.imageshack.model.SimpleAlbumModel;
import com.imageshack.model.SimpleUserModel;

public class AlbumsJsonParser {

	public static AlbumModel getAlbum(String jsonString) {
		AlbumModel album = new AlbumModel();
		JSONObject albumJson, json;

		try {
			albumJson = new JSONObject(jsonString);

			album.setProcessTime(albumJson.getInt(Const.PROCESS_TIME));

			if (!albumJson.getBoolean(Const.SUCCESS)) {
				setError(album, albumJson, false);
				return album;
			}

			json = new JSONObject(albumJson.getString(Const.RESULT));
			buildAlbumFromJson(json, album);

		} catch (JSONException e) {
			setError(album, null, true);
		}
		return album;
	}

	public static AlbumsModel getAlbums(String jsonString) {
		AlbumsModel albums = new AlbumsModel();
		ArrayList<AlbumModel> albumList = new ArrayList<AlbumModel>();
		JSONObject albumsJson, json;

		try {
			albumsJson = new JSONObject(jsonString);

			albums.setProcessTime(albumsJson.getInt(Const.PROCESS_TIME));

			if (!albumsJson.getBoolean(Const.SUCCESS)) {
				setError(albums, albumsJson, false);
				return albums;
			}

			json = new JSONObject(albumsJson.getString(Const.RESULT));
			albums.setLimit(json.getInt(Const.LIMIT));
			albums.setOffset(json.getInt(Const.OFFSET));
			albums.setTotal(json.getInt(Const.TOTAL));

			buildAlbumListFromJson(json.getJSONArray(Const.ALBUMS), albumList);
			albums.setAlbums(albumList);

		} catch (JSONException e) {
			setError(albums, null, true);
		}

		return albums;
	}

	public static void buildSimpleAlbumFromJson(JSONObject json,
			SimpleAlbumModel album) {
		try {
			album.setId(json.getString(Const.ID));
			if (album.getId() == null || "".equals(album.getId())) {
				album.setId("");
			} else {
				album.setTitle(json.getString(Const.TITLE));
				album.setPublic(json.getBoolean(Const.PUBLIC));
			}
		} catch (JSONException e) {
			album.setId("");
		}
	}

	public static void buildAlbumFromJson(JSONObject json, AlbumModel album) {
		SimpleUserModel owner = new SimpleUserModel();
		ArrayList<BasicImageModel> images = new ArrayList<BasicImageModel>();

		buildSimpleAlbumFromJson(json, album);

		if (album != null) {
			try {
				album.setCreationDate(json.getInt(Const.CREATION_DATE));
				album.setOwner(json.getBoolean(Const.IS_OWNER));

				UserJsonParser.buildSimpleUserFromJson(
						new JSONObject(json.getString(Const.OWNER)), owner);
				album.setOwner(owner);

				ImagesJsonParser.buildListOfBasicImagesFromJson(
						json.getJSONArray(Const.IMAGES), images);
				album.setImages(images);

			} catch (JSONException e) {
				album = null;
			}
		}
	}

	public static void buildAlbumListFromJson(JSONArray albumsJsonArray,
			ArrayList<AlbumModel> albums) {
		try {
			for (int i = 0; i < albumsJsonArray.length(); i++) {
				AlbumModel album = new AlbumModel();
				buildAlbumFromJson(albumsJsonArray.getJSONObject(i), album);
				if (album != null) {
					albums.add(album);
				}
			}
		} catch (JSONException e) {
			// skip bad json, should never happen
		}
	}

	public static void setError(AlbumModel model, JSONObject json,
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

	public static void setError(AlbumsModel model, JSONObject json,
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
