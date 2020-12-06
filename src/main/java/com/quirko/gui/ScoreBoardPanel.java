package com.quirko.gui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ScoreBoardPanel extends JFrame implements KeyListener{

    /*
    It is the class that creates a window showing the scores.
    */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private String [] columns;
    private String [][] rows;

    public ScoreBoardPanel() {
        this.setTitle("ScoreBoard");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 450, 300);
        this.setVisible(true);
        this.setBackground(Color.DARK_GRAY);
        this.addKeyListener(this);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(28, 28, 396, 173);
        contentPane.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        
        columns =new String[2];
        columns[0]="Number";
        columns[1]="Score";
        
        rows=new String[MenuPanel.howManyScore][2];
 
        for(int i = 0; i < rows.length; i++){
            rows[i][0] = String.valueOf(i + 1); 
            rows[i][1] = String.valueOf(MenuPanel.score[i]);
        }
        
        TableModel tablemodel=new DefaultTableModel(rows,columns);
        
        table.setModel(tablemodel);
        table.setBackground(Color.WHITE);
        table.setSelectionBackground(Color.GRAY);
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode(); 
		switch (keyCode) {
            case KeyEvent.VK_ESCAPE:
             MenuPanel.scoreboard.dispose();
        }
    }
 
    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
}
