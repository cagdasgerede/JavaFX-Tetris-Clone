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
    Enum diff;
    private Board board ;

    private final GuiController viewGuiController;

    public GameController(GuiController c,Enum diff) {
        this.diff=diff;
        viewGuiController = c;
        board= new SimpleBoard(25, 10, diff);
        board.createNewBrick();
        viewGuiController.setDifficulty(diff);
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
        DifficultySetPanel dfs= new DifficultySetPanel();
        Enum diff_new = dfs.getDiff();
        while(diff_new==null){
            diff_new = dfs.getDiff();
        }
      
        dfs.dispose();
        this.diff = diff_new;
        board.setDifficulty(diff);
        board.newGame();
        viewGuiController.setDifficulty(this.diff);
        viewGuiController.refreshGameBackground(board.getBoardMatrix());
    }
}
