package com.swing.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;


public class OrderDaoTest {
	
	private OrderDao oDao;

	public OrderDaoTest() {
		oDao = new OrderDao();
	}
	@Test
	public void testGetAllOrder() {
		assertNotNull(oDao.getAllOrder());
	}

	@Test
	public void testList() {
		assertNotNull(oDao.list(oDao.getAllOrder()));
	}

	@Test
	public void testAdd() {
		assertTrue(oDao.add(1, "pham van a", new Date())>0);
	}

	@Test
	public void testGetHistoryOrder() {
		assertNotNull(oDao.getHistoryOrder());
	}

	@Test
	public void testSetPrice() {
		assertTrue(oDao.setPrice(32, 9)>0);
	}

}
