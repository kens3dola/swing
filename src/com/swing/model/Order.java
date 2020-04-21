package com.swing.model;

import java.util.Date;

public class Order {
enum MyEnum{CONFIRMED,DENNIED};
private int id;
private int staff_id;
private MyEnum status;
private Date date;
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
