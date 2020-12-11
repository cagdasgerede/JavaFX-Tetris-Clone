package com.quirko.logic;

import java.util.Stack;

public class Achievements {
    
    /*
        Achievement ideas:
        1- total number of lines cleared
        2- # of lines cleared at once (1/2/3...)
        3- get (100/200/.../5000) score
        4- clear lines without rotating shape (cumulative)

    */

    private Stack<Integer> stackTotalLines, stackAtOnce, stackScore; //for keeping track of the next mile stone for an achievement

    private int totalLinesCleared;

    public Achievements() {
        totalLinesCleared = 0;

        stackTotalLines = new Stack<Integer>();
        stackTotalLines.push(100);
        stackTotalLines.push(50);
        stackTotalLines.push(20);
        stackTotalLines.push(10);
        stackTotalLines.push(5);
        stackTotalLines.push(1);

        stackAtOnce = new Stack<Integer>();
        stackAtOnce.push(3);
        stackAtOnce.push(2);
        stackAtOnce.push(1);

        stackScore = new Stack<Integer>();
        stackScore.push(2000);
        stackScore.push(1000);
        stackScore.push(500);
        stackScore.push(200);
        stackScore.push(100);
        stackScore.push(50);
    }

    public void clearedLines(int lines) {
        totalLinesCleared += lines;
        
        if (totalLinesCleared >= stackTotalLines.peek()) {
            //notify player
            stackTotalLines.pop();
        }

        if (lines >= stackAtOnce.peek()) {
            //notify player
            stackAtOnce.pop();
        }
        
    }

}