package com.quirko.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class LevelSuccessPanel extends BorderPane {

    Label levelSuccessLabel;
    String success = "";
    int godTier = 9;
    int excellent = 17;
    int veryGood = 25;
    int good = 35;
    int doBetter = 41;
    int terrible = 50;
    int quitThisGame = 70;

    public LevelSuccessPanel() {
        levelSuccessLabel = new Label(success);
        levelSuccessLabel.getStyleClass().add("levelSuccesStyle");
        setCenter(levelSuccessLabel);
    }

    public void setLeftMatrixCount(int leftMatrixCount) {
        if (leftMatrixCount < godTier) success = "God Tier!";
        else if (leftMatrixCount < excellent) success = "Excellent!";
        else if (leftMatrixCount < veryGood) success = "Very Good!";
        else if (leftMatrixCount < good) success = "Good!";
        else if (leftMatrixCount < doBetter) success = "Good Enough";
        else if (leftMatrixCount < terrible) success = "You MUST\n do better!";
        else if (leftMatrixCount < quitThisGame) success = "Terrible.";
        else success = "Quit this\n GAME.";

        levelSuccessLabel = null;
        levelSuccessLabel = new Label(success);
        levelSuccessLabel.getStyleClass().add("levelSuccesStyle");
        setCenter(levelSuccessLabel);
    }
}
