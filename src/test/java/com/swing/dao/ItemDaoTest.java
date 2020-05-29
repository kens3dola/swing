package com.swing.dao;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.swing.model.HistoryOrderModel;

public class ItemDaoTest {

	private ItemDao iDao;
	public ItemDaoTest() {
		iDao = new ItemDao();
	}
	@Test
	public void testGetAllItem() {
		assertNotNull(iDao.getAllItem());
	}

	@Test
	public void testList() {
		assertNotNull(iDao.list(iDao.getAllItem()));
	}

	@Test
	public void testAdd() {
		assertTrue(iDao.add("coca", 1, 32)>0);
	}

	@Test
	public void testGetOrderItem() {
		HistoryOrderModel ho = new HistoryOrderModel();
		ho.setOrder_id(32);
		assertTrue(iDao.getOrderItem(ho));
	}

}
