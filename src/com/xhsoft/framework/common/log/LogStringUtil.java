/*
 * $RCSfile: LogStringUtil,v $$
 * $Revision: 1.0  $
 * $Date: 2010-12-09  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.log;


import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title:LogStringUtil</p>
 * <p>Description:the utilities class for string processing</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author Wanggq
 * @date 2010-12-09
 */
public class LogStringUtil 
{
    private LogStringUtil() 
    {
    }
	
	/**
	 * <p>Description:Pad string to the left </p>
	 * @param original string to be padded
	 * @param string to pad
	 * @param location of string to pad
	 * @return the new string
	 */
    public static String lpad(String str, String chr, int length)
    {
        String retVal = str;
        for (int i = 0; i < length - str.length(); i++)
            retVal = chr + retVal;

        return retVal;
    }
	
    /**
     * <p>Description:check if the string is null or empty</p>
     * @param str the string
     * @return if the string is null or empty, return true, else return false
     */
    public static boolean isEmpty(String str) 
    {
        if (str == null) {
            return true;
        }

        if (str.trim().length() == 0) {
            return true;
        }

        return false;
    }

    /**
     * <p>Description:To prepare a string containing wildcard characters for use with SQL</p>
     * @param inputStr String
     * @param addWildcard if add SQL wildcard char
     * @return a string containing the new expression
     */
    public static String convertWildcardsToSQL(String inputStr,boolean addWildcard) 
    {
        if (inputStr == null) {
            return "";
        }

        StringBuffer buf = new StringBuffer();

        // Search through string's characters one by one.
        // If character is a '"', replace it with the appropriate
        // character for SQL
        int len = inputStr.length();
        boolean wildCardPresent = false;

        for (int i = 0; i < len; i++) 
        {
            char c = inputStr.charAt(i);

            switch (c) 
            {
            case '*':
                buf.append("%");
                wildCardPresent = true;

                break;

            case '?':
                buf.append("_");
                wildCardPresent = true;

                break;

            default:
                buf.append(c);

                break;
            }
        }

        if (addWildcard) {
            if (!wildCardPresent) {
                // prepend with wild character if none
                //exists already (BUG06901)
                //                buf.insert(0, "%");
                buf.append("%");
            }
        }

        return buf.toString();
    }

    /**
     * <p>Description:This method returns a string value surrounded by double quotes.</p>
     * @param string - the string variable
     * @return the string surround by double quotes
     */
    public static String dquote(String string) 
    {
        StringBuffer dQuoteString = new StringBuffer();
        dQuoteString.append('"').append(string).append('"');

        return dQuoteString.toString();
    }

    /**
     * <p>Description:To escape a string containing double quotes for use with SQL</p>
     * @param inputStr String
     * @return a string containing the new expression
     */
    public static String escapeDoubleQuotes(String inputStr) 
    {
        if (inputStr == null) {
            return "";
        }

        StringBuffer buf = new StringBuffer();

        // Search through string's characters one by one.
        // If character is a '"', replace it with the
        // appropriate character for SQL
        int len = inputStr.length();

        for (int i = 0; i < len; i++) 
        {
            char c = inputStr.charAt(i);

            switch (c) 
            {
            case '\'':
                buf.append("\\\'");

                break;

            case '\"':
                buf.append("\\\"");

                break;

            default:
                buf.append(c);

                break;
            }
        }

        return buf.toString();
    }

    /**
     * <p>Description:split a string into String array delimited by character</p>
     * @param str the original string
     * @param deli the delimit character
     * @return the String array or a zero length string array
     */
	public static String[] split(String str, char deli) 
    {
        // return no groups if we have an empty string
        if ((str == null) || "".equals(str)) {
            return new String[0];
        }

        ArrayList<String> parts = new ArrayList<String>();
        int currIdx;
        int prevIdx = 0;

        while ((currIdx = str.indexOf(deli, prevIdx)) > 0) 
        {
            String part = str.substring(prevIdx, currIdx).trim();
            parts.add(part);
            prevIdx = currIdx + 1;
        }

        parts.add(str.substring(prevIdx, str.length()).trim());

        String[] result = new String[parts.size()];
        parts.toArray(result);

        return result;
    }

    /**
     * <p>Description:This method returns a string value surrounded by single quotes.</p>
     * @param string - the string variable
     * @return the string surround by sinle quotes
     */
    public static String squote(String string) 
    {
        StringBuffer sQuoteString = new StringBuffer();
        sQuoteString.append("'").append(string).append("'");

        return sQuoteString.toString();
    }

    /**
     *<p>Description: this method is used to validate user inputs</p>
     * @param pwd password input
     * @param usrLogin username
     * @param firstName first name
     * @param lastName last name
     * @return boolean value of true or false
     */
    public static boolean validatePassword(String pwd, String usrLogin,
                                                                         String firstName, String lastName) 
    {
        // must be 5 characters or more
        if (pwd.length() < 5) {
            return false;
        }

        // Must not contain user logon
        if (pwd.indexOf(usrLogin) >= 0) {
            return false;
        }

        return true;
    }

    /**
     * <p>Description:check whether match or not</p>
     * @param wildCard the wildCard
     * @param stringToMatch the fieldValue
     * @return boolean
     */
    public static boolean wildCardMatch(String wildCard,
                                                                     String stringToMatch) 
    {
        if ((wildCard == null) || (stringToMatch == null)) {
            return false;
        }

        wildCard = wildCard.trim();
        stringToMatch = stringToMatch.trim();

        wildCard = wildCard.replaceAll("\\*", ".*");
        wildCard = wildCard.replaceAll("\\?", ".");

        /**
         * D098168: escape all potential problem special chars
         */
        wildCard = wildCard.replaceAll("\\(", "\\\\(");
        wildCard = wildCard.replaceAll("\\)", "\\\\)");
        wildCard = wildCard.replaceAll("\\[", "\\\\[");
        wildCard = wildCard.replaceAll("\\{", "\\\\{");


        Pattern p = Pattern.compile(wildCard);
        Matcher m = p.matcher(stringToMatch);

        return m.matches();
    }

    
    public static String addRightSpace(String str, int num)
    {
        StringBuffer sb = new StringBuffer(str);
        int length = str.length();
        while(length < num){
            sb.append(" ");
            length++;
        }
        return sb.toString();
    }

    public static String[] stringArrayRemoveAll(String[] a, String[] b)
    {
        if(a == null || b == null)
            return a;

        for(int i = 0; i < a.length; i++)
        {
        	
            for(int j = 0; j < b.length; j++)
            {
                if(a[i] == null){
                    continue;
                }
                
                if(a[i].equals(b[j])){
                    a[i] = null;
                }
            }
            
        }
        
        return a;
    }

    /**
     * <p>Description:If string array a equals to string array b, return true</p>
     * @param string[] a, string[] b
     * @return boolean
     */
    public static boolean compareStringArray(String[] a, String[] b)
    {
        int bLength = b.length;
        boolean ifFind = false;
        
        for (int i = 0; i<a.length; i++)
        {
            ifFind = false;
            if(a[i] == null){
                continue;
            }
            
            for(int j = 0; j < b.length; j++)
            {
                if(b[j].equals(a[i]) ){
                    bLength--;
                    ifFind = true;
                    break;
                }
            }
            
            if(ifFind){
                continue;
            }
            else
                return false;

        }
        
        if(bLength != 0)
            return false;
        else
            return true;
    }

    /**
     * <p>Description:Add Char To Left</p>
     * @param String the origin string
     * @param int the defined length of the output string
     * @param char the char to insert to the left
     * @return String the output string
     */
    public static String addCharToLeft(String str,int length, char achar)
    {
        StringBuffer buffer = new StringBuffer( str );
        int len = str.length();
        
        while ( len < length )
        {
            buffer = buffer.insert( 0, achar );
            len ++;
        }
        
        return buffer.toString();
    }

	/**
	 * <p>Description:generate string for js to define a array according a map.</p>
	 * @param m the map.
	 * @return the generate string.
	 * @author Guan, Wei-Qi.
	 * @since 5.23.2005
	 */
	@SuppressWarnings("unchecked")
	public static String Map2Js(Map m){
		Entry[] e = (Entry[]) m.entrySet().toArray(new Entry[m.size()]);
		
		String s ="var hqCdNm = new Array(" + e.length + ");";
        String key="";
        String value="";
        
		for(int i = 0; i < e.length; ++i) 
		{
            key="";
            value="";
            if(e[i].getKey()!=null)
                key = e[i].getKey().toString();
            
            if(e[i].getValue()!=null)
                value = e[i].getValue().toString();
            
			s += "hqCdNm[" + i + "] = new Array(\"" + key + "\", \"" + value + "\");";
		}
		
		return s;
	}
    
    public static String formatNumber(Object num)
    {
        if(num==null)
            return null;
        
        String number=num.toString().trim();
        
        if(number.length()==0)
            return null;
        
        if(number.indexOf(".")==-1)
            return number;
        
        return number.length()-number.indexOf(".")>2?number.substring(0, number.indexOf(".")+3):number;
    }
	
	/**
	 * <p>Description:Convert string which contains '%','_' to '\%' and '\_'</p>
	 * @param input string
	 * @return output string
	 */
	public static String addEscapeChar(String str)
	{
		String output = str.replaceAll("%","\\\\" + "%");
		output = output.replaceAll("_","\\\\" + "_");
        return output;
	}
    
    /**
	 * <p>Description:Sub a string with the length, </p>
	 * @param srcStr source string
	 * @param subLen the length what you want to sub a string
	 * @return subLen the length of sub string
	 */
    public static String subString(String srcStr,int subLen)
    {
        byte[] bytes=srcStr.getBytes();
        //add by yujie
        //when logwarn system will remove some character of chinese string
        //on 2005/10/18
        if(bytes.length<=subLen)
            return srcStr;
        
        char[] chars=srcStr.toCharArray();
        StringBuffer sb=new StringBuffer();
        
        for(int i=0,j=0;i<subLen&&i<bytes.length&&j<chars.length;)
        {
            char cbyte=(char)bytes[i];
            //normal English character
            if(cbyte==chars[j]){
                sb.append(chars[j]);
                i++;
                j++;
            } else{//Chinese character
                //if adding next Chinese character will be over limitted, 
                //then will not add this character
                if(i+1<subLen){
                    sb.append(chars[j]);
                    //one chinese character will be equal two bytes
                    i++;
                    i++;
                    j++;
                }
                else
                    break;
            }
        }
        
        return sb.toString();
    }
	
	/**
	 * <p>Description:Trim the input string</p>
	 * @param input string
	 * @return the trimmed string
	 */
	public static String trim(String str)
	{
		return (str == null ? "" : str.trim());
	}
    
    /**
     * <p>Description:Limit the string with the fixed length</p>
     * @param str
     * @param length
     * @return String 
     */
    public static String fixLength(String str, int length) 
    {
        StringBuffer returnValue = new StringBuffer(str);
        
        if(returnValue.length() >= length) {
            returnValue = new StringBuffer(returnValue.substring(0, length));
        } else {
            while(returnValue.length() < length) 
            {
                returnValue.insert(0, "0");
            }
        }
        
        return returnValue.toString();
    }

}