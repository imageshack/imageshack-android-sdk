package com.imageshack.client;

import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.listener.ResponseListener;
import com.imageshack.model.ActivitiesCountModel;
import com.imageshack.model.ActivitiesModel;
import com.imageshack.model.SimpleModel;
import com.imageshack.utility.ActivitiesJsonParser;
import com.imageshack.utility.SimpleResponseJsonParser;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ActivitiesClient extends ImageShackAbstractClient {

	private static final String API_ENDPOINT = ROUTE + Const.ACTIVITIES;
	private String authToken;

	public ActivitiesClient(String apiKey, String authToken) {
		super(apiKey);
		this.authToken = authToken;
	}

	/**
	 * Fetch user activities.
	 * 
	 * @param limit
	 *            the limit
	 * @param offset
	 *            the offset
	 * @param listener
	 *            Async http response listener
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
		client.get(API_ENDPOINT, params, new ActivitiesResponseHandler());
	}

	/**
	 * Fetch the number of new notifications
	 * 
	 * @param listener
	 *            Async http response listener
	 */
	public void getNewCount(ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		String url = String.format("%s/%s", API_ENDPOINT,
				Const.ACTIVITIES_NEW_COUNT);
		responseListener = listener;
		client.get(url, params, new ActivitiesCountResponseHandler());
	}

	/**
	 * Mark notifications as viewed
	 * 
	 * @param listener
	 *            Async http response listener
	 */
	public void markAsViewed(ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		String url = String.format("%s/%s", API_ENDPOINT,
				Const.ACTIVITIES_MARK_AS_VIEWED);
		responseListener = listener;
		client.post(url, params, new ActivitiesViewedResponseHandler());
	}

	private class ActivitiesResponseHandler extends AsyncHttpResponseHandler {

		public void onSuccess(String result) {
			ActivitiesModel activities = ActivitiesJsonParser
					.getActivities(result);
			responseListener.onResponse(activities);
		}

		public void onFailure(Throwable arg0, String errorMsg) {
			ActivitiesModel activities = new ActivitiesModel();

			try {
				ActivitiesJsonParser.setError(activities, new JSONObject(
						errorMsg), false);
			} catch (JSONException e) {
				activities.setSuccess(false);
				activities.setError(Const.GENERIC_ERROR);
			}

			responseListener.onResponse(activities);
		}

	}

	private class ActivitiesCountResponseHandler extends
			AsyncHttpResponseHandler {

		public void onSuccess(String result) {
			ActivitiesCountModel count = ActivitiesJsonParser
					.getNewCount(result);
			responseListener.onResponse(count);
		}

		public void onFailure(Throwable arg0, String errorMsg) {
			ActivitiesCountModel count = new ActivitiesCountModel();

			try {
				ActivitiesJsonParser.setError(count, new JSONObject(errorMsg),
						false);
			} catch (JSONException e) {
				count.setSuccess(false);
				count.setError(Const.GENERIC_ERROR);
			}

			responseListener.onResponse(count);
		}

	}

	private class ActivitiesViewedResponseHandler extends
			AsyncHttpResponseHandler {

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

}
