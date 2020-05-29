package com.swing.model;

import java.io.Serializable;


/**
 * The persistent class for the item database table.
 * 
 */
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private int orderId;

	private int quantity;


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

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Item(int id, String name, int orderId, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.orderId = orderId;
		this.quantity = quantity;
	}
	
}