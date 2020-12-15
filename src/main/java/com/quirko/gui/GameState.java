package com.quirko.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import com.quirko.app.GameController;
import com.quirko.logic.DownData;
import com.quirko.logic.ViewData;

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
    
    public static void loadGame(String fileName){
        String data = "";
        try {
            File file = new File(fileName);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String boardMatrix = "", currentShape = "", posX = "", posY = "", nextShape = "";
        boardMatrix = data.substring(0, data.indexOf("$$"));
        data = data.substring(data.indexOf("$$") + 2);
        currentShape = data.substring(0, data.indexOf("$$"));
        data = data.substring(data.indexOf("$$") + 2);
        posX = data.substring(0, data.indexOf("$$"));
        data = data.substring(data.indexOf("$$") + 2);
        posY = data.substring(0, data.indexOf("$$"));
        data = data.substring(data.indexOf("$$") + 2);
        nextShape = data.substring(0, data.indexOf("$$"));
        data = data.substring(data.indexOf("$$") + 2);
        int[][] intBoardMatrix = new int[GameController.board.getBoardMatrix().length][GameController.board.getBoardMatrix()[0].length];
        for(int i = 0; i < intBoardMatrix.length; i++)
            for(int j = 0; j < intBoardMatrix[i].length; j++)
                intBoardMatrix[i][j] = (int)(boardMatrix.charAt((i * intBoardMatrix[i].length) + j)) - 48;
        int[][] intCurrentShape = new int[GameController.board.getViewData().getBrickData().length][GameController.board.getViewData().getBrickData()[0].length];
        for(int i = 0; i < intCurrentShape.length; i++)
            for(int j = 0; j < intCurrentShape[i].length; j++)
                intCurrentShape[i][j] = (int)(currentShape.charAt((i * intCurrentShape[i].length) + j)) - 48;
        int intPosX = Integer.parseInt(posX);
        int intPosY = Integer.parseInt(posY);
        int[][] intNextShape = new int[intCurrentShape.length][intCurrentShape[0].length];
        for(int i = 0; i < intNextShape.length; i++)
            for(int j = 0; j < intNextShape[i].length; j++)
                intNextShape[i][j] = (int)(nextShape.charAt((i * intNextShape[i].length) + j)) - 48;
        int score = Integer.parseInt(data);
        GameController.setGameState( new GameState(intBoardMatrix, intCurrentShape, intNextShape, intPosX, intPosY, score));
        GameController.viewGuiController.refreshGameBackground(intBoardMatrix);
        GameController.viewGuiController.refreshBrick(new ViewData(intCurrentShape, intPosX, intPosY, intNextShape));
        DownData.setViewData(new ViewData(intCurrentShape, intPosX, intPosY, intNextShape));
        GameController.viewGuiController.pauseButton.selectedProperty().setValue(true);
    }

    public static void saveGame(String fileName){
        String boardMatrix = "", currentShape = "", nextShape = "", posX = "" + getGCPosX(), posY = "" +  getGCPosY(), score = "" +  getGCScore();
        for(int[] i : getGCBoardMatrix()){
            for(int j : i)
                boardMatrix += j;
            boardMatrix += "\n";
        }
        for(int[] i : getGCCurrentShape()){
            for(int j : i)
                currentShape += j;
            currentShape += "\n";
        }
        for(int[] i : getGCNextShape()){
            for(int j : i)
                nextShape += j;
            nextShape += "\n";
        }
        new File(fileName);
        try {
            FileWriter myWriter = new FileWriter(fileName, true);
            myWriter.write(boardMatrix);
            myWriter.write("$$\n");
            myWriter.write(currentShape);
            myWriter.write("$$\n");
            myWriter.write(posX);
            myWriter.write("$$\n");
            myWriter.write(posY);
            myWriter.write("$$\n");
            myWriter.write(nextShape);
            myWriter.write("$$\n");
            myWriter.write(score);
            myWriter.close();
            System.out.println("Successfully saved the game as : " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static int[][] getGCBoardMatrix(){
        return GameController.board.getBoardMatrix();
    }

    public static ViewData gGCViewData()
    {
        return new ViewData(getGCCurrentShape(), getGCPosX(), getGCPosY(), getGCNextShape());
    }
    
    public static int[][] getGCCurrentShape(){
        return GameController.board.getViewData().getBrickData();
    }

    
    public static int[][] getGCNextShape(){
        return GameController.board.getViewData().getNextBrickData();
    }

    
    public static int getGCPosX(){
        return GameController.board.getViewData().getxPosition();
    }

    
    public static int getGCPosY(){
        return GameController.board.getViewData().getyPosition();
    }
    
    public static int getGCScore(){
        return GameController.board.getScore().scoreProperty().getValue().intValue();
    }

}
