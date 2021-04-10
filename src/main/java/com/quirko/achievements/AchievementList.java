package com.quirko.achievements;

import java.util.ArrayList;

import javax.swing.*;

import com.quirko.app.GameController;

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
                temp[i]=achievements.get(i-1).currentState+"/"+achievements.get(i-1).goal;
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

        private int width = 350;
        
        private int height = 600;

    public ListFrame(JList<String> names,JList<String> complete) {
        setTitle("Achievements");
        setSize(width, height);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(600,300);

        JPanel name = new JPanel();
        name.add(names);
        name.setSize(200, 400);
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
        
        completed.setSize(150, 300);
        completed.add(complete);
        completed.setBackground(Color.WHITE);
        completed.setVisible(true);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setSize(width, height);
        splitPane.setDividerSize(0);
        splitPane.setDividerLocation(175);
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
        JTextField textFieldGoal;
        public AchievementForm(){
            String[] achievementTypes={"Score","Lines Destroyed Simultaneously","Total Lines Destroyed"};
            JPanel panel=new JPanel();
            textFieldGoal=new JTextField(10);
            addButton=new JButton("Add");
            addButton.addActionListener(this);
            setSize(300,150);
            setResizable(false);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            comboBox=new JComboBox<String>();
            for(int i=0;i<3;++i){
                comboBox.addItem(achievementTypes[i]);
            }
            JLabel label=new JLabel("Goal:");
            panel.add(label);
            label.setLabelFor(textFieldGoal);
            panel.add(textFieldGoal);
            panel.add(comboBox);
            panel.add(addButton);
            add(panel);
            setTitle("New Achievement");
            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int goal=Integer.parseInt(textFieldGoal.getText());
            if(comboBox.getSelectedItem().toString().equals("Lines Destroyed Simultaneously"))
                GameController.achievements.add(new LinesDestroyedSimultaneouslyAchievement(0, goal, false));
            else if(comboBox.getSelectedItem().toString().equals("Total Lines Destroyed"))
                GameController.achievements.add(new TotalLinesDestroyedAchievement(0, goal, false));
            else
                GameController.achievements.add(new ScoreAchievement(0, goal, false));
            dispose();
        }
    }
}


