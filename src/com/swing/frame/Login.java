package com.swing.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.swing.config.ImageIc;
import com.swing.dao.AccountDao;
import com.swing.frame.admin.AdminHome;
import com.swing.frame.staff.StaffHome;
import com.swing.model.Account;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf1;
	private JTextField tf2;

	private AccountDao accountDao;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login frame = new Login();
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
		setResizable(false);
		accountDao = new AccountDao();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 602, 630);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Login = new JLabel("Login");
		Login.setForeground(Color.BLUE);
		Login.setBackground(Color.BLUE);
		Login.setHorizontalAlignment(SwingConstants.CENTER);
		Login.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Login.setBounds(195, 123, 189, 43);
		contentPane.add(Login);

		tf1 = new JTextField();
		tf1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 51, 255)));
		tf1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tf1.setToolTipText("username");
		tf1.setBounds(246, 221, 241, 29);
		contentPane.add(tf1);
		tf1.setColumns(10);

		JLabel lb1 = new JLabel("Username");
		lb1.setIcon(ImageIc.getImgIcon(this.getClass(), "/design/social.png", 26, 26));
		lb1.setForeground(Color.BLUE);
		lb1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setBounds(104, 222, 132, 26);
		contentPane.add(lb1);

		JLabel lb2 = new JLabel("Password");
		lb2.setIcon(ImageIc.getImgIcon(this.getClass(), "/design/social.png", 26, 26));
		lb2.setForeground(Color.BLUE);
		lb2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb2.setHorizontalAlignment(SwingConstants.CENTER);
		lb2.setBounds(104, 336, 132, 26);
		contentPane.add(lb2);

		tf2 = new JPasswordField();
		tf2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 51, 255)));
		tf2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tf2.setToolTipText("password");
		tf2.setBounds(246, 337, 241, 29);
		contentPane.add(tf2);
		tf2.setColumns(10);

		JButton btn = new JButton("Submit");
		btn.setBackground(Color.BLUE);
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Account a = new Account();
				a.setUsername(tf1.getText());
				a.setPassword(tf2.getText());

				Account x = null;
				x = accountDao.validate(a);
				if (x != null) {
					if (x.getRole().equals("admin")) {
						dispose();
						AdminHome ah = new AdminHome(x.getId());
						ah.setVisible(true);
						ah.setLocationRelativeTo(null);
					} else {
						dispose();
						StaffHome sh = new StaffHome(x.getId());
						sh.setVisible(true);
						sh.setLocationRelativeTo(null);
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Wrong username or password.");
				}
			}
		});

		btn.setBounds(224, 447, 132, 43);
		contentPane.add(btn);

		JLabel jLabel13 = new JLabel();
		jLabel13.setIcon(new ImageIcon(Login.class.getResource("/design/d1.png")));
		jLabel13.setBounds(0, 330, 169, 271);
		contentPane.add(jLabel13);

		JLabel jLabel14 = new JLabel();
		jLabel14.setIcon(new ImageIcon(Login.class.getResource("/design/d2.png")));
		jLabel14.setBounds(427, 0, 165, 271);
		contentPane.add(jLabel14);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/design/account.png")));
		lblNewLabel.setBounds(211, 11, 156, 111);
		contentPane.add(lblNewLabel);
	}
}
