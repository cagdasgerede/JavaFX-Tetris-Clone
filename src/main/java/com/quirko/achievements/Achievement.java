package com.quirko.achievements;
public class Achievement{
    public String description;
    public int goal;
    public boolean completed;
    public Achievement(int goal,boolean completed){
        this.goal=goal;
        this.completed=completed;
    }
    public void showAchievement(){
        new AchievementPopUp(this);
    }
}