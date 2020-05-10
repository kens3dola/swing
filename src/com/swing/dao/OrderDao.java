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
import com.swing.model.HistoryOrderModel;
import com.swing.model.Order;

public class OrderDao {
	private Connection connection;

	public OrderDao() {
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

	public ResultSet getAllOrder() {
		String sql = "select * from sqa.order";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			return st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Order> list(ResultSet rs) {
		List<Order> l = new ArrayList<Order>();
		try {
			while (rs.next()) {
				try {
					l.add(new Order(rs.getInt("id"), rs.getInt("staff_id"), rs.getDate("date"), rs.getString("customer")));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public int add(int staff_id, String name, Date date) {
		String pattern = "yyyy-MM-dd";
		DateFormat df = new SimpleDateFormat(pattern);
		String sql = "insert into sqa.order(staff_id,customer,date) value(?,?,?)";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, staff_id);
			st.setString(2, name);
			st.setString(3, df.format(date));
			st.executeUpdate();
			return getOrderByDate(new java.sql.Date(date.getTime()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getOrderByDate(java.sql.Date date) {
		String query = "select * from sqa.order where date=?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setDate(1, date);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				try {
					return rs.getInt(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 0;
	}
	
	public List<HistoryOrderModel> getHistoryOrder() {
		List<HistoryOrderModel> list = new ArrayList<HistoryOrderModel>();
		String sql = "select * from sqa.order";
			PreparedStatement st;
			try {
				st = connection.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					try {
						HistoryOrderModel ho = new HistoryOrderModel();
						ho.setOrder_id(rs.getInt(1));
						ho.setStaff_id(rs.getInt(2));
						ho.setCustomer_name(rs.getString(3));
						ho.setOrder_date(rs.getDate(4));
						ho.setPrice(rs.getInt(5));
						new ItemDao().getOrderItem(ho);
						new TicketDao().getOrderTicket(ho);
						list.add(ho);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}
	
	public int setPrice( int id, int price) {
		String sql = "update sqa.order set price = ? where id = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, price);
			ps.setInt(2, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
