package com.swing.model;

import java.util.*;
public class Order {
private int id;
private int staff_id;
private String status;
private String date;
public Order(int staff_id,String status, String date) {
	super();
	this.staff_id = staff_id;
	this.status = status;
	this.date = date;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getStaff_id() {
	return staff_id;
}
public void setStaff_id(int staff_id) {
	this.staff_id = staff_id;
}
public String getStatus() {
    return this.status;
}

public void setStatus(String status) {
    this.status = status;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}


}