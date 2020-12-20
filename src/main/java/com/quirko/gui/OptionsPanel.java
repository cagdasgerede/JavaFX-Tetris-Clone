package com.quirko.gui;

import java.util.*;
import java.awt.*;
import java.awt.color.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.GridLayout;

import com.quirko.logic.MatrixOperations;
import com.quirko.logic.SimpleBoard;

public class OptionsPanel extends JFrame implements ActionListener{

	JButton commit;
	JTextArea selectedRadius;
	JTextArea selectedFrequency;

	public OptionsPanel(){
		this.setTitle("Options");
		this.setBackground(Color.DARK_GRAY);
		this.setSize(300, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(2,1));
		this.setBackground(Color.BLACK); 
		selectedRadius = new JTextArea(50,50);
		selectedRadius.setBackground(Color.GRAY);
		selectedRadius.setFont(new Font("Impact", Font.BOLD, 25));
		selectedRadius.setText("Selected radius: "+MatrixOperations.getRadius());
		selectedFrequency = new JTextArea(100,50);
		selectedFrequency.setFont(new Font("Impact", Font.BOLD, 25));
		selectedFrequency.setText("Selected frequency: "+SimpleBoard.getFrequency());
		selectedFrequency.setBackground(Color.GRAY);
		selectedFrequency.setEditable(false);
		selectedRadius.setEditable(false);
		JMenuBar mb=new JMenuBar();  
		JMenu menu=new JMenu("Frequency"); 
		JMenuItem i1=new JMenuItem("5");  
		JMenuItem i2=new JMenuItem("10");  
		JMenuItem i3=new JMenuItem("15");  
		JMenuItem i4=new JMenuItem("20");  
		JMenuItem i5=new JMenuItem("25");  
		menu.add(i1); menu.add(i2); menu.add(i3); menu.add(i4); menu.add(i5);
		JMenu menu1=new JMenu("Radius"); 
		JMenuItem i6=new JMenuItem("3");  
		JMenuItem i7=new JMenuItem("5");  
		JMenuItem i8=new JMenuItem("7");  
		JMenuItem i9=new JMenuItem("9"); 
		//I gave variables 3,5,7 and 9 because if the bomb exploids items symetrically 		 
		menu1.add(i6); menu1.add(i7); menu1.add(i8); menu1.add(i9); 
		i1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
					SimpleBoard.setFrequency(5);
					selectedFrequency.setText("Selected Frequency: 5");
			}
		});
		i2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				SimpleBoard.setFrequency(10);
				selectedFrequency.setText("Selected Frequency: 10");
			}
		});
		i3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				SimpleBoard.setFrequency(15);
				selectedFrequency.setText("Selected Frequency: 15");
			}
		});
		i4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				SimpleBoard.setFrequency(20);
				selectedFrequency.setText("Selected Frequency: 20");
			}
		});
		i5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				SimpleBoard.setFrequency(25);
				selectedFrequency.setText("Selected Frequency: 25");
			}
		});
		i6.addActionListener(this);
		i7.addActionListener(this);
		i8.addActionListener(this);
		i9.addActionListener(this);
		mb.add(menu);
		mb.add(menu1);
		this.setJMenuBar(mb);   
		this.add(selectedRadius);
		this.add(selectedFrequency); 
        this.setVisible(true); 
	}

	public void actionPerformed(ActionEvent e){
		String act_command = e.getActionCommand();
		if(act_command.equals("3")){
			MatrixOperations.setRadius(3);
			selectedRadius.setText("Selected radius: 3");
		}
		else if(act_command.equals("5")){
			MatrixOperations.setRadius(5);
			selectedRadius.setText("Selected radius: 5");
		}
		else if(act_command.equals("7")){
			MatrixOperations.setRadius(7);
			selectedRadius.setText("Selected radius: 7");
		}
		if(act_command.equals("9")){
			MatrixOperations.setRadius(9);
			selectedRadius.setText("Selected radius: 9");
		}

	}

}