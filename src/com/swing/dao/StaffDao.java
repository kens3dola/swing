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
<<<<<<< Updated upstream
=======
	public int update(Staff st) {
		String sql = "update staff set name=?, email=?, phone=?, gender=?, age=? where id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, st.getName());
			ps.setString(2, st.getEmail());
			ps.setString(3, st.getPhone());
			ps.setString(4, st.getGender());
			ps.setInt(5, st.getAge());
			ps.setInt(6, st.getId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
>>>>>>> Stashed changes
}
