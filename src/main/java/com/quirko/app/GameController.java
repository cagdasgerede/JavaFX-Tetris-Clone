package com.quirko.app;

import com.quirko.gui.GuiController;
import com.quirko.logic.*;
import com.quirko.logic.events.EventSource;
import com.quirko.logic.events.InputEventListener;
import com.quirko.logic.events.MoveEvent;

public class GameController implements InputEventListener {
    int sayac = 0;
    int y_sayac = 0;
    int bff ;
    private Board board = new SimpleBoard(25, 10);

    private final GuiController viewGuiController;

    public GameController(GuiController c) {
        viewGuiController = c;
        board.createNewBrick();
        viewGuiController.setEventListener(this);
        viewGuiController.initGameView(board.getBoardMatrix(), board.getViewData());
        viewGuiController.bindScore(board.getScore().scoreProperty());
    }

    @Override
    public DownData onDownEvent(MoveEvent event) {
        System.out.println("ysayac = " + y_sayac);
        System.out.println("sayac = "  + sayac);
        System.out.println("bff = " + bff);
        if(viewGuiController.buff == 1 || viewGuiController.buff == 2 || viewGuiController.buff == 3){
            y_sayac = sayac;
            bff = viewGuiController.buff;
        }

        boolean canMove = board.moveBrickDown();
        ClearRow clearRow = null;

        if (!canMove) {
            board.mergeBrickToBackground();
            clearRow = board.clearRows();
            int c = clearRow.getLinesRemoved(); 
            System.out.println(board);

            if(sayac-1 == y_sayac && bff == 1) { // row clear
                board.getScore().add(10);
            }

            if(sayac-1 == y_sayac && bff == 2) { // bomb

            }

            if(sayac-1 == y_sayac && bff == 3) { // change break
            
            }

            if (c > 0) {
                board.getScore().add(clearRow.getScoreBonus());
            }

            if (board.createNewBrick()) {
                viewGuiController.gameOver();
            }

            viewGuiController.refreshGameBackground(board.getBoardMatrix());
            sayac++;
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
        board.newGame();
        viewGuiController.refreshGameBackground(board.getBoardMatrix());
    }
}
