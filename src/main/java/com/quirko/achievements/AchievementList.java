package com.quirko.achievements;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.*;
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
        /**
         *
         */
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

        this.add(splitPane);
        }
    }
    
}
