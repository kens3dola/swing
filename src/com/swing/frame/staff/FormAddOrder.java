package com.swing.frame.staff;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.swing.model.MyEnum;
import com.swing.model.Order;
import com.swing.model.Ticket;
import com.swing.service.OrderService;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.Color;

public class FormAddOrder extends JFrame {

	private JPanel contentPane;
	private JTextField staff_id;
	private JTextField status;
	private OrderService orderService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAddOrder frame = new FormAddOrder();
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
	public FormAddOrder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("staff_id");
		lblNewLabel.setBounds(77, 50, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("status");
		lblNewLabel_1.setBounds(77, 99, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("date");
		lblNewLabel_2.setBounds(77, 155, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		staff_id = new JTextField();
		staff_id.setBounds(133, 47, 145, 20);
		contentPane.add(staff_id);
		staff_id.setColumns(10);
		
		status = new JFormattedTextField();
		status.setColumns(10);
		status.setBounds(133, 96, 145, 20);
		contentPane.add(status);
//		
//		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
//	      JFormattedTextField date = new JFormattedTextField(dateFormat);
		 JFormattedTextField date = new JFormattedTextField();
	      date.setFocusLostBehavior(JFormattedTextField.REVERT);
	      date.setForeground(new Color(0, 0, 0));
		
		date.setBounds(133, 152, 145, 20);
		contentPane.add(date);
		
		JButton btn = new JButton("Add Order");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = date.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date textFieldAsDate = null;

				try {
				    textFieldAsDate = sdf.parse(text);
				} catch (ParseException pe) {
				    // deal with ParseException
				}
			Order order = new Order(Integer.parseInt(staff_id.getText()),MyEnum.valueOf(status.getText()),textFieldAsDate);
			
					try {
						orderService.createOrder(order);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(contentPane, "Rewrite the information "
								+ "");
					}
				
			}
		});
		btn.setBounds(161, 206, 89, 23);
		contentPane.add(btn);
		
	}

}
