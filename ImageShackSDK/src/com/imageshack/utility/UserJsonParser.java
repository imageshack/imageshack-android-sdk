package com.imageshack.utility;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.model.MiniImageModel;
import com.imageshack.model.SimpleImageModel;
import com.imageshack.model.SimpleUserModel;
import com.imageshack.model.UserModel;
import com.imageshack.model.UserSettingsModel;

public class UserJsonParser {

	public static UserModel getUser(String jsonString) {
		UserModel user = new UserModel();
		JSONObject userJson, json;

		try {
			userJson = new JSONObject(jsonString);

			user.setProcessTime(userJson.getInt(Const.PROCESS_TIME));

			if (!userJson.getBoolean(Const.SUCCESS)) {
				setError(user, userJson, false);
				return user;
			}

			json = new JSONObject(userJson.getString(Const.RESULT));
			buildUserFromJson(json, user);

		} catch (JSONException e) {
			setError(user, null, true);
		}

		return user;
	}

	public static UserSettingsModel getSettings(String jsonString) {
		UserSettingsModel settings = new UserSettingsModel();
		JSONObject settingsJson, json;

		try {
			settingsJson = new JSONObject(jsonString);

			settings.setProcessTime(settingsJson.getInt(Const.PROCESS_TIME));

			if (!settingsJson.getBoolean(Const.SUCCESS)) {
				setError(settings, settingsJson, false);
				return settings;
			}

			json = new JSONObject(settingsJson.getString(Const.RESULT));
			settings.setPrivateProfile(json.getBoolean(Const.PRIVATE_PROFILE));
			settings.setPrivateDefaultAlbums(json
					.getBoolean(Const.PRIVATE_ALBUMS));
			settings.setPrivateDefaultImages(json
					.getBoolean(Const.PRIVATE_IMAGES));
			settings.setFollowingAllowed(json
					.getBoolean(Const.PRIVATE_FOLLOWING));
			settings.setEnabledUploadNotifications(json
					.getBoolean(Const.UPLOAD_NOTIFICATIONS));
			settings.setEnabledCommentsNotifications(json
					.getBoolean(Const.COMMENTS_NOTIFICATIONS));
			settings.setEnabledLikesNotifications(json
					.getBoolean(Const.LIKES_NOTIFICATIONS));
			settings.setEnabledFollowingNotifications(json
					.getBoolean(Const.FOLLOWING_NOTIFICATIONS));
			
		} catch (JSONException e) {
			setError(settings, null, true);
		}

		return settings;
	}

	public static void buildUserFromJson(JSONObject userJson, UserModel user) {
		ArrayList<SimpleImageModel> images = new ArrayList<SimpleImageModel>();

		buildSimpleUserFromJson(userJson, user);

		try {
			user.setCreationDate(userJson.getInt(Const.CREATION_DATE));
			user.setDescription(userJson.getString(Const.DESCRIPTION));
			user.setEmail(userJson.getString(Const.EMAIL));
			user.setFollowing(userJson.getBoolean(Const.IS_FOLLOWING));
			user.setFollowingAllowed(userJson
					.getBoolean(Const.FOLLOWING_ALLOWED));
			user.setGender(userJson.getString(Const.GENDER));
			user.setOwner(userJson.getBoolean(Const.IS_OWNER));

			ImagesJsonParser.buildSimpleImageArrayFromJson(
					userJson.getJSONArray(Const.LATEST_IMAGES), images);
			user.setLatestImages(images);

		} catch (JSONException e) {
			user = null;
		}
	}

	public static void buildSimpleUserFromJson(JSONObject userJson,
			SimpleUserModel user) {
		MiniImageModel avatar = new MiniImageModel();

		try {
			user.setUsername(userJson.getString(Const.USERNMAE));

			ImagesJsonParser.buildMiniImageFromJson(
					new JSONObject(userJson.getString(Const.AVATAR)), avatar);

			if (avatar.getServer() == 0) {
				avatar = null;
			}
			
			user.setAvatar(avatar);

		} catch (JSONException e) {
			user = null;
		}
	}

	public static void setError(UserModel model, JSONObject json,
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

	public static void setError(UserSettingsModel model, JSONObject json,
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
