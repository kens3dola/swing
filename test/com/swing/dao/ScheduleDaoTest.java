package com.swing.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScheduleDaoTest {

	private ScheduleDao sDao;
	
	public ScheduleDaoTest() {
		sDao = new ScheduleDao();
	}
	@Test
	void testGetAllSchedule() {
		assertNotNull(sDao.getAllSchedule());
	}

	@Test
	void testList() {
		assertNotNull(sDao.getAllSchedule());
	}

}
