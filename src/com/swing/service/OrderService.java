package com.swing.service;

import java.sql.SQLException;

import com.swing.dao.OrderDao;

import com.swing.model.Order;

public class OrderService {
private OrderDao OrderDao;
	
	public OrderService() throws ClassNotFoundException, SQLException {
		OrderDao = new OrderDao();
	}	
	public void createOrder(Order Order) throws SQLException {
		OrderDao.createOrder(Order);
	}

}
