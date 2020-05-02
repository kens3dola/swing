package com.swing.frame.staff;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Example implements Runnable {
    private JPanel jPanel;
    private JFrame jFrame;
    private JList<Algorithm> diagnosisList;
    private DefaultListModel<Algorithm> model;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Example());
    }

    @Override
    public void run() {
        jFrame = new JFrame();
        jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jFrame.getContentPane().add(jPanel);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jPanel.setPreferredSize(new Dimension(500, 300));
        model = new DefaultListModel<>();
        diagnosisList = new JList<Algorithm>(model);

        for (Algorithm a : Algorithm.values())
            model.addElement(a);

        jPanel.add(diagnosisList);

        jFrame.pack();
        jFrame.setVisible(true);
    }

    public static enum Algorithm {
        BFS, DFS, A_STAR
    }
}