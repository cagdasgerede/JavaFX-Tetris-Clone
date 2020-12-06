package com.quirko.gui;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SettingsMenuPanel extends JPanel implements KeyListener {
	/*
	This class is a settings menu.
	By pressing the settings button from the player exit menu-
	comes to this section and from here view "speed settings".
	*/

	private static final long serialVersionUID = 1L;
	private int width, height;
	static SpeedSettingsFrame gamespeedsettingsmenuframe;

    public SettingsMenuPanel() {
		this.setFocusable(true);
		this.addKeyListener(this);
		this.setBackground(Color.DARK_GRAY);

	}
    
    private String[] menus = new String[] { "SPEED SETTINGS"};

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
		switch (keyCode) {
			case KeyEvent.VK_UP:// up direction key
				focusIndex = (focusIndex + menus.length - 1) % menus.length; // Consider the meaning of % remainder.
				this.repaint(); 
				break;
			case KeyEvent.VK_DOWN:// down arrow
				focusIndex = (focusIndex + 1) % menus.length;
				this.repaint();
				break;
            case KeyEvent.VK_ENTER:
                
                //The user pressed the speed settings button.
                if(focusIndex == 0){
					gamespeedsettingsmenuframe = new SpeedSettingsFrame();
                }

            case KeyEvent.VK_ESCAPE:
                MenuPanel.settingFrame.dispose();
        }
               
            
        }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
}
