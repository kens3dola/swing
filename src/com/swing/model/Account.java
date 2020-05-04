package com.swing.model;

import java.io.Serializable;


/**
 * The persistent class for the account database table.
 * 
 */
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;


	private String password;

	private String role;

	private String username;


	public Account() {
	}


	public Account(int id, String username, String password, String role) {
		super();
		this.id = id;
		this.password = password;
		this.role = role;
		this.username = username;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}