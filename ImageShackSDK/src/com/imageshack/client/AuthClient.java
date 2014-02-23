package com.imageshack.client;

import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.listener.ResponseListener;
import com.imageshack.model.AuthModel;
import com.imageshack.model.SimpleModel;
import com.imageshack.utility.AuthJsonParser;
import com.imageshack.utility.SimpleResponseJsonParser;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class AuthClient extends ImageShackAbstractClient {

	private static final String API_ENDPOINT = ROUTE + Const.USER;

	public AuthClient(String apiKey) {
		super(apiKey);
	}

	/**
	 * Authenticate with ImageShack
	 * 
	 * @param username
	 *            the username or email
	 * @param password
	 *            the password
	 * @param listener
	 */
	public void login(String username, String password,
			ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.USERNMAE, username);
		params.put(Const.PASSWORD, password);

		String url = String.format("%s/%s", API_ENDPOINT, Const.LOGIN);
		responseListener = listener;
		client.post(url, params, new AuthResponseHandler());
	}

	/**
	 * Create a new ImageShack user.
	 * 
	 * @param email
	 *            the email, required
	 * @param username
	 *            the username, required
	 * @param password
	 *            the passowrd, required
	 * @param listener
	 *            the response listener that is notified when async http request
	 *            is completed
	 */
	public void register(String email, String username, String password,
			ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);

		if (email == null || username == null || password == null) {
			returnInvalidCall(listener, true);
		} else {
			params.put(Const.EMAIL, email);
			params.put(Const.PASSWORD, password);
			params.put(Const.USERNMAE, username);

			responseListener = listener;
			client.post(API_ENDPOINT, params, new SimpleResponseHandler());
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

	private class AuthResponseHandler extends AsyncHttpResponseHandler {

		public void onSuccess(String result) {
			AuthModel auth = AuthJsonParser.login(result);
			responseListener.onResponse(auth);
		}

		public void onFailure(Throwable arg0, String errorMsg) {
			AuthModel auth = new AuthModel();

			try {
				AuthJsonParser.setError(auth, new JSONObject(errorMsg), false);
			} catch (JSONException e) {
				auth.setSuccess(false);
				auth.setError(Const.AUTH_ERROR);
			} catch (NullPointerException e) {
				auth.setSuccess(false);
				auth.setError(Const.CHECK_NETWORK);
			}

			responseListener.onResponse(auth);
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
			AuthModel auth = new AuthModel();
			auth.setSuccess(false);
			auth.setProcessTime(0);
			auth.setError(Const.MISSING_PARAMS);
			listener.onResponse(auth);
		}
	}
	
}
