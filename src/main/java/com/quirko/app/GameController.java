package com.quirko.app;

import com.quirko.gui.GuiController;
import com.quirko.logic.*;
import com.quirko.logic.events.EventSource;
import com.quirko.logic.events.InputEventListener;
import com.quirko.logic.events.MoveEvent;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class GameController implements InputEventListener {

    private Level level = new Level(30);

    private Board board = new SimpleBoard(25, 10, level);

    private final GuiController viewGuiController;


    public GameController(GuiController c) {
        viewGuiController = c;
        board.createNewBrick();
        viewGuiController.setEventListener(this);
        viewGuiController.initGameView(board.getBoardMatrix(), board.getViewData());
        viewGuiController.bindScore(board.getScore().scoreProperty());
        viewGuiController.bindTarget(board.getLevel().targetProperty());
        viewGuiController.bindLevelID(board.getLevel().levelIDProperty());
        //Maxpoint for the first level is 250.
        board.updateLevel(level);
    }

    @Override
    public DownData onDownEvent(MoveEvent event) {
        boolean canMove = board.moveBrickDown();
        ClearRow clearRow = null;
        if (!canMove) {
            level.addCount(1);
            board.updateLevel(level);

            board.mergeBrickToBackground();
            clearRow = board.clearRows();
            if (clearRow.getLinesRemoved() > 0) {
                board.getScore().add(clearRow.getScoreBonus());
                level.addPoint(clearRow.getScoreBonus());
                board.updateLevel(level);

            }
            if (board.createNewBrick()) {
                System.out.println(level.getName() + " is over. You failed.");
                viewGuiController.resetSpeed();
                viewGuiController.gameOver();
                createNewGame(false);
            }

            viewGuiController.refreshGameBackground(board.getBoardMatrix());
        } else {
            //this is when player uses down arrow.
            if (event.getEventSource() == EventSource.USER) {
                level.addPoint(1);
                board.updateLevel(level);
                board.getScore().add(1);
            }
        }
        if(level.completed()){
            System.out.println(level.getName() + " is completed.");
            viewGuiController.setSpeed(level.getLevelNumber());
            createNewGame(true);
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
    public void createNewGame(boolean isNewLevel) {
        countOfFilledMatrix();
        if(isNewLevel){
            level.upgradeLevel();
            viewGuiController.nextGame(countOfFilledMatrix());
            board.nextGame();

        }
        else {
            board.gameOver();
        }
        board.updateLevel(level);
        viewGuiController.refreshGameBackground(board.getBoardMatrix());
    }


    @Override
    public ViewData onLevelUpEvent(MoveEvent event) {
        if(level.canLevelUp()){
            viewGuiController.setSpeed(level.getLevelNumber());
            level.levelUp();
            board.newGame();
            board.updateLevel(level);
            viewGuiController.refreshGameBackground(board.getBoardMatrix());

        }
        return board.getViewData();
    }

    @Override
    public ViewData onLevelDownEvent(MoveEvent event) {
        if(level.canLevelDown()){
            viewGuiController.downShift(level.getLevelNumber() -1);
            level.downgradeLevel();
            board.newGame();
            board.updateLevel(level);
            viewGuiController.refreshGameBackground(board.getBoardMatrix());

        }
        return board.getViewData();
    }

    public int countOfFilledMatrix(){
        int filledCount = 0;
        //250 is the matrix size. x * y dimensions of the game.
        for(int i = 0; i < 25; i++){
            for(int x = 0; x< 10; x++){
                if( board.getBoardMatrix()[i][x] != 0){
                    filledCount++;
                }
            }
        }
        System.out.println("Left matrix: " + filledCount);
        viewGuiController.levelSuccPanel.setLeftMatrixCount(filledCount);
        return filledCount;
    }
}
