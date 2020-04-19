package com.swing.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.swing.model.User;
import com.swing.service.UserService;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel roleLabel;
	
	private UserService userService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUser frame = new AddUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public UpdateUser(int id) throws ClassNotFoundException, SQLException {
		
		userService = new UserService();
		User u = userService.getUserById(id);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(73, 56, 120, 40);
		contentPane.add(idLabel);
		
		textField = new JTextField();
		textField.setBounds(241, 56, 454, 40);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(""+u.getId());
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(73, 107, 120, 40);
		contentPane.add(nameLabel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(241, 107, 454, 40);
		contentPane.add(textField_1);
		textField_1.setText(u.getName());
		
		JLabel mobileLabel = new JLabel("Mobile");
		mobileLabel.setBounds(73, 158, 120, 40);
		contentPane.add(mobileLabel);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(241, 158, 454, 40);
		contentPane.add(textField_2);
		textField_2.setText(u.getMobile());
		
		JLabel unameLabel = new JLabel("Username");
		unameLabel.setBounds(73, 209, 120, 40);
		contentPane.add(unameLabel);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(241, 209, 454, 40);
		contentPane.add(textField_3);
		textField_3.setText(u.getUsername());
		
		JLabel passLabel = new JLabel("Password");
		passLabel.setBounds(73, 260, 120, 40);
		contentPane.add(passLabel);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(241, 260, 454, 40);
		contentPane.add(textField_4);
		textField_4.setText(u.getPassword());
		
		roleLabel = new JLabel("Role");
		roleLabel.setBounds(73, 311, 120, 40);
		contentPane.add(roleLabel);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(241, 311, 454, 40);
		contentPane.add(textField_5);
		textField_5.setText(u.getRole());
		
		JLabel Favourite = new JLabel("Favourite");
		Favourite.setBounds(73, 362, 120, 40);
		contentPane.add(Favourite);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(241, 362, 454, 40);
		contentPane.add(textField_6);
		textField_6.setText(u.getFavourite());
		
		JButton createBtn = new JButton("Create");
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User u = new User(Integer.parseInt(textField.getText()),
						textField_1.getText(),
						textField_2.getText(),
						textField_3.getText(),
						textField_4.getText(),
						textField_5.getText(),
						textField_6.getText());
				try {
					userService.createUser(u);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					dispose();
					new Home().setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		createBtn.setBounds(73, 460, 133, 40);
		contentPane.add(createBtn);
	}
}
