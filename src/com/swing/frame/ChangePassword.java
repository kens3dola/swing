package com.swing.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.swing.dao.AccountDao;
import com.swing.frame.admin.AdminHome;
import com.swing.model.Account;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ChangePassword extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private AccountDao accountDao;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword frame = new ChangePassword(new Account());
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
	public ChangePassword(Account a) {
		if(a == null) {
			JOptionPane.showMessageDialog(null, "NotFound");
		}else {
			try {
				accountDao = new AccountDao();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setBounds(100, 100, 450, 300);
			setLocationRelativeTo(null);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel unameLbl = new JLabel("Username:");
			unameLbl.setBounds(37, 47, 72, 20);
			contentPane.add(unameLbl);
			
			JLabel passLbl = new JLabel("Enter new password:");
			passLbl.setBounds(37, 109, 109, 55);
			contentPane.add(passLbl);
			
			JLabel roleLbl = new JLabel("Role:");
			roleLbl.setBounds(37, 78, 72, 20);
			contentPane.add(roleLbl);
			
			JLabel role = new JLabel("New label");
			role.setBounds(119, 78, 72, 20);
			contentPane.add(role);
			role.setText(a.getRole());
			
			JButton submitBtn = new JButton("Submit");
			submitBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					accountDao.changePassword(a, textField.getText());
					dispose();
					new AdminHome(a.getId());
				}
			});
			submitBtn.setBounds(173, 213, 89, 23);
			contentPane.add(submitBtn);
			
			textField = new JTextField();
			textField.setBounds(173, 126, 209, 20);
			contentPane.add(textField);
			textField.setColumns(10);
			
			JLabel uname = new JLabel("New label");
			uname.setBounds(119, 50, 46, 14);
			uname.setText(a.getUsername());
			contentPane.add(uname);

		}
	}
}
