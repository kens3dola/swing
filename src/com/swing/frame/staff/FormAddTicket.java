package com.swing.frame.staff;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.swing.frame.admin.AdminHome;
import com.swing.model.Account;
import com.swing.model.Ticket;
import com.swing.service.AccountService;
import com.swing.service.TicketService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FormAddTicket extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField order_id;
	private JTextField showtime_id;
	private JTextField seat_id;
	private TicketService ticketService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAddTicket frame = new FormAddTicket();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormAddTicket() {
		try {
			ticketService = new TicketService();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("order_id");
		lblNewLabel.setBounds(33, 28, 72, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("showtime_id");
		lblNewLabel_1.setBounds(33, 58, 72, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("seat_id");
		lblNewLabel_2.setBounds(33, 83, 72, 14);
		contentPane.add(lblNewLabel_2);
		
		order_id = new JTextField();
		order_id.setBounds(102, 25, 149, 20);
		contentPane.add(order_id);
		order_id.setColumns(10);
		
		showtime_id = new JTextField();
		showtime_id.setBounds(102, 55, 149, 20);
		contentPane.add(showtime_id);
		showtime_id.setColumns(10);
		
		seat_id = new JTextField();
		seat_id.setBounds(102, 80, 149, 20);
		contentPane.add(seat_id);
		seat_id.setColumns(10);
		
		JButton btn = new JButton("Add Ticket");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ticket a = new Ticket(Integer.parseInt(order_id.getText()),Integer.parseInt(showtime_id.getText()),Integer.parseInt(seat_id.getText()));
				
					try {
						ticketService.createTicket(a);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(contentPane, "Rewrite the information "
								+ "");
					}
				
			}
		});
			
		btn.setBounds(126, 126, 89, 23);
		contentPane.add(btn);
		
		
	}

}
