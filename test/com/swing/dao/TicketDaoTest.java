package com.swing.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.swing.model.HistoryOrderModel;
import com.swing.model.Ticket;

class TicketDaoTest {
	
	private TicketDao tDao;
	
	public TicketDaoTest() {
		tDao = new TicketDao();
	}

	@Test
	void testGetAllTicket() {
		assertNotNull(tDao.getAllTicket());
	}

	@Test
	void testList() {
		assertNotNull(tDao.getAllTicket());
	}

	@Test
	void testGetTicketByShow() {
		assertNotNull(tDao.getTicketByShow(1));
	}

	@Test
	void testAdd() {
		Ticket t = new Ticket();
		t.setOrderId(32);
		t.setPrice(3);
		t.setSeatId(1);
		t.setShowtimeId(1);
		assertTrue(tDao.add(t)>0);
	}

	@Test
	void testGetOrderTicket() {
		HistoryOrderModel ho = new HistoryOrderModel();
		ho.setOrder_id(32);
		assertNotNull(tDao.getOrderTicket(ho));
	}

}
