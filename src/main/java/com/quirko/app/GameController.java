package com.quirko.app;

import com.quirko.gui.DifficultySetPanel;
import com.quirko.gui.DifficultyType;
import com.quirko.gui.GuiController;
import com.quirko.logic.*;
import com.quirko.logic.events.EventSource;
import com.quirko.logic.events.InputEventListener;
import com.quirko.logic.events.MoveEvent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.quirko.gui.GuiController;

public class GameController implements InputEventListener {
    DifficultyType difficultyLevel;
    
    private Board board;

    private final GuiController viewGuiController;

    public GameController(GuiController c, DifficultyType difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        viewGuiController = c;
        board= new SimpleBoard(25, 10, difficultyLevel);
        board.createNewBrick();
        viewGuiController.setDifficulty(difficultyLevel);
        viewGuiController.setEventListener(this);
        viewGuiController.initGameView(board.getBoardMatrix(), board.getViewData());
        viewGuiController.bindScore(board.getScore().scoreProperty());
    }

    @Override
    public DownData onDownEvent(MoveEvent event) {
        boolean canMove = board.moveBrickDown();
        ClearRow clearRow = null;
        if (!canMove) {
            board.mergeBrickToBackground();
            clearRow = board.clearRows();
            if (clearRow.getLinesRemoved() > 0) {
                board.getScore().add(clearRow.getScoreBonus());
            }
            if (board.createNewBrick()) {
                viewGuiController.gameOver();
            }

            viewGuiController.refreshGameBackground(board.getBoardMatrix());

        } else {
            if (event.getEventSource() == EventSource.USER) {
                board.getScore().add(1);
            }
        }
        return new DownData(clearRow, board.getViewData());
    }

    @Override
    public ViewData onLeftEvent(MoveEvent event) {
        board.moveBrickLeft();
        return board.getViewData();
    }

    @Override
    public ViewData onRightEvent(MoveEvent event) {
        board.moveBrickRight();
        return board.getViewData();
    }

    @Override
    public ViewData onRotateEvent(MoveEvent event) {
        board.rotateLeftBrick();
        return board.getViewData();
    }


    @Override
    public void createNewGame() {
        DifficultySetPanel dfs = new DifficultySetPanel();
        DifficultyType difficultyLevel_new = dfs.getDifficultyLevel();
        while(difficultyLevel_new == null){
            difficultyLevel_new = dfs.getDifficultyLevel();
        }
        
        dfs.dispose();
        this.difficultyLevel = difficultyLevel_new;
        board.setDifficulty(difficultyLevel);
        board.newGame();
        viewGuiController.setDifficulty(this.difficultyLevel);
        viewGuiController.refreshGameBackground(board.getBoardMatrix());
    }
}
