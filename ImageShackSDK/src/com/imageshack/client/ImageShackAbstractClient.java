package com.imageshack.client;

import com.imageshack.constant.Const;
import com.imageshack.listener.ResponseListener;
import com.loopj.android.http.AsyncHttpClient;

public class ImageShackAbstractClient {
	
	protected AsyncHttpClient client;
	protected ResponseListener responseListener;
	protected String apiKey;
	protected static final String ROUTE = Const.PROTOCOL + Const.API;
	
	public ImageShackAbstractClient(String apiKey) {
		client = new AsyncHttpClient();
		client.addHeader("Host", "imageshack.com");
		this.apiKey = apiKey;
	}
	
}
