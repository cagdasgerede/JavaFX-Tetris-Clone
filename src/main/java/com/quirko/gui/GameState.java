package com.quirko.gui;

public class GameState {
    private int[][] boardMatrix;
    private int[][] currentShape;
    private int[][] nextShape;
    private int posX;
    private int posY;
    private int score;

    public GameState(int[][] boardMatrix, int[][] currentShape, int[][] nextShape, int posX, int posY, int score) {
        this.setBoardMatrix(boardMatrix);
        this.setCurrentShape(currentShape);
        this.setNextShape(nextShape);
        this.setPosX(posX);
        this.setPosY(posY);
        this.setScore(score);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int[][] getNextShape() {
        return nextShape;
    }

    public void setNextShape(int[][] nextShape) {
        this.nextShape = nextShape;
    }

    public int[][] getCurrentShape() {
        return currentShape;
    }

    public void setCurrentShape(int[][] currentShape) {
        this.currentShape = currentShape;
    }

    public int[][] getBoardMatrix() {
        return boardMatrix;
    }

    public void setBoardMatrix(int[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
    }
    
}
