/*
 * $RCSfile: ModuleConfig,v $$
 * $Revision: 1.0  $
 * $Date: 2011  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.config;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>Title: ModuleConfig</p> 
 * <p>Description: ModuleConfig</p> 
 * <p>Copyright: Copyright (c) 2011</p> 
 * @author lizj
 * @version 1.0
 */
public class ModuleConfig extends Getter
{
    private String name;
    private String description;
    
    private Map<String, Parameter> parameters;
    
    public ModuleConfig()
    {
        parameters = new HashMap<String, Parameter>();
    }
    
    /**
     * @param name
     */
    public ModuleConfig(String name)
    {
        this.name = name;
        parameters = new HashMap<String, Parameter>();
    }
    
    /**
     * @param name
     * @param description
     */
    public ModuleConfig(String name, String description)
    {
        this.name = name;
        this.description = description;
        parameters = new HashMap<String, Parameter>();
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
     * @param parameter
     */
    protected Parameter setParameter(String name, Parameter parameter)
    {
        parameters.put(name, parameter);
        
        return parameter;
    }
    
    /**
     * @param name
     * @return
     */
    public Parameter getParameter(String name)
    {
        return parameters.get(name);
    }
    
    /**
     * @return
     */
    public Map<String, Parameter> getParameters()
    {
        return parameters;
    }
    
    /**
     * @param name
     * @return
     * @author lizj
     */
    public Parameter remove(String name)
    {
        if(name != null)
        {
            return this.parameters.remove(name);
        }
        
        return null;
    }
    
    /**
     * @param parameter
     * @return
     * @author lizj
     */
    public Parameter remove(Parameter parameter)
    {
        if(parameter != null)
        {
            return this.parameters.remove(parameter.getName());
        }
        
        return null;
    }

    @Override
    public String getValue()
    {
        return this.name;
    }

    @Override
    public String getValue(String name)
    {
        Parameter parameter = this.getParameter(name);
        
        return (parameter != null ? parameter.getValue() : null);
    }
    
    /**
     * @param <T>
     * @param model
     * @return
     * @author lizj
     */
    public <T> T getModel(Class<T> model)
    {
        T instance = null;
        
        try
        {
            instance = model.newInstance();
        }
        catch(InstantiationException e)
        {
            e.printStackTrace();
        }
        catch(IllegalAccessException e)
        {
            e.printStackTrace();
        }
        
        if(instance != null)
        {
            Method[] methods = model.getMethods();
            
            Object value = null;
            
            for(int i = 0; i < methods.length; i++)
            {
                String name = methods[i].getName();
                
                Class<?>[] parameterTypes = methods[i].getParameterTypes();
                
                if(name.startsWith("set") && parameterTypes.length == 1)
                {
                    name = name.substring(3);
                    
                    value = getValue(parameterTypes[0], name);
                    
                    if(value != null)
                    {
                        try
                        {
                            methods[i].invoke(instance, new Object[]{value});
                        }
                        catch(Exception e)
                        {
                        }
                    }
                }
            }
        }
        
        return instance;
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
        
        buf.append(indent).append(indent);
        buf.append("<Module name=\"").append((this.name != null ? this.name : "")).append("\"");
        buf.append(" description=\"").append((this.description != null ? this.description : "")).append("\">\r\n");
        
        for(Map.Entry<String, Parameter> entry : this.getParameters().entrySet())
        {
            Parameter parameter = entry.getValue();
            
            buf.append(parameter.toString(indent));
        }
        
        buf.append(indent).append(indent);
        buf.append("</Module>\r\n");
        
        return buf.toString();
    }
}

