package com.swing.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class OrderDaoTest {
	
	private OrderDao oDao;

	public OrderDaoTest() {
		oDao = new OrderDao();
	}
	@Test
	void testGetAllOrder() {
		assertNotNull(oDao.getAllOrder());
	}

	@Test
	void testList() {
		assertNotNull(oDao.list(oDao.getAllOrder()));
	}

	@Test
	void testAdd() {
		assertTrue(oDao.add(1, "pham van a", new Date())>0);
	}

	@Test
	void testGetHistoryOrder() {
		assertNotNull(oDao.getHistoryOrder());
	}

	@Test
	void testSetPrice() {
		assertTrue(oDao.setPrice(32, 9)>0);
	}

}
