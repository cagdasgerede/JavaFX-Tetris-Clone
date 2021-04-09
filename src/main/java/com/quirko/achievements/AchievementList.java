package com.quirko.achievements;

import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class AchievementList {
    ArrayList<Achievement> achievements;
    public AchievementList(){
        achievements=new ArrayList<Achievement>();
    }
    public String[] namesToStringArray(){
        String[] temp=new String[achievements.size()+1];
        temp[0]="Achievement Name";
        for (int i = 1; i < temp.length; i++) {
            temp[i]=achievements.get(i-1).name;
        }
        return temp;
    }
    public String[] completedToStringArray(){
        String[] temp=new String[achievements.size()+1];
        temp[0]="Progress";
        for (int i = 1; i < temp.length; i++) {
            if(achievements.get(i-1).completed)
                temp[i]="completed";
            else
                temp[i]="not completed";
        }
        return temp;
    }
    public void showAchievements(){
        JList<String> nameList=new JList<String>(namesToStringArray());
        JList<String> completedList=new JList<String>(completedToStringArray());
        new ListFrame(nameList, completedList);
    }
    public void add(Achievement e){
        achievements.add(e);
    }
    public Achievement get(int i){
        return achievements.get(i);
    }
    public int size(){
        return achievements.size();
    }
    private static class ListFrame extends JFrame{
        private static final long serialVersionUID = 1L;

        private int width = 300;
        
        private int height = 600;

    public ListFrame(JList<String> names,JList<String> complete) {
        setTitle("Achievements");
        setSize(width, height);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        JPanel name = new JPanel();
        name.add(names);
        name.setSize(150, 400);
        name.setBackground(Color.WHITE);
        name.setVisible(true);

        JPopupMenu popup=new JPopupMenu();
        JMenuItem addItem=new JMenuItem("Add Achievement");
        addItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new AchievementForm();
            }
        });
        popup.add(addItem);

        JPanel completed = new JPanel();
        
        completed.setSize(150, 400);
        completed.add(complete);
        completed.setBackground(Color.WHITE);
        completed.setVisible(true);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setSize(width, height);
        splitPane.setDividerSize(0);
        splitPane.setDividerLocation(150);
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(name);
        splitPane.setRightComponent(completed);
        splitPane.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) { }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.isPopupTrigger())
                    popup.show(e.getComponent(), e.getX(), e.getY());
            }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        this.add(splitPane);
        this.setResizable(false);
        }
    }
    private static class AchievementForm extends JFrame implements ActionListener{
        private static final long serialVersionUID = 1L;
        JComboBox<String> comboBox;
        JButton addButton;
        JTextField textField;
        public AchievementForm(){
            String[] achievementTypes={"Score","Line Destroyed Simultaneously","Total Linees Destroyed"};
            JPanel panel=new JPanel();
            textField=new JTextField(10);
            addButton=new JButton("Add");
            addButton.addActionListener(this);
            setSize(300,350);
            setResizable(false);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            comboBox=new JComboBox<String>();
            for(int i=0;i<3;++i){
                comboBox.addItem(achievementTypes[i]);
            }
            panel.add(comboBox);
            panel.add(textField);
            panel.add(addButton);
            add(panel);
            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(textField.getText().toString());
            System.out.println(comboBox.getSelectedItem().toString());
            dispose();
        }
    }
}


