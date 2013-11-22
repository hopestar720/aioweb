/*
 * $RCSfile: JDBCPoolAppender,v $$
 * $Revision: 1.0  $
 * $Date: 2010-12-09  $
 *
 * Copyright (C) 2011 GyTech, Inc. All rights reserved.
 *
 * This software is the proprietary information of GyTech, Inc.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.log;

//import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.spi.LoggingEvent;

import com.xhsoft.framework.base.service.AppServiceHelper;

/**
 * <p>Title:JDBCPoolAppender</p>
 * <p>Description:JDBCPoolAppender</p>
 * <p>Copyright:Copyright (C) 2011</p>
 * @author Wanggq
 * @date 2010-12-09
 */
public class JDBCPoolAppender extends JDBCAppender 
{
	DataSource ds = null;
	
	/**
	 * <p>Description:初始化</p>
	 * @param sql	 
	 * @return void
	 * @author wgq
	 * @version 1.0
	 */
	@Override
	protected void execute(String sql) 
	{
		Statement stmt = null;
		
		try {
			
			if(connection==null){
				ds = (DataSource) AppServiceHelper.findBean("dataSource");
				connection=ds.getConnection();
			}
			
			if (connection == null)
				return;
			// System.out.println(sql + ":" + this.connection);
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {

		} finally {
			
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * <p>Description:getLogStatement</p>
	 * @param event	 
	 * @return String
	 * @author wgq
	 * @version 1.0
	 */
	@Override
	public String getLogStatement(LoggingEvent event) 
	{
		StringBuffer sbuf = new StringBuffer();
		sbuf.append(layout.format(event));
		
		if (layout.ignoresThrowable()) {
			
			sbuf.delete(sbuf.length() - 2, sbuf.length());
			String[] s = event.getThrowableStrRep();
			
			if (s != null) {
				for (int j = 0; j < 10; j++) 
				{
					sbuf.append("\r\n");
					sbuf.append(s[j]);
				}
			}
			
			sbuf.append("')");
		}
		
		return sbuf.toString();
	}

}
