package com.swing.model;

import java.io.Serializable;


/**
 * The persistent class for the schedule database table.
 * 
 */
public class Schedule implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String shift;

	public Schedule() {
	}

	public Schedule(int id, String shift) {
		super();
		this.id = id;
		this.shift = shift;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShift() {
		return this.shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String toString() {
		if(shift.equals("1"))return "Id:"+id+" Time: 7h00-9h30";
		if(shift.equals("2"))return "Id:"+id+" Time: 9h30-11h00";
		if(shift.equals("3"))return "Id:"+id+" Time: 1h00-2h30";
		return "Id:"+id+" Time: 2h30-5h00";
	}
}