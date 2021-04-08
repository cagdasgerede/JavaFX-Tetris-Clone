package com.quirko.achievements;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;  
import java.awt.event.*;
public class AchievementPopUp extends JFrame{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JFrame frame;
    JLabel label;
    JButton but;
    public AchievementPopUp(Achievement e){
        frame=new JFrame("Achievement unlocked.");
        label=new JLabel(e.description);
        but=new JButton("OK");
        but.addActionListener(new buttonHandler());
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.add(label);
        frame.add(but);
        frame.setVisible(true);
    }    
    private class buttonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
        
    }
}