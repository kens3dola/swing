package com.swing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.swing.config.JdbcConnection;
import com.swing.model.Schedule;
import com.swing.model.Showtime;

public class ShowtimeDao {
	private Connection connection;

	public ShowtimeDao(){
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
	
	public ResultSet getAllShowtime() {
		String sql = "select * from Showtime";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			return st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Showtime> list(ResultSet rs){
		List<Showtime> l = new ArrayList<Showtime>();
		try {
			while(rs.next()) {
				try {
					l.add(new Showtime(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4),rs.getString(5)));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public int add(int movieId, int scheduleId, Date date, String room) {
		String pattern = "yyyy-MM-dd";
		DateFormat df = new SimpleDateFormat(pattern);
		String sql = "insert into showtime(schedule_id,movie_id,date, room) value(?,?,?,?)";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, scheduleId);
			st.setInt(2, movieId);
			st.setString(3, df.format(date));
			st.setString(4, room);
			return st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public int delete(int id) {
		String sql = "delete from showtime where id = "+id;

		try {
			PreparedStatement st = connection.prepareStatement(sql);
			return st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public List<Showtime> getByMovieId(int m_id, Date date) {
		String pattern = "yyyy-MM-dd";
		DateFormat df = new SimpleDateFormat(pattern);
		String sql = "select * from showtime where movie_id = ? and date=?";
		try {
			List<Showtime> list = new ArrayList<Showtime>();
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, m_id);
			st.setString(2, df.format(date));
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				list.add(new Showtime(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4),rs.getString(5)));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
<<<<<<< Updated upstream
=======

	public boolean getOrderShowtime(HistoryOrderModel ho, int id) {
		String sql = "select * from showtime where id=" + id;
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				ho.setRoom(rs.getString(5));
				ho.setShow_date(rs.getDate(4));
				ho.setShowtime(new Schedule(rs.getInt(2), ""+rs.getInt(2)).toString());
				new MovieDao().getOrderMovie(ho,rs.getInt(3));
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
>>>>>>> Stashed changes
}
