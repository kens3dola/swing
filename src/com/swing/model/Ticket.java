package com.swing.model;

import java.io.Serializable;


/**
 * The persistent class for the ticket database table.
 * 
 */
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private int orderId;

	private int seatId;

	private int showtimeId;
	
	private int price;

	public Ticket() {
	}

	public Ticket(int id, int orderId, int seatId, int showtimeId) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.seatId = seatId;
		this.showtimeId = showtimeId;
	}

	public int getId() {
		return this.id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getSeatId() {
		return this.seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public int getShowtimeId() {
		return this.showtimeId;
	}

	public void setShowtimeId(int showtimeId) {
		this.showtimeId = showtimeId;
	}

}