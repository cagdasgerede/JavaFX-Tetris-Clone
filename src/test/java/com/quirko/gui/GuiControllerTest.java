package com.quirko.gui;

import javafx.util.Pair;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GuiControllerTest {

    @Test
    public void testScoreboardSize() {
        Scanner file = null;
        try {
            file = new Scanner(new File("src/main/resources/test_score.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int numLines = 0;
        while (file.hasNextLine()) {
            numLines++;
            file.nextLine();
        }

        assertEquals(numLines, 10);
    }

    @Test
    public void testNullScore() {
        GuiController guiController = new GuiController();
        guiController.loadScores();
        boolean noNull = true;

        for (Pair<String, Integer> p : guiController.getScores()) {
            noNull &= p.getValue() != null;
        }

        assertTrue(noNull);
    }

    @Test
    public void validScore() {
        GuiController guiController = new GuiController();
        guiController.loadScores();
        boolean allValid = true;

        for (Pair<String, Integer> p : guiController.getScores()) {
            allValid &= (p.getValue() >= 0);
        }

        assertTrue(allValid);
    }
}
