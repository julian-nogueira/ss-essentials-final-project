package com.ss.jb.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

abstract class BaseDAO<T> {
	
	protected Connection conn = null;
	
	public BaseDAO(Connection conn) throws ClassNotFoundException, SQLException {
		this.conn = conn;
	}
	
	public void save(String sql, Object[] vals) throws SQLException, ClassNotFoundException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		Integer count = 1;
		for(Object o: vals) {
			pstmt.setObject(count, o);
			count++;
		}
		pstmt.executeUpdate();
	}
	
	public Integer saveReturnPrimaryKey(String sql, Object[] vals) throws SQLException, ClassNotFoundException {
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		Integer count = 1;
		for(Object o: vals) {
			pstmt.setObject(count, o);
			count++;
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		while(rs.next()) {
			return rs.getInt(1);
		}
		return null;
	}
	
	public List<T> read(String sql, Object[] vals) throws SQLException, ClassNotFoundException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		Integer count = 1;
		for(Object o: vals) {
			pstmt.setObject(count, o);
			count++;
		}
		ResultSet rs = pstmt.executeQuery();
		return extractData(rs);
	}
	
	abstract public List<T> extractData(ResultSet rs) throws SQLException;
}




