package com.quirko.logic;

public interface Board {

    boolean moveBrickDown();

    boolean moveBrickLeft();

    boolean moveBrickRight();

    boolean rotateLeftBrick();

    boolean createNewBrick();

    int[][] getBoardMatrix();

    void setBoardMatrix(int[][] boardMatrix);

    ViewData getViewData();

    void setViewData(int[][] currentShape, int posX, int posY, int[][] nextShape);

    void mergeBrickToBackground();

    ClearRow clearRows();

    Score getScore();

    void setScore(int score);

    void newGame();
}
