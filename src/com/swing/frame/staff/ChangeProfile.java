package com.swing.frame.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.swing.dao.StaffDao;
import com.swing.model.Staff;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class ChangeProfile extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameF;
	private JTextField emailF;
	private JTextField phoneF;
	private JTextField ageF;
	private StaffDao staffDao;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChangeProfile frame = new ChangeProfile(1);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ChangeProfile( int account_id) {
		staffDao = new StaffDao();
		Staff st = staffDao.getStaff(account_id);
		setTitle("Update profile");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlightText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel name = new JLabel("Name");
		name.setForeground(SystemColor.textHighlight);
		name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		name.setBounds(10, 136, 88, 29);
		contentPane.add(name);
		
		nameF = new JTextField();
		nameF.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.textHighlight));
		nameF.setForeground(SystemColor.textHighlight);
		nameF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameF.setBounds(108, 136, 316, 29);
		nameF.setText(st.getName());
		contentPane.add(nameF);
		nameF.setColumns(10);
		
		emailF = new JTextField();
		emailF.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.textHighlight));
		emailF.setForeground(SystemColor.textHighlight);
		emailF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailF.setColumns(10);
		emailF.setText(st.getEmail());
		emailF.setBounds(108, 194, 316, 29);
		contentPane.add(emailF);
		
		JLabel email = new JLabel("Email");
		email.setForeground(SystemColor.textHighlight);
		email.setFont(new Font("Tahoma", Font.PLAIN, 14));
		email.setBounds(10, 194, 88, 29);
		contentPane.add(email);
		
		phoneF = new JTextField();
		phoneF.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.textHighlight));
		phoneF.setForeground(SystemColor.textHighlight);
		phoneF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phoneF.setColumns(10);
		phoneF.setText(st.getPhone());
		phoneF.setBounds(108, 251, 316, 29);
		contentPane.add(phoneF);
		
		JLabel phone = new JLabel("Phone number");
		phone.setForeground(SystemColor.textHighlight);
		phone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phone.setBounds(10, 251, 88, 29);
		contentPane.add(phone);
		
		JLabel gender = new JLabel("Gender");
		gender.setForeground(SystemColor.textHighlight);
		gender.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gender.setBounds(10, 309, 88, 29);
		contentPane.add(gender);
		
		JRadioButton male = new JRadioButton("Male");
		male.setForeground(SystemColor.textHighlight);
		male.setFont(new Font("Tahoma", Font.PLAIN, 14));
		male.setBounds(108, 309, 135, 29);
		contentPane.add(male);
		
		JRadioButton female = new JRadioButton("Female");
		female.setForeground(SystemColor.textHighlight);
		female.setFont(new Font("Tahoma", Font.PLAIN, 14));
		female.setBounds(245, 309, 135, 29);
		contentPane.add(female);
		
		if(st.getGender().equals("male"))male.setSelected(true);
		else female.setSelected(true);
		
		JLabel age = new JLabel("Age");
		age.setForeground(SystemColor.textHighlight);
		age.setFont(new Font("Tahoma", Font.PLAIN, 14));
		age.setBounds(10, 362, 88, 26);
		contentPane.add(age);
		
		ageF = new JFormattedTextField(NumberFormat.getNumberInstance());
		ageF.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.textHighlight));
		ageF.setForeground(SystemColor.textHighlight);
		ageF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ageF.setBounds(108, 362, 112, 29);
		ageF.setText(String.valueOf(st.getAge()));
		contentPane.add(ageF);
		ageF.setColumns(10);
		
		JButton update = new JButton("Update");
		update.setBackground(SystemColor.textHighlight);
		update.setForeground(SystemColor.text);
		update.setFont(new Font("Tahoma", Font.PLAIN, 14));
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				st.setName(nameF.getText());
				st.setEmail(emailF.getText());
				st.setPhone(phoneF.getText());
				try {
					st.setAge(Integer.parseInt(ageF.getText()));
					st.setGender((male.isSelected())?"male":"female");
					staffDao.update(st);
					JOptionPane.showMessageDialog(null, "Updated");
					dispose();
					StaffHome sh = new StaffHome(st.getAccountId());
					sh.setVisible(true);
					sh.setLocationRelativeTo(null);
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Age must be a number");
				}
			}
		});
		update.setBounds(134, 421, 171, 29);
		contentPane.add(update);
		
		JLabel lblNewLabel = new JLabel("Change profile");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setIcon(new ImageIcon(ChangeProfile.class.getResource("/design/icons8_profile_50px.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 414, 114);
		contentPane.add(lblNewLabel);
	}
}
