package com.swing.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.swing.model.Account;

class AccountDaoTest {

	private AccountDao accDao;
	public AccountDaoTest() {
			accDao = new AccountDao();		
	}

	@Test
	void testGetAllAccounts() {
		assertNotNull(accDao.getAllAccounts());
	}

	@Test
	void testGetAccountById() {
		assertNotNull(accDao.getAccountById(1));
	}

	@Test
	void testCreateAccount() {
		int t = accDao.createAccount("pham", "sang", "staff");
		assertTrue(t>0);
	}

	@Test
	void testValidate() {
		Account test = new Account();
		test.setUsername("pham");
		test.setPassword("sang");
		assertNotNull(accDao.validate(test));
	}

	@Test
	void testChangePassword() {
		assertTrue(accDao.changePassword(1, "admin")>0);
	}

	@Test
	void testUpdate() {
		assertTrue(accDao.update(new Account(1, "admin", "admin", "admin"))>0);
	}

	@Test
	void testGetList() {
		assertNotNull(accDao.getList());
	}

	@Test
	void testDelete() {
		assertTrue(accDao.delete(0)==0);
	}

}
