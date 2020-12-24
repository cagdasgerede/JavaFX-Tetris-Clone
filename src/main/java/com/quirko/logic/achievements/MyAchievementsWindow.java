package com.quirko.logic.achievements;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;


public class MyAchievementsWindow {
    
    private final int WIDTH = 260;
    private final int HEIGHT = 207;
    
    JFrame frame;
    JLabel lblUsername, lblTotal, lblAtOnce, lblScored;

    ArrayList<JLabel> achievements;

    public MyAchievementsWindow(String username, int totalCleared, int atOnce, int scored) {
        frame = new JFrame("Achievements");
        frame.setResizable(false);
        frame.setBounds(0, 0, WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

        lblUsername = new JLabel(username + "'s Best Achievements");
        lblUsername.setFont(new Font("Myriad Web Pro", Font.BOLD, 18));
        lblUsername.setBounds(20, 8, 240, 30);
        lblUsername.setVisible(true);
        frame.add(lblUsername);

        JSeparator sep1 = new JSeparator();
		sep1.setBounds(0, 47, WIDTH, 2);
		frame.add(sep1);

        lblTotal = new JLabel("Total lines cleared:           " + totalCleared);
        lblTotal.setFont(new Font("Myriad Web Pro", Font.BOLD, 14));
        lblTotal.setBounds(27, 52, 240, 30);
        lblTotal.setVisible(true);
        frame.add(lblTotal);

        JSeparator sep2 = new JSeparator();
		sep2.setBounds(0, 87, WIDTH, 2);
		frame.add(sep2);

        lblAtOnce = new JLabel("Lines cleared at once:     " + atOnce);
        lblAtOnce.setFont(new Font("Myriad Web Pro", Font.BOLD, 14));
        lblAtOnce.setBounds(27, 92, 240, 30);
        lblAtOnce.setVisible(true);
        frame.add(lblAtOnce);

        JSeparator sep3 = new JSeparator();
		sep3.setBounds(0, 127, WIDTH, 2);
		frame.add(sep3);

        lblScored = new JLabel("Highest score:                     " + scored);
        lblScored.setFont(new Font("Myriad Web Pro", Font.BOLD, 14));
        lblScored.setBounds(27, 131, 240, 30);
        lblScored.setVisible(true);
        frame.add(lblScored);

        frame.setVisible(true);
    }

    public void dispose() {
        frame.dispose();
    }
}
