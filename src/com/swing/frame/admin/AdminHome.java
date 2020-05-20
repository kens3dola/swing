package com.swing.frame.admin;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;

import com.swing.dao.AccountDao;
import com.swing.dao.MovieDao;
import com.swing.dao.ScheduleDao;
import com.swing.dao.ShowtimeDao;
import com.swing.frame.Login;
import com.swing.model.Account;
import com.swing.model.Movie;
import com.swing.model.Schedule;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

public class AdminHome extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private AccountDao accountDao;
	private MovieDao movieDao;
	private ScheduleDao scheDao;
	private ShowtimeDao showDao;

	private JTable show_table = new JTable();
	private JTable movie_table = new JTable();
	private JTable user_table = new JTable();
	private JTextField username;
	private JTextField password;
	private final ButtonGroup roleBtnGroup = new ButtonGroup();

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AdminHome frame = new AdminHome(0);
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
	/**
	 * @param id
	 */
	/**
	 * @param id
	 */
	public AdminHome(int id) {
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Cinema");
		setLocationRelativeTo(null);
		movieDao = new MovieDao();
		scheDao = new ScheduleDao();
		showDao = new ShowtimeDao();
		accountDao = new AccountDao();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 576);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(null);
		menuBar.setBounds(new Rectangle(10, 0, 50, 50));
		menuBar.setBackground(Color.WHITE);
		menuBar.setBorderPainted(false);
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Option");
		mnNewMenu.setMnemonic('O');
		mnNewMenu.setOpaque(true);
		mnNewMenu.setHorizontalAlignment(SwingConstants.RIGHT);
		mnNewMenu.setBackground(Color.WHITE);
		mnNewMenu.setBorder(null);
		menuBar.add(mnNewMenu);

		JButton btnNewButton = new JButton("Change password");
		btnNewButton.setBackground(Color.WHITE);
		mnNewMenu.add(btnNewButton);
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);

		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setBackground(Color.WHITE);
		mnNewMenu.add(logoutBtn);
		logoutBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Are you sure to want to log out?");
				if (option == JOptionPane.YES_OPTION) {
					dispose();
					Login l = new Login();
					l.setLocationRelativeTo(null);
					l.setVisible(true);
				}
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass = JOptionPane.showInputDialog("Enter new password");
				if (pass != null && pass.length() > 0) {
					accountDao.changePassword(id, pass);
					JOptionPane.showMessageDialog(null, "Password changed");
				}
			}
		});
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setOpaque(false);
		contentPane.setBorder(null);
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setRequestFocusEnabled(false);
		tabbedPane.setFocusable(false);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tabbedPane.setFocusTraversalKeysEnabled(false);
		tabbedPane.setBorder(null);
		tabbedPane.setForeground(new Color(0, 0, 0));
		tabbedPane.setBackground(SystemColor.text);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int i = tabbedPane.getSelectedIndex();
				switch (i) {
				case 0:
					TableModel model = com.swing.config.DbUtils.resultSetToTableModel(showDao.getAllShowtime());
					show_table.setModel(model);
					break;
				case 1:
					TableModel model1 = com.swing.config.DbUtils.resultSetToTableModel(movieDao.getAllMovies());
					movie_table.setModel(model1);
					break;
				case 2:
					TableModel model2 = com.swing.config.DbUtils.resultSetToTableModel(accountDao.getList());
					user_table.setModel(model2);
					break;
				}
			}
		});
		tabbedPane.setBounds(0, 0, 746, 520);
		contentPane.add(tabbedPane);

		// Show tab
		showTabInit(tabbedPane);

		// movie tab
		movieTabInit(tabbedPane);

		// user tab
		userTabInit(tabbedPane);
	}

	private void showTabInit(JTabbedPane tabbedPane) {
		JDateChooser date = new JDateChooser();
		date.getCalendarButton().setBackground(SystemColor.textHighlight);
		JComboBox<String> room = new JComboBox<String>();
		room.setBackground(Color.WHITE);
		room.setForeground(SystemColor.textHighlight);
		date.setBounds(95, 319, 221, 31);
		date.setBorder(new LineBorder(SystemColor.textHighlight));
		JPanel panel1 = new JPanel();
		panel1.setFocusTraversalKeysEnabled(false);
		panel1.setDoubleBuffered(false);
		panel1.setBorder(null);
		panel1.setBackground(Color.WHITE);
		panel1.add(date);
		JList<Movie> movieList = new JList<Movie>();
		movieList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		movieList.setAlignmentX(Component.RIGHT_ALIGNMENT);
		movieList.setForeground(SystemColor.textHighlight);
		JList<Schedule> movieList2 = new JList<Schedule>();
		movieList2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		movieList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		movieList2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		movieList2.setForeground(SystemColor.textHighlight);
		tabbedPane.addTab("Manage shows", null, panel1, null);
		show_table.getTableHeader().setBackground(Color.WHITE);
		show_table.getTableHeader().setForeground(new Color(0, 0, 255));
		show_table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));
		show_table.setForeground(new Color(0, 0, 255));
		show_table.setFillsViewportHeight(true);
		show_table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		show_table.setFocusable(false);
		show_table.setFocusTraversalKeysEnabled(false);
		show_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		show_table.setRowMargin(0);
		show_table.setShowVerticalLines(false);
		show_table.setShowHorizontalLines(false);
		show_table.setShowGrid(false);
		show_table.setGridColor(Color.WHITE);
		show_table.setBorder(null);
		show_table.setBackground(Color.WHITE);

		JScrollPane scroll1 = new JScrollPane(show_table);
		scroll1.setAutoscrolls(true);
		scroll1.setOpaque(true);
		scroll1.setFocusable(false);
		scroll1.setFocusTraversalKeysEnabled(false);
		scroll1.setBorder(new LineBorder(Color.GRAY));
		scroll1.setBackground(Color.WHITE);
		scroll1.setBounds(326, 37, 405, 444);
		panel1.add(scroll1);

		panel1.setLayout(null);

		show_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		show_table.setBounds(new Rectangle(10, 39, 721, 444));

		JButton showNew = new JButton("ADD");
		showNew.setBackground(SystemColor.textHighlight);
		showNew.setForeground(Color.WHITE);
		showNew.setFont(new Font("Tahoma", Font.PLAIN, 14));
		showNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Movie m = movieList.getSelectedValue();
				Schedule s = movieList2.getSelectedValue();
				Date d = date.getDate();
				String r = String.valueOf(room.getSelectedItem());
				if (m != null && s != null && d != null && r != null) {
					showDao.add(m.getId(), s.getId(), d, r);
					TableModel model = com.swing.config.DbUtils.resultSetToTableModel(showDao.getAllShowtime());
					show_table.setModel(model);
					JOptionPane.showMessageDialog(null, "A new show is added");
				} else {
					JOptionPane.showMessageDialog(null, "Please choose one movie and one schedule");
				}
			}
		});
		showNew.setBounds(95, 428, 111, 31);
		panel1.add(showNew);

		JButton btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.setFocusTraversalKeysEnabled(false);
		btnNewButton_4.setFocusPainted(false);
		btnNewButton_4.setBorder(new LineBorder(new Color(64, 64, 64), 0, true));
		btnNewButton_4.setBackground(SystemColor.text);
		btnNewButton_4.setForeground(Color.DARK_GRAY);
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_4.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_4.setIcon(new ImageIcon(AdminHome.class.getResource("/design/icons8_trash_23px.png")));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = show_table.getSelectedRow();
				if (row == -1)
					JOptionPane.showMessageDialog(null, "Please select a show");
				else {
					String image_name = String.valueOf(show_table.getValueAt(row, 2));
					File f = new File("src/images/" + image_name);
					f.delete();
					showDao.delete(Integer.parseInt(String.valueOf(show_table.getValueAt(row, 0))));
					TableModel model = com.swing.config.DbUtils.resultSetToTableModel(showDao.getAllShowtime());
					show_table.setModel(model);
					JOptionPane.showMessageDialog(null, "One show is deleted");
				}
			}
		});
		btnNewButton_4.setName("showDelete");
		btnNewButton_4.setBounds(645, 0, 86, 34);
		panel1.add(btnNewButton_4);

		movieList.setBounds(87, 156, 182, 36);
		DefaultListModel<Movie> model = new DefaultListModel<Movie>();
		List<Movie> l;
		l = movieDao.list(movieDao.getAllMovies());
		model.addAll(l);
		movieList.setModel(model);
		JScrollPane scrollPane = new JScrollPane(movieList);
		scrollPane.setBorder(new LineBorder(SystemColor.textHighlight));
		scrollPane.setBounds(95, 125, 221, 86);
		panel1.add(scrollPane);

		movieList.setBounds(87, 156, 182, 36);
		DefaultListModel<Schedule> model2 = new DefaultListModel<Schedule>();
		List<Schedule> l2;
		l2 = scheDao.list(scheDao.getAllSchedule());
		model2.addAll(l2);
		movieList2.setModel(model2);
		JScrollPane scrollPane_1 = new JScrollPane(movieList2);
		scrollPane_1.setBorder(new LineBorder(SystemColor.textHighlight));
		scrollPane_1.setBounds(95, 222, 221, 86);
		panel1.add(scrollPane_1);

		JLabel lblNewLabel = new JLabel("Movie");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 125, 75, 86);
		panel1.add(lblNewLabel);

		JLabel lblSchedule = new JLabel("Schedule");
		lblSchedule.setVerticalAlignment(SwingConstants.TOP);
		lblSchedule.setForeground(SystemColor.textHighlight);
		lblSchedule.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSchedule.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchedule.setBounds(10, 221, 75, 86);
		panel1.add(lblSchedule);

		JLabel lblNewLabel_1 = new JLabel("Date");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setForeground(SystemColor.textHighlight);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 318, 75, 31);
		panel1.add(lblNewLabel_1);

		JLabel lblNewLabel_9 = new JLabel("Room");
		lblNewLabel_9.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_9.setForeground(SystemColor.textHighlight);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(10, 360, 75, 31);
		panel1.add(lblNewLabel_9);

		room.setModel(new DefaultComboBoxModel<String>(new String[] { "A", "B", "C", "D" }));
		room.setBounds(95, 361, 221, 31);
		panel1.add(room);

		JLabel lblNewLabel_11 = new JLabel("Add new show");
		lblNewLabel_11.setForeground(SystemColor.textHighlight);
		lblNewLabel_11.setIcon(new ImageIcon(AdminHome.class.getResource("/design/icons8_tv_show_50px.png")));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_11.setBounds(10, 20, 306, 94);
		panel1.add(lblNewLabel_11);
	}

	private void movieTabInit(JTabbedPane tabbedPane) {
		JTextField name = new JTextField();
		name.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.textHighlight));
		name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JTextField price = new JTextField();
		price.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.textHighlight));
		price.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JTextField image = new JTextField();
		image.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.textHighlight));
		image.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		panel1.setBorder(null);
		JLabel movie_id = new JLabel("");
		tabbedPane.addTab("Manage movies", null, panel1, null);
		movie_table.setForeground(SystemColor.textHighlight);
		movie_table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		movie_table.setGridColor(SystemColor.text);
		movie_table.setFillsViewportHeight(true);
		movie_table.setBackground(SystemColor.text);
		movie_table.getTableHeader().setBackground(Color.WHITE);
		movie_table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));
		movie_table.getTableHeader().setForeground(SystemColor.textHighlight);
		JScrollPane scroll1 = new JScrollPane(movie_table);
		scroll1.setOpaque(false);
		scroll1.setBackground(SystemColor.text);
		scroll1.setBounds(303, 38, 428, 445);
		panel1.add(scroll1);

		panel1.setLayout(null);

		movie_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		movie_table.setBounds(new Rectangle(10, 39, 721, 444));

		JButton addMovie = new JButton("ADD");
		addMovie.setForeground(SystemColor.text);
		addMovie.setBackground(SystemColor.textHighlight);
		addMovie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (addMovie.getText().equals("Update")) {
					Movie m = new Movie();
					m.setId(Integer.parseInt(movie_id.getText()));
					m.setName(name.getText());
					m.setImage(image.getText());
					m.setPrice(Integer.parseInt(price.getText()));
					movieDao.updateMovie(m);
					JOptionPane.showMessageDialog(null, "Updated");
					name.setText("");
					image.setText("");
					price.setText("");
					movie_id.setText("");
					addMovie.setText("Add new movie");
				} else {
					String n = name.getText();
					String i = image.getText();
					int p = Integer.parseInt(price.getText());
					movieDao.add(n, i, p);
					System.out.println("jar vfu Cinema.jar images");
					JOptionPane.showMessageDialog(null, "Added");
				}
				TableModel model1 = com.swing.config.DbUtils.resultSetToTableModel(movieDao.getAllMovies());
				movie_table.setModel(model1);

			}
		});
		addMovie.setBounds(100, 438, 131, 31);
		panel1.add(addMovie);

		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setFocusTraversalKeysEnabled(false);
		btnNewButton_1.setBackground(SystemColor.text);
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setIcon(new ImageIcon(AdminHome.class.getResource("/design/icons8_edit_22px.png")));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = movie_table.getSelectedRow();
				if (row != -1) {
					addMovie.setText("Update");
					movie_id.setText(String.valueOf(movie_table.getValueAt(row, 0)));
					name.setText(String.valueOf(movie_table.getValueAt(row, 1)));
					image.setText(String.valueOf(movie_table.getValueAt(row, 2)));
					price.setText(String.valueOf(movie_table.getValueAt(row, 3)));
				}
			}
		});
		btnNewButton_1.setName("showUpdate");
		btnNewButton_1.setBounds(534, 0, 94, 34);
		panel1.add(btnNewButton_1);

		JButton btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.setBorder(null);
		btnNewButton_4.setFocusTraversalKeysEnabled(false);
		btnNewButton_4.setFocusPainted(false);
		btnNewButton_4.setBackground(SystemColor.text);
		btnNewButton_4.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_4.setIcon(new ImageIcon(AdminHome.class.getResource("/design/icons8_trash_23px.png")));
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (movie_table.getSelectedRow() != -1) {
					movieDao.delete(
							Integer.parseInt(String.valueOf(movie_table.getValueAt(movie_table.getSelectedRow(), 0))));
					TableModel model1 = com.swing.config.DbUtils.resultSetToTableModel(movieDao.getAllMovies());
					movie_table.setModel(model1);
					JOptionPane.showMessageDialog(null, "Deleted");
				}
			}
		});
		btnNewButton_4.setName("showDelete");
		btnNewButton_4.setBounds(637, 0, 94, 34);
		panel1.add(btnNewButton_4);

		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setForeground(SystemColor.textHighlight);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 140, 94, 28);
		panel1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Image");
		lblNewLabel_3.setForeground(SystemColor.textHighlight);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 243, 94, 28);
		panel1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Price");
		lblNewLabel_4.setForeground(SystemColor.textHighlight);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 345, 94, 28);
		panel1.add(lblNewLabel_4);

		name.setBounds(114, 140, 179, 31);
		panel1.add(name);
		name.setColumns(10);

		price.setBounds(114, 345, 179, 31);
		panel1.add(price);
		price.setColumns(10);

		image.setBounds(114, 243, 142, 31);
		panel1.add(image);
		image.setColumns(10);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setFocusable(false);
		btnNewButton_2.setFocusTraversalKeysEnabled(false);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setIcon(new ImageIcon(AdminHome.class.getResource("/design/icons8_plus_math_23px.png")));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Filechooser fc = new Filechooser();
				String imgName = fc.choose();
				JLabel lblNewLabel_5 = new JLabel("New label");
				lblNewLabel_5.setBounds(10, 50, 46, 14);
				panel1.add(lblNewLabel_5);
				image.setText(imgName);
			}
		});
		btnNewButton_2.setBounds(266, 243, 27, 33);
		panel1.add(btnNewButton_2);

		movie_id.setBounds(10, 39, 0, 0);
		panel1.add(movie_id);

		JLabel lblNewLabel_10 = new JLabel("Add new movie");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setForeground(SystemColor.textHighlight);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_10.setIcon(new ImageIcon(AdminHome.class.getResource("/design/icons8_clapperboard_50px.png")));
		lblNewLabel_10.setBounds(31, 39, 262, 95);
		panel1.add(lblNewLabel_10);
	}

	private void userTabInit(JTabbedPane tabbedPane) {
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		panel1.setBorder(null);
		JLabel account_id = new JLabel("");
		JRadioButton admin = new JRadioButton("admin");
		admin.setForeground(SystemColor.textHighlight);
		admin.setBackground(SystemColor.text);
		admin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JRadioButton staff = new JRadioButton("staff");
		staff.setForeground(SystemColor.textHighlight);
		staff.setBackground(SystemColor.text);
		staff.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabbedPane.addTab("Manage users", null, panel1, null);
		user_table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		user_table.setForeground(SystemColor.textHighlight);
		user_table.setGridColor(SystemColor.text);
		user_table.setBackground(SystemColor.text);
		user_table.setFillsViewportHeight(true);
		user_table.getTableHeader().setBackground(Color.white);
		user_table.getTableHeader().setForeground(SystemColor.textHighlight);
		user_table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));

		JScrollPane scroll1 = new JScrollPane(user_table);
		scroll1.setBounds(315, 45, 416, 446);
		panel1.add(scroll1);

		panel1.setLayout(null);

		user_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		user_table.setBounds(new Rectangle(10, 39, 721, 444));

		JButton showNew = new JButton("ADD");
		showNew.setFont(new Font("Tahoma", Font.PLAIN, 14));
		showNew.setBackground(SystemColor.textHighlight);
		showNew.setForeground(Color.WHITE);
		showNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (showNew.getText().equals("Update")) {
					String role = (roleBtnGroup.getSelection() == admin.getModel()) ? "admin" : "staff";
					accountDao.update(new Account(Integer.parseInt(account_id.getText()), username.getText(),
							password.getText(), role));
					JOptionPane.showMessageDialog(null, "Updated");
					showNew.setText("Add new account");
					account_id.setText("");
					username.setText("");
					password.setText("");
					roleBtnGroup.clearSelection();
				} else {
					String role = (roleBtnGroup.getSelection() == admin.getModel()) ? "admin" : "staff";
					accountDao.createAccount(username.getText(), password.getText(), role);
					JOptionPane.showMessageDialog(null, "Added");
				}
				TableModel model2 = com.swing.config.DbUtils.resultSetToTableModel(accountDao.getList());
				user_table.setModel(model2);
			}
		});
		showNew.setBounds(91, 410, 115, 30);
		panel1.add(showNew);

		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setIcon(new ImageIcon(AdminHome.class.getResource("/design/icons8_edit_22px.png")));
		btnNewButton_1.setFocusTraversalKeysEnabled(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(SystemColor.text);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = user_table.getSelectedRow();
				if (row != -1) {
					showNew.setText("Update");
					account_id.setText(String.valueOf(user_table.getValueAt(row, 0)));
					username.setText(String.valueOf(user_table.getValueAt(row, 1)));
					password.setText(String.valueOf(user_table.getValueAt(row, 2)));
					if (user_table.getValueAt(row, 3).equals("admin")) {
						admin.setSelected(true);
						staff.setSelected(false);
					} else {
						admin.setSelected(false);
						staff.setSelected(true);
					}
				}
			}
		});
		btnNewButton_1.setName("showUpdate");
		btnNewButton_1.setBounds(543, 0, 89, 34);
		panel1.add(btnNewButton_1);

		JButton btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.setIcon(new ImageIcon(AdminHome.class.getResource("/design/icons8_trash_23px.png")));
		btnNewButton_4.setFocusTraversalKeysEnabled(false);
		btnNewButton_4.setFocusPainted(false);
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_4.setBorder(null);
		btnNewButton_4.setBackground(SystemColor.text);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = user_table.getSelectedRow();
				if (row != -1) {
					accountDao.delete(Integer.parseInt(String.valueOf(user_table.getValueAt(row, 0))));
					JOptionPane.showMessageDialog(null, "Deleted");
					TableModel model2 = com.swing.config.DbUtils.resultSetToTableModel(accountDao.getList());
					user_table.setModel(model2);
				}
			}
		});
		btnNewButton_4.setName("showDelete");
		btnNewButton_4.setBounds(642, 0, 89, 34);
		panel1.add(btnNewButton_4);

		JLabel lblNewLabel_6 = new JLabel("Username");
		lblNewLabel_6.setForeground(SystemColor.textHighlight);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(10, 149, 71, 37);
		panel1.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Password");
		lblNewLabel_7.setForeground(SystemColor.textHighlight);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(10, 235, 71, 37);
		panel1.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Role");
		lblNewLabel_8.setForeground(SystemColor.textHighlight);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(10, 315, 71, 37);
		panel1.add(lblNewLabel_8);

		username = new JTextField();
		username.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.textHighlight));
		username.setFont(new Font("Tahoma", Font.PLAIN, 14));
		username.setBounds(91, 152, 214, 34);
		panel1.add(username);
		username.setColumns(10);

		password = new JTextField();
		password.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.textHighlight));
		password.setFont(new Font("Tahoma", Font.PLAIN, 14));
		password.setColumns(10);
		password.setBounds(91, 235, 214, 37);
		panel1.add(password);

		roleBtnGroup.add(admin);
		admin.setBounds(91, 315, 106, 37);
		panel1.add(admin);

		roleBtnGroup.add(staff);
		staff.setBounds(202, 315, 107, 37);
		panel1.add(staff);

		account_id.setBounds(10, 37, 0, 0);
		panel1.add(account_id);

		JLabel lblNewLabel_12 = new JLabel("Add new account");
		lblNewLabel_12.setForeground(SystemColor.textHighlight);
		lblNewLabel_12.setIcon(new ImageIcon(AdminHome.class.getResource("/design/icons8_user_groups_50px.png")));
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_12.setBounds(10, 15, 299, 123);
		panel1.add(lblNewLabel_12);
	}
}
