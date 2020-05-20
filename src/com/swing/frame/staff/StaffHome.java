package com.swing.frame.staff;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.swing.config.ImageIc;
import com.swing.dao.AccountDao;
import com.swing.dao.ItemDao;
import com.swing.dao.MovieDao;
import com.swing.dao.OrderDao;
import com.swing.dao.ShowtimeDao;
import com.swing.dao.StaffDao;
import com.swing.dao.TicketDao;
import com.swing.frame.HistoryOrder;
import com.swing.frame.Login;
import com.swing.model.Movie;
import com.swing.model.Showtime;
import com.swing.model.Ticket;
import com.toedter.calendar.JDateChooser;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;
import java.awt.Insets;
import java.awt.Cursor;

public class StaffHome extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int staff_id;
	private int account_id;

	private JLayeredPane contentPane;
	private AccountDao accountDao;
	private MovieDao movieDao;
	private ShowtimeDao showDao;
	private TicketDao ticDao;
	private OrderDao orderDao;
	private ItemDao itemDao;
	private StaffDao staffDao;

	private List<Movie> list;
	int current = 0, size;

	JLabel image = new JLabel("");
	JButton first = new JButton("First");
	JButton prev = new JButton("Prev");
	JButton next = new JButton("Next");
	JButton last = new JButton("Last");
	JButton movieNextBtn = new JButton("Choose");
	JLabel name = new JLabel("");
	JLabel price = new JLabel("");
	JLabel movie_id = new JLabel("");
	JDateChooser date = new JDateChooser();
	JComboBox<Showtime> shows = new JComboBox<Showtime>();
	private final JPanel seatPane = new JPanel();
	private final JPanel itemPane = new JPanel();
	private final JPanel moviePane = new JPanel();
	private final JLabel lblNewLabel = new JLabel("Date");
	JLabel seatChosen = new JLabel("");
	JButton seatNextBtn = new JButton("Next");
	private final JLabel lblNewLabel_1 = new JLabel("Seat chosen:");
	JLabel seat_price = new JLabel("0 $");

	JLabel popcorn = new JLabel("");
	JLabel coca = new JLabel("");
	JSpinner pop = new JSpinner();
	JLabel lblNewLabel_2 = new JLabel("Quantity:");
	JLabel lblNewLabel_2_1 = new JLabel("Quantity:");
	JSpinner coc = new JSpinner();
	JButton done = new JButton("Done");
	private final JButton btnNewButton_2 = new JButton("History order");
	private final JLabel lblNewLabel_3 = new JLabel("Price: ");
	private final JLabel item_price = new JLabel("0 $");
	private final JLabel lblNewLabel_4 = new JLabel("Popcorn : 1$/1");
	private final JLabel lblNewLabel_4_1 = new JLabel("Coca : 1$/1");
	private final JLabel tvImg = new JLabel("");
	private final JButton btnNewButton_3_1 = new JButton("");
	private final JButton btnNewButton_4 = new JButton("Home");

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StaffHome frame = new StaffHome(2);
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
	public StaffHome(int id) {
		setUndecorated(true);
		setBackground(Color.RED);
		setTitle("Cinema");
		setResizable(false);
		accountDao = new AccountDao();
		movieDao = new MovieDao();
		showDao = new ShowtimeDao();
		ticDao = new TicketDao();
		orderDao = new OrderDao();
		itemDao = new ItemDao();
		staffDao = new StaffDao();
		list = movieDao.list(movieDao.getAllMovies());
		size = list.size();
		account_id = id;
		staff_id = staffDao.getStaff(id).getId();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 564);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		menuBar.setBorderPainted(false);
		menuBar.setBackground(new Color(255, 204, 153));
		setJMenuBar(menuBar);
		btnNewButton_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				StaffHome sh = new StaffHome(account_id);
				sh.setVisible(true);
				sh.setLocationRelativeTo(null);
			}
		});
		btnNewButton_4.setForeground(new Color(0, 51, 255));
		btnNewButton_4.setMnemonic('H');
		btnNewButton_4.setFocusTraversalKeysEnabled(false);
		btnNewButton_4.setFocusPainted(false);
		btnNewButton_4.setBorder(null);
		btnNewButton_4.setBackground(new Color(255, 204, 153));
		btnNewButton_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnNewButton_4.setIcon(null);

		menuBar.add(btnNewButton_4);

		JMenu mnNewMenu = new JMenu("Option");
		mnNewMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu.setMargin(new Insets(2, 4, 2, 2));
		mnNewMenu.setRequestFocusEnabled(false);
		mnNewMenu.setOpaque(true);
		mnNewMenu.setMnemonic('O');
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnNewMenu.setBackground(new Color(255, 204, 153));
		mnNewMenu.setHorizontalAlignment(SwingConstants.RIGHT);
		mnNewMenu.setForeground(new Color(0, 51, 255));
		menuBar.add(mnNewMenu);

		JButton btnNewButton_1 = new JButton("Change profile");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setForeground(new Color(51, 153, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ChangeProfile c = new ChangeProfile(account_id);
				c.setLocationRelativeTo(null);
				c.setVisible(true);
			}
		});
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setForeground(new Color(51, 153, 255));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HistoryOrder().setVisible(true);
			}
		});

		mnNewMenu.add(btnNewButton_2);
		mnNewMenu.add(btnNewButton_1);

		JButton btnNewButton = new JButton("Change password");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setForeground(new Color(51, 153, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBackground(new Color(255, 255, 255));
		mnNewMenu.add(btnNewButton);

		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logoutBtn.setForeground(new Color(51, 153, 255));
		logoutBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		logoutBtn.setBackground(new Color(255, 255, 255));
		mnNewMenu.add(logoutBtn);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 153));
		menuBar.add(panel);
		panel.setLayout(null);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setBorder(null);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		btnNewButton_3.setBackground(new Color(255, 204, 153));
		btnNewButton_3.setFocusTraversalKeysEnabled(false);
		btnNewButton_3.setFocusPainted(false);
		btnNewButton_3.setIcon(new ImageIcon(StaffHome.class.getResource("/design/icons8_delete_24px.png")));
		btnNewButton_3.setBounds(659, 0, 24, 24);
		panel.add(btnNewButton_3);
		btnNewButton_3_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		btnNewButton_3_1.setIcon(new ImageIcon(StaffHome.class.getResource("/design/icons8_expand_arrow_24px.png")));
		btnNewButton_3_1.setFocusTraversalKeysEnabled(false);
		btnNewButton_3_1.setFocusPainted(false);
		btnNewButton_3_1.setBorder(null);
		btnNewButton_3_1.setBackground(new Color(255, 204, 153));
		btnNewButton_3_1.setBounds(625, 0, 24, 24);

		panel.add(btnNewButton_3_1);
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Are you sure to want to logout?");
				if(option == JOptionPane.YES_OPTION) {
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
				if(pass!=null && pass.length()>0) {
					accountDao.changePassword(account_id, pass);
					JOptionPane.showMessageDialog(null, "Password changed!");
				}
			}
		});

		slide();
		showMovie();
	}

	private void slide() {
		contentPane = new JLayeredPane();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		moviePane.setForeground(new Color(255, 204, 153));
		moviePane.setBackground(new Color(255, 204, 153));

		// moviePane
		contentPane.add(moviePane);
		moviePaneInit(moviePane);

		JLabel lblNewLabel_5 = new JLabel("Now showing");
		lblNewLabel_5.setIcon(new ImageIcon(StaffHome.class.getResource("/design/icons8_film_reel_50px.png")));
		lblNewLabel_5.setForeground(new Color(51, 153, 204));
		lblNewLabel_5.setBackground(Color.WHITE);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		lblNewLabel_5.setBounds(36, 11, 720, 55);
		moviePane.add(lblNewLabel_5);

//		seatPaneInit(seatPane,new Showtime(1, 1, 1, new Date(), "A"));
		seatPane.setBackground(new Color(255, 204, 153));

//		itemPaneInit(itemPane, null);

		contentPane.add(seatPane);
		seatPane.setLayout(null);
		seatChosen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		seatChosen.setForeground(SystemColor.textHighlight);
		seatChosen.setBackground(SystemColor.textHighlightText);

		seatChosen.setBounds(100, 480, 374, 40);
		seatPane.add(seatChosen);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(SystemColor.textHighlight);
		lblNewLabel_1.setBackground(SystemColor.textHighlightText);

		lblNewLabel_1.setBounds(10, 480, 80, 40);

		seatPane.add(lblNewLabel_1);
		seat_price.setForeground(SystemColor.textHighlight);

		seat_price.setBounds(484, 480, 54, 40);
		seatPane.add(seat_price);
		tvImg.setBounds(10, 11, 764, 266);

		seatPane.add(tvImg);
		itemPane.setBackground(new Color(255, 204, 153));
		// seatPane
		contentPane.add(itemPane);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setForeground(SystemColor.textHighlight);
		lblNewLabel_3.setBounds(341, 436, 46, 31);

		itemPane.add(lblNewLabel_3);
		item_price.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item_price.setForeground(SystemColor.textHighlight);
		item_price.setBounds(397, 436, 91, 31);

		itemPane.add(item_price);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setForeground(SystemColor.textHighlight);

		itemPane.add(lblNewLabel_4);
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4_1.setForeground(SystemColor.textHighlight);

		itemPane.add(lblNewLabel_4_1);

	}

	private void showMovie() {
		Movie m = list.get(current);
		movie_id.setHorizontalAlignment(SwingConstants.CENTER);
		movie_id.setText("id:" + m.getId());
		name.setForeground(new Color(51, 153, 204));
		name.setFont(new Font("Arial", Font.BOLD, 14));
		name.setText("Name: " + m.getName());
		price.setForeground(new Color(51, 153, 204));
		price.setFont(new Font("Arial", Font.PLAIN, 14));
		price.setText("Price: " + m.getPrice());
		image.setIcon(createImgIcon(m.getImage()));
	}

	private ImageIcon createImgIcon(String imgName) {
		if (StaffHome.class.getResource("/images/" + imgName) != null) {
			ImageIcon i = new ImageIcon(new ImageIcon(StaffHome.class.getResource("/images/" + imgName)).getImage()
				 	.getScaledInstance(764, 384, Image.SCALE_SMOOTH));
			return i;
		}else if (StaffHome.class.getResource("src/images/" + imgName) != null) {
			ImageIcon i = new ImageIcon(new ImageIcon(StaffHome.class.getResource("src/images/" + imgName)).getImage()
					.getScaledInstance(764, 384, Image.SCALE_SMOOTH));
			return i;
		}
		return new ImageIcon();
	}

	private void changeLayout(JPanel pane) {
		contentPane.removeAll();
		contentPane.add(pane);
		contentPane.repaint();
		contentPane.revalidate();
	}

	private void moviePaneInit(JPanel moviePane) {
		date.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		date.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (date.getDate() != null) {
					List<Showtime> showList = showDao.getByMovieId(list.get(current).getId(), date.getDate());
					DefaultComboBoxModel<Showtime> model = new DefaultComboBoxModel<Showtime>();
					model.addAll(showList);
					shows.setModel(model);
				}
			}
		});
		lblNewLabel.setForeground(new Color(51, 153, 204));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(259, 455, 46, 31);

		moviePane.add(lblNewLabel);
		date.setBounds(314, 455, 159, 31);
		moviePane.add(date);
		shows.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		shows.setBackground(SystemColor.textHighlightText);
		shows.setFocusTraversalKeysEnabled(false);
		shows.setForeground(new Color(51, 51, 204));
		shows.setFont(new Font("Tahoma", Font.PLAIN, 14));
		shows.setBounds(490, 455, 181, 31);
		moviePane.add(shows);
		moviePane.setBounds(0, 0, 784, 539);
		moviePane.setLayout(null);
		image.setBounds(10, 64, 764, 384);
		moviePane.add(image);
		first.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		first.setFocusTraversalKeysEnabled(false);
		first.setFocusPainted(false);
		first.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, Color.CYAN));
		first.setIcon(new ImageIcon(StaffHome.class.getResource("/design/icons8_first_22px_3.png")));
		first.setBackground(new Color(255, 255, 255));
		first.setForeground(new Color(0, 153, 204));
		first.setFont(new Font("Tahoma", Font.PLAIN, 14));
		first.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				date.setDate(null);
				shows.setSelectedItem(null);
				current = 0;
				showMovie();
			}
		});

		first.setBounds(36, 497, 89, 31);
		moviePane.add(first);
		prev.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		prev.setFocusTraversalKeysEnabled(false);
		prev.setFocusPainted(false);
		prev.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, Color.CYAN));
		prev.setIcon(new ImageIcon(StaffHome.class.getResource("/design/icons8_previous_22px_3.png")));
		prev.setBackground(new Color(255, 255, 255));
		prev.setForeground(new Color(0, 153, 204));
		prev.setFont(new Font("Tahoma", Font.PLAIN, 14));

		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (current > 0) {
					date.setDate(null);
					shows.setSelectedItem(null);
					current--;
					showMovie();
				}
			}
		});
		prev.setBounds(135, 497, 89, 31);
		moviePane.add(prev);
		next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		next.setFocusTraversalKeysEnabled(false);
		next.setFocusPainted(false);
		next.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, Color.CYAN));
		next.setIcon(new ImageIcon(StaffHome.class.getResource("/design/icons8_next_22px_4.png")));
		next.setBackground(new Color(255, 255, 255));
		next.setForeground(new Color(0, 153, 204));
		next.setFont(new Font("Tahoma", Font.PLAIN, 14));
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (current < size - 1) {
					date.setDate(null);
					shows.setSelectedItem(null);
					current++;
					showMovie();
				}
			}
		});

		next.setBounds(235, 497, 89, 31);
		moviePane.add(next);
		last.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		last.setFocusTraversalKeysEnabled(false);
		last.setFocusPainted(false);
		last.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, Color.CYAN));
		last.setIcon(new ImageIcon(StaffHome.class.getResource("/design/icons8_last_22px_1.png")));
		last.setBackground(new Color(255, 255, 255));
		last.setForeground(new Color(0, 153, 204));
		last.setFont(new Font("Tahoma", Font.PLAIN, 14));
		last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				date.setDate(null);
				shows.setSelectedItem(null);
				current = size - 1;
				showMovie();
			}
		});

		last.setBounds(334, 497, 89, 31);
		moviePane.add(last);
		movieNextBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		movieNextBtn.setFocusTraversalKeysEnabled(false);
		movieNextBtn.setFocusPainted(false);
		movieNextBtn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, Color.CYAN));
		movieNextBtn.setIcon(new ImageIcon(StaffHome.class.getResource("/design/icons8_enter_22px.png")));
		movieNextBtn.setBackground(new Color(255, 255, 255));
		movieNextBtn.setForeground(new Color(0, 153, 204));
		movieNextBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		movieNextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (shows.getSelectedItem() != null) {
					Showtime s = (Showtime) shows.getSelectedItem();
					changeLayout(seatPane);
					// showPane
					seatPaneInit(seatPane, s);
				}
			}
		});

		movieNextBtn.setBounds(670, 497, 105, 31);
		moviePane.add(movieNextBtn);

		name.setBounds(36, 455, 222, 31);
		moviePane.add(name);

		price.setBounds(681, 455, 70, 31);
		moviePane.add(price);

		movie_id.setBounds(0, 11, 1, 1);
		moviePane.add(movie_id);
	}

	private void seatPaneInit(JPanel seatPane, Showtime show) {
		tvImg.setIcon(ImageIc.getImgIcon(this.getClass(), "/design/tv.jpg", 774, 250));
		seatNextBtn.setForeground(new Color(51, 153, 204));
		seatNextBtn.setBackground(SystemColor.textHighlightText);
		seatNextBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		seatNextBtn.setIcon(new ImageIcon(StaffHome.class.getResource("/design/icons8_enter_22px.png")));

		seatNextBtn.setBounds(650, 480, 100, 40);
		seatPane.add(seatNextBtn);
		seatNextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Ticket> ticL = new ArrayList<Ticket>();
				String[] seatArr = seatChosen.getText().split(" ");
				if (seatArr.length > 0) {
					for (int i = 1; i < seatArr.length; i++) {
						Ticket tic = new Ticket();
						tic.setShowtimeId(show.getId());
						tic.setSeatId(Integer.parseInt(seatArr[i]));
						ticL.add(tic);
					}
					changeLayout(itemPane);
					// showPane
					itemPaneInit(itemPane, ticL);
				}
			}
		});

		seatPane.setBounds(0, 0, 784, 539);
		seatPane.setLayout(null);
		List<Ticket> ticList = ticDao.getTicketByShow(show.getId());
		for (int i = 0; i < 9; i++) {
			String seatName = "S" + (i + 1);
			boolean isAvailable = true;
			for (Ticket t : ticList) {
				if (t.getSeatId() == (i + 1))
					isAvailable = false;
			}
			seatPane.add(addToggleButton(seatName, isAvailable, i, 0, seatChosen));
		}
		for (int i = 9; i < 18; i++) {
			String seatName = "A" + (i - 8);
			boolean isAvailable = true;
			for (Ticket t : ticList) {
				if (t.getSeatId() == (i + 1))
					isAvailable = false;
			}
			seatPane.add(addToggleButton(seatName, isAvailable, i - 9, 1, seatChosen));
		}
		for (int i = 18; i < 27; i++) {
			String seatName = "B" + (i - 17);
			boolean isAvailable = true;
			for (Ticket t : ticList) {
				if (t.getSeatId() == (i + 1))
					isAvailable = false;
			}
			seatPane.add(addToggleButton(seatName, isAvailable, i - 18, 2, seatChosen));
		}
	}

	private void itemPaneInit(JPanel itemPane, List<Ticket> ticL) {
		lblNewLabel_4.setBounds(20, 381, 129, 42);
		lblNewLabel_4_1.setBounds(402, 381, 120, 42);
		itemPane.setBounds(0, 0, 784, 539);
		itemPane.setLayout(null);

		popcorn.setIcon(new ImageIcon(new ImageIcon(StaffHome.class.getResource("/items/popcorn.jpg")).getImage()
				.getScaledInstance(382, 339, Image.SCALE_SMOOTH)));
		popcorn.setBounds(10, 21, 382, 339);
		itemPane.add(popcorn);

		coca.setIcon(new ImageIcon(new ImageIcon(StaffHome.class.getResource("/items/coca.jpg")).getImage()
				.getScaledInstance(382, 339, Image.SCALE_SMOOTH)));
		coca.setBounds(392, 21, 382, 339);
		itemPane.add(coca);
		pop.setForeground(new Color(0, 51, 255));
		pop.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int price = Integer.parseInt(String.valueOf(pop.getValue()));
				int price2 = Integer.parseInt(String.valueOf(coc.getValue()));
				item_price.setText((price + price2) + " $");
			}
		});

		pop.setModel(new SpinnerNumberModel(0, 0, null, 1));
		pop.setBounds(260, 383, 132, 42);
		itemPane.add(pop);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setForeground(SystemColor.textHighlight);

		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(159, 383, 91, 39);
		itemPane.add(lblNewLabel_2);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setForeground(SystemColor.textHighlight);

		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setBounds(532, 383, 100, 42);
		itemPane.add(lblNewLabel_2_1);
		coc.setForeground(SystemColor.text);

		coc.setModel(new SpinnerNumberModel(0, 0, null, 1));
		coc.setBounds(642, 383, 132, 42);
		itemPane.add(coc);
		coc.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int price = Integer.parseInt(String.valueOf(pop.getValue()));
				int price2 = Integer.parseInt(String.valueOf(coc.getValue()));
				item_price.setText((price + price2) + " $");
			}
		});
		done.setIcon(new ImageIcon(StaffHome.class.getResource("/design/icons8_tick_box_24px.png")));
		done.setBackground(new Color(255, 255, 255));
		done.setForeground(new Color(0, 51, 255));
		done.setFont(new Font("Tahoma", Font.PLAIN, 16));

		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null, "Enter customer name");
				if (!name.isBlank() || !name.isEmpty()) {
					int od = orderDao.add(staff_id, name, new Date());
					int order_price = 0;
					for (Ticket tic : ticL) {
						if (tic.getSeatId() < 10) {
							tic.setPrice(3);
							order_price += 3;
						} else if (tic.getSeatId() < 19) {
							tic.setPrice(2);
							order_price += 2;
						} else {
							tic.setPrice(1);
							order_price += 1;
						}
						tic.setOrderId(od);
						ticDao.add(tic);
					}
					int popQuantity = Integer.parseInt(String.valueOf(pop.getValue()));
					int cocQuantity = Integer.parseInt(String.valueOf(coc.getValue()));

					if (popQuantity > 0)
						itemDao.add("popcorn", popQuantity, od);
					if (cocQuantity > 0)
						itemDao.add("coca", cocQuantity, od);
					order_price += (popQuantity + cocQuantity);
					orderDao.setPrice(od, order_price);
					int choice = JOptionPane.showConfirmDialog(null,
							"Successfully ordered. Do you want to make a new order?");
					if (choice == JOptionPane.YES_OPTION) {
						dispose();
						StaffHome sh = new StaffHome(account_id);
						sh.setVisible(true);
						sh.setLocationRelativeTo(null);
					} else {
						dispose();
						new Login().setVisible(true);
					}
				}
			}
		});
		done.setBounds(303, 478, 185, 36);
		itemPane.add(done);
	}

	private JToggleButton addToggleButton(String name, boolean isAvailable, int x, int y, JLabel lbl) {
		JToggleButton btn = new JToggleButton((x + 1 + 9 * y) + "-" + name);
		btn.setBounds(60 + x * 75, 280 + y * 55, 65, 50);
		btn.setBackground(Color.white);
		btn.setForeground(new Color(51, 103, 204));
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		btn.setFont(new Font("Segoe UI", Font.BOLD, 11));
		if (!isAvailable)
			btn.setEnabled(false);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btn.isSelected()) {
					int price = Integer.parseInt(seat_price.getText().substring(0, 1));
					if ((x + 1 + 9 * y) < 10) {
						price += 3;
					} else if ((x + 1 + 9 * y) < 19) {
						price += 2;
					} else {
						price++;
					}
					seat_price.setText(price + " $");
					String text = lbl.getText();
					lbl.setText(text + " " + (x + 1 + 9 * y));
				} else {
					int price = Integer.parseInt(seat_price.getText().substring(0, 1));
					if ((x + 1 + 9 * y) < 10) {
						price -= 3;
					} else if ((x + 1 + 9 * y) < 19) {
						price -= 2;
					} else {
						price--;
					}
					seat_price.setText(price + " $");
					String text = lbl.getText();
					String n = " " + (x + 1 + 9 * y);
					lbl.setText(text.replace(n, ""));
				}
			}
		});
		return btn;
	}
}
