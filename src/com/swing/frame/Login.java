package com.swing.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.swing.dao.AccountDao;
import com.swing.frame.admin.AdminHome;
import com.swing.frame.staff.StaffHome;
import com.swing.model.Account;
import com.swing.service.AccountService;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf1;
	private JTextField tf2;
	private JComboBox<String> cb;
	
	private AccountService accountService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		try {
			accountDao = new AccountDao();
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
		
		JLabel Login = new JLabel("Login");
		Login.setHorizontalAlignment(SwingConstants.CENTER);
		Login.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Login.setBounds(146, 27, 137, 26);
		contentPane.add(Login);
		
		tf1 = new JTextField();
		tf1.setBounds(122, 64, 274, 29);
		contentPane.add(tf1);
		tf1.setColumns(10);
		
		JLabel lb1 = new JLabel("Username");
		lb1.setBounds(25, 64, 87, 26);
		contentPane.add(lb1);
		
		JLabel lb2 = new JLabel("Password");
		lb2.setBounds(25, 119, 87, 26);
		contentPane.add(lb2);
		
		tf2 = new JTextField();
		tf2.setBounds(122, 119, 274, 29);
		contentPane.add(tf2);
		tf2.setColumns(10);
		
		JButton btn = new JButton("Submit");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Account a = new Account(tf1.getText(), tf2.getText(), (String)cb.getSelectedItem());
				if(accountService.validate(a)) {
					if(a.getRole().equals("Admin")){
						dispose();
						new AdminHome().setVisible(true);
					}else {
						dispose();
						new StaffHome().setVisible(true);
					}
				}else {
					JOptionPane.showMessageDialog(contentPane, "Wrong username or password.");
				}
			}
		});
		
		
		btn.setBounds(171, 227, 89, 23);
		contentPane.add(btn);
		
		cb = new JComboBox<String>();
		cb.setBounds(122, 173, 138, 29);
		cb.addItem("Staff");
		cb.addItem("Admin");
		contentPane.add(cb);
	}
}