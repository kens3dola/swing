package com.swing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.swing.config.JdbcConnection;
import com.swing.model.Account;
import com.swing.model.Item;

public class ItemDao {
	private Connection conn;
	
	private static final String SELECTALL = "select *from Item";
	private static final String SELECTBYID = "select * from Item where id=?";
	private static final String INSERT = "insert into Item(name, quatity, price) values(?, ?,?)";
	private static final String UPDATEITEM = "update Item set name=?, quantity=?, price=?";
	private static final String DELETEITEM = "delete from Item where id=?";
	
	public ItemDao() throws ClassNotFoundException, SQLException {
		conn = JdbcConnection.getConnection();
	}
	public List<Item> getAllItems() throws SQLException{
		List<Item> list = new ArrayList<>();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(SELECTALL);
		while(rs.next()) {
			list.add(new Item(rs.getInt(1), rs.getName(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));
		}
		return list;
	}
	public Item getItemById(int id) throws SQLException{
		Item item = null;
		PreparedStatement st = conn.prepareStatement(SELECTBYID);
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			item = new Item(rs.getInt(1), rs.getName(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
		}
		return item;
	}
	public void insertItem(Item item) throws SQLException{
		PreparedStatement st = conn.prepareStatement(INSERT);
		st.setString(1, item.getName());
		st.setInt(2, item.getQuantity());
		st.setInt(3, item.getPrice());
		st.executeUpdate();
	}
	public boolean updateItem(Item item) throws SQLException{
		boolean rowDeleted;
		try(Connection conn = JdbcConnection.getConnection(); 
			PreparedStatement prep = conn.prepareStatement(UPDATEITEM)){
			prep.setString(1, item.getName());
			prep.setInt(2, item.getQuantity());
			prep.setInt(3, item.getPrice());
			prep.setInt(4, item.getId());
			rowDeleted = prep.executeUpdate() >0;
		}
		return rowDeleted;
	}
	public boolean deleteItem(int id) throws SQLException, ClassNotFoundException {
		boolean rowDeleted;
		try(Connection conn = JdbcConnection.getConnection(); 
			PreparedStatement prep = conn.prepareStatement(DELETEITEM)){
			prep.setInt(1, id);
			rowDeleted = prep.executeUpdate() >0;
		}
		return rowDeleted;
	}
	
}