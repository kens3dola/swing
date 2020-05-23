package com.swing.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.swing.model.HistoryOrderModel;

class ShowtimeDaoTest {

	private ShowtimeDao sDao;
	
	public ShowtimeDaoTest() {
		sDao = new ShowtimeDao();
	}
	@Test
	void testGetAllShowtime() {
		assertNotNull(sDao.getAllShowtime());
	}

	@Test
	void testList() {
		assertNotNull(sDao.list(sDao.getAllShowtime()));
	}

	@Test
	void testAdd() {
		assertTrue(sDao.add(1, 1, new Date(), "D")>0);
	}

	@Test
	void testDelete() {
		assertTrue(sDao.delete(0)==0);
	}

	@Test
	void testGetByMovieId() throws ParseException {
		String pattern = "yyyy-MM-dd";
		DateFormat df = new SimpleDateFormat(pattern);
		assertNotNull(sDao.getByMovieId(1, df.parse("2020-05-13")));
	}

	@Test
	void testGetOrderShowtime() {
		HistoryOrderModel ho = new HistoryOrderModel();
		ho.setOrder_id(32);
		assertNotNull(sDao.getOrderShowtime(ho, 1));
	}

}
