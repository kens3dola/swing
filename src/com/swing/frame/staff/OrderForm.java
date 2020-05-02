package com.swing.frame.staff;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import com.swing.model.Order;
import com.swing.model.Ticket;
import com.swing.service.OrderService;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class OrderForm extends JFrame {

	private JPanel contentPane;
	private JTextField staff_id;
	private OrderService orderService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderForm frame = new OrderForm();
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
	public OrderForm() {
		try {
			orderService = new OrderService();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label1 = new JLabel("staff_id");
		label1.setBounds(34, 44, 77, 14);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("status");
		label2.setBounds(34, 78, 77, 14);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("date");
		label3.setBounds(34, 113, 77, 14);
		contentPane.add(label3);
		
		staff_id = new JTextField();
		staff_id.setBounds(121, 41, 192, 20);
		contentPane.add(staff_id);
		staff_id.setColumns(10);
		
		JComboBox<String> status = new JComboBox<String>();
		status.setBounds(121, 74, 192, 22);
		status.addItem("confirmed");
		status.addItem("dennied");
		status.setSelectedItem(null);
		status.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = (String)status.getSelectedItem();
				label2.setText(value);
				
			}
		});
		
		contentPane.add(status);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-dd-mm");
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		dateChooser.setBounds(121, 113, 192, 20);
		contentPane.add(dateChooser);
		
		JButton btn = new JButton("add order");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				Order a = new Order(Integer.parseInt(staff_id.getText()),String.valueOf(status.getSelectedItem()),date);
				
				try {
					orderService.createOrder(a);
					JOptionPane.showMessageDialog(contentPane, "Sucessfully added"
							+ "");
					new StaffHome().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(contentPane, "Rewrite the information "
							+ "");
				}
			
			}
		});
		btn.setBounds(170, 166, 89, 23);
		contentPane.add(btn);
	}
}
