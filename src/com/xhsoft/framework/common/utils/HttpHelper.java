/*
 * $RCSfile: HttpUtils,v $$
 * $Revision: 1.0  $
 * $Date: 2011  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>Title:HttpUtils</p>
 * <p>Description:HttpUtils</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @since 2011
 */
public class HttpHelper 
{
	public static final String Response_Code = "Response-Code";
	public static final String Response_Message = "Response-Message";
	public static final String ENCODING = "Encoding";
	
	/**
     * 获得指定URL的数据
     * @param url
     * @param headers 包含HTTP请求的头，所以Map中必须是<String,String>
     * @param connectTimeout 设置连接超时时间，单位是ms
     * @param readTimeout 设置读超时时间，单位是ms
     * @return 内容，同时在headers中，包含HTTP响应的头，
     * 			其中：
     * 				<code>Response-Code</code>对应 Response Code
     * 				<code>Response-Message</code>对应 Response Message
     * 				<code>Encoding</code>对应响应内容体的Encoding
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
	public static byte[] webGet(URL url, java.util.Map headers,int connectTimeout,int readTimeout) throws IOException 
    {
        if(headers == null)
            	headers = new java.util.HashMap();
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();

            String jdkV = System.getProperty("java.vm.version");
            if(jdkV!=null && jdkV.indexOf("1.5")!=-1){
              Class clazz = conn.getClass();
              try{
                java.lang.reflect.Method method = clazz.getMethod("setConnectTimeout",new Class[]{int.class});
                method.invoke(conn,new Object[]{new Integer(connectTimeout)});
                method = clazz.getMethod("setReadTimeout",new Class[]{int.class});
                method.invoke(conn,new Object[]{new Integer(readTimeout)});
              }catch(Exception ec){
                ec.printStackTrace();
              }
            }else{
              System.setProperty("sun.net.client.defaultConnectTimeout","" + connectTimeout);
              System.setProperty("sun.net.client.defaultReadTimeout", "" + readTimeout);
            }
            
            Iterator it = headers.entrySet().iterator();
            while (it.hasNext()) 
            {
                Map.Entry me = (Map.Entry) it.next();
                String key = (String) me.getKey();
                String value = (String) me.getValue();
                if (key != null && value != null) {
                    conn.setRequestProperty(key, value);
                }
            }

            String contentType = conn.getContentType();
            String encoding = null;
            if (contentType != null &&
                contentType.toLowerCase().indexOf("charset") > 0) {
                int k = contentType.toLowerCase().indexOf("charset");
                if (contentType.length() > k + 7) {
                    String sss = contentType.substring(k + 7).trim();
                    k = sss.indexOf("=");
                    if (k >= 0 && sss.length() > k + 1) {
                        encoding = sss.substring(k + 1).trim();
                        if (encoding.indexOf(";") > 0) {
                            encoding = encoding.substring(0,
                                encoding.indexOf(";")).trim();
                        }
                    }

                }
            }
            headers.clear();

            int k = 0;
            String feildValue = null;
            while ( (feildValue = conn.getHeaderField(k)) != null) 
            {
                String key = conn.getHeaderFieldKey(k);
                k++;
                if (key != null) {
                    headers.put(key, feildValue);
                }
            }
            headers.put("Response-Code", new Integer(conn.getResponseCode()));
            headers.put("Response-Message", conn.getResponseMessage());

            java.io.InputStream bis = conn.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte bytes[] = new byte[1024];
            int n = 0;
            while ( (n = bis.read(bytes)) > 0) 
            {
                out.write(bytes, 0, n);
            }
            bis.close();
            
            bytes = out.toByteArray();
            if (encoding == null) {
                try {
                    for (int i = 0; i < 64 && i < bytes.length - 2; i++) {
                        if (bytes[i] == '?' && bytes[i + 1] == '>') {
                            String s = new String(bytes, 0, i);
                            if (s.indexOf("encoding") > 0) {
                                s = s.substring(s.indexOf("encoding") + 8);
                                if (s.indexOf("=") >= 0) {
                                    s = s.substring(s.indexOf("=") + 1).trim();
                                    if (s.charAt(0) == '"') {
                                        s = s.substring(1);
                                    }
                                    if (s.indexOf("\"") > 0) {
                                        encoding = s.substring(0,
                                            s.indexOf("\""));

                                    }
                                }
                            }
                        }
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (encoding == null) {
                encoding = "UTF-8";
            }
            headers.put("Encoding", encoding);

            return bytes;

        }catch (IOException e) {
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.disconnect();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


	/**
     * 向指定的URL的发送数据，并获得返回值
     * @param url
     * @param headers 包含HTTP请求的头，所以Map中必须是<String,String>
     * @param content 内容体
     * @param connectTimeout 设置连接超时时间，单位是ms
     * @param readTimeout 设置读超时时间，单位是ms

     * @return 内容，同时在headers中，包含HTTP响应的头，
     * 			其中：
     * 				<code>Response-Code</code>对应 Response Code
     * 				<code>Response-Message</code>对应 Response Message
     * 				<code>Encoding</code>对应响应内容体的Encoding
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
	public static byte[] webPost(URL url, byte[] content,java.util.Map headers,int connectTimeout,int readTimeout) throws IOException 
    {
        HttpURLConnection urlconnection = null;
        try {
            urlconnection = (HttpURLConnection)url.openConnection();

            //set connectTimeout and readTimeout
            String jdkV = System.getProperty("java.vm.version");
            if(jdkV!=null && jdkV.indexOf("1.5")!=-1){
              Class clazz = urlconnection.getClass();
              try{
                java.lang.reflect.Method method = clazz.getMethod("setConnectTimeout",new Class[]{int.class});
                method.invoke(urlconnection,new Object[]{new Integer(connectTimeout)});
                method = clazz.getMethod("setReadTimeout",new Class[]{int.class});
                method.invoke(urlconnection,new Object[]{new Integer(readTimeout)});
              }catch(Exception ec){
                ec.printStackTrace();
              }
            }else{
              System.setProperty("sun.net.client.defaultConnectTimeout","" + connectTimeout);
              System.setProperty("sun.net.client.defaultReadTimeout", "" + readTimeout);
            }
            
            Iterator it = headers.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry me = (Map.Entry) it.next();
                String key = (String) me.getKey();
                String value = (String) me.getValue();
                if (key != null && value != null) {
                	urlconnection.setRequestProperty(key, value);
                }
            }
            urlconnection.setRequestProperty("Request-method", "post");
            urlconnection.setRequestProperty("Content-length",
                                             Integer.toString(content.length));

            urlconnection.setDoInput(true);
            urlconnection.setDoOutput(true);
	
            
            OutputStream outputstream = urlconnection.getOutputStream();
            outputstream.write(content);
            outputstream.flush();


            String contentType = urlconnection.getContentType();
            String encoding = null;
            if (contentType != null &&
                contentType.toLowerCase().indexOf("charset") > 0) {
                int k = contentType.toLowerCase().indexOf("charset");
                if (contentType.length() > k + 7) {
                    String sss = contentType.substring(k + 7).trim();
                    k = sss.indexOf("=");
                    if (k >= 0 && sss.length() > k + 1) {
                        encoding = sss.substring(k + 1).trim();
                        if (encoding.indexOf(";") > 0) {
                            encoding = encoding.substring(0,
                                encoding.indexOf(";")).trim();
                        }
                    }

                }
            }
            headers.clear();

            int k = 0;
            String feildValue = null;
            while ( (feildValue = urlconnection.getHeaderField(k)) != null) 
            {
                String key = urlconnection.getHeaderFieldKey(k);
                k++;
                if (key != null) {
                    headers.put(key, feildValue);
                }
            }
            headers.put("Response-Code", new Integer(urlconnection.getResponseCode()));
            headers.put("Response-Message", urlconnection.getResponseMessage());

            java.io.InputStream bis = urlconnection.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte bytes[] = new byte[1024];
            int n = 0;
            
            @SuppressWarnings("unused")
			int count = 0;
            while( (n = bis.read(bytes)) > 0)
            {
                out.write(bytes, 0, n);
            }
            bis.close();
            bytes = out.toByteArray();
            out.close();
            outputstream.close();

            if (encoding == null) {
                encoding = "UTF-8";
            }
            headers.put("Encoding", encoding);
            
            return bytes;
        }catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if (urlconnection != null){
            	urlconnection.disconnect();
            }
        }
    }
}
