package com.xhsoft.framework.common.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBPoolUtil {
	private static String DRIVER = null;
	private static String URL = null;
	private static String USERNAME = null;
	private static String PASSWORD = null;
	
	private static int SUCCESS = 0;
	private static int FAILURE = -1;

	public synchronized static Connection getConn() throws ClassNotFoundException, SQLException{
		
		
		Connection conn = null;
		if(loadCfg("jdbc.properties")==SUCCESS){
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		return conn;
	}
	
	/**
	 * <p>Description:TODO</p>
	 * @param url
	 * @return 
	 * @author lijw
	 * @since 2013-3-5
	 */
	private static int loadCfg(String url){
		Properties prop = new Properties();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(url);
		try {
			prop.load(in);
			DRIVER = prop.get("jdbc.driverClassName").toString();
			URL = prop.get("jdbc.url").toString();
			USERNAME = prop.get("jdbc.username").toString();
			PASSWORD = prop.get("jdbc.password").toString();
		} catch (IOException e) {
			e.printStackTrace();
			return FAILURE;
		}
		return SUCCESS;
	}
	
	/**
	 * <p>Description:TODO</p>
	 * @param conn
	 * @param pstmt
	 * @param rs 
	 * @author lijw
	 * @since 2013-3-5
	 */
	protected static void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if(null!=rs){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				rs = null;
			}
		}
		if(null!=pstmt){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				pstmt = null;
			}
		}
		if(null!=conn){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				conn = null;
			}
		}
	}
	
	/**
	 * <p>Description:TODO</p>
	 * @param conn
	 * @param stmt
	 * @param rs 
	 * @author lijw
	 * @since 2013-3-5
	 */
	protected static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
		if(null!=rs){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				rs = null;
			}
		}
		if(null!=stmt){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				stmt = null;
			}
		}
		if(null!=conn){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				conn = null;
			}
		}
	}
	
	/**
	 * <p>Description:TODO</p>
	 * @param conn
	 * @param pstmt 
	 * @author lijw
	 * @since 2013-3-5
	 */
	protected static void closeAll(Connection conn, PreparedStatement pstmt) {
		if(null!=pstmt){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				pstmt = null;
			}
		}
		if(null!=conn){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				conn = null;
			}
		}
	}
	/**
	 * <p>Description:TODO</p>
	 * @param conn
	 * @param pstmt 
	 * @author lijw
	 * @since 2013-3-5
	 */
	protected static void closeAll(Connection conn, CallableStatement cs) {
		if(null!=cs){
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				cs = null;
			}
		}
		if(null!=conn){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				conn = null;
			}
		}
	}
}
