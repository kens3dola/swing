package com.swing.model;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the order database table.
 * 
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date date;

	private int id;

	private int staffId;

	private String customer;
	
	private int price;

	public Order() {
	}

	public Order(int id, int staffId, Date date, String customer) {
		super();
		this.date = date;
		this.id = id;
		this.staffId = staffId;
		this.customer = customer;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStaffId() {
		return this.staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return this.price;
	}
}