/*
 * $RCSfile: StringUtil,v $$
 * $Revision: 1.0  $
 * $Date: 2011  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <p>Title:StringUtil</p>
 * <p>Description:StringUtil</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @since 2011
 */
public final class StringUtil 
{
	 public static final String Empty = "";
	    
	 private StringUtil(){}
	    
	    /**
	     * <p>Description:对两个字符串List进行排列组合</p>
	     * @param list1
	     * @param list2
	     * @param token
	     * @return List<String>
	     * @author zhangrf
	     * @version 1.0
	     */
	    public static List<String> permutation(List<String> list1 , List<String> list2, String token)
	    {
	    	List<String> result = new ArrayList<String>();
	    	
	    	for(String s1 : list1)
	    	{
	    		for(String s2 : list2)
	    		{
	    		    StringBuilder sb = new StringBuilder();
	    			
	    			sb.append(s1);
	    		
	    			if(!isEmpty(token)){
	    				sb.append(token);
	    			}
	    			
	    			sb.append(s2);
	    			
	    			result.add(sb.toString());
	    		}
	    	}
	    	
	    	return result;
	    }
	    
	    /**
	     * @param args
	     * @return boolean
	     */
	    public static boolean isEmpty(String ... args)
	    {
	        if(args != null && args.length > 0){
	            for(String s : args)
	            {
	                if(s == null || s.trim().length() == 0 ){
	                    return true;
	                }
	            }
	            
	            return false;
	        }
	        
	        return true;
	    }
	    
	    /**
	     * @param s
	     * @param array
	     * @return boolean
	     */
	    public static boolean in(String s, String[] array)
	    {
	        if(s == null || array == null || array.length < 1){
	            return false;
	        }
	        
	        for(int i = 0; i < array.length; i++)
	        {
	            if(array[i] != null && array[i].equals(s)){
	                return true;
	            }
	        }
	        
	        return false;
	    }
	    
	    /**
	     * @param s
	     * @param array
	     * @return boolean
	     */
	    public static boolean notIn(String s, String[] array)
	    {
	        return !in(s, array);
	    }
	    
	    /**
	     * @param s
	     * @return String
	     * @author lizj
	     */
	    public static String trim(String s)
	    {
	        return (s != null ? s.trim() : Empty);
	    }
	    
	    /**
	     * @param array
	     * @param token
	     * @return
	     * @author lizj
	     */
	    public static String join(char[] array, String token)
	    {
	        StringBuilder buf = new StringBuilder();
	        
	        if(array != null && array.length > 0){
	            int count = array.length - 1;
	            
	            for(int i = 0; i < count; i++)
	            {
	                buf.append(array[i]).append(token);
	            }
	            
	            if(count < array.length){
	                buf.append(array[count]);
	            }
	        }
	        
	        return buf.toString();
	    }
	    
	    /**
	     * @param array
	     * @param token
	     * @return String
	     * @author lizj
	     */
	    public static String join(boolean[] array, String token)
	    {
	        StringBuilder buf = new StringBuilder();
	        
	        if(array != null && array.length > 0){
	            int count = array.length - 1;
	            
	            for(int i = 0; i < count; i++)
	            {
	                buf.append(array[i]).append(token);
	            }
	            
	            if(count < array.length){
	                buf.append(array[count]);
	            }
	        }
	        
	        return buf.toString();
	    }
	    
	    /**
	     * @param array
	     * @param token
	     * @return String
	     * @author lizj
	     */
	    public static String join(byte[] array, String token)
	    {
	        StringBuilder buf = new StringBuilder();
	        
	        if(array != null && array.length > 0){
	            int count = array.length - 1;
	            
	            for(int i = 0; i < count; i++)
	            {
	                buf.append(array[i]).append(token);
	            }
	            
	            if(count < array.length){
	                buf.append(array[count]);
	            }
	        }
	        
	        return buf.toString();
	    }
	    
	    /**
	     * @param array
	     * @param token
	     * @return String
	     * @author lizj
	     */
	    public static String join(short[] array, String token)
	    {
	        StringBuilder buf = new StringBuilder();
	        
	        if(array != null && array.length > 0){
	            int count = array.length - 1;
	            
	            for(int i = 0; i < count; i++)
	            {
	                buf.append(array[i]).append(token);
	            }
	            
	            if(count < array.length){
	                buf.append(array[count]);
	            }
	        }
	        
	        return buf.toString();
	    }
	    
	    /**
	     * @param array
	     * @param token
	     * @return String
	     * @author lizj
	     */
	    public static String join(int[] array, String token)
	    {
	        StringBuilder buf = new StringBuilder();
	        
	        if(array != null && array.length > 0){
	            int count = array.length - 1;
	            
	            for(int i = 0; i < count; i++)
	            {
	                buf.append(array[i]).append(token);
	            }
	            
	            if(count < array.length){
	                buf.append(array[count]);
	            }
	        }
	        
	        return buf.toString();
	    }
	    
	    /**
	     * @param array
	     * @param token
	     * @return String
	     * @author lizj
	     */
	    public static String join(float[] array, String token)
	    {
	        StringBuilder buf = new StringBuilder();
	        
	        if(array != null && array.length > 0){
	            int count = array.length - 1;
	            
	            for(int i = 0; i < count; i++)
	            {
	                buf.append(array[i]).append(token);
	            }
	            
	            if(count < array.length){
	                buf.append(array[count]);
	            }
	        }
	        
	        return buf.toString();
	    }
	    
	    /**
	     * @param array
	     * @param token
	     * @return String
	     * @author lizj
	     */
	    public static String join(double[] array, String token)
	    {
	        StringBuilder buf = new StringBuilder();
	        
	        if(array != null && array.length > 0){
	            int count = array.length - 1;
	            
	            for(int i = 0; i < count; i++)
	            {
	                buf.append(array[i]).append(token);
	            }
	            
	            if(count < array.length){
	                buf.append(array[count]);
	            }
	        }
	        
	        return buf.toString();
	    }
	    
	    /**
	     * @param array
	     * @param token
	     * @return String
	     * @author lizj
	     */
	    public static String join(long[] array, String token)
	    {
	        StringBuilder buf = new StringBuilder();
	        
	        if(array != null && array.length > 0){
	            int count = array.length - 1;
	            
	            for(int i = 0; i < count; i++)
	            {
	                buf.append(array[i]).append(token);
	            }
	            
	            if(count < array.length){
	                buf.append(array[count]);
	            }
	        }
	        
	        return buf.toString();
	    }
	    
	    /**
	     * @param contextPath
	     * @param url
	     * @param parameters
	     * @return String
	     * @author lizj
	     */
	    public static String getUrl(String contextPath, String url, String parameters)
	    {
	        StringBuilder buf = new StringBuilder();
	        
	        if(!StringUtil.isEmpty(url)){
	            String path = url.trim().replace('\\', '/');

	            if(!StringUtil.isEmpty(contextPath)){
	                if(!path.toLowerCase().startsWith("http://") && !path.toLowerCase().startsWith("https://")){
	                    buf.append(contextPath);
	    
	                    if(!path.startsWith("/")){
	                        buf.append("/");
	                    }
	                }
	            }

	            buf.append(path);

	            if(!StringUtil.isEmpty(parameters)){
	                if(path.indexOf("?") < 0 && path.indexOf("&") < 0){
	                    buf.append("?");
	                }else{
	                    buf.append("&");
	                }
	                
	                char c = parameters.trim().charAt(0);
	                
	                if(c == '?' || c == '&'){
	                    buf.append(parameters.substring(1));
	                }else{
	                    buf.append(parameters);
	                }
	            }
	        }else{
	            buf.append("#");
	        }

	        return buf.toString();
	    }
	    
	    /**
	     * @param array
	     * @param token
	     * @return String
	     * @author lizj
	     */
	    public static String join(String[] array, String token)
	    {
	        StringBuilder buf = new StringBuilder();
	        
	        if(array != null && array.length > 0){
	            int count = array.length - 1;
	            
	            for(int i = 0; i < count; i++)
	            {
	                buf.append(array[i]).append(token);
	            }
	            
	            if(count < array.length){
	                buf.append(array[count]);
	            }
	        }
	        
	        return buf.toString();
	    }
	    
	    /**
	     * 
	     * @param x
	     * @return - String
	     * @author: lizj
	     */
	    public static String ltrim(String x)
	    {
	        if(x == null){
	            return null;
	        }
	        
	        for(int i = 0; i < x.length(); i++)
	        {
	            if(x.charAt(i) != ' '){
	                return x.substring(i);
	            }
	        }
	        
	        return x;
	    }
	    
	    /**
	     * 
	     * @param x
	     * @return - String
	     * @author: lizj
	     */
	    public static String rtrim(String x)
	    {
	        if(x == null){
	            return null;
	        }
	        
	        for(int i = x.length() - 1; i > -1; i--)
	        {
	            if(x.charAt(i) != ' '){
	                return x.substring(0, i + 1);
	            }
	        }
	        
	        return x;
	    }

	    /**
	     * <p>Description:返回字符串x中分隔符s之前的子字符串
	     *                              例如: x = "11111$2222";
	     *                              getb4(x, "$")返回 "111111"
	     *                              方法名getb4是getBefore的简写
	     * </p>
	     * @param x
	     * @param s
	     * @return - String
	     * @author: lizj
	     */
	    public static String getB4(String x, String s)
	    {
	        if(x == null){
	            return null;
	        }
	        
	        int k = x.indexOf(s);
	        
	        if(k > -1){
	            return x.substring(0, k);
	        }
	        
	        return null;
	    }
	    
	    /**
	     * <p>Description:返回字符串x中分隔符s之前的子字符串<BR>
	     *                              例如: x = "11111$2222";<BR>
	     *                              geta5(x, "$")返回 "2222"<BR>
	     *                              方法名geta5是getAfter的简写<BR>
	     * </p>
	     * @param x
	     * @param s
	     * @return - String
	     * @author: lizj
	     */
	    public static String getA5(String x, String s)
	    {
	        if(x == null){
	            return null;
	        }
	        
	        int k = x.indexOf(s);
	        
	        if(k > -1 && (k + s.length()) <= x.length()){
	            return x.substring(k + s.length());
	        }
	        
	        return null;
	    }
	    
	    /**
	     * 
	     * @param x
	     * @param s
	     * @return
	     */
	    public static String getLastB4(String x, String s)
	    {
	        if(x == null){
	            return null;
	        }
	        
	        int k = x.lastIndexOf(s);
	        
	        if(k > -1){
	            return x.substring(0, k);
	        }
	        
	        return null;
	    }
	    
	    /**
	     * 
	     * @param x
	     * @param s
	     * @return
	     */
	    public static String getLastA5(String x, String s)
	    {
	        if(x == null){
	            return null;
	        }
	        
	        int k = x.lastIndexOf(s);
	        
	        if(k > -1 && (k + s.length()) <= x.length()){
	            return x.substring(k + s.length());
	        }
	        
	        return null;
	    }
	    
	    /**
	     * 
	     * @param src
	     * @param x
	     * @param y
	     * @return String
	     * @author: lizj
	     */
	    public static String replace(String src, String x, String y)
	    {
	        if(src == null || x == null || x.length() < 1){
	            return src;
	        }
	        
	        StringBuilder buf = new StringBuilder();
	        
	        int s = 0;
	        int e = 0;
	        
	        int d = x.length();
	        
	        do
	        {
	            /* 查找开始*/
	            e = src.indexOf(x, s);
	            
	            if(e == -1){           
	                buf.append(src.substring(s));
	                
	                break;
	            }else{
	                buf.append(src.substring(s, e)).append(y);
	                
	                s = e + d;
	            }
	        }
	        while(true);
	        
	        return buf.toString();
	    }
	   
	    
	    /**
	     * @param src
	     * @param token
	     * @return List<String>
	     * @author: lizj
	     */
	    public static List<String> list(String src, String token)
	    {
	        List<String> list = new ArrayList<String>();
	        
	        StringTokenizer st = new StringTokenizer(src, token);
	        
	        while(st.hasMoreTokens())
	        {
	            list.add(st.nextToken());
	        }
	        
	        return list;
	    }

	    /**
	     * 
	     * @param s
	     * @param len
	     * @param x
	     * @return - String
	     * @author: lizj
	     */
	    public static String padding(String s, int len, String x)
	    {
	        if(s == null || s.trim().length() < 1){
	            return s;
	        }
	        
	        int size = s.length();
	        
	        for(int i = 0; i< s.length(); i++)
	        {
	            if(s.charAt(i) >= 0x4E00){
	                size++;
	            }
	        }
	    
	        int length = Math.abs(len);
	        
	        StringBuilder buf = new StringBuilder();
	        
	        if(len > 0){
	            buf.append(s);
	        }
	        
	        while(size < length)
	        {
	            buf.append(x);
	            
	            size += x.length();
	        }
	        
	        if(len < 0){
	            buf.append(s);
	        }
	    
	        return buf.toString();
	    }

	    /**
	     * 
	     * @param s
	     * @param len
	     * @param x
	     * @return - String
	     * @author: lizj
	     */
	    public static String padLeft(String s, int len, String x)
	    {
	        return padding(s, (0 - len), x);
	    }

	    /**
	     * 
	     * @param s
	     * @param len
	     * @param x
	     * @return - String
	     * @author: lizj
	     */
	    public static String padRight(String s, int len, String x)
	    {
	        return padding(s, len, x);
	    }
	    
	    
	    /**
	     * @param s
	     * @param encoding
	     * @return String
	     */
	    public static String urlEncode(String s, String encoding)
	    {
	        if(s != null){
	            try{
	                return java.net.URLEncoder.encode(s, encoding);
	            }catch(UnsupportedEncodingException e){
	                e.printStackTrace();
	            }
	        }
	        
	        return Empty;
	    }
	    
	    /**
	     * @param s
	     * @param defaultValue
	     * @return String
	     */
	    public static String quote(String s)
	    {
	        return quote(s, Empty);
	    }
	    
	    /**
	     * @param s
	     * @param defaultValue
	     * @return String
	     */
	    public static String quote(String s, String defaultValue)
	    {
	        StringBuilder buf = new StringBuilder();
	        
	        buf.append("\"");
	        buf.append((s != null ? s : defaultValue));
	        buf.append("\"");
	        
	        return buf.toString();
	    }
	    
	    /**
	     * @param sql
	     * @return String
	     * @author lizj
	     */
	    public static String toSqlString(String sql)
	    {
	        if(sql == null || sql.trim().length() < 1){
	            return sql;
	        }
	        
	        StringReader sr   = new StringReader(sql.trim());
	        BufferedReader br = new BufferedReader(sr);
	        
	        StringBuilder buf = new StringBuilder();

	        try{
	            String str = br.readLine();
	            
	            while(str != null)
	            {
	                if(!str.trim().startsWith("--") && str.trim().length() > 0){
	                    buf.append(str + " \r\n");
	                }
	                
	                str = br.readLine();
	            }
	        }catch(IOException e){
	            e.printStackTrace();
	        }
	        
	        String result = buf.toString().trim();
	        
	        if(result.length() > 0 && result.charAt(result.length() - 1) == ';'){
	            result = result.substring(0, result.length() - 1);
	        }
	        
	        return result;
	    }
	    
	    /**
	     * @param x
	     * @return String
	     */
	    public static String toTxtString(String x)
	    {
	        return toTxtString(x, Empty);
	    }
	    
	    /**
	     * 
	     * @param x
	     * @return String
	     */
	    public static String toTxtString(String x, String defaultValue)
	    {
	        if(x == null){
	            return defaultValue;
	        }
	        
	        char c;
	        
	        StringBuilder buf = new StringBuilder();
	        
	        for(int i = 0, size = x.length(); i < size; i++)
	        {
	            c = x.charAt(i);
	            
	            switch (c)
	            {
	                case '\\':
	                {
	                    buf.append("\\\\"); break;
	                }
	                case '\'':
	                {
	                    buf.append("\\\'"); break;
	                }
	                case '"':
	                {
	                    buf.append("\\\""); break;
	                }
	                case '\r':
	                {
	                    buf.append("\\r"); break;
	                }
	                case '\n':
	                {
	                    buf.append("\\n"); break;
	                }
	                case '\t':
	                {
	                    buf.append("\\t"); break;
	                }
	                case '\b':
	                {
	                    buf.append("\\b"); break;
	                }
	                case '\f':
	                {
	                    buf.append("\\f"); break;
	                }
	                default :
	                {
	                    buf.append(c); break;
	                }
	            }   
	        }
	        
	        return buf.toString();
	    }
	    
	    /**
	     * @param x
	     * @return String
	     */
	    public static String toXmlString(String s)
	    {
	        return toXmlString(s, Empty);
	    }
	    
	    /**
	     * <p>!@#$%^&amp;*()_+|</p>
	     * <p>:&quot;?&gt;&lt;~`*</p>
	     * @param x
	     * @return String
	     * 
	     * @author lizj
	     */
	    public static String toXmlString(String s, String defaultValue)
	    {
	        if(s == null){
	            return defaultValue;
	        }
	        
	        char c;
	        
	        StringBuilder buf = new StringBuilder();
	        
	        for(int i = 0, size = s.length(); i < size; i++)
	        {
	            c = s.charAt(i);
	            
	            switch (c)
	            {
	                case '&':
	                {
	                    buf.append("&amp;");
	                    break;
	                }
	                case '"':
	                {
	                    buf.append("&quot;");
	                    break;
	                }
	                case '<':
	                {
	                    buf.append("&lt;");
	                    break;
	                }
	                case '>':
	                {
	                    buf.append("&gt;");
	                    break;
	                }
	                default :
	                {
	                    buf.append(c);
	                    break;
	                }
	                
	            }   
	        }
	        
	        return buf.toString();
	    }
	    
	    /**
	     * @param name
	     * @return String
	     */
	    public static String toCamel(String name)
	    {
	        if(null == name || name.trim().length() < 1){
	            return "";
	        }
	        
	        String[] subs = name.split("_");
	        
	        StringBuilder buf = new StringBuilder();
	        
	        if(name.startsWith("_")){
	            buf.append("_");
	        }
	        
	        if(1 == subs.length){
	            /*
	            char chars[] = name.toCharArray();
	            
	            chars[0] = Character.toUpperCase(chars[0]);
	            
	            return new String(chars);
	            */
	            
	            String s = subs[0];
	            
	            if("ID".equals(s)){
	                buf.append(s);
	            }else if(s.toUpperCase().equals(s)){
	                // 如果全部都是大写
	                buf.append(Character.toUpperCase(s.charAt(0)));
	                buf.append(s.substring(1).toLowerCase());
	            }else{
	                buf.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1));
	            }
	        } else{
	            for(String s : subs)
	            {
	                if(s.length() > 0){
	                    // buf.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1).toLowerCase());
	                    
	                    if("ID".equals(s)){
	                        buf.append(s);
	                    }else if(s.toUpperCase().equals(s)){
	                        // 如果全部都是大写
	                        buf.append(Character.toUpperCase(s.charAt(0)));
	                        buf.append(s.substring(1).toLowerCase());
	                    }else{
	                        buf.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1));
	                    }
	                }
	            }
	        }
	        
	        if(name.endsWith("_")){
	            buf.append("_");
	        }
	        
	        return buf.toString();
	    }
	    
	    /**
	     * @param s
	     * @param charset1
	     * @param charset2
	     * @return String
	     * @author lizj
	     */
	    public static String decode(String s, String charset1, String charset2)
	    {
	        if(s != null){
	            try{
	                return new String(s.getBytes(charset1), charset2);
	            }catch(UnsupportedEncodingException e){
	            }
	        }
	        
	        return Empty;
	    }
	    
	    /**
	     * @param s
	     * @return String
	     */
	    public static String htmlEncode(String s)
	    {
	        if(s == null){
	            return StringUtil.Empty;
	        }
	        
	        StringBuilder buf = new StringBuilder();

	        for(int i = 0; i < s.length(); i++)
	        {
	            char c = s.charAt(i);

	            if(c >= 0x160 && c < 256){
	                buf.append("&#" + (int)(c) + ";");

	                continue;
	            }

	            switch(c)
	            {
	                case 0x22:
	                    buf.append("&quot;");
	                    break;
	                case 0x3C:
	                    buf.append("&lt;");
	                    break;
	                case 0x3E:
	                    buf.append("&gt;");
	                    break;
	                case 0x26:
	                    buf.append("&amp;");
	                    break;
	                default:
	                    buf.append(c);
	                    break;
	            }
	        }

	        return buf.toString();
	    }
	    
	    /**
	     * @param b
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(boolean b)
	    {
	        return Boolean.toString(b);
	    }
	    
	    /**
	     * @param b
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(byte b)
	    {
	        return Byte.toString(b);
	    }
	    
	    /**
	     * @param b
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(char c)
	    {
	        return Character.toString(c);
	    }
	    
	    /**
	     * @param s
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(short s)
	    {
	        return Short.toString(s);
	    }
	    
	    /**
	     * @param i
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(int i)
	    {
	        return Integer.toString(i);
	    }
	    
	    /**
	     * @param f
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(float f)
	    {
	        return Float.toString(f);
	    }
	    
	    /**
	     * @param d
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(double d)
	    {
	        return Double.toString(d);
	    }
	    
	    /**
	     * @param l
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(long l)
	    {
	        return Long.toString(l);
	    }
	    
	    /**
	     * @param b
	     * @return
	     * @author: lizj
	     */
	    public static String toString(Boolean b)
	    {
	        return b != null ? b.toString() : Empty;
	    }
	    
	    /**
	     * @param b
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(Byte b)
	    {
	        return b != null ? b.toString() : Empty;
	    }
	    
	    /**
	     * @param s
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(Short s)
	    {
	        return s != null ? s.toString() : Empty;
	    }
	    
	    /**
	     * @param c
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(Character c)
	    {
	        return c != null ? c.toString() : Empty;
	    }
	    
	    /**
	     * @param i
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(Integer i)
	    {
	        return i != null ? i.toString() : Empty;
	    }
	    
	    /**
	     * @param f
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(Float f)
	    {
	        return f != null ? f.toString() : Empty;
	    }
	    
	    /**
	     * @param d
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(Double d)
	    {
	        return d != null ? d.toString() : Empty;
	    }
	    
	    /**
	     * @param l
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(Long l)
	    {
	        return l != null ? l.toString() : Empty;
	    }
	    
	    /**
	     * @param s
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(String s)
	    {
	        return s != null ? s : Empty;
	    }
	    
	    /**
	     * @param buf
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(StringBuffer buf)
	    {
	        return buf != null ? buf.toString() : Empty;
	    }
	    
	    
	    /**
	     * @param b
	     * @param defaultValue
	     * @return String
	     */
	    public static String toString(boolean b, String defaultValue)
	    {
	        return Boolean.toString(b);
	    }
	    
	    /**
	     * @param b
	     * @param defaultValue
	     * @return String
	     * @author: lizj 
	     */
	    public static String toString(byte b, String defaultValue)
	    {
	        return Byte.toString(b);
	    }
	    
	    /**
	     * @param b
	     * @param defaultValue
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(char c, String defaultValue)
	    {
	        return Character.toString(c);
	    }
	    
	    /**
	     * @param s
	     * @param defaultValue
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(short s, String defaultValue)
	    {
	        return Short.toString(s);
	    }
	    
	    /**
	     * @param i
	     * @param defaultValue
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(int i, String defaultValue)
	    {
	        return Integer.toString(i);
	    }
	    
	    /**
	     * @param f
	     * @param defaultValue
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(float f, String defaultValue)
	    {
	        return Float.toString(f);
	    }
	    
	    /**
	     * @param d
	     * @param defaultValue
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(double d, String defaultValue)
	    {
	        return Double.toString(d);
	    }
	    
	    /**
	     * @param l
	     * @param defaultValue
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(long l, String defaultValue)
	    {
	        return Long.toString(l);
	    }
	    
	    /**
	     * @param b
	     * @param defaultValue
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(Boolean b, String defaultValue)
	    {
	        return (b != null ? b.toString() : defaultValue);
	    }
	    
	    /**
	     * @param b
	     * @param defaultValue
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(Byte b, String defaultValue)
	    {
	        return (b != null ? b.toString() : defaultValue);
	    }
	    
	    /**
	     * @param s
	     * @param defaultValue
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(Short s, String defaultValue)
	    {
	        return (s != null ? s.toString() : defaultValue);
	    }
	    
	    /**
	     * @param c
	     * @param defaultValue
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(Character c, String defaultValue)
	    {
	        return (c != null ? c.toString() : defaultValue);
	    }
	    
	    /**
	     * @param i
	     * @param defaultValue
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(Integer i, String defaultValue)
	    {
	        return (i != null ? i.toString() : defaultValue);
	    }
	    
	    /**
	     * @param f
	     * @param defaultValue
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(Float f, String defaultValue)
	    {
	        return (f != null ? f.toString() : defaultValue);
	    }
	    
	    /**
	     * @param d
	     * @param defaultValue
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(Double d, String defaultValue)
	    {
	        return (d != null ? d.toString() : defaultValue);
	    }
	    
	    /**
	     * @param l
	     * @param defaultValue
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(Long l, String defaultValue)
	    {
	        return (l != null ? l.toString() : defaultValue);
	    }
	    
	    /**
	     * @param s
	     * @param defaultValue
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(String s, String defaultValue)
	    {
	        return (s != null ? s : defaultValue);
	    }
	    
	    /**
	     * @param buf
	     * @param defaultValue
	     * @return String
	     * @author: lizj
	     */
	    public static String toString(StringBuffer buf, String defaultValue)
	    {
	        return (buf != null ? buf.toString() : defaultValue);
	    }
	 
	    
	    /**
	     * <p>Description:首字母转小写,如果第二个字符是大写则首字母仍然是大写，无需转换小写</p>
	     * @param value
	     * @return String
	     * @author Jason_Su
	     */
	    public static String toHeadLowCase(String value)
	    {
	    	if(isEmpty(value)){
	    		return null;
	    	}else{
	    		if(value.length() > 1){
	    			if(Character.isUpperCase(value.charAt(1))){
	    				return value;
	    			}
	    			return value.substring(0,1).toLowerCase() + value.substring(1);
	    		}else{
	    			return value.toLowerCase();
	    		}
	    		
	    	}
	    }
	
	/**  
	* <p>Description:MD5 加密  </p>
	* @param str
	* @return String
	*/
	public static String getMD5Str(String str) 
	{
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) 
		{
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}
}
