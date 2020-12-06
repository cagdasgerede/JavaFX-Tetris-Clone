package com.quirko.gui;

import javax.swing.JFrame;
public class MenuFrame extends JFrame{
	
	/*
	 Creates a frame by calling the MenuPanel class.
	*/
	private static final long serialVersionUID = 1L;
	private int width = 600, height = 400;

	public MenuFrame() {
		this.setTitle("Escape Menu");
		MenuPanel panel = new MenuPanel();
		getContentPane().add(panel, "Center");
		this.setSize(width, height); 
		this.setResizable(false); 
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
		this.setVisible(true);
	}
 
	 
}