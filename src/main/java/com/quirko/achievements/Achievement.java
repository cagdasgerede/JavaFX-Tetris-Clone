package com.quirko.achievements;
public class Achievement{
    public String description;
    public int goal;
    public boolean completed;
    public Achievement(int goal,boolean completed){
        this.goal=goal;
        this.completed=completed;
        if(this.getClass()==ScoreAchievement.class){
            description="Congatulations! You have completed \""+goal+" score\" achievement!";
        }
        else if(this.getClass()==TotalLinesDestroyedAchievement.class){
            description="Congatulations! You have completed \""+goal+" total lines destroyed\" achievement!";
        }
        else if(this.getClass()==LinesDestroyedSimultaneouslyAchievement.class){
            description="Congatulations! You have completed \""+goal+" lines destroyed simultaneously\" achievement!";
        }
    }
    public void showAchievement(){
        new AchievementPopUp(this);
    }
}