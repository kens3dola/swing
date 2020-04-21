package com.swing.model;

public class Ticket {
	public Ticket(int id, int order_id, int showtime_id, int seat_id) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.showtime_id = showtime_id;
		this.seat_id = seat_id;
	}
	private int id;
	private int order_id;
	private int showtime_id;
	private int seat_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getShowtime_id() {
		return showtime_id;
	}
	public void setShowtime_id(int showtime_id) {
		this.showtime_id = showtime_id;
	}
	public int getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}
	

}
