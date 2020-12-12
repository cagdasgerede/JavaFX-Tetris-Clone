package com.quirko.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.quirko.gui.GuiController;
import com.quirko.app.GameController;


public class DifficultySetPanel extends JFrame implements ActionListener{
	
	public Enum diff;
	
	public DifficultySetPanel(){
		setSize(500,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Difficulty Choice");
		setLayout(new BorderLayout());
		
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new  GridLayout(3,1));
		button_panel.setBackground(Color.BLACK);
		
		JButton easy = new JButton("EASY");
		JButton medium = new JButton("MEDIUM");
		JButton hard = new JButton("HARD");

		easy.addActionListener(this);
		medium.addActionListener(this);
		hard.addActionListener(this);
		
		easy.setBackground(Color.DARK_GRAY);
		medium.setBackground(Color.DARK_GRAY);
		hard.setBackground(Color.DARK_GRAY);

		easy.setForeground(Color.GREEN);
		medium.setForeground(Color.YELLOW);
		hard.setForeground(Color.red);

		easy.setFont(new Font("Arial", Font.BOLD, 40));
		medium.setFont(new Font("Arial", Font.BOLD, 40));
		hard.setFont(new Font("Arial", Font.BOLD, 40));

		button_panel.add(easy);
		button_panel.add(medium);
		button_panel.add(hard);
		
		add(button_panel,BorderLayout.CENTER);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action_command = e.getActionCommand();
		if(action_command.equals("EASY")) {
			this.diff=DifficultyType.EASY;
		}
		else if(action_command.equals("MEDIUM")) {
            this.diff=DifficultyType.MEDIUM;
		}
		else if(action_command.equals("HARD")) {
            this.diff=DifficultyType.HARD;
		}	
	}
	
    public 	Enum getDiff(){
		System.out.println("");
		return this.diff;
	}	
}
