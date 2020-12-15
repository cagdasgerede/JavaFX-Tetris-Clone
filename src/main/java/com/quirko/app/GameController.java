package com.quirko.app;

import com.quirko.gui.GameState;
import com.quirko.gui.GuiController;
import com.quirko.logic.*;
import com.quirko.logic.bricks.*;
import com.quirko.logic.events.EventSource;
import com.quirko.logic.events.InputEventListener;
import com.quirko.logic.events.MoveEvent;

public class GameController implements InputEventListener {
    public static Board board = new SimpleBoard(25, 10);
    private static GameState gameState;
    public static GuiController viewGuiController;
    public GameController(GuiController c) {
        viewGuiController = c;
        board.createNewBrick();
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


    public static void saveGame(String fileName){
       gameState.saveGame(fileName);
    }

    public static void loadGame(String fileName){
        gameState.loadGame(fileName);
    }
    @Override
    public void createNewGame() {
        board.newGame();
        viewGuiController.refreshGameBackground(board.getBoardMatrix());
    }

    public static void initGameState(){
        gameState = new GameState(gameState.getGCBoardMatrix(), gameState.getGCCurrentShape(), gameState.getGCNextShape(), gameState.getGCPosX(), gameState.getGCPosY(), gameState.getGCScore());
    }
    
    public GameState getGameState(){
        return gameState;
    }

    public static void setGameState(GameState gameState){
        board.setBoardMatrix(gameState.getBoardMatrix());
        board.setViewData(gameState.getCurrentShape(), gameState.getPosX(), gameState.getPosY(), gameState.getNextShape());
        int currentShape = 0, nextShape = 0;
        for(int i = 0; i < gameState.getNextShape().length * gameState.getNextShape()[0].length; i++)
            if(gameState.getNextShape()[i / gameState.getNextShape().length][i % gameState.getNextShape().length] != nextShape){
                nextShape = gameState.getNextShape()[i / gameState.getNextShape().length][i % gameState.getNextShape().length];
                break;
            }
        for(int i = 0; i < gameState.getCurrentShape().length * gameState.getCurrentShape()[0].length; i++)
            if(gameState.getCurrentShape()[i / gameState.getCurrentShape().length][i % gameState.getCurrentShape().length] != currentShape){
                currentShape = gameState.getCurrentShape()[i / gameState.getCurrentShape().length][i % gameState.getCurrentShape().length];
                break;
            }
        SimpleBoard.setBrick(currentShape);
        RandomBrickGenerator.addBrick(nextShape);
    }
}
