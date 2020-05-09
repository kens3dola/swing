package com.swing.frame;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.swing.dao.OrderDao;
import com.swing.model.HistoryOrderModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class HistoryOrder extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table = new JTable();
	private final JLabel lblNewLabel = new JLabel("History order");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoryOrder frame = new HistoryOrder();
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
	public HistoryOrder() {
		setTitle("Orders");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.text);
		panel.setBounds(0, 0, 1600, 838);
		contentPane.add(panel);
		panel.setLayout(null);

        DefaultTableModel model = new DefaultTableModel();
		List<HistoryOrderModel> list = new OrderDao().getHistoryOrder();
		model.addColumn("Order id");
		model.addColumn("Staff id");
		model.addColumn("Customer");
		model.addColumn("Order date");
		model.addColumn("Items");
		model.addColumn("Seats");
		model.addColumn("Room");
		model.addColumn("Time");
		model.addColumn("Movie");
		model.addColumn("Date");
		model.addColumn("Total price");
		for(HistoryOrderModel h:list) {
			model.addRow(new Object[] {h.getOrder_id(),h.getStaff_id(),h.getCustomer_name(),h.getOrder_date(),h.getItems(),h.getSeats(),h.getRoom(),h.getShowtime(),h.getMovie(),h.getShow_date(),h.getPrice()});
		}
        table.setForeground(SystemColor.textHighlight);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setBackground(SystemColor.text);
        table.setFillsViewportHeight(true);
        table.setAlignmentX(Component.RIGHT_ALIGNMENT);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        table.getTableHeader().setBackground(Color.white);
        table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.getTableHeader().setForeground(SystemColor.textHighlight);
        table.setModel(model);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(8).setPreferredWidth(100);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 93, 1580, 734);
		panel.add(scrollPane);
		lblNewLabel.setIcon(new ImageIcon(HistoryOrder.class.getResource("/design/icons8_order_history_50px.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(10, 11, 1580, 71);
		panel.add(lblNewLabel);
	}
}
