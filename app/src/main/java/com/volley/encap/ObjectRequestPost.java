package com.volley.encap;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

public class ObjectRequestPost<T> extends Request<T> {

	private ResponseListener<T> mlistener;
	/* �������� json �õ� */
	private Gson mGson;
	/* ���� gson ���� json ���ݵ�ʱ����Ҫ�õ�������� */
	private Type mClazz;
	private Map<String, String> mParams;// ����

	public ObjectRequestPost(String url, Type type,
			Map<String, String> mParams, ResponseListener<T> listener) {
		super(Method.POST, url, listener);
		this.mGson = new Gson();
		this.mlistener = listener;
		this.mParams = mParams;
		this.mClazz = type;
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			T result;
			String jsonString = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			result = mGson.fromJson(jsonString, mClazz);
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

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		// TODO Auto-generated method stub
		return mParams;
	}

	/*@Override
	public String getBodyContentType() {
		// TODO Auto-generated method stub
		// return
		// super.getBodyContentType();//������applicaton/json,��ʾ���������json��,���Ƿ�������֧��
		return String.format("application/x-www-form-urlencoded; charset=%s",
				"utf-8");
	}*/

}
