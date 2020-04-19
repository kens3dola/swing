package com.swing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.swing.config.JdbcConnection;
import com.swing.model.User;

public class UserDao {
	private Connection connection;
	
	public UserDao() throws ClassNotFoundException, SQLException {
		connection = JdbcConnection.getConnection();
	}
	
	public List<User> getAllUsers() throws SQLException{
		List<User> list = new ArrayList<>();
		String sql = "select * from user";
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			list.add(new User(rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7)));
		}
		return list;
	}
	
	public User getUserById(int id) throws SQLException {
		User u = null;
		String sql = "select * from user where id="+id;
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			u = new User(rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7));
		}
		return u;
	}
	
	public void createUser(User user) throws SQLException {
		String sql = "insert into user value(?,?,?,?,?,?,?)";
		PreparedStatement st = connection.prepareStatement(sql);
		st.setInt(1, user.getId());
		st.setString(2, user.getName());
		st.setString(3, user.getMobile());
		st.setString(4, user.getUsername());
		st.setString(5, user.getPassword());
		st.setString(6, user.getRole());
		st.setString(7, user.getFavourite());
		
		st.executeUpdate();
	}
}
