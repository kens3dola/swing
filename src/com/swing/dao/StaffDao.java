package com.swing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.swing.config.JdbcConnection;
import com.swing.model.Staff;

public class StaffDao {
	private Connection connection;

	public StaffDao() throws ClassNotFoundException, SQLException {
		connection = JdbcConnection.getConnection();
	}
	public Staff getStaff(int account_id) {
		String sql = "select * from staff where account_id="+ account_id;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return new Staff(
						rs.getInt(7),
						rs.getInt(3),
						rs.getString(5),
						rs.getString(4),
						rs.getInt(1),
						rs.getString(2),
						rs.getString(6)
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
