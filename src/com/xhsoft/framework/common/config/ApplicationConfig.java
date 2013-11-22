/*
 * $RCSfile: ApplicationConfig,v $$
 * $Revision: 1.0  $
 * $Date: 2011  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.config;

import java.util.HashMap;
import java.util.Map;


/**
 * <p>Title: ApplicationConfig</p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright (c) 2011</p> 
 * @author lizj
 * @version 1.0
 */
public class ApplicationConfig extends Getter
{
    private String name;
    private String description;
    
    private Map<String, ModuleConfig> moduleConfigs;
    
    /**
     */
    public ApplicationConfig()
    {
        moduleConfigs = new HashMap<String, ModuleConfig>();
    }
    
    /**
     * @param name
     */
    public ApplicationConfig(String name)
    {
        this.name = name;
        moduleConfigs = new HashMap<String, ModuleConfig>();
    }
    
    /**
     * @param name
     * @param description
     */
    public ApplicationConfig(String name, String description)
    {
        this.name = name;
        this.description = description;
        moduleConfigs = new HashMap<String, ModuleConfig>();
    }
    
    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @param name
     * @param module
     */
    protected void setModuleConfig(String name, ModuleConfig module)
    {
        moduleConfigs.put(name, module);
    }
    
    /**
     * @param name
     * @return
     */
    public ModuleConfig getModuleConfig(String name)
    {
        return moduleConfigs.get(name);
    }
    
    /**
     * @param name
     * @return
     * @author lizj
     */
    public ModuleConfig remove(String name)
    {
        return remove(getModuleConfig(name));
    }
    
    /**
     * @param moduleConfig
     * @return
     * @author lizj
     */
    public ModuleConfig remove(ModuleConfig moduleConfig)
    {
        if(moduleConfig != null){
            return moduleConfigs.remove(moduleConfig.getName());
        }
        
        return null;
    }
    
    /**
     * @return Map<String, ModuleConfig>
     * @author lizj
     */
    public Map<String, ModuleConfig> getModuleConfigs()
    {
        return moduleConfigs;
    }
    
    /**
     * @return String
     * @author lizj
     */
    @Override
    public String getValue()
    {
        return this.name;
    }

    @Override
    public String getValue(String name)
    {
        return getValue(name, null);
    }

    @Override
    public String getValue(String name, String defaultValue)
    {
        if(name != null){
            String[] a = name.replace('\\', '/').split("\\/");
            
            if(a.length >= 2){
                return getValue(a[0], a[1], null);
            }
        }
        
        return null;
    }
    
    /**
     * @param moduleName
     * @param name
     * @param defaultValue
     * @return
     */
    private String getValue(String moduleName, String name, String defaultValue)
    {
        String value = null;
        
        ModuleConfig moduleConfig = this.getModuleConfig(moduleName);
        
        if(moduleConfig != null){
            Parameter parameter = moduleConfig.getParameter(name);
            
            if(parameter != null){
                value = parameter.getValue();
            }
        }
        
        value = (value != null ? value : defaultValue);
        
        return value;
    }
    
    /**
     * @param <T>
     * @param model
     * @param applicationName
     * @param moduleName
     * @return
     */
    public <T> T getModel(Class<T> model, String moduleName)
    {
        ModuleConfig moduleConfig = this.getModuleConfig(moduleName);
        
        if(moduleConfig != null){
            return moduleConfig.getModel(model);
        }
        
        return null;
    }
    
    public String getMemeoryAddress()
    {
        return super.toString();
    }
    
    /**
     * @author lizj
     */
    @Override
	public String toString()
    {
        return toString("    ");
    }

    /**
     * @param indent
     * @return
     */
    public String toString(String indent)
    {
        StringBuilder buf = new StringBuilder();
        
        buf.append(indent).append("<Application name=\"").append((this.name != null ? this.name : "")).append("\"");
        buf.append(" description=\"").append((this.description != null ? this.description : "")).append("\">\r\n");
        
        for(Map.Entry<String, ModuleConfig> entry : this.getModuleConfigs().entrySet())
        {
            ModuleConfig moduleConfig = entry.getValue();
            
            buf.append(moduleConfig.toString(indent));
        }
        
        buf.append(indent).append("</Application>\r\n");
        
        return buf.toString();
    }
}

