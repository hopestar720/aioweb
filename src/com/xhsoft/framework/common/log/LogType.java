/*
 * $RCSfile: LogType,v $$
 * $Revision: 1.0  $
 * $Date: 2010-12-09  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.log;

/**
 * <p>Title:LogType</p>
 * <p>Description:日志类型</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author Wanggq
 * @date 2010-12-09
 */
public class LogType 
{
	public LogType()
    {
    }

    public static boolean isLogType(int i)
    {
        return i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6;
    }

    public static boolean isLogType(String s)
    {
        return s.equals("INTEGER") || s.equals("STRING") || s.equals("NUMBER") || s.equals("TIMESTAMP") || s.equals("SEQUENCE") || s.equals("BLOB");
    }

    public static int parseLogType(String s)
    {
        if(s.equals("INTEGER"))
            return 1;
        if(s.equals("STRING"))
            return 2;
        if(s.equals("NUMBER"))
            return 3;
        if(s.equals("TIMESTAMP"))
            return 4;
        if(s.equals("SEQUENCE"))
            return 5;
        if(s.equals("BLOB"))
            return 6;

        return 2;
    }

    public static final int INTEGER = 1;
    public static final String INTEGER_VAR = "INTEGER";
    public static final int STRING = 2;
    public static final String STRING_VAR = "STRING";
    public static final int NUMBER = 3;
    public static final String NUMBER_VAR = "NUMBER";
    public static final int TIMESTAMP = 4;
    public static final String TIMESTAMP_VAR = "TIMESTAMP";
    public static final int SEQUENCE = 5;
    public static final String SEQUENCE_VAR = "SEQUENCE";
    public static final int BLOB = 6;
    public static final String BLOB_VAR = "BLOB";
}
