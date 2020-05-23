package com.swing.model;

import java.io.Serializable;


/**
 * The persistent class for the movie database table.
 * 
 */
public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String image;

	private String name;

	private int price;

	public Movie() {
	}

	public Movie(int id, String name, int price, String image) {
		super();
		this.id = id;
		this.image = image;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	public String toString() {
		return "Id:"+id+" Name"+this.name;
	}

}