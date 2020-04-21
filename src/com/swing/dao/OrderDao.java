package com.swing.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.swing.config.JdbcConnection;
import com.swing.model.Order;
import com.swing.model.Ticket;

public class OrderDao {
private Connection connection;
	
	public OrderDao() throws ClassNotFoundException, SQLException {
		connection = JdbcConnection.getConnection();
	}
	public void createOrder(Order a) throws SQLException {
		String sql = "insert into ticket value(?,?,?,?)";
		PreparedStatement st = connection.prepareStatement(sql);
		st.setInt(1, a.getId());
		st.setInt(2, a.getStaff_id());
		st.setString(3,a.getStatus());
		st.setDate(4, (Date) a.getDate());;
		
		st.executeUpdate();
	}


}
