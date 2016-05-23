package com.volley.encap;

import java.io.UnsupportedEncodingException;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

public class StringRequestGet extends Request<String> {

	private ResponseListener<String> mlistener;

	public StringRequestGet(String url, ResponseListener<String> mlistener) {
		super(Method.GET, url, mlistener);
		// TODO Auto-generated constructor stub
		this.mlistener = mlistener;
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		// TODO Auto-generated method stub
		String parsed;
		try {
			parsed = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
		} catch (UnsupportedEncodingException e) {
			parsed = new String(response.data);
		}
		return Response.success(parsed,
				HttpHeaderParser.parseCacheHeaders(response));
	}

	@Override
	protected void deliverResponse(String response) {
		// TODO Auto-generated method stub
		mlistener.onResponse(response);
	}
}
