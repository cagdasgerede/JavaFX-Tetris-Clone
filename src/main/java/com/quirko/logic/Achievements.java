package com.quirko.logic;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Achievements {

    private Stack<Integer> stackTotalLines, stackAtOnce, stackScore; //for keeping track of the next mile stone for an achievement
    private Queue<String> completed;
    private int totalLinesCleared;

    public Achievements() {
        totalLinesCleared = 0;
        completed = new LinkedList<String>();

        stackTotalLines = new Stack<Integer>();
        stackTotalLines.push(100);
        stackTotalLines.push(50);
        stackTotalLines.push(20);
        stackTotalLines.push(10);
        stackTotalLines.push(5);
        stackTotalLines.push(1);

        stackAtOnce = new Stack<Integer>();
        stackAtOnce.push(4);
        stackAtOnce.push(3);
        stackAtOnce.push(2);
        stackAtOnce.push(1);

        stackScore = new Stack<Integer>();
        stackScore.push(5000);
        stackScore.push(3000);
        stackScore.push(2000);
        stackScore.push(1500);
        stackScore.push(1000);
        stackScore.push(500);
        stackScore.push(300);
        stackScore.push(100);
    }

    public void clearedLines(int lines) {
        totalLinesCleared += lines;
        
        if (totalLinesCleared >= stackTotalLines.peek()) {
            int total = stackTotalLines.pop();
            completed.add("New Achievement!\nTotal lines cleared: " + total);
        }

        if (lines >= stackAtOnce.peek()) {
            int atOnce = stackAtOnce.pop();
            completed.add("New Achievement!\nClear " + atOnce + " lines at once!");
        }
        
    }

    public void scored(int score) {
        if (score >= stackScore.peek()) {
            int scr = stackScore.pop();
            completed.add("New Achievement!\nGet " + scr + " points.");
        }
    }

    //if dequeue is true, dequeues and returns a completed achievement that has not been notified to the user yet
    //else, returns either null or a copy of the top element
    public String getCompleted(boolean dequeue) {
        if (dequeue)
            return completed.poll();
        else
            return completed.peek();
    }

}