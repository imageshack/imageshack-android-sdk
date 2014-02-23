package com.imageshack.client;

import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.listener.ResponseListener;
import com.imageshack.model.CommentsModel;
import com.imageshack.model.SimpleModel;
import com.imageshack.utility.CommentsJsonParser;
import com.imageshack.utility.SimpleResponseJsonParser;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class CommentsClient extends ImageShackAbstractClient {

	private static final String API_ENDPOINT = ROUTE + Const.COMMENTS;
	private String authToken;

	public CommentsClient(String apiKey, String authToken) {
		super(apiKey);
		this.authToken = authToken;
	}

	/**
	 * Get list of comments for a given image
	 * 
	 * @param imageId
	 *            the image id
	 * @param limit
	 *            max number of comments per call, default is 10
	 * @param offset
	 *            offset for pagination
	 * @param listener
	 */
	public void get(String imageId, Integer limit, String offset,
			ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (limit != null) {
			params.put(Const.LIMIT, limit);
		}

		if (offset != null) {
			params.put(Const.OFFSET, offset);
		}

		if (imageId != null) {
			String url = String.format("%s/%s", API_ENDPOINT, imageId);
			responseListener = listener;
			client.get(url, params, new CommentsResponseHandler());
		} else {
			returnInvalidCall(listener, false);
		}
	}

	/**
	 * Add a comment to an image
	 * 
	 * @param imageId
	 *            the image id, required
	 * @param comment
	 *            the comment, required
	 * @param source
	 *            the source, can be the app name, optional
	 * @param listener
	 */
	public void add(String imageId, String comment, String source,
			ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);
		params.put(Const.COMMENT, comment);

		if (source != null) {
			params.put(Const.SOURCE, source);
		}

		if (imageId != null) {
			String url = String.format("%s/%s", API_ENDPOINT, imageId);
			responseListener = listener;
			client.post(url, params, new SimpleResponseHandler());
		} else {
			returnInvalidCall(listener, true);
		}
	}

	/**
	 * Delete comment
	 * 
	 * @param imageId
	 *            the image id
	 * @param commentId
	 *            the comment id
	 * @param listener
	 */
	public void delete(String commentId, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (commentId != null) {
			String url = String.format("%s/%s", API_ENDPOINT, commentId);
			responseListener = listener;
			client.delete(null, url, null, params, new SimpleResponseHandler());
		} else {
			returnInvalidCall(listener, true);
		}
	}

	private class CommentsResponseHandler extends AsyncHttpResponseHandler {

		public void onSuccess(String result) {
			CommentsModel comments = CommentsJsonParser.getComments(result);
			responseListener.onResponse(comments);
		}

		public void onFailure(Throwable arg0, String errorMsg) {
			CommentsModel comments = new CommentsModel();

			try {
				CommentsJsonParser.setError(comments, new JSONObject(errorMsg),
						false);
			} catch (JSONException e) {
				comments.setSuccess(false);
				comments.setError(Const.COMMENTS_ERROR);
			}

			responseListener.onResponse(comments);
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
			CommentsModel comments = new CommentsModel();
			comments.setSuccess(false);
			comments.setProcessTime(0);
			comments.setError(Const.MISSING_PARAMS);
			listener.onResponse(comments);
		}
	}

}
