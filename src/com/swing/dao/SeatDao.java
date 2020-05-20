package com.swing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.swing.config.JdbcConnection;
import com.swing.model.Seat;

public class SeatDao {
	private Connection connection;

	public SeatDao(){
		try {
			connection = JdbcConnection.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet getAllSeat() {
		String sql = "select * from seat";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			return st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Seat> list(ResultSet rs) {
		List<Seat> l = new ArrayList<Seat>();
		try {
			while (rs.next()) {
				try {
					l.add(new Seat(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
}
