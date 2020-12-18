package com.quirko.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class settingsPanel extends BorderPane {

    public settingsPanel() {
        final Label settingsLabel = new Label("Settings"); 
        settingsLabel.getStyleClass().add("settingsStyle"); 
        setTop(settingsLabel);

    }

}