package com.quirko.app;

import com.quirko.gui.GuiController2;
import com.quirko.logic.*;
import com.quirko.logic.events.EventSource;
import com.quirko.logic.events.InputEventListener2;
import com.quirko.logic.events.MoveEvent;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameController2 implements InputEventListener2 {

    private Board board = new SimpleBoard(25, 10);
    private Board board2 = new SimpleBoard(25, 10);

    private final GuiController2 viewGuiController;

    public GameController2(GuiController2 c) {
        viewGuiController = c;
        board.createNewBrick();
        board2.createNewBrick();
        viewGuiController.setEventListener(this);
        viewGuiController.initGameView(board.getBoardMatrix(), board.getViewData());
        viewGuiController.initGameView2(board2.getBoardMatrix(), board2.getViewData());
        viewGuiController.bindScore(board.getScore().scoreProperty());
        viewGuiController.bindScore2(board2.getScore().scoreProperty());
        
    }

    @Override
    public DownData onDownEvent(MoveEvent event, KeyEvent key) {
        if(key.getCode()==KeyCode.S){
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
        ///////////////////for second player
        else{
        boolean canMove = board2.moveBrickDown();
        ClearRow clearRow = null;
        if (!canMove) {
            board2.mergeBrickToBackground();
            clearRow = board.clearRows();
            if (clearRow.getLinesRemoved() > 0) {
                board2.getScore().add(clearRow.getScoreBonus());
            }
            if (board2.createNewBrick()) {
                viewGuiController.gameOver2();
            }

            viewGuiController.refreshGameBackground2(board2.getBoardMatrix());

        } else {
            if (event.getEventSource() == EventSource.USER) {
                board2.getScore().add(1);
            }
        }
        return new DownData(clearRow, board2.getViewData());
        }
    }


    public DownData onDownEvent(MoveEvent event, KeyCode code) {
        if(code==KeyCode.S){//first player
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
        ///////////////////for second player
    else{
        boolean canMove = board2.moveBrickDown();
        ClearRow clearRow = null;
        if (!canMove) {
            board2.mergeBrickToBackground();
            clearRow = board.clearRows();
            if (clearRow.getLinesRemoved() > 0) {
                board2.getScore().add(clearRow.getScoreBonus());
            }
            if (board2.createNewBrick()) {
                viewGuiController.gameOver2();
            }

            viewGuiController.refreshGameBackground2(board2.getBoardMatrix());

        } else {
            if (event.getEventSource() == EventSource.USER) {
                board2.getScore().add(1);
            }
        }
        return new DownData(clearRow, board2.getViewData());
        }
    }

    @Override
    public ViewData onLeftEvent(MoveEvent event,KeyEvent key) {
        if(key.getCode()==KeyCode.A){
        board.moveBrickLeft();
        return board.getViewData();}
        else{
        board2.moveBrickLeft();
        return board2.getViewData();}
    }

    @Override
    public ViewData onRightEvent(MoveEvent event,KeyEvent key) {
        if(key.getCode()==KeyCode.D){
        board.moveBrickRight();
        return board.getViewData();}
        else{
        board2.moveBrickRight();
        return board2.getViewData();}
    }

    @Override
    public ViewData onRotateEvent(MoveEvent event,KeyEvent key) {
        if(key.getCode()==KeyCode.W){
        board.rotateLeftBrick();
        return board.getViewData();}
        else{
        board2.rotateLeftBrick();
        return board2.getViewData();}
    }


    @Override
    public void createNewGame(boolean first) {
        if(first){
        board.newGame();
        viewGuiController.refreshGameBackground(board.getBoardMatrix());}
        else{
        board2.newGame();
        viewGuiController.refreshGameBackground2(board2.getBoardMatrix());
        }
    }
    
}
