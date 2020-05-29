package com.swing.model;

import java.io.Serializable;


/**
 * The persistent class for the seat database table.
 * 
 */
public class Seat implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private int price;

	private int roomId;

	private String status;

	private String name;

	public Seat() {
	}

	public Seat(int id, int roomId, String status, String name, int price) {
		super();
		this.id = id;
		this.price = price;
		this.roomId = roomId;
		this.status = status;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getRoomId() {
		return this.roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}