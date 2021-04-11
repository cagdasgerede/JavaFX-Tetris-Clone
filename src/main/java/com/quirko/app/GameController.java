package com.quirko.app;

import com.quirko.gui.CurrentState;
import com.quirko.gui.GuiController;
import com.quirko.logic.*;
import com.quirko.logic.bricks.RandomBrickGenerator;
import com.quirko.logic.events.EventSource;
import com.quirko.logic.events.InputEventListener;
import com.quirko.logic.events.MoveEvent;

public class GameController implements InputEventListener {

    public static Board board = new SimpleBoard(25, 10);
    public static CurrentState currentState;
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
        currentState.save(fileName);
    }

    public static void loadGame(String fileName){
        currentState.load(fileName);
    }

    @Override
    public void createNewGame() {
        board.newGame();
        viewGuiController.refreshGameBackground(board.getBoardMatrix());
    }

    public static void initializeCurrentState(){
        currentState = new CurrentState(currentState.getGMatrix(), currentState.getGCurrentShape(),
                currentState.getGNextShape(), currentState.getPos_x(), currentState.getPos_y(), currentState.getScore());
    }

    public CurrentState getCurrentState(){
        return currentState;
    }

    public static void setCurrentState(CurrentState currentState){
        board.setBoard(currentState.getBoard());
        board.setViewData(currentState.getCurrentShape(), currentState.getPos_x(), currentState.getPos_y(), currentState.getComingShape());
        int currentShape = 0, nextShape = 0;
        for(int i = 0; i < currentState.getComingShape().length * currentState.getComingShape()[0].length; i++)
            if(currentState.getComingShape()[i / currentState.getComingShape().length][i % currentState.getComingShape().length] != nextShape){
                nextShape = currentState.getComingShape()[i / currentState.getComingShape().length][i % currentState.getComingShape().length];
                break;
            }
        for(int i = 0; i < currentState.getCurrentShape().length * currentState.getCurrentShape()[0].length; i++)
            if(currentState.getCurrentShape()[i / currentState.getCurrentShape().length][i % currentState.getCurrentShape().length] != currentShape){
                currentShape = currentState.getCurrentShape()[i / currentState.getCurrentShape().length][i % currentState.getCurrentShape().length];
                break;
            }
        SimpleBoard.setBrick(currentShape);
        RandomBrickGenerator.addBrick(nextShape);
    }
}
