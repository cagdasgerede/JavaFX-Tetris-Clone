package com.quirko.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class SpecialDropsPanel extends BorderPane {

    public SpecialDropsPanel() {
        final Label specialDropsLabel = new Label("COLOR DESTROYER\n"
        +"(Special Drop)\n"
        +"Change the color in the\n"
        +"middle of the big square\n"
        +"to destroy all the\n"
        +"bricks of that color!\n\n\n"
        +"SCORE MULTIPLEXER\n"
        +"(Very Special Drop)\n"
        +"Get Sprpoints to multiply\n"
        +"score increase with them!\n"
        +"To get Sprpoints, touch the\n"
        +"far right and far left with\n"
        +"the single square.");

        specialDropsLabel.getStyleClass().add("specialDropsStyle");
        setCenter(specialDropsLabel);
    }
    
}