package com.ss.jb.library.dao;

import com.ss.jb.library.service.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

abstract class BaseDAO<T> {
	
	protected Connection conn = null;
	protected Util util = null;
	
	public BaseDAO() throws ClassNotFoundException, SQLException {
		util = new Util();
	}
	
	public void save(String sql, Object[] vals) throws SQLException, ClassNotFoundException {
		conn = util.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		Integer count = 1;
		for(Object o: vals) {
			pstmt.setObject(count, o);
			count++;
		}
		pstmt.executeUpdate();
		conn.commit();
		conn.close();
	}
	
	public List<T> read(String sql, Object[] vals) throws SQLException, ClassNotFoundException {
		conn = util.getConnection();
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




