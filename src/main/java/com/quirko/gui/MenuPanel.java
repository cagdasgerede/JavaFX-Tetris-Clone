package com.quirko.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuPanel extends JFrame {
	 
	private static final long serialVersionUID = 1L;
	private JButton settings, load, save, scoreBoard, exit;
	private int width = 400 , height = 510;

	public JButton getSettingsButton(){
		return settings;
	}

	public JButton getLoadButton(){
		return load;
	}

	public JButton getSaveButton(){
		return save;
	}

	public JButton getScoreBoardButton(){
		return scoreBoard;
	}

	public JButton getExitButton(){
		return exit;
	}

	public MenuPanel(){
		this.setTitle("Escape Menu");
		this.setSize(width, height);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1, 1, 10));
		panel.setBackground(Color.DARK_GRAY);
		
		settings = new JButton("SETTINGS");
		panel.add(settings);
		settings.addActionListener(new myActionListener());
		settings.setBackground(Color.MAGENTA);

		load = new JButton("LOAD");
		panel.add(load);
		load.addActionListener(new myActionListener());
		load.setBackground(Color.GREEN);

		save = new JButton("SAVE");
		panel.add(save);
		save.addActionListener(new myActionListener());
		save.setBackground(Color.YELLOW);

		scoreBoard = new JButton("SCORE BOARD");
		panel.add(scoreBoard);
		scoreBoard.addActionListener(new myActionListener());
		scoreBoard.setBackground(Color.RED);

		exit = new JButton("EXIT");
		panel.add(exit);
		exit.addActionListener(new myActionListener());
		exit.setBackground(Color.ORANGE);

		this.getContentPane().add(panel);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setFocusable(true);
		this.addKeyListener(new myActionListener());
		this.setVisible(true);
	}

	class myActionListener implements ActionListener, KeyListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			checkWhichButtonPressed(e.getActionCommand());
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				MenuPanel.this.dispose();
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void keyReleased(KeyEvent e) {
		}
	}

	public void save() throws IOException{
	}

	public void load() throws IOException{
	}

	public void loadScore() throws IOException{
	}

	public void exit(){
		System.exit(0);
	}
	
	public void createSettingsMenu(){
		new SettingsMenuPanel();
	}

	public void checkWhichButtonPressed(String button){
		
		if(button.equals("SETTINGS")){
			createSettingsMenu();
		}

		else if(button.equals("LOAD")){
			try {
				load();
			} catch (IOException e) {
				Logger.getLogger(MenuPanel.class.getName()).severe(e.getMessage());
			}
		}

		else if(button.equals("SAVE")){
			try {
				save();
			} catch (IOException e) {
				Logger.getLogger(MenuPanel.class.getName()).severe(e.getMessage());
			}
		}

		else if(button.equals("SCOREBOARD")){
			try {
				loadScore();
			} catch (IOException e) {
				Logger.getLogger(MenuPanel.class.getName()).severe(e.getMessage());
			}
		}

		else if(button.equals("EXIT")){
			exit();
		}
	}
}
