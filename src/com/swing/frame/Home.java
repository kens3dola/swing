package com.swing.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.swing.model.User;
import com.swing.service.UserService;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	private UserService userService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() throws ClassNotFoundException, SQLException {
		userService = new UserService();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(10, 451, 564, 99);
		contentPane.add(table);
		DefaultTableModel model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		model.addColumn("ID");
		model.addColumn("NAME");
		model.addColumn("MOBILE");
		model.addColumn("USERNAME");
		model.addColumn("PASSWORD");
		model.addColumn("ROLE");
		model.addColumn("FAVOURITE");
		List<User> list = userService.getAllUsers();
		for(User user:list) {
			model.addRow(new Object[] {user.getId(),user.getName(),user.getMobile(),user.getUsername(),user.getPassword(),user.getRole(),user.getFavourite()});
		}
		
		JButton addUserBtn = new JButton("Add");
		addUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new AddUser().setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		addUserBtn.setBounds(10, 29, 97, 23);
		contentPane.add(addUserBtn);
		
		JButton updateUserBtn = new JButton("Update");
		updateUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.valueOf(String.valueOf(model.getValueAt(table.getSelectedRow(), 0)));
				dispose();
				try {
					new UpdateUser(id).setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		updateUserBtn.setBounds(135, 29, 103, 23);
		contentPane.add(updateUserBtn);
		
		JButton refreshBtn = new JButton("Refresh");
		refreshBtn.setBounds(262, 29, 89, 23);
		contentPane.add(refreshBtn);
		refreshBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				List<User> list = null;
				try {
					list = userService.getAllUsers();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				for(User user:list) {
					model.addRow(new Object[] {user.getId(),user.getName(),user.getMobile(),user.getUsername(),user.getPassword(),user.getRole(),user.getFavourite()});
				}
			}
		});
		
		JButton deleteUserBtn = new JButton("Delete");
		deleteUserBtn.setBounds(485, 27, 89, 23);
		contentPane.add(deleteUserBtn);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 63, 564, 487);
		contentPane.add(scrollPane);
	}
}
