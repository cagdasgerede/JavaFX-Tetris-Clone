package com.quirko.logic.achievements;

import java.util.Stack;

public class LinesClearedAtOnce implements Achievements {
    
    private Stack<Integer> atOnce, completed;

    public LinesClearedAtOnce() {
        completed = new Stack<Integer>();
        atOnce = new Stack<Integer>();
        atOnce.push(4);
        atOnce.push(3);
        atOnce.push(2);
    }

    public LinesClearedAtOnce(int milestone) {
        completed = new Stack<Integer>();
        atOnce = new Stack<Integer>();
        atOnce.push(4);
        atOnce.push(3);
        atOnce.push(2);
        
        if (atOnce.contains(milestone)) 
            while (!atOnce.isEmpty() && atOnce.peek() != milestone)
                completed.push(atOnce.pop());
    }

    public boolean checkAchievement(int lines) {
        if (!atOnce.isEmpty()) {
            if (lines >= atOnce.peek()) {
                completed.add(atOnce.pop());
                Achievements.notDisplayed.add(getCompletionMessage());
                return true;
            }
        }
        return false;
    }

    public String getCompletionMessage() {
        return "New Achievement!\nClear " + getLastCompleted() + " lines at once!";
    }

    public int getLastCompleted() {
        if (!completed.isEmpty())
            return completed.peek();
        return 0;
    }

}
