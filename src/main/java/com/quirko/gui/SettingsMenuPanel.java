package com.quirko.gui;

import java.awt.*;
import java.awt.event.*;
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

		speedSettings = new JButton("SPEED SETTINGS");
		panel.add(speedSettings);
		speedSettings.addActionListener(new myActionListener());
		speedSettings.setBackground(Color.ORANGE);

		this.getContentPane().add(panel , "CENTER");
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setFocusable(true);
		this.addKeyListener(new myActionListener());
		this.setVisible(true);
	}

	private class myActionListener implements ActionListener, KeyListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == speedSettings){
				new SpeedSettingsMenuPanel();
			}
			SettingsMenuPanel.this.dispose();
		}
		 
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				SettingsMenuPanel.this.dispose();
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}
