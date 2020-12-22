package com.quirko.logic.achievements;

import java.util.Stack;

public class Scored implements Achievements {
    
    private Stack<Integer> scores, completed;

    public Scored() {
        completed = new Stack<Integer>();
        scores = new Stack<Integer>();
        scores.push(5000);
        scores.push(3000);
        scores.push(2000);
        scores.push(1500);
        scores.push(1000);
        scores.push(500);
        scores.push(300);
        scores.push(100);
    }

    public Scored(int milestone) {
        completed = new Stack<Integer>();
        scores = new Stack<Integer>();
        scores.push(5000);
        scores.push(3000);
        scores.push(2000);
        scores.push(1500);
        scores.push(1000);
        scores.push(500);
        scores.push(300);
        scores.push(100);

        if (scores.contains(milestone)) {
            while (!scores.isEmpty()) {
                if (scores.peek() == milestone) {
                    completed.push(scores.pop());
                    break;
                }
                else
                    completed.push(scores.pop());
            }
        }
    }

    public boolean checkAchievement(int score) {
        boolean success = false;
        while (!scores.isEmpty() && score >= scores.peek()) {
            completed.add(scores.pop());
            success = true;
        }
        if (success)
            Achievements.notDisplayed.add(getCompletionMessage());
        return success;
    }
    
    public String getCompletionMessage() {
        return "New Achievement!\nGet " + getLastCompleted() + " points.";
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
        return scores.size();
    }

}
