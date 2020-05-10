package com.swing.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.swing.model.HistoryOrderModel;

class ItemDaoTest {

	private ItemDao iDao;
	public ItemDaoTest() {
		iDao = new ItemDao();
	}
	@Test
	void testGetAllItem() {
		assertNotNull(iDao.getAllItem());
	}

	@Test
	void testList() {
		assertNotNull(iDao.list(iDao.getAllItem()));
	}

	@Test
	void testAdd() {
		assertTrue(iDao.add("coca", 1, 32)>0);
	}

	@Test
	void testGetOrderItem() {
		HistoryOrderModel ho = new HistoryOrderModel();
		ho.setOrder_id(32);
		assertTrue(iDao.getOrderItem(ho));
	}

}
