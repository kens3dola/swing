package com.swing.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.swing.model.HistoryOrderModel;
import com.swing.model.Movie;

class MovieDaoTest {
	
	private MovieDao mDao;
	
	public MovieDaoTest() {
		mDao = new MovieDao();
	}

	@Test
	void testGetAllMovies() {
		assertNotNull(mDao.getAllMovies());
	}

	@Test
	void testAdd() {
		assertTrue(mDao.add("Rise the red lantern", "risetheredlantern.jpg", 10)>0);
	}

	@Test
	void testGetMovieById() {
		assertNotNull(mDao.getMovieById(1));
	}

	@Test
	void testUpdateMovie() {
		Movie m = new Movie();
		m.setId(1);
		m.setName("Balika Vadhu");
		m.setImage("BalikaVadhu.png");
		m.setPrice(5);
		assertTrue(mDao.updateMovie(m)>0);
	}

	@Test
	void testList() {
		assertNotNull(mDao.list(mDao.getAllMovies()));
	}

	@Test
	void testDelete() {
		assertTrue(mDao.delete(0)==0);
	}

	@Test
	void testGetOrderMovie() {
		HistoryOrderModel ho = new HistoryOrderModel();
		ho.setOrder_id(32);
		assertNotNull(mDao.getOrderMovie(ho, 1));
	}

}
