package com.volley.encap;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

public class ObjectRequestGet<T> extends Request<T> {

	private ResponseListener<T> mlistener;
	/* �������� json �õ� */
	private Gson mGson;
	/* ���� gson ���� json ���ݵ�ʱ����Ҫ�õ�������� */
	private Type mClazz;

	public ObjectRequestGet(String url, Type type, ResponseListener listener) {
		super(Method.GET, url, listener);
		this.mlistener = listener;
		this.mClazz = type;
		mGson = new Gson();
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		 try {
	            T result ;
	            String jsonString =
	                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
	            result = mGson.fromJson(jsonString,mClazz) ;
	            return Response.success(result,
	                    HttpHeaderParser.parseCacheHeaders(response));
	        } catch (UnsupportedEncodingException e) {
	            return Response.error(new ParseError(e));
	        }
	}

	@Override
	protected void deliverResponse(T response) {
		mlistener.onResponse(response);
	}

}
