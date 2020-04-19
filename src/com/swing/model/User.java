package com.swing.model;

public class User {
	private int id;
	private String name;
	private String mobile;
	private String username;
	private String password;
	private String role;
	private String favourite;
	
	public User(String name, String mobile, String username, String password, String role, String favourite) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.username = username;
		this.password = password;
		this.role = role;
		this.favourite = favourite;
	}
	
	public User(int id, String name, String mobile, String username, String password, String role, String favourite) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.username = username;
		this.password = password;
		this.role = role;
		this.favourite = favourite;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFavourite() {
		return favourite;
	}
	public void setFavourite(String favourite) {
		this.favourite = favourite;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", mobile=" + mobile + ", username=" + username + ", password="
				+ password + ", role=" + role + ", favourite=" + favourite + "]";
	}
	
	
}
