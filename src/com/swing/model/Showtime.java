package com.swing.model;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the showtime database table.
 * 
 */
public class Showtime implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private int movieId;

	private int scheduleId;
	
	private Date date;
	
	private String room;

	
	public Showtime(int id, int movieId, int scheduleId, Date date, String room) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.scheduleId = scheduleId;
		this.date = date;
		this.room = room;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getMovieId() {
		return movieId;
	}


	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}


	public int getScheduleId() {
		return scheduleId;
	}


	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getRoom() {
		return room;
	}


	public void setRoom(String room) {
		this.room = room;
	}


	public String toString() {
		String sche = "";
		if(scheduleId==1) {
			sche = " - Time: 7h00-9h30";
		}else if(scheduleId==2) {
			sche = " - Time: 9h30-11h00";
		}else if(scheduleId==3) {
			sche = " - Time: 1h00-2h30";
		}else {
			sche = " - Time: 2h30-5h00";
		}
		
		return this.date.toString()+sche;
	}
}