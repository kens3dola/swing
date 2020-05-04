package com.swing.frame.staff;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
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

import com.swing.dao.AccountDao;
import com.swing.dao.ItemDao;
import com.swing.dao.MovieDao;
import com.swing.dao.OrderDao;
import com.swing.dao.ScheduleDao;
import com.swing.dao.ShowtimeDao;
import com.swing.dao.StaffDao;
import com.swing.dao.TicketDao;
import com.swing.frame.ChangePassword;
import com.swing.frame.Login;
import com.swing.model.Movie;
import com.swing.model.Order;
import com.swing.model.Showtime;
import com.swing.model.Ticket;
import com.toedter.calendar.JDateChooser;

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
	private ScheduleDao scheDao;
	private ShowtimeDao showDao;
	private TicketDao ticDao;
	private OrderDao orderDao;
	private ItemDao itemDao;
	private StaffDao staffDao;

	private List<Movie> list;
	int current = 0, size;

	JLabel image = new JLabel("");
	JButton first = new JButton("First");
	JButton prev = new JButton("Previous");
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

	JLabel popcorn = new JLabel("");
	JLabel coca = new JLabel("");
	JSpinner pop = new JSpinner();
	JLabel lblNewLabel_2 = new JLabel("Quantity:");
	JLabel lblNewLabel_2_1 = new JLabel("Quantity:");
	JSpinner coc = new JSpinner();
	JButton done = new JButton("Done");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffHome frame = new StaffHome(0);
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
	public StaffHome(int id) {
		try {
			accountDao = new AccountDao();
			movieDao = new MovieDao();
			scheDao = new ScheduleDao();
			showDao = new ShowtimeDao();
			ticDao = new TicketDao();
			orderDao = new OrderDao();
			itemDao = new ItemDao();
			staffDao = new StaffDao();
			list = movieDao.list(movieDao.getAllMovies());
			size = list.size();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		account_id = id;
		staff_id = staffDao.getStaff(id).getId();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 800, 600);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		menuBar.setBorderPainted(false);
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Option");
		mnNewMenu.setBackground(Color.WHITE);
		mnNewMenu.setHorizontalAlignment(SwingConstants.RIGHT);
		mnNewMenu.setForeground(Color.BLACK);
		menuBar.add(mnNewMenu);

		JButton btnNewButton = new JButton("Change password");
		btnNewButton.setBackground(Color.WHITE);
		mnNewMenu.add(btnNewButton);

		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setBackground(Color.WHITE);
		mnNewMenu.add(logoutBtn);
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Login().setVisible(true);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new ChangePassword(accountDao.getAccountById(id)).setVisible(true);
					;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		slide();
		showMovie();
	}

	private void slide() {
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// moviePane
		contentPane.add(moviePane);
		moviePaneInit(moviePane);

		contentPane.add(seatPane);
		seatPane.setLayout(null);

		seatChosen.setBounds(130, 480, 400, 40);
		seatPane.add(seatChosen);

		lblNewLabel_1.setBounds(10, 480, 120, 40);

		seatPane.add(lblNewLabel_1);
		// seatPane
		contentPane.add(itemPane);

	}

	private void showMovie() {
		Movie m = list.get(current);
		movie_id.setHorizontalAlignment(SwingConstants.CENTER);
		movie_id.setText("id:" + m.getId());
		name.setText("Name: " + m.getName());
		price.setText("Price: " + m.getPrice());
		image.setIcon(createImgIcon(m.getImage()));
	}

	private ImageIcon createImgIcon(String imgName) {
		if (StaffHome.class.getResource("/images/" + imgName) != null) {
			ImageIcon i = new ImageIcon(new ImageIcon(StaffHome.class.getResource("/images/" + imgName)).getImage()
					.getScaledInstance(700, 437, Image.SCALE_SMOOTH));
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

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(234, 459, 70, 31);

		moviePane.add(lblNewLabel);
		date.setBounds(314, 459, 159, 31);
		moviePane.add(date);
		shows.setBounds(489, 459, 181, 31);
		moviePane.add(shows);
		moviePane.setBounds(0, 0, 784, 539);
		moviePane.setLayout(null);
		image.setBounds(56, 11, 700, 437);
		moviePane.add(image);
		first.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current = 0;
				showMovie();
			}
		});

		first.setBounds(36, 505, 89, 23);
		moviePane.add(first);

		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (current > 0) {
					current--;
					showMovie();
				}
			}
		});
		prev.setBounds(135, 505, 89, 23);
		moviePane.add(prev);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (current < size - 1) {
					current++;
					showMovie();
				}
			}
		});

		next.setBounds(234, 505, 89, 23);
		moviePane.add(next);
		last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current = size - 1;
				showMovie();
			}
		});

		last.setBounds(333, 505, 89, 23);
		moviePane.add(last);
		movieNextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (shows.getSelectedItem()!=null) {
					Showtime s = (Showtime) shows.getSelectedItem();
					changeLayout(seatPane);
					// showPane
					seatPaneInit(seatPane, s);
				}
			}
		});

		movieNextBtn.setBounds(685, 505, 89, 23);
		moviePane.add(movieNextBtn);

		name.setBounds(35, 455, 231, 39);
		moviePane.add(name);

		price.setBounds(680, 455, 70, 39);
		moviePane.add(price);

		movie_id.setBounds(0, 11, 46, 433);
		moviePane.add(movie_id);
	}

	private void seatPaneInit(JPanel seatPane, Showtime show) {

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
			String seatName = "A" + (i - 9);
			boolean isAvailable = true;
			for (Ticket t : ticList) {
				if (t.getId() == (i + 1))
					isAvailable = false;
			}
			seatPane.add(addToggleButton(seatName, isAvailable, i - 9, 1, seatChosen));
		}
		for (int i = 18; i < 27; i++) {
			String seatName = "B" + (i - 18);
			boolean isAvailable = true;
			for (Ticket t : ticList) {
				if (t.getId() == (i + 1))
					isAvailable = false;
			}
			seatPane.add(addToggleButton(seatName, isAvailable, i - 18, 2, seatChosen));
		}
	}

	private void itemPaneInit(JPanel itemPane, List<Ticket> ticL) {
		itemPane.setBounds(0, 0, 784, 539);
		itemPane.setLayout(null);

		popcorn.setIcon(new ImageIcon(new ImageIcon(StaffHome.class.getResource("/items/popcorn.jpg")).getImage()
				.getScaledInstance(382, 339, Image.SCALE_SMOOTH)));
		popcorn.setBounds(10, 11, 382, 339);
		itemPane.add(popcorn);

		coca.setIcon(new ImageIcon(new ImageIcon(StaffHome.class.getResource("/items/coca.jpg")).getImage()
				.getScaledInstance(382, 339, Image.SCALE_SMOOTH)));
		coca.setBounds(392, 11, 382, 339);
		itemPane.add(coca);

		pop.setModel(new SpinnerNumberModel(0, 0, null, 1));
		pop.setBounds(167, 361, 52, 42);
		itemPane.add(pop);

		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(63, 361, 80, 36);
		itemPane.add(lblNewLabel_2);

		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setBounds(515, 361, 80, 36);
		itemPane.add(lblNewLabel_2_1);

		coc.setModel(new SpinnerNumberModel(0, 0, null, 1));
		coc.setBounds(616, 361, 52, 42);
		itemPane.add(coc);
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null, "Enter customer name");
				if (!name.isBlank() || !name.isEmpty()) {
					int od = orderDao.add(staff_id, name, new Date());

					for (Ticket tic : ticL) {
						tic.setOrderId(od);
						ticDao.add(tic);
					}
					int popQuantity = Integer.parseInt(String.valueOf(pop.getValue()));
					int cocQuantity = Integer.parseInt(String.valueOf(coc.getValue()));

					if (popQuantity > 0)
						itemDao.add("popcorn", popQuantity, od);
					if (cocQuantity > 0)
						itemDao.add("coca", cocQuantity, od);

					JOptionPane.showConfirmDialog(null, "Successfully ordered!");
					dispose();
					new StaffHome(account_id).setVisible(true);
				}
			}
		});
		done.setBounds(303, 478, 185, 36);
		itemPane.add(done);
	}

	private JToggleButton addToggleButton(String name, boolean isAvailable, int x, int y, JLabel lbl) {
		JToggleButton btn = new JToggleButton((x + 1 + 9 * y) + "-" + name);
		btn.setBounds(60 + x * 75, 280 + y * 55, 65, 50);
		if (!isAvailable)
			btn.setEnabled(false);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btn.isSelected()) {
					String text = lbl.getText();
					lbl.setText(text + " " + (x + 1 + 9 * y));
				} else {
					String text = lbl.getText();
					String n = " " + (x + 1 + 9 * y);
					lbl.setText(text.replace(n, ""));
				}
			}
		});
		return btn;
	}
}
