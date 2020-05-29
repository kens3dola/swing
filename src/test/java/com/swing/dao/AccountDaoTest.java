package com.swing.dao;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.swing.model.Account;

public class AccountDaoTest {

	private AccountDao accDao;
	public AccountDaoTest() {
			accDao = new AccountDao();		
	}

	@Test
	public void testGetAllAccounts() {
		assertNotNull(accDao.getAllAccounts());
	}

	@Test
	public void testGetAccountById() {
		assertNotNull(accDao.getAccountById(1));
	}

	@Test
	public void testCreateAccount() {
		int t = accDao.createAccount("pham", "sang", "staff");
		assertTrue(t>0);
	}

	@Test
	public void testValidate() {
		Account test = new Account();
		test.setUsername("pham");
		test.setPassword("sang");
		assertNotNull(accDao.validate(test));
	}

	@Test
	public void testChangePassword() {
		assertTrue(accDao.changePassword(1, "admin")>0);
	}

	@Test
	public void testUpdate() {
		assertTrue(accDao.update(new Account(1, "admin", "admin", "admin"))>0);
	}

	@Test
	public void testGetList() {
		assertNotNull(accDao.getList());
	}

	@Test
	public void testDelete() {
		assertTrue(accDao.delete(0)==0);
	}

}
