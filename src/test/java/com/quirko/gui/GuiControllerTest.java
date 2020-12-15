package com.quirko.gui;

import javafx.util.Pair;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import static org.junit.Assert.*;

public class GuiControllerTest {

    @Test
    public void testScoreboardSize() {
        Scanner file = null;
        try {
            file = new Scanner(new File("score.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int numLines = 0;
        while (file.hasNextLine()) {
            numLines++;
            file.nextLine();
        }

        GuiController guiController = new GuiController();
        guiController.loadScores();
        assertEquals(guiController.getScores().size(), numLines);
    }

    @Test
    public void testNullScore() {
        GuiController guiController = new GuiController();
        guiController.loadScores();
        boolean noNull = true;

        if (guiController.getScores() != null) {
            for (Pair<String, Integer> p : guiController.getScores()) {
                noNull &= p.getValue() != null;
            }
        }

        assertTrue(noNull);
    }

    @Test
    public void validScore() {
        GuiController guiController = new GuiController();
        guiController.loadScores();
        boolean allValid = true;

        if (guiController.getScores() != null) {
            for (Pair<String, Integer> p : guiController.getScores()) {
                allValid &= (p.getValue() >= 0);
            }
        }

        assertTrue(allValid);
    }
}
