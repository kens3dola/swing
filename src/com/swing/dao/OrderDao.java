package com.swing.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.swing.config.JdbcConnection;
import com.swing.model.Order;


public class OrderDao {
private Connection connection;
	
	public OrderDao() throws ClassNotFoundException, SQLException {
		connection = JdbcConnection.getConnection();
	}
	public void createOrder(Order a) throws SQLException {
		String sql = "insert into sqa.order(`staff_id`,`status`,`date`) values(?,?,?)";
		PreparedStatement st = connection.prepareStatement(sql);
		st.setInt(1, a.getStaff_id());
		st.setString(2,a.getStatus());
		st.setString(3, a.getDate());
		st.executeUpdate();
	}


}