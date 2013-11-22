/*
 * $RCSfile: Config,v $$
 * $Revision: 1.0  $
 * $Date: 2011  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.config;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xhsoft.framework.common.config.ApplicationConfig;

/**
 * <p>Title: Config</p> 
 * <p>Description: Config</p> 
 * <p>Copyright: Copyright (c) 2011</p> 
 * @author lizj
 * @version 1.0
 */
public class Config
{
    private static ApplicationConfig applicationConfig;
    private static final Log log = LogFactory.getLog(Config.class);

    /**
     * New Config
     */
    private Config()
    {
    }

    /**
     * @return
     * @author lizj
     */
    public static int getRunningMode()
    {
        int runningMode = applicationConfig.getInteger("Base/RUNNINGMODE", 1);

        if(log.isDebugEnabled()){
            log.debug("RunningMode: " + runningMode);
        }

        return runningMode;
    }
    
    /**
     * @return
     * @author lizj
     */
    public static ApplicationConfig getApplicationConfig()
    {
        return applicationConfig;
    }
    
    /**
     * @param _application
     * @author lizj
     */
    public static void setApplicationConfig(ApplicationConfig _applicationConfig)
    {
        if(applicationConfig == null){
            applicationConfig = _applicationConfig;
        }
    }
    
    /**
     * @return
     * @author lizj
     */
    public static String getApplicationName()
    {
        return applicationConfig.getName();
    }
    
    /**
     * @return
     * @author lizj
     */
    public static String getApplicationDescription()
    {
        return applicationConfig.getDescription();
    }

    /**
     * @param name
     * @return
     */
    public static String getValue(String name)
    {
        return applicationConfig.getValue(name);
    }
    
    /**
     * @param name
     * @return
     */
    public static String getValue(String name, String defaultValue)
    {
        return applicationConfig.getValue(name, defaultValue);
    }
    
    /**
     * @param name
     * @return
     */
    public static String getString(String name)
    {
        return applicationConfig.getValue(name);
    }
    
    /**
     * @param name
     * @return
     */
    public static String getString(String name, String defaultValue)
    {
        return applicationConfig.getValue(name, defaultValue);
    }
    
    /**
     * @param name
     * @param defaultValue
     * @return
     */
    public static Boolean getBoolean(String name, Boolean defaultValue)
    {
        return applicationConfig.getBoolean(name, defaultValue);
    }

    /**
     * @param name
     * @return
     */
    public static Boolean getBoolean(String name)
    {
        return applicationConfig.getBoolean(name);
    }

    /**
     * @param name
     * @param defaultValue
     * @return
     */
    public static Byte getByte(String name, Byte defaultValue)
    {
        return applicationConfig.getByte(name, defaultValue);
    }

    /**
     * @param name
     * @return
     */
    public static Byte getByte(String name)
    {
        return applicationConfig.getByte(name);
    }
    
    /**
     * @param name
     * @param defaultValue
     * @return
     */
    public static Double getDouble(String name, Double defaultValue)
    {
        return applicationConfig.getDouble(name, defaultValue);
    }
    
    /**
     * @param name
     * @return
     */
    public static Double getDouble(String name)
    {
        return applicationConfig.getDouble(name);
    }
    
    /**
     * @param name
     * @param defaultValue
     * @return
     */
    public static Float getFloat(String name, Float defaultValue)
    {
        return applicationConfig.getFloat(name, defaultValue);
    }
    
    /**
     * @param name
     * @return
     */
    public static Float getFloat(String name)
    {
        return applicationConfig.getFloat(name);
    }
    
    /**
     * @param name
     * @param defaultValue
     * @return
     */
    public static Integer getInteger(String name, Integer defaultValue)
    {
        return applicationConfig.getInteger(name, defaultValue);
    }
    
    /**
     * @param name
     * @return
     */
    public static Integer getInteger(String name)
    {
        return applicationConfig.getInteger(name);
    }
    
    /**
     * @param name
     * @param defaultValue
     * @return
     */
    public static Long getLong(String name, Long defaultValue)
    {
        return applicationConfig.getLong(name, defaultValue);
    }
    
    /**
     * @param name
     * @return
     */
    public static Long getLong(String name)
    {
        return applicationConfig.getLong(name);
    }
    
    /**
     * @param name
     * @param defaultValue
     * @return
     */
    public static Short getShort(String name, Short defaultValue)
    {
        return applicationConfig.getShort(name, defaultValue);
    }
    
    /**
     * @param name
     * @return
     */
    public static Short getShort(String name)
    {
        return applicationConfig.getShort(name);
    }

    /**
     * @param name
     * @param format
     * @return
     */
    public static Date getDate(String name, String format)
    {
        return applicationConfig.getDate(name, format);
    }
    
    /**
     * @param name
     * @param format
     * @return
     */
    public static Timestamp getTimestamp(String name, String format)
    {
        return applicationConfig.getTimestamp(name, format);
    }
    
    /**
     * @param <T>
     * @param model
     * @param moduleName
     * @return
     */
    public static <T> T getModel(Class<T> model, String name)
    {
        return applicationConfig.getModel(model, name);
    }
}