package com.swing.model;

import java.util.Date;

public class Order {
private int id;
private int staff_id;
private MyEnum status;
private Date date;
public Order(int staff_id, MyEnum status, Date date) {
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
    return this.status.name();
}

public void setStatus(String status) {
    this.status = MyEnum.valueOf(status);
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}


}


