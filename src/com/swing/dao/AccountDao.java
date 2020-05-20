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

public class AccountDao {
	private Connection connection;

	public AccountDao(){
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

	public List<Account> getAllAccounts(){
		List<Account> list = new ArrayList<>();
		String sql = "select * from Account";
		Statement st;
		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				list.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Account getAccountById(int id){
		Account u = null;
		String sql = "select * from Account where id=" + id;
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				u = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
			return u;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int createAccount(String name, String password, String role){
		String sql = "insert into account(username, password, role) value(?,?,?)";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, password);
			st.setString(3, role);
			return st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public Account validate(Account a){
		Account u = null;
		String sql = "select * from Account where username='" + a.getUsername()+"' and password='"+a.getPassword()+"'";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				u = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
			return u;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int changePassword(Account acc, String newPass) {
		String sql = "update account set password=? where id=?";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, newPass);
			st.setInt(2, acc.getId());
			return st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int update(Account acc) {
		String sql = "update account set username=?, password=?, role='"+acc.getRole()+"' where id=?";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, acc.getUsername());
			st.setString(2, acc.getPassword());
			st.setInt(3, acc.getId());
			return st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public ResultSet getList() {
		String sql = "select * from Account";
		try {
			Statement st;
			st = connection.createStatement();
			return st.executeQuery(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	public int delete(int id) {
		String sql = "delete from account where id = "+id;
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			return st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
