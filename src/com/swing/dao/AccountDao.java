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

	public AccountDao() throws ClassNotFoundException, SQLException {
		connection = JdbcConnection.getConnection();
	}

	public List<Account> getAllAccounts() throws SQLException {
		List<Account> list = new ArrayList<>();
		String sql = "select * from Account";
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			list.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
		return list;
	}

	public Account getAccountById(int id) throws SQLException {
		Account u = null;
		String sql = "select * from Account where id=" + id;
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			u = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
		}
		return u;
	}

	public void createAccount(Account a) throws SQLException {
		String sql = "insert into account value(?,?,?,?,?,?,?)";
		PreparedStatement st = connection.prepareStatement(sql);
		st.setInt(1, a.getId());
		st.setString(2, a.getUsername());
		st.setString(3, a.getPassword());
		st.setString(4, a.getRole());

		st.executeUpdate();
	}

	public Account validate(Account a) throws SQLException {
		Account u = null;
		String sql = "select * from Account where username='" + a.getUsername()+"' and password='"+a.getPassword()+"'";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			u = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
		}
		return u;
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
}
