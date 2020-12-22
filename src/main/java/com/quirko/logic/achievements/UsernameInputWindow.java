package com.quirko.logic.achievements;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputListener;

import com.quirko.gui.Main;

public class UsernameInputWindow {

    public static String username;

    private JFrame frame;
    String[] args;

    private final int WIDTH = 250;
    private final int HEIGHT = 185;

    JButton btnSave;
    JLabel label;
    JTextField txtField;

    public UsernameInputWindow(String[] args) {
        this.args = args;
        setup();
    }

    private void setup() {
        frame = new JFrame("Username");
        frame.setResizable(false);
        frame.setBounds(0, 0, WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

        label = new JLabel("Please enter your username");
        label.setFont(new Font("Myriad Web Pro", Font.BOLD, 14));
        label.setBounds(27, 15, 180, 30);
        label.setVisible(true);
        frame.add(label);

        txtField = new JTextField();
        txtField.setEditable(true);
        txtField.setFont(new Font("Myriad Web Pro", Font.PLAIN, 14));
        txtField.setHorizontalAlignment(SwingConstants.CENTER);
        txtField.setBounds(27, 55, 180, 30);
        txtField.setVisible(true);
        frame.add(txtField);

        btnSave = new JButton("Save");
        btnSave.setBounds((WIDTH - 90) / 2 - 8, HEIGHT - 85, 90, 30);
        btnSave.addMouseListener(new MouseInputListener(){
			public void mousePressed(java.awt.event.MouseEvent e) {
                if (txtField.getText() == null || txtField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Warning! Username cannot be left empty.", "Invalid Username", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    username = txtField.getText();
                    frame.dispose();
                    Main.main(args);
                }
            }
            
            public void mouseClicked(java.awt.event.MouseEvent e) {}

			public void mouseReleased(java.awt.event.MouseEvent e) {}

			public void mouseEntered(java.awt.event.MouseEvent e) {}

			public void mouseExited(java.awt.event.MouseEvent e) {}

			public void mouseDragged(java.awt.event.MouseEvent e) {}

			public void mouseMoved(java.awt.event.MouseEvent e) {}
        });

        btnSave.setVisible(true);
        frame.add(btnSave);

        frame.setVisible(true);
    }

}
