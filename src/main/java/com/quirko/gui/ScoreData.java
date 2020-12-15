package com.quirko.gui;

import javafx.beans.property.SimpleStringProperty;

public class ScoreData {
    SimpleStringProperty playerName;
    SimpleStringProperty score;

    ScoreData(String playerName, String score) {
        this.playerName = new SimpleStringProperty(playerName);
        this.score = new SimpleStringProperty(score);
    }

    public String getPlayerName(){
        return playerName.get();
    }
    public void setFileName(String nname){
        playerName.set(nname);
    }
    public String getScore(){
        return score.get();
    }
    public void setScore(String nscore){
        score.set(nscore);
    }
}