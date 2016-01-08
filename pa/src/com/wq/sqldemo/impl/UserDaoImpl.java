package com.wq.sqldemo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wq.sqldemo.dao.InfoDao;
import com.wq.sqldemo.model.Zhihuer;
import com.wq.sqldemo.utl.ConnectionUtil;

public class UserDaoImpl implements InfoDao {

	public boolean check(String username) {
		ConnectionUtil util = new ConnectionUtil();
		Connection conn = util.getConnection();
		String sql = "select id,name,password from UserTbl where name=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if (rs != null) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.ConnectionClose(conn);
		}
		return false;
	}

	public Zhihuer login(String username, String password) {
		ConnectionUtil util = new ConnectionUtil();
		Connection conn = util.getConnection();
		String sql = "select id,name,password from UserTbl where name=? and password=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				Zhihuer u = new Zhihuer();
				u.setId(id);
//				u.setName(username);
//				u.setPassword(password);
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.ConnectionClose(conn);
		}

		return null;
	}

	public void insert(Zhihuer u) {
		Connection conn = new ConnectionUtil().getConnection();
		String sql = "insert into zhihu(id,user_name) values(?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u.getId());
			pstmt.setString(2, u.getUser_name());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			new ConnectionUtil().ConnectionClose(conn);
		}
	}
	

}
