package com.quirko.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class LevelSuccessPanel extends BorderPane {

    Label levelSuccessLabel;
    String success = "";
    private final static int GOD_TIER = 9;
    private final static int EXCELLENT = 17;
    private final static int VERY_GOOD = 25;
    private final static int GOOD = 35;
    private final static int DO_BETTER = 41;
    private final static int TERRIBLE = 50;
    private final static int QUIT_THIS_GAME = 70;

    public LevelSuccessPanel() {
        levelSuccessLabel = new Label(success);
        levelSuccessLabel.getStyleClass().add("levelSuccesStyle");
        setCenter(levelSuccessLabel);
    }

    public void setLeftMatrixCount(int leftMatrixCount) {
        success = getResultLabel(leftMatrixCount);
        levelSuccessLabel = new Label(success);
        levelSuccessLabel.getStyleClass().add("levelSuccesStyle");
        setCenter(levelSuccessLabel);
    }

    private String getResultLabel(int leftMatrixCount){
        if (leftMatrixCount < GOD_TIER){
            return "God Tier!";
        }
        else if (leftMatrixCount < EXCELLENT){
            return "Excellent!";
        }
        else if (leftMatrixCount < VERY_GOOD){
            return "Very Good!";
        }
        else if (leftMatrixCount < GOOD){
            return "Good!";
        }
        else if (leftMatrixCount < DO_BETTER){
            return "Good Enough";
        }
        else if (leftMatrixCount < TERRIBLE){
            return "You MUST\n do better!";
        }
        else if (leftMatrixCount < QUIT_THIS_GAME){
            return "Terrible.";
        }
        else{
            return "Quit this\n GAME.";
        }
    }

}
