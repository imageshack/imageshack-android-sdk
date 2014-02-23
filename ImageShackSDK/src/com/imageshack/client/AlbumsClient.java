package com.imageshack.client;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.listener.ResponseListener;
import com.imageshack.model.AlbumModel;
import com.imageshack.model.AlbumsModel;
import com.imageshack.model.SimpleModel;
import com.imageshack.utility.AlbumsJsonParser;
import com.imageshack.utility.SimpleResponseJsonParser;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class AlbumsClient extends ImageShackAbstractClient {

	private static final String API_ENDPOINT = ROUTE + Const.ALBUMS;
	private String authToken;

	public AlbumsClient(String apiKey, String authToken) {
		super(apiKey);
		this.authToken = authToken;
	}

	/**
	 * Fetch album data
	 * 
	 * @param albumId
	 *            the album id
	 * @param listener
	 *            the listener for async http response
	 */
	public void get(String albumId, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (albumId != null) {
			String url = String.format("%s/%s", API_ENDPOINT, albumId);
			responseListener = listener;
			client.get(url, params, new AlbumResponseHandler());
		} else {
			returnInvalidCall(listener, false);
		}
	}

	/**
	 * Fetch user album
	 * 
	 * @param limit
	 *            the max number of albums to return per request
	 * @param offset
	 *            the offset for pagination
	 * @param listener
	 *            the listener for async http response
	 */
	public void get(Integer limit, Integer offset, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);
		params.put(Const.SHOW_PRIVATE, "1");

		if (limit != null) {
			params.put(Const.LIMIT, limit.intValue());
		}

		if (offset != null) {
			params.put(Const.OFFSET, offset.intValue());
		}

		responseListener = listener;
		client.get(API_ENDPOINT, params, new AlbumsResponseHandler());
	}

	/**
	 * Create an album
	 * 
	 * @param title
	 *            album title, required
	 * @param description
	 *            album description, optional
	 * @param isPublic
	 *            false will make the album private, the default is true,
	 *            optional
	 * @param imageIds
	 *            list of images to be added to the album, optional
	 * @param listener
	 */
	public void create(String title, String description, Boolean isPublic,
			ArrayList<String> imageIds, ResponseListener listener) {
		StringBuilder ids = new StringBuilder();
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (description != null) {
			params.put(Const.DESCRIPTION, description);
		}

		if (isPublic != null) {
			params.put(Const.PUBLIC, isPublic.toString());
		}

		if (imageIds != null) {
			for (String id : imageIds) {
				ids.append(id).append(",");
			}
			params.put(Const.IDS, ids.toString());
		}

		if (title != null) {
			params.put(Const.TITLE, title);
			responseListener = listener;
			client.post(API_ENDPOINT, params, new AlbumResponseHandler());
		} else {
			returnInvalidCall(listener, false);
		}
	}

	/**
	 * Update album
	 * 
	 * @param title
	 *            new title, optional
	 * @param description
	 *            new description, optional
	 * @param isPublic
	 *            false will make the album private, optional
	 * @param listener
	 */
	public void update(String albumId, String title, String description,
			Boolean isPublic, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);
		boolean update = false;

		if (title != null) {
			update = true;
			params.put(Const.TITLE, title);
		}

		if (description != null) {
			update = true;
			params.put(Const.DESCRIPTION, description);
		}

		if (isPublic != null) {
			update = true;
			params.put(Const.PUBLIC, isPublic.toString());
		}

		if (albumId != null && update) {
			String url = String.format("%s/%s", API_ENDPOINT, albumId);
			responseListener = listener;
			client.put(url, params, new AlbumResponseHandler());
		} else {
			returnInvalidCall(listener, false);
		}

	}

	/**
	 * Add images to an album
	 * 
	 * @param albumId
	 *            the album id, required
	 * @param imageIds
	 *            list of image ids, required
	 * @param listener
	 */
	public void add(String albumId, ArrayList<String> imageIds,
			ResponseListener listener) {
		StringBuilder ids = new StringBuilder();
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (albumId != null && imageIds != null && imageIds.size() > 1) {
			for (String id : imageIds) {
				ids.append(id).append(",");
			}

			params.put(Const.FILES, ids.toString());

			String url = String.format("%s/%s/%s", API_ENDPOINT, albumId,
					Const.ALBUMS_ADD_IMAGES);

			responseListener = listener;
			client.put(url, params, new AlbumResponseHandler());
		} else {
			returnInvalidCall(listener, false);
		}
	}

	/**
	 * Delete images from an album
	 * 
	 * @param albumId
	 *            the album id, required
	 * @param imageIds
	 *            list of image ids, required
	 * @param listener
	 */
	public void delete(String albumId, ArrayList<String> imageIds,
			ResponseListener listener) {
		StringBuilder ids = new StringBuilder();
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (albumId != null && imageIds != null && imageIds.size() > 1) {
			for (String id : imageIds) {
				ids.append(id).append(",");
			}

			params.put(Const.FILES, ids.toString());

			String url = String.format("%s/%s/%s", API_ENDPOINT, albumId,
					Const.ALBUMS_DELETE_IMAGES);

			responseListener = listener;
			client.put(url, params, new AlbumResponseHandler());
		} else {
			returnInvalidCall(listener, false);
		}
	}

	/**
	 * Remove the entire album and its contents
	 * 
	 * @param albumId
	 *            the album id
	 * @param listener
	 */
	public void remove(String albumId, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (albumId != null) {
			String url = String.format("%s/%s", API_ENDPOINT, albumId);
			responseListener = listener;
			client.delete(null, url, null, params, new SimpleResponseHandler());
		} else {
			returnInvalidCall(listener, true);
		}
	}

	private class AlbumResponseHandler extends AsyncHttpResponseHandler {

		public void onSuccess(String result) {
			AlbumModel album = AlbumsJsonParser.getAlbum(result);
			responseListener.onResponse(album);
		}

		public void onFailure(Throwable arg0, String errorMsg) {
			AlbumModel album = new AlbumModel();

			try {
				AlbumsJsonParser.setError(album, new JSONObject(errorMsg),
						false);
			} catch (JSONException e) {
				album.setSuccess(false);
				album.setError(Const.ALBUM_ERROR);
			}

			responseListener.onResponse(album);
		}

	}

	private class AlbumsResponseHandler extends AsyncHttpResponseHandler {

		public void onSuccess(String result) {
			AlbumsModel albums = AlbumsJsonParser.getAlbums(result);
			responseListener.onResponse(albums);
		}

		public void onFailure(Throwable arg0, String errorMsg) {
			AlbumsModel albums = new AlbumsModel();

			try {
				AlbumsJsonParser.setError(albums, new JSONObject(errorMsg),
						false);
			} catch (JSONException e) {
				albums.setSuccess(false);
				albums.setError(Const.ALBUM_ERROR);
			}

			responseListener.onResponse(albums);
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

	private void returnInvalidCall(ResponseListener listener, boolean isSimple) {
		if (isSimple) {
			SimpleModel model = new SimpleModel();
			model.setSuccess(false);
			model.setProcessTime(0);
			model.setError(Const.MISSING_PARAMS);
			listener.onResponse(model);
		} else {
			AlbumModel album = new AlbumModel();
			album.setSuccess(false);
			album.setProcessTime(0);
			album.setError(Const.MISSING_PARAMS);
			listener.onResponse(album);
		}
	}

}
