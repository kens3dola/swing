package com.swing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.swing.config.JdbcConnection;
import com.swing.model.HistoryOrderModel;
import com.swing.model.Movie;
import com.swing.model.Schedule;
import com.swing.model.Showtime;

public class ScheduleDao {
	private Connection connection;
	
	private static final String SELECT_ALL_SCHEDULES = "select * from SCHEDULE";
	private static final String INSERT_SCHEDULE_SQL = "INSERT INTO SCHEDULE (shift) VALUES (?)";
	private static final String SELECT_SCHEDULE_BY_ID = "SELECT * FROM SCHEDULE WHERE id =?;";
	private static final String UPDATE_SCHEDULE_SQL = "UPDATE schedule SET shift=? WHERE id = ?;";
	
	public ScheduleDao(){
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

	public ResultSet getAllSchedule() {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(SELECT_ALL_SCHEDULES);
			ResultSet rs = preparedStatement.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Schedule> list(ResultSet rs) {
		List<Schedule> sches = new ArrayList<>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String shift = rs.getString("shift");
				sches.add(new Schedule(id, shift));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sches;
	}

}
