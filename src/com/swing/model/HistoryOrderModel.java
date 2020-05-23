package com.swing.model;

import java.sql.Date;

public class HistoryOrderModel {

	private int order_id;
	private int staff_id;
	private String customer_name;
	private Date order_date;
	
	private String items;
	
	private String seats;
	
	private String room;
	private String showtime;
	private String movie;
	private Date show_date;
	
	private int price;
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getShowtime() {
		return showtime;
	}
	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public Date getShow_date() {
		return show_date;
	}
	public void setShow_date(Date show_date) {
		this.show_date = show_date;
	}
	public HistoryOrderModel(int order_id, int staff_id, String customer_name, Date order_date, String items,
			String seats, String room, String showtime, String movie, Date show_date) {
		super();
		this.order_id = order_id;
		this.staff_id = staff_id;
		this.customer_name = customer_name;
		this.order_date = order_date;
		this.items = items;
		this.seats = seats;
		this.room = room;
		this.showtime = showtime;
		this.movie = movie;
		this.show_date = show_date;
	}
	public HistoryOrderModel() {
	}
	
}
