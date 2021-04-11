package com.quirko.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class ScoreboardPanel extends BorderPane {

    public ScoreboardPanel() {
        final Label gameOverLabel = new Label("SCOREBOARD");
        gameOverLabel.getStyleClass().add("scoreboardStyle");
        setCenter(gameOverLabel);
    }

}