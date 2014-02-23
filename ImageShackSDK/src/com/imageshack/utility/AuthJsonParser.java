package com.imageshack.utility;

import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.model.AuthModel;
import com.imageshack.model.SimpleImageModel;

public class AuthJsonParser {

	public static AuthModel login(String jsonString) {
		AuthModel auth = new AuthModel();
		SimpleImageModel avatar = new SimpleImageModel();
		JSONObject authJson, json;

		try {
			authJson = new JSONObject(jsonString);
			
			auth.setProcessTime(authJson.getInt(Const.PROCESS_TIME));

			if (!authJson.getBoolean(Const.SUCCESS)) {
				setError(auth, authJson, false);
				return auth;
			}
			
			json = new JSONObject(authJson.getString(Const.RESULT));
			auth.setAuthToken(json.getString(Const.AUTH_TOKEN));
			auth.setUserId(json.getLong(Const.USERID));
			auth.setEmail(json.getString(Const.EMAIL));
			auth.setUsername(json.getString(Const.USERNMAE));
			auth.setMembership(json.getString(Const.MEMBERSHIP));
			auth.setMembershipItemNumber(json
					.getString(Const.MEMBERSHIP_ITEM_NUM));

			ImagesJsonParser.buildMiniImageFromJson(new JSONObject(
					json.getString(Const.AVATAR)), avatar);
			
			if (avatar.getId() == null || avatar.getId().equals("")) {
				avatar = null;
			}
			
			auth.setAvatar(avatar);

		} catch (JSONException e) {
			setError(auth, null, true);
		} catch (NullPointerException e) {
			auth.setError(Const.CHECK_NETWORK);
		}

		return auth;
	}
	
	public static void setError(AuthModel model, JSONObject json, boolean isException) {
		model.setSuccess(false);
		
		if (isException) {
			model.setError(Const.JSON_PARSING_EXCEPTION);
		} else {
			try {
				model.setError(json.getJSONObject(Const.ERROR).getString(Const.ERROR_MESSAGE));
			} catch (JSONException e) {
				model.setError(Const.JSON_PARSING_EXCEPTION);
			}
		}
	}

}
