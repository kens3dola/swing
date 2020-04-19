package com.swing.service;

import java.sql.SQLException;
import java.util.List;

import com.swing.dao.UserDao;
import com.swing.model.User;

public class UserService {
	private UserDao userDao;
	
	public UserService() throws ClassNotFoundException, SQLException {
		userDao = new UserDao();
	}
	
	public List<User> getAllUsers() throws SQLException{
		return userDao.getAllUsers();
	}
	
	public void createUser(User user) throws SQLException {
		userDao.createUser(user);
	}
	
	public User getUserById(int id) throws SQLException {
		return userDao.getUserById(id);
	}
}
