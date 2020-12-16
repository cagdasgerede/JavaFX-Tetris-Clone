package com.quirko.logic;

import com.quirko.logic.bricks.BrickGenerator;
import com.quirko.logic.bricks.Brick;

public interface Board {

    boolean moveBrickDown();

    boolean moveBrickLeft();

    boolean moveBrickRight();

    boolean rotateLeftBrick();

    boolean changeBrick();

    void setNextBrickAsDotBrick();

    boolean createNewBrick();

    int[][] getBoardMatrix();

    ViewData getViewData();

    void mergeBrickToBackground();

    ClearRow clearRows();

    Score getScore();

    void setCurrentBrickAsDotBrick();

    void newGame();
}
