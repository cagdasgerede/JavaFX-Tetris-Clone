package com.quirko.logic.achievements;
import java.util.LinkedList;
import java.util.Queue;

public interface Achievements {

    public static Queue<String> notDisplayed = new LinkedList<String>();

    public String getCompletionMessage();
    public boolean checkAchievement(int number);
    public int getLastCompleted();
    public int completedCount();
    public int uncompletedCount();
    
}
