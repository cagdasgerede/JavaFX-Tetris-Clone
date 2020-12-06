package com.quirko.gui;

import javax.swing.JFrame;
public class SpeedSettingsFrame extends JFrame{
	
	/*
	 Creates a frame by calling the SpeedSettingsMenuPanel class.
	*/
	private static final long serialVersionUID = 1L;
	private int width = 600, height = 400;

	public SpeedSettingsFrame() {
		this.setTitle("Speed Settings Menu");
		SpeedSettingsMenuPanel panel = new SpeedSettingsMenuPanel();
		getContentPane().add(panel, "Center");
		this.setSize(width, height); 
		this.setResizable(false); 
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
		this.setVisible(true);
	}
 
	 
}
