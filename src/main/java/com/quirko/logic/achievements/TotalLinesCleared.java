package com.quirko.logic.achievements;

import java.util.Stack;

public class TotalLinesCleared implements Achievements {
    
    private Stack<Integer> totalLines, completed;

    private int totalCleared = 0;

    public TotalLinesCleared() {
        completed = new Stack<Integer>();
        totalLines = new Stack<Integer>();
        totalLines.push(500);
        totalLines.push(300);
        totalLines.push(100);
        totalLines.push(50);
        totalLines.push(20);
        totalLines.push(10);
        totalLines.push(1);
    }

    public TotalLinesCleared(int milestone) {
        completed = new Stack<Integer>();
        totalLines = new Stack<Integer>();
        totalLines.push(500);
        totalLines.push(300);
        totalLines.push(100);
        totalLines.push(50);
        totalLines.push(20);
        totalLines.push(10);
        totalLines.push(1);

        if (totalLines.contains(milestone)) {
            while (!totalLines.isEmpty()) {
                if (totalLines.peek() == milestone) {
                    completed.push(totalLines.pop());
                    break;
                }
                else
                    completed.push(totalLines.pop());
            }
        }   
    }

    public boolean checkAchievement(int lines) {
        totalCleared += lines;
        
        boolean success = false;
        while (!totalLines.isEmpty() && lines >= totalLines.peek()) {
            completed.add(totalLines.pop());
            success = true;
        }
        if (success)
            Achievements.notDisplayed.add(getCompletionMessage());
        return success;
    }

    public String getCompletionMessage() {
        return "New achievement!\nTotal lines cleared: " + getLastCompleted();
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
        return totalLines.size();
    }

}
