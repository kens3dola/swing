package com.swing.model;

import java.io.Serializable;


/**
 * The persistent class for the admin database table.
 * 
 */
public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;

	private int accountId;

	public Admin() {
	}

	public int getAccountId() {
		return this.accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

}