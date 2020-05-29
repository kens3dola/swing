package com.swing.dao;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.swing.model.HistoryOrderModel;

public class ShowtimeDaoTest {

	private ShowtimeDao sDao;
	
	public ShowtimeDaoTest() {
		sDao = new ShowtimeDao();
	}
	@Test
	public void testGetAllShowtime() {
		assertNotNull(sDao.getAllShowtime());
	}

	@Test
	public void testList() {
		assertNotNull(sDao.list(sDao.getAllShowtime()));
	}

	@Test
	public void testAdd() {
		assertTrue(sDao.add(1, 1, new Date(), "D")>0);
	}

	@Test
	public void testDelete() {
		assertTrue(sDao.delete(0)==0);
	}

	@Test
	public void testGetByMovieId() throws ParseException {
		String pattern = "yyyy-MM-dd";
		DateFormat df = new SimpleDateFormat(pattern);
		assertNotNull(sDao.getByMovieId(1, df.parse("2020-05-13")));
	}

	@Test
	public void testGetOrderShowtime() {
		HistoryOrderModel ho = new HistoryOrderModel();
		ho.setOrder_id(32);
		assertNotNull(sDao.getOrderShowtime(ho, 1));
	}

}
