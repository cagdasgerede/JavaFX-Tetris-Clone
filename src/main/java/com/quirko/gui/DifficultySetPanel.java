package com.quirko.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
    public String diff;
	public DifficultySetPanel(){
		diff="";
		setSize(500,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Difficulty Choice");
		setLayout(new BorderLayout());
		
		JPanel tus_paneli = new JPanel();
		tus_paneli.setLayout(new  GridLayout(3,1));
		tus_paneli.setBackground(Color.cyan);
		
		JButton easy = new JButton("EASY");
		JButton medium = new JButton("MEDIUM");
		JButton hard = new JButton("HARD");
		
		easy.addActionListener(this);
		medium.addActionListener(this);
		hard.addActionListener(this);
		
		tus_paneli.add(easy);
		tus_paneli.add(medium);
		tus_paneli.add(hard);
		
		add(tus_paneli,BorderLayout.CENTER);

		setVisible(true);
		System.out.println(diff);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		String action_command = e.getActionCommand();
		if(action_command.equals("EASY")) {
			this.diff="easy";
			System.out.println(diff);
            //this.dispose();
		}
		else if(action_command.equals("MEDIUM")) {
            this.diff="medium";
           // this.dispose();
			
		}
		else if(action_command.equals("HARD")) {
            this.diff="hard";
         //   this.dispose();
		}
		
		
		
    }
    public String getDiff(){
		System.out.println(diff);
		return this.diff;
	}
	
}