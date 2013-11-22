package com.xhsoft.framework.common.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;

public class DBHelper {

	/**查询**/
	//无条件查询
	public ResultSet excuteQuery(String sql){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBPoolUtil.getConn();
			stmt = conn.createStatement();
			stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			DBPoolUtil.closeAll(conn,stmt,rs);
		}
		return rs;
	}
	
	//按条件查询
	public ResultSet excuteQuery(String sql,List<Object> param){
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = DBPoolUtil.getConn();
			pstmt = conn.prepareStatement(sql);
			for(int i=0;i<param.size();i++){
				pstmt.setObject(i+1, param.get(i));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBPoolUtil.closeAll(conn,pstmt,rs);
		}
		return rs;
	}
	
	/**非查询操作**/
	public int excuteNotQuery(String sql,List<Object> lsParam){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = DBPoolUtil.getConn();
			pstmt = conn.prepareStatement(sql);
			for(int i=0;i<lsParam.size();i++){
				pstmt.setObject(i+1, lsParam.get(i));
			}
			pstmt.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			result = -1;
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally{
			DBPoolUtil.closeAll(conn, pstmt);
		}
		return result;
	}
	
	
	/**批量操作**/
	public int excuteBatch(String sql,Collection<List<Object>> collection){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = DBPoolUtil.getConn();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			for(List<Object> list : collection){
				for(int i=0;i<list.size();i++){
					pstmt.setObject(i+1, list.get(i));
				}
				pstmt.addBatch();
				result++;
			}
			int []batch = pstmt.executeBatch();
			System.out.println(batch);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBPoolUtil.closeAll(conn,pstmt);
		}
		return result;
	}
	
	/**执行存储过程**/
	//无参数
	public void excuteProcedure(String sql){
		Connection conn = null;
		CallableStatement cs = null;
		
		try {
			conn = DBPoolUtil.getConn();
			cs = conn.prepareCall(sql);
			cs.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBPoolUtil.closeAll(conn, cs);
		}
	}
	//仅有输入参数
	public void excutePorcedure(String sql,List<Object> lsIn){
		Connection conn = null;
		CallableStatement cs = null;
		
		try {
			conn = DBPoolUtil.getConn();
			cs = conn.prepareCall(sql);
			for(int i=0;i<lsIn.size();i++){
				cs.setObject(i+1, lsIn.get(i));
			}
			cs.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBPoolUtil.closeAll(conn, cs);
		}
	}
	//输入参数和输入参数
	public void excutePorcedure(String sql,List<Object> lsIn,List<Object> lsOut){
		Connection conn = null;
		CallableStatement cs = null;
		
		try {
			conn = DBPoolUtil.getConn();
			cs = conn.prepareCall(sql);
			for(int i=0;i<lsIn.size();i++){
				cs.setObject(i+1, lsIn.get(i));
			}
			for(int i=0;i<lsOut.size();i++){
				cs.registerOutParameter(i+1, Type.INT);
			}
			cs.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBPoolUtil.closeAll(conn, cs);
		}
	}
	
	public List<Map<String,Object>> fillData(ResultSet rs){
		if(rs == null){
			return null;
		}
		List<Map<String,Object>> lsData = new ArrayList<Map<String,Object>>();
		try {
			ResultSetMetaData rsMeta = rs.getMetaData();
			int cols = rsMeta.getColumnCount();
			while(rs.next()){
				for(int i=0;i<cols;i++){
					Object obj;
					switch(rsMeta.getColumnType(i)){
					case Types.ARRAY:
						obj = rs.getArray(i);
						break;
					case Types.BIGINT:
						obj = rs.getInt(i);
						break;
					case Types.BINARY:
						obj = rs.getBinaryStream(i);
						break;
					case Types.BIT:
						obj = rs.getBytes(i);
						break;
					case Types.BLOB:
						obj = rs.getBlob(i);
						break;
					case Types.BOOLEAN:
						obj = rs.getBoolean(i);
						break;
					case Types.CHAR:
						obj = rs.getCharacterStream(i);
						break;
					case Types.CLOB:
						obj = rs.getClob(i);
						break;
					case Types.DATE:
						obj = rs.getDate(i);
						break;
					case Types.TIMESTAMP:
						obj = rs.getTimestamp(i);
						break;
					case Types.LONGVARCHAR:
						obj = rs.getCharacterStream(i);
						break;
					case Types.LONGNVARCHAR:
						obj = rs.getNCharacterStream(i);
						break;
					}
					
					//未完待续
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lsData;
	}
	
	public static void getDBInfo(){
		Connection conn = null;
		try {
			conn = DBPoolUtil.getConn();
			DatabaseMetaData dbMeta = conn.getMetaData();
			dbMeta.getDatabaseProductName();
			dbMeta.getDatabaseProductVersion();
			dbMeta.getDriverName();
			dbMeta.getDriverVersion();
			dbMeta.supportsBatchUpdates();
			dbMeta.supportsResultSetType(ResultSet.CONCUR_READ_ONLY);
			dbMeta.getTables(null, null, null, null);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//test
	public static void main(String args[]){
		getDBInfo();
	}
}
