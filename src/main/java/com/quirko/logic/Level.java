package com.quirko.logic;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Level {

    private int levelNumber;
    private int maxPoint;
    private int currentPoint;
    private int moveCount;
    private IntegerProperty target = new SimpleIntegerProperty(0);
    private IntegerProperty levelID = new SimpleIntegerProperty(0);


    //This constructor sets information for the first level. After that, levels increases
    // automatically.
    public Level(int maxPoint) {
        this.levelNumber = 1;
        this.maxPoint = maxPoint;
        this.currentPoint = 0;
        this.moveCount = 0;
        target = new SimpleIntegerProperty(maxPoint);
        levelID= new SimpleIntegerProperty(1);
    }



    public boolean completed(){
        return currentPoint >= maxPoint;
    }

    public String getName() {
        return levelNumber + ". Level";
    }

    public int getMaxPoint() {
        return maxPoint;
    }

    public double getCurrentPoint() {
        return currentPoint;
    }

    public int getMoveCount() {
        return moveCount;
    }

    @Override
    public String toString() {
        return "Level{" +
                "levelNumber=" + levelNumber +
                ", maxPoint=" + maxPoint +
                ", currentPoint=" + currentPoint +
                ", moveCount=" + moveCount +
                '}';
    }

    public void addPoint(int toBeAdded){
        currentPoint += toBeAdded;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void resetLevel(){
        levelNumber = 1;
        currentPoint = 0;
        moveCount = 0;
        maxPoint = 25;
        target.setValue( maxPoint);
        levelID.setValue(1);
        System.out.println("Level resettled");
    }

    public void addCount(int toBeAdded){
        moveCount += toBeAdded;
    }



    public double successRate(){
        return currentPoint / moveCount * 20;
    }

    public void upgradeLevel(){
        System.out.println("Level upgraded");
        this.levelNumber++;
        this.maxPoint = (int) Math.round(maxPoint * 1.2);
        maxPoint *= 1.2;
        target.setValue( (maxPoint + 4) / 5 * 5);
        levelID.setValue(levelNumber);
        this.currentPoint = 0;
        this.moveCount = 0;
    }


    public IntegerProperty targetProperty() {
        return target;
    }

    public IntegerProperty levelIDProperty() {
        return levelID;
    }


}
