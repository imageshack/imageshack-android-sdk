package com.imageshack.client;

import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.listener.ResponseListener;
import com.imageshack.model.SimpleModel;
import com.imageshack.model.UserModel;
import com.imageshack.model.UserSettingsModel;
import com.imageshack.utility.SimpleResponseJsonParser;
import com.imageshack.utility.UserJsonParser;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class UserClient extends ImageShackAbstractClient {

	private static final String API_ENDPOINT = ROUTE + Const.USER;
	private String authToken;

	public UserClient(String apiKey, String authToken) {
		super(apiKey);
		this.authToken = authToken;
	}

	/**
	 * Fetches user information from ImageShack and returns UserModel. If
	 * username is null, then returns the UserModel of the authenticated user.
	 * 
	 * @param username
	 *            the username of user to fetch
	 * @param imageLimit
	 *            the image limit for latest images, optional
	 * @param listener
	 *            the response listener that is notified when async http request
	 *            is completed
	 */
	public void get(String username, Integer imageLimit,
			ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (imageLimit != null) {
			params.put(Const.IMAGE_LIMIT, imageLimit.intValue());
		}

		responseListener = listener;

		if (username == null) {
			client.get(API_ENDPOINT, params, new UserResponseHandler());
		} else {
			client.get(API_ENDPOINT + "/" + username, params,
					new UserResponseHandler());
		}
	}

	/**
	 * Update user
	 * 
	 * @param username
	 *            the current username, optional
	 * @param email
	 *            new email, optional
	 * @param newUsername
	 *            new username, optional
	 * @param password
	 *            new password, optional
	 * @param firstName
	 *            new first name, optional
	 * @param lastName
	 *            new last name, optional
	 * @param gender
	 *            new gender, optional
	 * @param location
	 *            new location, optional
	 * @param description
	 *            new description, optional
	 * @param avatarServer
	 *            new avatar server, optional
	 * @param avatarFilename
	 *            new avatar filename, optional
	 * @param listener
	 *            the listener for async http response
	 */
	public void update(String username, String email, String newUsername,
			String password, String firstName, String lastName, String gender,
			String location, String description, String avatarServer,
			String avatarFilename, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (email != null) {
			params.put(Const.EMAIL, email);
		}

		if (newUsername != null) {
			params.put(Const.USERNMAE, newUsername);
		}

		if (password != null) {
			params.put(Const.PASSWORD, password);
		}

		if (firstName != null) {
			params.put(Const.FIRST_NAME, firstName);
		}

		if (lastName != null) {
			params.put(Const.LAST_NAME, lastName);
		}

		if (gender != null) {
			params.put(Const.GENDER, gender);
		}

		if (location != null) {
			params.put(Const.LOCATION, location);
		}

		if (description != null) {
			params.put(Const.DESCRIPTION, description);
		}

		if (avatarServer != null) {
			params.put(Const.AVATAR_SERVER, avatarServer);
		}

		if (avatarFilename != null) {
			params.put(Const.AVATAR_FILENAME, avatarFilename);
		}

		responseListener = listener;
		client.put(API_ENDPOINT, params, new UserResponseHandler());
	}

	/**
	 * Delete logged in user
	 * 
	 * @param listener
	 *            the listener for async http response
	 */
	public void delete(ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		responseListener = listener;

		client.delete(null, API_ENDPOINT, null, params,
				new DeleteUserResponseHandler());
	}

	/**
	 * Get user settings
	 * 
	 * @param listener
	 *            the listener for async http response
	 */
	public void getSettings(ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		String url = String.format("%s/%s", API_ENDPOINT, Const.SETTINGS);
		
		responseListener = listener;
		client.get(url, params, new UserSettingsResponseHandler());
	}

	/**
	 * Update user settings
	 * 
	 * @param isPrivateDefaultImages
	 *            make new images private by default
	 * @param isPrivateDefaultAlbums
	 *            make new albums private by default
	 * @param isFollowingAllowed
	 *            opt out of following feature
	 * @param enabledUploadNotifications
	 *            enable/disable new uploads email notifications
	 * @param enableCommentsNotifications
	 *            enable/disable comments email notifications
	 * @param enableLikesNotifications
	 *            enable/disable likes email notifications
	 * @param enableFollowingNotifications
	 *            enable/disable new followers email notifications
	 * @param listener
	 */
	public void updateSettings(Boolean isPrivateDefaultImages,
			Boolean isPrivateDefaultAlbums, Boolean isFollowingAllowed,
			Boolean enabledUploadNotifications,
			Boolean enableCommentsNotifications,
			Boolean enableLikesNotifications,
			Boolean enableFollowingNotifications, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (isPrivateDefaultImages != null) {
			params.put(Const.PRIVATE_IMAGES, isPrivateDefaultImages.toString());
		}

		if (isPrivateDefaultAlbums != null) {
			params.put(Const.PRIVATE_ALBUMS, isPrivateDefaultAlbums);
		}

		if (isFollowingAllowed != null) {
			params.put(Const.PRIVATE_FOLLOWING, isFollowingAllowed);
		}

		if (enabledUploadNotifications != null) {
			params.put(Const.UPLOAD_NOTIFICATIONS, enabledUploadNotifications);
		}

		if (enableCommentsNotifications != null) {
			params.put(Const.COMMENTS_NOTIFICATIONS,
					enableCommentsNotifications);
		}

		if (enableLikesNotifications != null) {
			params.put(Const.LIKES_NOTIFICATIONS, enableLikesNotifications);
		}

		if (enableFollowingNotifications != null) {
			params.put(Const.FOLLOWING_NOTIFICATIONS, enableLikesNotifications);
		}

		String url = String.format("%s/%s", API_ENDPOINT, Const.SETTINGS);
		responseListener = listener;
		client.put(url, params, new UserSettingsResponseHandler());
	}

	private class UserResponseHandler extends AsyncHttpResponseHandler {

		public void onSuccess(String result) {
			UserModel user = UserJsonParser.getUser(result);
			responseListener.onResponse(user);
		}

		public void onFailure(Throwable arg0, String errorMsg) {
			UserModel user = new UserModel();

			try {
				UserJsonParser.setError(user, new JSONObject(errorMsg), false);
			} catch (JSONException e) {
				user.setSuccess(false);
				user.setError(Const.USER_ERROR);
			}

			responseListener.onResponse(user);
		}

	}

	private class DeleteUserResponseHandler extends AsyncHttpResponseHandler {

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

	private class UserSettingsResponseHandler extends AsyncHttpResponseHandler {

		public void onSuccess(String result) {
			UserSettingsModel settings = UserJsonParser.getSettings(result);
			responseListener.onResponse(settings);
		}

		public void onFailure(Throwable arg0, String errorMsg) {
			UserSettingsModel settings = new UserSettingsModel();

			try {
				UserJsonParser.setError(settings, new JSONObject(errorMsg),
						false);
			} catch (JSONException e) {
				settings.setSuccess(false);
				settings.setError(Const.USER_ERROR);
			}

			responseListener.onResponse(settings);
		}
	}

}
