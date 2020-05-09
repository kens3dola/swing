package com.swing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.swing.config.JdbcConnection;
import com.swing.model.HistoryOrderModel;
import com.swing.model.Ticket;

public class TicketDao {
	private Connection connection;

	public TicketDao(){
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
	
	public ResultSet getAllTicket() {
		String sql = "select * from Ticket";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			return st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Ticket> list(ResultSet rs){
		List<Ticket> l = new ArrayList<Ticket>();
		try {
			while(rs.next()) {
				try {
					Ticket t = new Ticket(rs.getInt(1),rs.getInt(2),rs.getInt(4),rs.getInt(3));
					l.add(t);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	public List<Ticket> getTicketByShow(int show_id){
		String sql = "select * from Ticket where showtime_id="+show_id;
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			List<Ticket> l =  new ArrayList<Ticket>();
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				try {
					Ticket t = new Ticket();
					t.setId(rs.getInt(1));
					t.setOrderId(rs.getInt(2));
					t.setSeatId(rs.getInt(4));
					t.setShowtimeId(rs.getInt(3));
					l.add(t);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return l;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void add(Ticket tic) {
		String sql = "insert into ticket(order_id,showtime_id,seat_id) value(?,?,?)";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, tic.getOrderId());
			st.setInt(2, tic.getShowtimeId());
			st.setInt(3, tic.getSeatId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getOrderTicket(HistoryOrderModel ho) {
		String sql = "select * from ticket where order_id=" + ho.getOrder_id();
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String seats = (ho.getSeats()==null)?"":ho.getSeats();
				seats +=(" "+rs.getInt(4));
				ho.setSeats(seats);
				new ShowtimeDao().getOrderShowtime(ho, rs.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
