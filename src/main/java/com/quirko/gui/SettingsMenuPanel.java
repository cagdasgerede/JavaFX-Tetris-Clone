package com.quirko.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SettingsMenuPanel extends JFrame{
	 
	private static final long serialVersionUID = 1L;
	JButton speedSettings;
	private int width = 400 , height = 510;

	public SettingsMenuPanel(){
		this.setTitle("Settings Menu");
		this.setSize(width, height);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		this.getContentPane().add(panel , "CENTER");
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setFocusable(true);
		this.setVisible(true);
	}
}
