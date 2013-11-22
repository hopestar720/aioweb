/*
 * $RCSfile: Parameter,v $$
 * $Revision: 1.0  $
 * $Date: 2011  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.config;


/**
 * <p>Title: Parameter</p> 
 * <p>Description:Parameter </p> 
 * <p>Copyright: Copyright (c) 2011</p> 
 * @author lizj
 * @version 1.0
 */
public class Parameter extends Getter
{
    private String name;
    private String value;
    private String description;
    
    /**
     * 
     */
    public Parameter()
    {
    }
    
    public Parameter(String name)
    {
        this.name = name;
    }
    
    /**
     * @param name
     * @param value
     */
    public Parameter(String name, String value)
    {
        this.name = name;
        this.value = value;
    }
    
    /**
     * @param name
     * @param value
     * @param description
     */
    public Parameter(String name, String value, String description)
    {
        this.name = name;
        this.value = value;
        this.description = description;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    @Override
	public String getValue()
    {
        return value;
    }
    
    public void setValue(String value)
    {
        this.value = value;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String getValue(String name)
    {
        return this.value;
    }
    
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
        buf.append(indent).append("<Parameter");
        
        boolean b = false;
        
        if(this.description != null && this.description.trim().length() > 0)
        {
            b = true;
        }
        
        if(b == true)
        {
            buf.append(">\r\n");
            buf.append(indent).append(indent).append(indent).append(indent);
            buf.append("<Name>").append((this.name != null ? this.name : "")).append("</Name>\r\n");

            buf.append(indent).append(indent).append(indent).append(indent);
            buf.append("<Value>").append((this.value != null ? this.value : "")).append("</Value>\r\n");

            buf.append(indent).append(indent).append(indent).append(indent);
            buf.append("<Description>").append((this.description != null ? this.description : "")).append("</Description>\r\n");
            
            buf.append(indent).append(indent);
            buf.append(indent).append("</Parameter>\r\n");
        }
        else
        {
            buf.append(" name=\"").append((this.name != null ? this.name : "")).append("\">");
            buf.append((this.value != null ? this.value : ""));
            buf.append("</Parameter>\r\n");
        }
        
        return buf.toString();
    }
}
