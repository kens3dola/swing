package com.swing.service;

import java.sql.SQLException;

import com.swing.dao.TicketDao;
import com.swing.dao.TicketDao;
import com.swing.model.Ticket;
import com.swing.model.Ticket;


public class TicketService {
	private TicketDao TicketDao;
	public TicketService() throws ClassNotFoundException, SQLException {
		TicketDao = new TicketDao();
	}	
	public void createTicket(Ticket Ticket) throws SQLException {
		TicketDao.createTicket(Ticket);
	}


}