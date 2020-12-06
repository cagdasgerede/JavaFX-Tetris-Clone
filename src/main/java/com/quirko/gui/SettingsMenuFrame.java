package com.quirko.gui;
import javax.swing.JFrame;
public class SettingsMenuFrame extends JFrame {
    /*
	Creates a frame by calling the SettingsMenuPanel class.
	*/
	private static final long serialVersionUID = 1L;
	private int width = 600, height = 400;

    public SettingsMenuFrame() {
		this.setTitle("Settings Menu");
		SettingsMenuPanel panel = new SettingsMenuPanel();
		getContentPane().add(panel, "Center");
		this.setSize(width, height); 
		this.setResizable(false); 
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
		this.setVisible(true);
	}
}
