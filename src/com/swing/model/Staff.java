package com.swing.model;

import java.io.Serializable;


/**
 * The persistent class for the staff database table.
 * 
 */
public class Staff implements Serializable {
	private static final long serialVersionUID = 1L;

	private int accountId;

	private int age;

	private String email;

	private String gender;

	private int id;

	private String name;

	private String phone;

	public Staff() {
	}

	public int getAccountId() {
		return this.accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Staff(int accountId, int age, String email, String gender, int id, String name, String phone) {
		super();
		this.accountId = accountId;
		this.age = age;
		this.email = email;
		this.gender = gender;
		this.id = id;
		this.name = name;
		this.phone = phone;
	}
	
	
}