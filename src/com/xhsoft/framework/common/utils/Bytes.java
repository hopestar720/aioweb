/*
 * $RCSfile: Bytes,v $$
 * $Revision: 1.0  $
 * $Date: 2011  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.utils;

import java.security.MessageDigest;

/**
 * <p>Title:Bytes</p>
 * <p>Description:Bytes</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @since 2011
 */
public class Bytes 
{
	/**
     * 由于String.subString对汉字处理存在问题（把一个汉字视为一个字节)，因此在   
     * 包含汉字的字符串时存在隐患，现调整如下：   
     * @param src 要截取的字符串   
     * @param start_idx 开始坐标（包括该坐标)   
     * @param end_idx   截止坐标（包括该坐标）   
     * @return   
     */   
    public static String substring(String src, int start_idx, int end_idx)
    {    
        byte[] b = src.getBytes();    
        String tgt = "";    
        for(int i=start_idx; i<=end_idx; i++){    
            tgt +=(char)b[i];    
        }    
        return tgt;    
    }    
    
    /**
	 * MD5 加密
	 * @param s
	 * @return
	 */
	public final static String MD5(String s) 
	{
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
}
