package com.volley.encap;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

public class StringRequestPost extends Request<String> {

	private ResponseListener<String> mListener;
	private Map<String, String> params;

	public StringRequestPost(String url, ResponseListener<String> mListener,
			Map<String, String> params) {
		super(Method.POST, url, mListener);
		this.mListener = mListener;
		this.params = params;
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
		mListener.onResponse(response);
	}
	
	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		// TODO Auto-generated method stub
		return params;
	}

}
