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
        User.atOnce = 0;
    }

    public LinesClearedAtOnce(int milestone) {
        completed = new Stack<Integer>();
        atOnce = new Stack<Integer>();
        atOnce.push(4);
        atOnce.push(3);
        atOnce.push(2);
        
        if (atOnce.contains(milestone)) {
            while (!atOnce.isEmpty()) {
                if (atOnce.peek() == milestone) {
                    completed.push(atOnce.pop());
                    break;
                }
                else
                    completed.push(atOnce.pop());
            }
        }
        User.atOnce = getLastCompleted();
    }

    public boolean checkAchievement(int lines) {
        boolean success = false;
        while (!atOnce.isEmpty() && lines >= atOnce.peek()) {
            completed.add(atOnce.pop());
            success = true;
        }
        if (success) {
            User.atOnce = getLastCompleted();
            Achievements.notDisplayed.add(getCompletionMessage());
        }
        return success;
    }

    public String getCompletionMessage() {
        return "New Achievement!\nClear " + getLastCompleted() + " lines at once!";
    }

    public int getLastCompleted() {
        if (!completed.isEmpty())
            return completed.peek();
        return 0;
    }

    public int completedCount() {
        return completed.size();
    }

    public int uncompletedCount() {
        return atOnce.size();
    }

}
