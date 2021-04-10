package com.quirko.achievements; 
public class ScoreAchievement extends Achievement{
    public ScoreAchievement(int currentState,int goal,boolean completed) {
        super(currentState,goal,completed);
    }
}
/*
    Score achievement is achieved by reaching a score in a single game without failing.
*/