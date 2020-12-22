package com.quirko.logic.achievements;

import java.util.Stack;

public class TotalLinesCleared {
    
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
        
        if (!totalLines.isEmpty()) {
            if (totalCleared >= totalLines.peek()) {
                completed.add(totalLines.pop());
                Achievements.notDisplayed.add(getCompletionMessage());
                return true;
            }
        }
        return false;
    }

    public String getCompletionMessage() {
        return "New achievement!\nTotal lines cleared: " + getLastCompleted();
    }

    public int getLastCompleted() {
        if (!completed.isEmpty())
            return completed.peek();
        return 0;
    }
}
