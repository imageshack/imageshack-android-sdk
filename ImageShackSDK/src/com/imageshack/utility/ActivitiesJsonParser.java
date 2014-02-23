package com.imageshack.utility;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.model.ActivitiesCountModel;
import com.imageshack.model.ActivitiesModel;
import com.imageshack.model.ActivityModel;
import com.imageshack.model.ImageShackModel;
import com.imageshack.model.MiniImageModel;
import com.imageshack.model.SimpleUserModel;

public class ActivitiesJsonParser {

	public static final int ACTION_TYPE_LIKE = 1;
	public static final int ACTION_TYPE_COMMENT = 2;
	public static final int ACTION_TYPE_MESSAGE = 3;
	public static final int ACTION_TYPE_FOLLOW = 4;
	public static final int ACTION_TYPE_MENTION = 5;

	public static final int OBJECT_TYPE_IMAGE = 1;
	public static final int OBJECT_TYPE_ALBUM = 2;
	public static final int OBJECT_TYPE_MESSAGE = 3;
	public static final int OBJECT_TYPE_USER = 4;

	public static ActivitiesModel getActivities(String jsonString) {
		ActivitiesModel activities = new ActivitiesModel();
		ArrayList<ActivityModel> activityList = new ArrayList<ActivityModel>();
		JSONObject activitiesJson, json;

		try {
			activitiesJson = new JSONObject(jsonString);

			activities
					.setProcessTime(activitiesJson.getInt(Const.PROCESS_TIME));

			if (!activitiesJson.getBoolean(Const.SUCCESS)) {
				setError(activities, activitiesJson, false);
				return activities;
			}

			json = new JSONObject(activitiesJson.getString(Const.RESULT));
			activities.setLimit(json.getInt(Const.LIMIT));
			activities.setOffset(json.getInt(Const.OFFSET));
			activities.setTotal(json.getInt(Const.TOTAL));

			buildActivitiesListFromJson(json.getJSONArray(Const.ACTIVITIES),
					activityList);
			activities.setActivities(activityList);

		} catch (JSONException e) {
			setError(activities, null, true);
		}

		return activities;
	}

	public static ActivitiesCountModel getNewCount(String jsonString) {
		ActivitiesCountModel count = new ActivitiesCountModel();
		JSONObject countJson;

		try {
			countJson = new JSONObject(jsonString);

			count.setProcessTime(countJson.getInt(Const.PROCESS_TIME));

			if (!countJson.getBoolean(Const.SUCCESS)) {
				setError(count, countJson, false);
				return count;
			}

			count.setCount(countJson.getInt(Const.RESULT));

		} catch (JSONException e) {
			setError(count, null, true);
		}

		return count;
	}

	public static void buildActivitiesListFromJson(
			JSONArray activitiesJsonArray, ArrayList<ActivityModel> activities) {
		try {
			for (int i = 0; i < activitiesJsonArray.length(); i++) {
				ActivityModel activity = new ActivityModel();
				buildActivityFromJson(activitiesJsonArray.getJSONObject(i),
						activity);
				if (activity != null) {
					activities.add(activity);
				}
			}
		} catch (JSONException e) {
			// skip bad json, should never happen
		}

	}

	public static void buildActivityFromJson(JSONObject json,
			ActivityModel activity) {
		SimpleUserModel actor = new SimpleUserModel();
		JSONObject obj;

		try {
			activity.setDate(json.getInt(Const.DATE));
			activity.setText(json.getString(Const.TEXT));
			activity.setActionType(json.getInt(Const.ACTION_TYPE));
			activity.setObjectType(json.getInt(Const.OBJECT_TYPE));

			UserJsonParser.buildSimpleUserFromJson(
					new JSONObject(json.getString(Const.ACTOR)), actor);
			
			activity.setActor(actor);

			obj = new JSONObject(json.getString(Const.OBJECT));
			activity.setObject(buildObjectFromJson(obj, actor,
					activity.getObjectType()));

		} catch (JSONException e) {
			activity = null;
		}
	}

	public static ImageShackModel buildObjectFromJson(JSONObject json,
			SimpleUserModel actor, int objectType) {
		ImageShackModel object = null;

		switch (objectType) {
		case OBJECT_TYPE_IMAGE:
			object = new MiniImageModel();
			ImagesJsonParser.buildMiniImageFromJson(json,
					(MiniImageModel) object);
			break;

		case OBJECT_TYPE_ALBUM:
			// TODO
			object = null;
			break;

		case OBJECT_TYPE_USER:
			object = actor;
			break;

		default:
			object = null;
			break;
		}

		return object;
	}

	public static void setError(ActivitiesModel model, JSONObject json,
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

	public static void setError(ActivitiesCountModel model, JSONObject json,
			boolean isException) {
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
