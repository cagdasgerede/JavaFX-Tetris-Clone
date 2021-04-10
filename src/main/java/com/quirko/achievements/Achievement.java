package com.quirko.achievements;
public class Achievement{
    public String name;
    public String description;
    public int goal,currentState;
    public boolean completed;
    public Achievement(int currentState,int goal,boolean completed){
        this.goal=goal;
        this.completed=completed;
        this.currentState=currentState;
        if(this.getClass()==ScoreAchievement.class){
            name=goal+" Score";
            description="Congatulations! You have completed \""+goal+" score\" achievement!";
        }
        else if(this.getClass()==TotalLinesDestroyedAchievement.class){
            name=goal+" Total Lines Destroyed";
            description="Congatulations! You have completed \""+goal+" total lines destroyed\" achievement!";
        }
        else if(this.getClass()==LinesDestroyedSimultaneouslyAchievement.class){
            name=goal+" Lines Destroyed Simultaneously";
            description="Congatulations! You have completed \""+goal+" lines destroyed simultaneously\" achievement!";
        }
    }
    public void showAchievement(){
        new AchievementPopUp(this);
    }
}