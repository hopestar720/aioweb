package com.xhsoft.framework.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class HttpUtil {

	private HttpURLConnection httpConn = null;
	private int connTimeout = 10000;
	private int readTimeout = 10000;
	private String params;

	private void init() {
		Properties property = new Properties();
		InputStream inStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("http.properties");
		try {
			if (inStream != null) {
				property.load(inStream);
				if (property.get("http.connect_timeout") != null){
					connTimeout = Integer.parseInt(property.get("http.connect_timeout").toString());
				}
				if (property.get("http.read_timeout") != null){
					readTimeout = Integer.parseInt(property.get("http.read_timeout").toString());
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HttpUtil(String strUrl) {
		try {
			init();
			URL url = new URL(strUrl);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setReadTimeout(readTimeout);
			httpConn.setConnectTimeout(connTimeout);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * @param mParams
	 * @return
	 */
	public int post(Map<String,String> mParams) {
		int code = 200;
		try {
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			/** POST方法不能使用缓存 */
			httpConn.setUseCaches(false);
			initPostParams(mParams);
			byte[] bypes = params.getBytes();
			httpConn.getOutputStream().write(bypes);
			httpConn.getOutputStream().flush();
			httpConn.getOutputStream().close();
			code = httpConn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			
		}
		return code;
	}
	public int post() {
		int code = 200;
		try {
			httpConn.setRequestMethod("POST");
			code = httpConn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return code;
	}

	public int download() {
		int code = 200;
		return code;
	}
	
	public int upload(){
		int code = 200;
		try {
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setUseCaches(false);
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("connection", "Keep-Alive");
			httpConn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			httpConn.setRequestProperty("Charsert", "UTF-8");
			httpConn.setRequestProperty("Content-Type", "multipart/form-data; ");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		return code;
	}

	public int get() {
		int code = 200;
		try {
			httpConn.setRequestMethod("GET");
			code = httpConn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return code;
	}

	private String initPostParams(Map<String, String> mParams) {
		StringBuilder sbParams = new StringBuilder();
		for (Entry<String, String> entry : mParams.entrySet()) {
			sbParams.append(entry.getKey() + "=" + entry.getValue() + "&");
		}
		return sbParams.deleteCharAt(sbParams.length() - 1).toString();
	}
	
	public void destory(){
		if(httpConn!=null){
			httpConn.disconnect();
		}
	}

}
