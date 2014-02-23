package com.imageshack.utility;

import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.model.SimpleModel;

public class SimpleResponseJsonParser {
	
	public static SimpleModel parse(String jsonString) {
		SimpleModel model = new SimpleModel();
		JSONObject simpleJson;
		
		try {
			simpleJson = new JSONObject(jsonString);
			
			model.setProcessTime(simpleJson.getInt(Const.PROCESS_TIME));
			
			if (!simpleJson.getBoolean(Const.SUCCESS)) {
				setError(model, simpleJson, false);
				return model;
			}
			
			
		} catch (JSONException e) {
			setError(model, null, true);
		}
		
		return model;
	}
	
	public static void setError(SimpleModel model, JSONObject json, boolean isException) {
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
