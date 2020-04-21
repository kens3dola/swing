package com.swing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.swing.config.JdbcConnection;
import com.swing.model.Ticket;

public class TicketDao {
private Connection connection;
	
	public TicketDao() throws ClassNotFoundException, SQLException {
		connection = JdbcConnection.getConnection();
	}
	public void createTicket(Ticket a) throws SQLException {
		String sql = "insert into ticket value(?,?,?,?)";
		PreparedStatement st = connection.prepareStatement(sql);
		st.setInt(1, a.getId());
		st.setInt(2, a.getOrder_id());
		st.setInt(3,a.getShowtime_id());
		st.setInt(4, a.getSeat_id());
		
		st.executeUpdate();
	}

}
