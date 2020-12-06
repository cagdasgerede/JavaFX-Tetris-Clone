package com.quirko.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class SpeedSettingsMenuPanel extends JPanel implements KeyListener {
	/*
	This class is a settings menu.
	Game speed may change from this window
	*/
	private static final long serialVersionUID = 1L;
	private int width, height;
	static SettingsMenuFrame settingFrame;
	public SpeedSettingsMenuPanel() {
		this.setFocusable(true);
		this.addKeyListener(this);
		this.setBackground(Color.DARK_GRAY);

	}

	private String[] menus = new String[] { "TOO SLOW", "SLOW", "FAST", "TOO FAST" };

	private int focusIndex;

	private int menu_x = 260;

	private int[] menu_ys = new int[] { 80, 110, 140, 170 };

	 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		 
		g.setColor(new Color(0x9391d6));
		g.fillRect(0, 0, width, height);

	 
		for (int i = 0; i < menus.length; i++) {
			int x = menu_x;
			int y = menu_ys[i];

			 
			if (i == focusIndex) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.WHITE);
			}

			g.drawString(menus[i], x, y);
		}
	}

	 
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode(); 
			if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W){  // up direction key
				focusIndex = (focusIndex + menus.length - 1) % menus.length; 
				this.repaint(); 
			}
			if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S){  // down arrow
				focusIndex = (focusIndex + 1) % menus.length;
				this.repaint();
			}
			if(keyCode == KeyEvent.VK_ENTER){ // Enter key
				//Çok yavaş tuşuna basıldıysa hızı 1 yapar...
				if(focusIndex == 0){
					SettingsMenuPanel.gamespeedsettingsmenuframe.dispose();
					GuiController.timeLine.setRate(1.0);
				}
				//Yavaş tuşuna basıldıysa hızı 2 yapar...
				if(focusIndex == 1){
					SettingsMenuPanel.gamespeedsettingsmenuframe.dispose();
					GuiController.timeLine.setRate(2.0);
				}
				//Hızlı tuşuna basıldıysa hızı 4 yapar...
				if(focusIndex == 2){
					SettingsMenuPanel.gamespeedsettingsmenuframe.dispose();
					GuiController.timeLine.setRate(4.0);
				}
				//Çok hızlı tuşuna basıldıysa hızı 5 yapar...
				if(focusIndex == 3){
					SettingsMenuPanel.gamespeedsettingsmenuframe.dispose();
					GuiController.timeLine.setRate(5.0);
				}
			}

			if(keyCode == KeyEvent.VK_ESCAPE){ //Escape Key
				SettingsMenuPanel.gamespeedsettingsmenuframe.dispose();
			}
		}
 
	@Override
	public void keyReleased(KeyEvent e) {
	}
 
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	
}
