package com.swing.service;

import java.sql.SQLException;
import java.util.List;

import com.swing.dao.AccountDao;
import com.swing.model.Account;

public class AccountService {
	private AccountDao AccountDao;
	
	public AccountService() throws ClassNotFoundException, SQLException {
		AccountDao = new AccountDao();
	}
	
	public List<Account> getAllAccounts() throws SQLException{
		return AccountDao.getAllAccounts();
	}	
	public void createAccount(Account Account) throws SQLException {
		AccountDao.createAccount(Account);
	}
	
	public Account getAccountById(int id) throws SQLException {
		return AccountDao.getAccountById(id);
	}

	public Account validate(Account a) throws SQLException {
		return AccountDao.validate(a);
	}
}
