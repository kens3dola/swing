package com.swing.dao;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ScheduleDaoTest {

	private ScheduleDao sDao;
	
	public ScheduleDaoTest() {
		sDao = new ScheduleDao();
	}
	@Test
	public void testGetAllSchedule() {
		assertNotNull(sDao.getAllSchedule());
	}

	@Test
	public void testList() {
		assertNotNull(sDao.getAllSchedule());
	}

}
