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

	private String room;

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

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Item(int id, String name, int orderId, int quantity, String room) {
		super();
		this.id = id;
		this.name = name;
		this.orderId = orderId;
		this.quantity = quantity;
		this.room = room;
	}
	
}