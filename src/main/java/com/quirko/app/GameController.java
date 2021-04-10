package com.quirko.app;

import com.quirko.gui.GuiController;
import com.quirko.logic.*;
import com.quirko.logic.events.EventSource;
import com.quirko.logic.events.InputEventListener;
import com.quirko.logic.events.MoveEvent;

public class GameController implements InputEventListener {

    private Board board = new SimpleBoard(25, 10);

    private final GuiController viewGuiController;
    private int currentScore = 0;
    private int currentLevel = 0;
    private long startTime;

    public GameController(GuiController c) {
        viewGuiController = c;
        board.createNewBrick();
        viewGuiController.setEventListener(this);
        viewGuiController.initGameView(board.getBoardMatrix(), board.getViewData());
        viewGuiController.bindScore(board.getScore().scoreProperty());
        viewGuiController.bindLevel(board.getLevel().levelProperty());
        startTime = System.currentTimeMillis();
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
        currentScore = board.getScore().getScoreValue();
        long endTime = System.currentTimeMillis();
        long timeDiff = (endTime - startTime)/1000;
        if(currentScore >= 1000 && !(currentLevel >= 5)){
            currentLevel = 5;
            board.getLevel().add(1);
            viewGuiController.changeLevel(timeDiff,currentLevel);
            viewGuiController.changeSpeed(200);
        }else if(currentScore >= 600 && !(currentLevel >= 4)){
            currentLevel = 4;
            board.getLevel().add(1);
            viewGuiController.changeLevel(timeDiff,currentLevel);
            viewGuiController.changeSpeed(250);
        }else if(currentScore >= 300 && !(currentLevel >= 3)){
            currentLevel = 3;
            board.getLevel().add(1);
            viewGuiController.changeLevel(timeDiff,currentLevel);
            viewGuiController.changeSpeed(300);
        }else if(currentScore >= 150 && !(currentLevel >= 2)){
            currentLevel = 2;
            board.getLevel().add(1);
            viewGuiController.changeLevel(timeDiff,currentLevel);
            viewGuiController.changeSpeed(500);
        }else if(currentScore >= 50 && !(currentLevel >= 1)){
            currentLevel = 1;
            board.getLevel().add(1);
            viewGuiController.changeLevel(timeDiff,currentLevel);
            viewGuiController.changeSpeed(600);
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
        board.newGame();
        currentLevel = 0;
        currentScore = 0;
        startTime = System.currentTimeMillis();
        viewGuiController.refreshGameBackground(board.getBoardMatrix());
    }
}
