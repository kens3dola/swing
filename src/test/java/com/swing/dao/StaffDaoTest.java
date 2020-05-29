package com.swing.dao;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.swing.model.Staff;

public class StaffDaoTest {

	private StaffDao sDao;
	
	public StaffDaoTest() {
		sDao = new StaffDao();
	}
	@Test
	public void testGetStaff() {
		assertNotNull(sDao.getStaff(2));
	}

	@Test
	public void testUpdate() {
		Staff s = new Staff(2,17,"spq@gmail.com","male",1,"pham","088888888");
		assertTrue(sDao.update(s)>0);
	}

}
