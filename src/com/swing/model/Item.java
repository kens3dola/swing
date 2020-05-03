package com.swing.model;

public class Item {
	private int id;
	private Name name;
	private int quantity;
	private int price;
	private int order_id;
	
	public enum Name{
		popcorn, redbull;
	}

	public Item(int id, Name name, int quantity, int order_id) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.order_id = order_id;
	}

	public Item(Name name, int quantity, int order_id) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.order_id = order_id;
	}

	public Item() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Name getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
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

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", quantity=" + quantity + ", order_id=" + order_id + "]";
	}
	public int moneyItem(int quantity, int price) {
		return quantity*price;
	}
	
		
}
