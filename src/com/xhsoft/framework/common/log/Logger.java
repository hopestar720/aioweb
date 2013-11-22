/*
 * $RCSfile: Logger,v $$
 * $Revision: 1.0  $
 * $Date: 2007-02-18  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Priority;

/**
 * <p>Title:Logger</p>
 * <p>Description:框架基类操作数据库的Dao接口</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author Tyrone
 * @date 2007-02-18
 */
public class Logger
{
    private final static String ERROR_LOG = "com.xhsoft.error";
    private final static String APP_LOG = "com.xhsoft.application";
    private final static String AUTH_LOG = "com.xhsoft.logon";
    private final static String TRANS_LOG = "com.xhsoft.transaction";
    private final static String INF_LOG = "com.xhsoft.interface";
    
    private static org.apache.log4j.Logger errorLog;
    private static org.apache.log4j.Logger appLog;
    private static org.apache.log4j.Logger authLog;
    private static org.apache.log4j.Logger transLog;
    private static org.apache.log4j.Logger infLog;
    
    private final static String APP_LOG_SEQ = "SEQ_APP_LOG";
    private final static String AUTH_LOG_SEQ = "SEQ_AUTH_LOG";
    private final static String TRANS_LOG_SEQ = "SEQ_TRANS_LOG";
    private final static String INF_LOG_SEQ = "SEQ_INF_LOG";
    
    public static final String CONF_SYSTEM_PARMAS = "SystemParams";
	public static final String SYS_USER_CD = "SYSTEM";



    static {
        appLog = org.apache.log4j.Logger.getLogger(APP_LOG);
        authLog = org.apache.log4j.Logger.getLogger(AUTH_LOG);
        transLog = org.apache.log4j.Logger.getLogger(TRANS_LOG);
        infLog = org.apache.log4j.Logger.getLogger(INF_LOG);
        
        try {
			Logger.logInfo("Logger is ready on machine " + java.net.InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
    }
	
    /**
	 * <p>Description:初始化</p>
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
	public static void init()
	{
        errorLog= org.apache.log4j.Logger.getLogger(ERROR_LOG);
		appLog = org.apache.log4j.Logger.getLogger(APP_LOG);
        authLog = org.apache.log4j.Logger.getLogger(AUTH_LOG);
        transLog = org.apache.log4j.Logger.getLogger(TRANS_LOG);
        infLog = org.apache.log4j.Logger.getLogger(INF_LOG);
	}
     
	/**
	 * <p>Description:Log fatal message, which will be insert into tbl_log_app table</p>
	 * @param message
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings("deprecation")
	public static void logFatal(String message)
    {
        if(appLog!=null&&appLog.isEnabledFor(Priority.FATAL))
            logFatal(StackContextUtil.getCallerClassName(1),
                StackContextUtil.getCallerMethodName(1), SYS_USER_CD, message);
    }

    /**
	 * <p>Description:Log fatal message, which will be insert into tbl_log_app table</p>
	 * @param userCd
	 * @param message
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings({ "unused", "deprecation" })
	private static void logFatal(String userCd, String message)
    {
        if(appLog!=null&&appLog.isEnabledFor(Priority.FATAL))
            logFatal(StackContextUtil.getCallerClassName(1),
                StackContextUtil.getCallerMethodName(1), userCd, message);
    }

    /**
	 * <p>Description:Log fatal message, which will be insert into tbl_log_app table</p>
	 * @param className
	 * @param methodName
	 * @param userCd
	 * @param description
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings("deprecation")
	public static void logFatal(String className, String methodName, String userCd, String description)
    {
        String message;

        message = APP_LOG_SEQ + "|" + toDateString(new Date()) + "|" + "FATAL|" + className + "|" + methodName + "|" + userCd + "|" + description;
     
            if(appLog!=null&&appLog.isEnabledFor(Priority.FATAL))
                appLog.fatal(message);
            
            if(errorLog!=null&&!errorLog.isEnabledFor(Priority.WARN))
                errorLog.fatal(message);
       
    }

    /**
	 * <p>Description:Log error message, which will be insert into tbl_log_app table</p>
	 * @param message
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings("deprecation")
	public static void logError(String message)
    { 
        if (appLog != null && appLog.isEnabledFor(Priority.ERROR))
            logError(StackContextUtil.getCallerClassName(1),
                StackContextUtil.getCallerMethodName(1), SYS_USER_CD, message);
    }
    
    /**
	 * <p>Description:Log error message, which will be insert into tbl_log_app table</p>
	 * @param message
	 * @param Throwable
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings("deprecation")
	public static void logError(String message,Throwable e)
    { 
        if (appLog != null && appLog.isEnabledFor(Priority.ERROR))
            logError(StackContextUtil.getCallerClassName(1),
                StackContextUtil.getCallerMethodName(1), SYS_USER_CD, message);
    }

    /**
	 * <p>Description:Log error message, which will be insert into tbl_log_app table</p>
	 * @param userCd
	 * @param message
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings({ "unused", "deprecation" })
	private static void logError(String userCd, String message)
    {
        if (appLog != null && appLog.isEnabledFor(Priority.ERROR))
            logError(StackContextUtil.getCallerClassName(1),
                StackContextUtil.getCallerMethodName(1), userCd, message);
    }

    /**
	 * <p>Description:Log error message, which will be insert into tbl_log_app table</p>
	 * @param className
	 * @param methodName
	 * @param userCd
	 * @param description
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings("deprecation")
	public static void logError(String className, String methodName, String userCd, String description)
    {
        String message;

        message = APP_LOG_SEQ + "|" + toDateString(new Date()) + "|" + "ERROR|" + className + "|" + methodName + "|" + userCd + "|" + description;
       
        if (appLog != null && appLog.isEnabledFor(Priority.ERROR))
            appLog.error(message);
            
        if (errorLog != null && !errorLog.isEnabledFor(Priority.WARN))
            errorLog.error(message);
       
    }

    /**
	 * <p>Description:Log warn message, which will be insert into tbl_log_app table</p>
	 * @param message
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings({ "deprecation", "unused" })
	private static void logWarn(String message)
    {
        if (appLog != null && appLog.isEnabledFor(Priority.WARN))
            logWarn(StackContextUtil.getCallerClassName(1),
                StackContextUtil.getCallerMethodName(1), SYS_USER_CD, message);
    }

    /**
	 * <p>Description:Log warn message, which will be insert into tbl_log_app table</p>
	 * @param userCd
	 * @param message
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings({ "deprecation", "unused" })
	private static void logWarn(String userCd, String message)
    {
        if (appLog != null && appLog.isEnabledFor(Priority.WARN))
            logWarn(StackContextUtil.getCallerClassName(1),
                StackContextUtil.getCallerMethodName(1), userCd, message);
    }

    /**
	 * <p>Description:Log warn message, which will be insert into tbl_log_app table</p>
	 * @param className
	 * @param methodName
	 * @param userCd
	 * @param description
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings("deprecation")
	private static void logWarn(String className, String methodName, String userCd, String description)
    {
        String message;

        message = APP_LOG_SEQ + "|" + toDateString(new Date()) + "|" + "WARN|" + className + "|" + methodName + "|" + userCd + "|" + description;
        
            if (appLog != null && appLog.isEnabledFor(Priority.WARN))
                appLog.warn(message);
       
    }

    /**
	 * <p>Description:Log information message, which will be insert into tbl_log_app table</p>
	 * @param message
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings("deprecation")
	public static void logInfo(String message)
    {
        if (appLog != null && appLog.isEnabledFor(Priority.INFO))
            logInfo(StackContextUtil.getCallerClassName(1),
                StackContextUtil.getCallerMethodName(1), SYS_USER_CD, message);
    }

    /**
	 * <p>Description:Log information message, which will be insert into tbl_log_app table</p>
	 * @param userCd
	 * @param message
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings({  "unused", "deprecation" })
	private static void logInfo(String userCd, String message)
    {
        if (appLog != null && appLog.isEnabledFor(Priority.INFO))
            logInfo(StackContextUtil.getCallerClassName(1),
                StackContextUtil.getCallerMethodName(1), userCd, message);
    }

    /**
	 * <p>Description:Log information message, which will be insert into tbl_log_app table</p>
	 * @param className
	 * @param methodName
	 * @param userCd
	 * @param description
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings("deprecation")
	private static void logInfo(String className, String methodName, String userCd, String description)
    {
        String message;

        message = APP_LOG_SEQ + "|" + toDateString(new Date()) + "|" + "INFO|" + className + "|" + 
                methodName + "|" + userCd + "|" + description;
       
            if (appLog != null && appLog.isEnabledFor(Priority.INFO))
                appLog.info(message);
        
    }

    /**
	 * <p>Description:Log debug message, which will be insert into tbl_log_app table</p>
	 * @param message
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings("deprecation")
	public static void logDebug(String message)
    {
        if (appLog != null && appLog.isEnabledFor(Priority.DEBUG))
            logDebug(StackContextUtil.getCallerClassName(1),
                StackContextUtil.getCallerMethodName(1), SYS_USER_CD, message);
    }

    /**
	 * <p>Description:Log debug message, which will be insert into tbl_log_app table</p>
	 * @param userCd
	 * @param message
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings({ "unused", "deprecation" })
	private static void logDebug(String userCd, String message)
    {
        if (appLog != null && appLog.isEnabledFor(Priority.DEBUG))
            logDebug(StackContextUtil.getCallerClassName(1),
                StackContextUtil.getCallerMethodName(1), userCd, message);
    }

    /**
	 * <p>Description:Log debug message, which will be insert into tbl_log_app table</p>
	 * @param className
	 * @param methodName
	 * @param userCd
	 * @param description
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings("deprecation")
	private static void logDebug(String className, String methodName, String userCd, String description)
    {
        String message;

        message = APP_LOG_SEQ + "|" + toDateString(new Date()) + "|" + "DEBUG|" + className + "|" +
        			StackContextUtil.getCallerLineNumber(1) + "|"+
                  methodName + "|" + userCd + "|" + description;
        
            if (appLog != null && appLog.isEnabledFor(Priority.DEBUG))
                appLog.debug(message);
       
        
    }

    /**
	 * <p>Description:log authentication message, which will be insert into tbl_log_trans table in DB</p>
	 * @param ipAddr      
     * @param userCd      
     * @param description 
     * @param status  
     * @param action    
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings("deprecation")
	public static void logAuth(String ipAddr, String userCd, String description,
                                                  String status, String action)
    {
        String message;

        message =AUTH_LOG_SEQ + "|" + toDateString(new Date()) + "|" + ipAddr + "|" + 
                    userCd + "|" + status + "|" + action + "|" + description;
                    
        
            if (authLog != null && !authLog.isEnabledFor(Priority.DEBUG))
                authLog.info(message);
        
    }
    
    /**
	 * <p>Description:log Transaction message, which will be insert into tbl_log_trans table in DB</p>
	 * @param transType   Transaction type, such as Order, which is defined in the TransactionType class
     * @param transAction create, delete, or update
     * @param userCd      login user code
     * @param status      success or fail
     * @param description description information
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    public static void logTrans(String transType, String transAction, String transId,
                                                   String status, String description)
    {
        logTrans(SYS_USER_CD,transType, transAction, transId,
                                status, description,null,null,null);
        
    }
    
    /**
	 * <p>Description:log Transaction message, which will be insert into tbl_log_trans table in DB</p>
	 * @param transType   Transaction type, such as Order, which is defined in the TransactionType class
     * @param transAction create, delete, or update
     * @param userCd      login user code
     * @param status      success or fail
     * @param description description information
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    public static void logTrans(String userCd, String transType, String transAction, String transId,
                                String status, String description)
    {
        logTrans(userCd,transType, transAction, transId,
                                status, description,null,null,null);
    
    }
    
    /**
	 * <p>Description:log Transaction message, which will be insert into tbl_log_trans table in DB</p>
	 * @param transType   Transaction type, such as Order, which is defined in the TransactionType class
     * @param transAction create, delete, or update
     * @param userCd      login user code
     * @param status      success or fail
     * @param description description information
     * @param reserve1    Reserved parameter1
     * @param reserve2    Reserved parameter2
     * @param reserve3    Reserved parameter3
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings("deprecation")
	public static void logTrans(String userCd, String transType, 
    		                                       String transAction, String transId, 
                                                   String status, String description, 
                                                   String reserve1, String reserve2, String reserve3)
    {
        String message;

        message = TRANS_LOG_SEQ + "|" + toDateString(new Date()) + "|" + transType + "|" + 
                    transAction + "|" + transId + "|" + userCd + "|" + status + "|" + description
                     + "|" + reserve1 + "|" + reserve2 + "|" + reserve3;
                     
            if (transLog != null && !transLog.isEnabledFor(Priority.DEBUG))
                transLog.info(message);
        
    }

    /**
	 * <p>Description:log interface message, which will be insert into tbl_log_inf table in DB</p>
	 * @param infType     interface type (B2B, SAP, e-HR, DWH, DyM ...)
     * @param direction   interaction direction IN/OUT
     * @param bizAction   biz action between interface, such as create order
     * @param status      success, fail, delay and ondemand
     * @param description description information
     * @param docId       Doc id, such as iDoc_id, Queue_id. Optional.
     * @param content     Detail interaction content, such as messge content
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    public static void logInterface(String infType, String direction, String bizAction, 
                                                        String status, String description, String docId, StringBuffer content)
    {
        logInterface(SYS_USER_CD,infType,direction,bizAction,
            status, description, docId, content,null, null,null,null);
    }

    /**
	 * <p>Description:log interface message, which will be insert into tbl_log_inf table in DB</p>
	 * @param infType     interface type (B2B, SAP, e-HR, DWH, DyM ...)
     * @param direction   interaction direction IN/OUT
     * @param bizAction   biz action between interface, such as create order
     * @param status      success, fail, delay and ondemand
     * @param description description information
     * @param docId       Doc id, such as iDoc_id, Queue_id. Optional.
     * @param content     Detail interaction content, such as messge content
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    public static void logInterface(String infType, String direction, String bizAction, 
                                                         String status, String description, String docId, 
                                                         StringBuffer contentStr,byte[] contentByte)
    {
        logInterface(SYS_USER_CD,infType,direction,bizAction,
            status, description, docId, contentStr,contentByte,null,null,null);
    }

    /**
	 * <p>Description:log interface message, which will be insert into tbl_log_inf table in DB</p>
	 * @param userCd      login user code
     * @param infType     interface type (B2B, SAP, e-HR, DWH, DyM ...)
     * @param direction   interaction direction IN/OUT
     * @param bizAction   biz action between interface, such as create order
     * @param status      success, fail, delay and ondemand
     * @param description description information
     * @param docId       Doc id, such as iDoc_id, Queue_id. Optional.
     * @param content     Detail interaction content, such as messge content
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    public static void logInterface(String userCd, String infType, String direction, String bizAction, 
                                                         String status, String description, String docId, StringBuffer content)
    {
        logInterface(userCd,infType,direction,bizAction,
            status, description, docId, content,null, null,null,null);
    }
    
    /**
	 * <p>Description:log interface message, which will be insert into tbl_log_inf table in DB</p>
	 * @param userCd      login user code
     * @param infType     interface type (B2B, SAP, e-HR, DWH, DyM ...)
     * @param direction   interaction direction IN/OUT
     * @param bizAction   biz action between interface, such as create order
     * @param status      success, fail, delay and ondemand
     * @param description description information
     * @param docId       Doc id, such as iDoc_id, Queue_id. Optional.
     * @param content     Detail interaction content, such as messge content
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    public static void logInterface(String userCd, String infType, String direction, String bizAction, 
                                                         String status, String description, String docId, StringBuffer contentStr,
                                                         byte[] contentByte)
    {
        logInterface(userCd,infType,direction,bizAction,
            status, description, docId, contentStr,contentByte, null,null,null);
    }
    
    /**
	 * <p>Description:log interface message, which will be insert into tbl_log_inf table in DB</p>
	 * @param infType     interface type (B2B, SAP, e-HR, DWH, DyM ...)
     * @param direction   interaction direction IN/OUT
     * @param bizAction   biz action between interface, such as create order
     * @param status      success, fail, delay and ondemand
     * @param description description information
     * @param docId       Doc id, such as iDoc_id, Queue_id. Optional.
     * @param content     Detail interaction content, such as messge content
     * @param reserve1    Reserved parameter1
     * @param reserve2    Reserved parameter2
     * @param reserve3    Reserved parameter3
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    public static void logInterface(String infType, String direction, String bizAction, 
                                                         String status, String description, String docId, StringBuffer content, 
                                                         String reserve1, String reserve2, String reserve3)
    {
        logInterface(SYS_USER_CD,infType,direction,bizAction,
            status, description, docId, content,null, reserve1,reserve2,reserve3);
    }
    
    /**
	 * <p>Description:log interface message, which will be insert into tbl_log_inf table in DB</p>
	 * @param infType     interface type (B2B, SAP, e-HR, DWH, DyM ...)
     * @param direction   interaction direction IN/OUT
     * @param bizAction   biz action between interface, such as create order
     * @param status      success, fail, delay and ondemand
     * @param description description information
     * @param docId       Doc id, such as iDoc_id, Queue_id. Optional.
     * @param content     Detail interaction content, such as messge content
     * @param reserve1    Reserved parameter1
     * @param reserve2    Reserved parameter2
     * @param reserve3    Reserved parameter3
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    public static void logInterface(String infType, String direction, String bizAction, 
                                                         String status, String description, String docId, StringBuffer contentStr,
                                                         byte[] contentByte, 
                                                         String reserve1, String reserve2, String reserve3)
    {
        logInterface(SYS_USER_CD,infType,direction,bizAction,
            status, description, docId, contentStr,contentByte, reserve1,reserve2,reserve3);
    }

    /**
	 * <p>Description:log interface message, which will be insert into tbl_log_inf table in DB</p>
	 * @param userCd      login user code
     * @param infType     interface type (B2B, SAP, e-HR, DWH, DyM ...)
     * @param direction   interaction direction IN/OUT
     * @param bizAction   biz action between interface, such as create order
     * @param status      success, fail, delay and ondemand
     * @param description description information
     * @param docId       Doc id, such as iDoc_id, Queue_id. Optional.
     * @param content     Detail interaction content, such as messge content
     * @param reserve1    Reserved parameter1
     * @param reserve2    Reserved parameter2
     * @param reserve3    Reserved parameter3
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    public static void logInterface(String userCd, String infType, String direction, String bizAction, 
                                                         String status, String description, String docId, StringBuffer content, 
                                                         String reserve1, String reserve2, String reserve3)
    {
        logInterface(userCd, infType, direction, bizAction, 
         status, description, docId, content, null,
         reserve1, reserve2, reserve3);
    }
    
    /**
	 * <p>Description:log interface message, which will be insert into tbl_log_inf table in DB</p>
	 * @param userCd      login user code
     * @param infType     interface type (B2B, SAP, e-HR, DWH, DyM ...)
     * @param direction   interaction direction IN/OUT
     * @param bizAction   biz action between interface, such as create order
     * @param status      success, fail, delay and ondemand
     * @param description description information
     * @param docId       Doc id, such as iDoc_id, Queue_id. Optional.
     * @param content     Detail interaction content, such as messge content
     * @param reserve1    Reserved parameter1
     * @param reserve2    Reserved parameter2
     * @param reserve3    Reserved parameter3
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    @SuppressWarnings("deprecation")
	public static void logInterface(String userCd, String infType, String direction, String bizAction, 
                                                         String status, String description, String docId, StringBuffer contentStr,
                                                         byte[] contentByte, 
                                                         String reserve1, String reserve2, String reserve3)
    {
        String message;
        
        String storeFileNm=null;
        
        if(contentStr!=null&&contentByte!=null)
            Logger.logError("logger inteface error, content is duplicated");
            
        if(contentStr!=null||contentByte!=null)
            storeFileNm=executeBlob(contentStr,contentByte,direction,infType);

        message=INF_LOG_SEQ + "|" + toDateString(new Date()) + "|" +   
                    infType + "|" + direction + "|" + bizAction + "|" + status + "|" + 
                    description + "|" + docId + "|" + storeFileNm + "|" + reserve1 + "|" + reserve2 + "|" + reserve3;
        
        if (infLog != null && !infLog.isEnabledFor(Priority.DEBUG))
            infLog.info(message);
    }
    
    /**
	 * <p>Description:store the content to the file</p>
	 * @param contentStr
	 * @param contentByte
	 * @param direction
	 * @param infType
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
    private static String executeBlob(StringBuffer contentStr,byte[] contentByte, 
    		                                                   String direction,String infType)
    {
        //Hashtable settings = (Hashtable)ConfigurationSettings.getConfig(AppConstants.CONFIGURATION_FILE_DIR);
        String specDivider="/";
        String path="";
        //String rootPath=(String)settings.get(AppConstants.CONFIGURATION_LOG_INTERFACE_CONTENT_DIRECTORY);
        String rootPath = "c:/blob";
        
        if(rootPath==null||rootPath.length()==0)
        {
            Logger.logError("The root path of log file is null");
            return null;
        }
        rootPath=rootPath+specDivider+direction+specDivider+infType;
        path+=rootPath+specDivider+dateToString(new Date());
        File f=null;
        String tempNm=null;
        
        try
        {
            boolean fileExist=true;
            while(fileExist)
            {
                if(contentStr!=null)
                    tempNm=path+".xml";
                else
                    tempNm=path;
                f=new File(tempNm);
                if(f.exists())
                    path+="1";
                else
                    fileExist=false;
            }
            
            if(!f.canWrite())//no directory
            {
                f=new File(rootPath);
                f.mkdirs();
            }
            f=null;
            if(contentStr!=null)
            {
                FileWriter fw = new FileWriter(tempNm);
                PrintWriter out = new PrintWriter(fw);
                try {
                    out.println(contentStr);  
                } finally {
                    if(out != null)
                        out.close();
                    if(fw != null)
                        fw.close();
                }
            }
            else
            {
                FileOutputStream out= new FileOutputStream(tempNm);
                PrintStream p = new PrintStream( out );
                try {
                    p.write(contentByte);
                } finally {
                    if(p != null)
                        p.close();
                    if(out != null)
                        out.close();
                }
            }
            return tempNm;
        }
        catch(IOException e)
        {
            //ExceptionManager.publish(e);
            //throw a new exception
            return null;
        }
    }
    
    /**
	 * <p>Description:toDateString</p>
	 * @param date
	 * @return String
	 * @author wgq
	 * @version 1.0
	 */
    private static String toDateString(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
    
    private static String dateToString(Date date){
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        return formatter.format(date);
    }
}
