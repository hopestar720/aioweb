/*
 * $RCSfile: PathUtil,v $$
 * $Revision: 1.0  $
 * $Date: 2011  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.utils;

/**
 * <p>Title:PathUtile</p>
 * <p>Description:PathUtil</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @since 2011
 */
public class PathUtil 
{
	
	/**
	 * <p>Description:getWebClassesPath</p>
	 * @return String
	 */
	public String getWebClassesPath() 
	{
		   String path = getClass().getProtectionDomain().getCodeSource()
		     .getLocation().getPath();
		   return path;
		  
		}

	/**
	 * <p>Description:getWebInfPath</p>
	 * @return String
	 * @exception IllegalAccessException
	 */
		public String getWebInfPath() throws IllegalAccessException
		{
		   String path = getWebClassesPath();
		   
		   if (path.indexOf("WEB-INF") > 0) {
		    path = path.substring(0, path.indexOf("WEB-INF")+8);
		   } else {
		    throw new IllegalAccessException("路径获取错误");
		   }
		   
		   return path;
		}

		/**
		 * <p>Description:getWebRoot</p>
		 * @return String
		 * @exception IllegalAccessException
		 */
		public String getWebRoot() throws IllegalAccessException
		{
		   String path = getWebClassesPath();
		   
		   if (path.indexOf("WEB-INF") > 0) {
		    path = path.substring(0, path.indexOf("WEB-INF/classes"));
		   } else {
		    throw new IllegalAccessException("路径获取错误");
		   }
		   
		   return path;
		}
		

}
