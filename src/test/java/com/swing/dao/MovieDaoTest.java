package com.swing.dao;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.swing.model.HistoryOrderModel;
import com.swing.model.Movie;

public class MovieDaoTest {
	
	private MovieDao mDao;
	
	public MovieDaoTest() {
		mDao = new MovieDao();
	}

	@Test
	public void testGetAllMovies() {
		assertNotNull(mDao.getAllMovies());
	}

	@Test
	public void testAdd() {
		assertTrue(mDao.add("Rise the red lantern", "risetheredlantern.jpg", 10)>0);
	}

	@Test
	public void testGetMovieById() {
		assertNotNull(mDao.getMovieById(1));
	}

	@Test
	public void testUpdateMovie() {
		Movie m = new Movie();
		m.setId(1);
		m.setName("Balika Vadhu");
		m.setImage("BalikaVadhu.png");
		m.setPrice(5);
		assertTrue(mDao.updateMovie(m)>0);
	}

	@Test
	public void testList() {
		assertNotNull(mDao.list(mDao.getAllMovies()));
	}

	@Test
	public void testDelete() {
		assertTrue(mDao.delete(0)==0);
	}

	@Test
	public void testGetOrderMovie() {
		HistoryOrderModel ho = new HistoryOrderModel();
		ho.setOrder_id(32);
		assertNotNull(mDao.getOrderMovie(ho, 1));
	}

}
