package com.swing.dao;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.swing.model.HistoryOrderModel;
import com.swing.model.Ticket;

public class TicketDaoTest {
	
	private TicketDao tDao;
	
	public TicketDaoTest() {
		tDao = new TicketDao();
	}

	@Test
	public void testGetAllTicket() {
		assertNotNull(tDao.getAllTicket());
	}

	@Test
	public void testList() {
		assertNotNull(tDao.getAllTicket());
	}

	@Test
	public void testGetTicketByShow() {
		assertNotNull(tDao.getTicketByShow(1));
	}

	@Test
	public void testAdd() {
		Ticket t = new Ticket();
		t.setOrderId(32);
		t.setPrice(3);
		t.setSeatId(1);
		t.setShowtimeId(1);
		assertTrue(tDao.add(t)>0);
	}

	@Test
	public void testGetOrderTicket() {
		HistoryOrderModel ho = new HistoryOrderModel();
		ho.setOrder_id(32);
		assertNotNull(tDao.getOrderTicket(ho));
	}

}
