package com.swing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.swing.config.JdbcConnection;
import com.swing.model.Item;

public class ItemDao {
	private Connection connection;

	public ItemDao(){
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
	
	public ResultSet getAllItem() {
		String sql = "select * from item";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			return st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Item> list(ResultSet rs){
		List<Item> l = new ArrayList<Item>();
		try {
			while(rs.next()) {
				try {
					l.add(new Item(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5)));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public void add(String name, int quantity, int order_id) {
		String sql = "insert into item(name,quantity,order_id) value(?,?,?)";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, name);
			st.setInt(2, quantity);
			st.setInt(3, order_id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
