package com.scs.commons;

import java.io.IOException;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class APIManager {
	
	public static Response performUploadRequest(String url,String method,String paylord) throws IOException
	{
		OkHttpClient client=new OkHttpClient();
		MediaType mediaType=MediaType.parse("application/json");
		RequestBody body=RequestBody.create(mediaType,paylord);
		Request request=new Request.Builder()
				.url(url)
				.method(method, body)
				.addHeader("content-type","application/json")
				.addHeader("Accept", "application/json")
				.build();
		return client.newCall(request).execute();
		
	}
	
	public static int getCodeAndClose(Response response)
	{
		int code=response.code();
		try{
			response.body().close();
		}catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return code;
	}

}
