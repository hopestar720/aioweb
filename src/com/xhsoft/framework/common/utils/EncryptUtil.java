package com.xhsoft.framework.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
	
	public static final String ENCRY_MD5 = "md5";
	public static final String ENCRY_SHA = "sha-1";
	
	public static String md5(String input){
		return encrypt(input,ENCRY_MD5);
	}
	
	public static String sha1(String input){
		return encrypt(input,ENCRY_SHA);
	}
	
	public static String encrypt(String input,String ...algorithms){
		String algorithm = ENCRY_MD5;
		if(input==null||"".equals(input.trim())){
			throw new IllegalArgumentException("Please input the encrypted content !");
		}
		if(algorithms.length>0){
			algorithm = algorithms[0];
		}
		byte[] bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(input.getBytes("UTF8"));
			bytes = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return byte2hex(bytes);
	}
	
	private static String byte2hex(byte[] bytes){
		StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < bytes.length; ++i) {  
            sb.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1,3));  
        }  
        return sb.toString(); 
	}
	
	private static String byto2hex2(byte[] bin){
	    StringBuffer buf = new StringBuffer();
	    for (int i = 0; i < bin.length; ++i) {
	        int x = bin[i] & 0xFF, h = x >>> 4, l = x & 0x0F;
	        buf.append((char) (h + ((h < 10) ? '0' : 'a' - 10)));
	        buf.append((char) (l + ((l < 10) ? '0' : 'a' - 10)));
	    }
	    return buf.toString();
	} 

}
