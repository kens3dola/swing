package com.swing.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.swing.model.Staff;

class StaffDaoTest {

	private StaffDao sDao;
	
	public StaffDaoTest() {
		sDao = new StaffDao();
	}
	@Test
	void testGetStaff() {
		assertNotNull(sDao.getStaff(2));
	}

	@Test
	void testUpdate() {
		Staff s = new Staff(2,17,"spq@gmail.com","male",1,"pham","088888888");
		assertTrue(sDao.update(s)>0);
	}

}
