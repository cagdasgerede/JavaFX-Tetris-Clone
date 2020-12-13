package com.quirko.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SpeedSettingsMenuPanel extends JFrame{
	 
	private static final long serialVersionUID = 1L;

	JButton tooSlowSpeed, slowSpeed, fastSpeed, tooFastSpeed;
	private int width = 400, height = 510;
	private double speed;

	public SpeedSettingsMenuPanel() {
		this.setTitle("Speed Settings");
		this.setSize(width, height);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		tooSlowSpeed = new JButton("TOO SLOW");
		panel.add(tooSlowSpeed);
		tooSlowSpeed.addActionListener(new myActionListener());
		tooSlowSpeed.setLocation(300, 180);
		tooSlowSpeed.setBackground(Color.BLUE);

		slowSpeed = new JButton("SLOW");
		panel.add(slowSpeed);
		slowSpeed.addActionListener(new myActionListener());
		slowSpeed.setBackground(Color.GREEN);

		fastSpeed = new JButton("FAST");
		panel.add(fastSpeed);
		fastSpeed.addActionListener(new myActionListener());
		fastSpeed.setBackground(Color.YELLOW);

		tooFastSpeed = new JButton("TOO FAST");
		panel.add(tooFastSpeed);
		tooFastSpeed.addActionListener(new myActionListener());
		tooFastSpeed.setBackground(Color.RED);

		this.getContentPane().add(panel , "CENTER");
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.addKeyListener(new myActionListener());
		this.setFocusable(true);
		this.setVisible(true);
	}

	private class myActionListener implements ActionListener , KeyListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == tooSlowSpeed) speed = 1.0;

			else if(e.getSource() == slowSpeed) speed = 2.0;
			 
			else if(e.getSource() == fastSpeed) speed = 4.0;
			 
			else if(e.getSource() == tooFastSpeed) speed = 5.0;
			 
			SpeedSettingsMenuPanel.this.dispose();
			GuiController.getTimeLine().setRate(speed);
		}

		@Override
		public void keyPressed(KeyEvent e) {
		   if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				SpeedSettingsMenuPanel.this.dispose();
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
