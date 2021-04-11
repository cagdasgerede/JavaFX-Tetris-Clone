package com.quirko.logic;

public class ScoreName {
    
    private int score;
    private String name;
    public ScoreName(){}
    public ScoreName(int score, String name){
        this.score = score;
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    public int getScore(){
        return score;
    }
    public void setScore(int score){
        this.score = score;
    }
    public void setName(String name){
        this.name = name;
    }
}
