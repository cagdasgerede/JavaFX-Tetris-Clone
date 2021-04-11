package com.quirko.gui;

import com.quirko.app.GameController;
import com.quirko.logic.DownData;
import com.quirko.logic.ViewData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CurrentState {

    public int[][] board;
    public int[][] currentShape;
    public int[][] comingShape;
    public int pos_x, pos_y, score;

    public CurrentState(int[][] board, int[][] currentShape, int[][] comingShape, int pos_x, int pos_y, int score) {
        this.board = board;
        this.currentShape = currentShape;
        this.comingShape = comingShape;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.score = score;
    }

    public static void save(String fileName){

        StringBuilder board = new StringBuilder();
        StringBuilder currentShape = new StringBuilder();
        StringBuilder nextShape = new StringBuilder();
        String comingShape = "";
        String pos_x = "" + getGPosX();
        String pos_y = "" + getGPosY();
        String score = "" + getGScore();

        int [][] tempBoard = getGMatrix();
        for(int i = 0 ; i<tempBoard.length; i++){
            for(int j = i; j<tempBoard[0].length; j++){
                board.append(tempBoard[i][j]);
            }board.append("\n");
        }

        int [][] tempCurrentShape = getGCurrentShape();
        for(int i = 0 ; i<tempCurrentShape.length; i++){
            for(int j = i; j<tempCurrentShape[0].length; j++){
                currentShape.append(tempCurrentShape[i][j]);
            }currentShape.append("\n");
        }

        int [][] tempNextShape = getGNextShape();
        for(int i = 0 ; i<tempNextShape.length; i++){
            for(int j = i; j<tempNextShape[0].length; j++){
                nextShape.append(tempNextShape[i][j]);
            }nextShape.append("\n");
        }

        new File(fileName);
        FileWriter writer;
        try {
            writer = new FileWriter(fileName, true);

            writer.write(String.valueOf(board));
            writer.write("$$\n");

            writer.write(String.valueOf(currentShape));
            writer.write("$$\n");

            writer.write(pos_x);
            writer.write("$$\n");

            writer.write(pos_y);
            writer.write("$$\n");

            writer.write(String.valueOf(nextShape));
            writer.write("$$\n");

            writer.close();
            System.out.println("Successfully saved : " + fileName);

        } catch (IOException e) {
            System.out.println("Game cannot be saved !");
            e.printStackTrace();
        }


    }

    public static void load(String fileName){

        String content = "";

        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                content += reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Game cannot be loaded !");
            e.printStackTrace();
        }
        String board = "", currentShape = "", posX = "", posY = "", nextShape = "";

        board = content.substring(0, content.indexOf("$$"));
        content = content.substring(content.indexOf("$$") + 2);

        currentShape = content.substring(0, content.indexOf("$$"));
        content = content.substring(content.indexOf("$$") + 2);

        posX = content.substring(0, content.indexOf("$$"));
        content = content.substring(content.indexOf("$$") + 2);

        posY = content.substring(0, content.indexOf("$$"));
        content = content.substring(content.indexOf("$$") + 2);

        nextShape = content.substring(0, content.indexOf("$$"));
        content = content.substring(content.indexOf("$$") + 2);


        int [][] tempBoard = new int[GameController.board.getBoardMatrix().length]
                [GameController.board.getBoardMatrix()[0].length];
        for(int i = 0; i< tempBoard.length; i++){
            for (int j = 0 ; j<tempBoard[i].length; j++ ){
                tempBoard[i][j] = board.charAt( i * tempBoard[i].length + j ) - 48;
            }
        }


        int [][] tempCurrentShape = new int[GameController.board.getViewData().getBrickData().length]
                [GameController.board.getViewData().getBrickData()[0].length];
        for(int i = 0; i< tempCurrentShape.length; i++){
            for (int j = 0 ; j<tempCurrentShape[i].length; j++ ){
                tempCurrentShape[i][j] = currentShape.charAt( i * tempCurrentShape[i].length + j ) - 48;
            }
        }

        int tempPosX = Integer.parseInt(posX);
        int tempPosY = Integer.parseInt(posY);


        int [][] tempNextShape = new int[GameController.board.getViewData().getBrickData().length]
                [GameController.board.getViewData().getBrickData()[0].length];
        for(int i = 0; i< tempNextShape.length; i++){
            for (int j = 0 ; j<tempNextShape[i].length; j++ ){
                tempNextShape[i][j] = nextShape.charAt( i * tempNextShape[i].length + j ) - 48;
            }
        }

        int score = Integer.parseInt(content);

        GameController.setCurrentState( new CurrentState(tempBoard , tempCurrentShape,
                tempNextShape, tempPosX, tempPosY, score));

        GameController.viewGuiController.refreshGameBackground(tempBoard);
        GameController.viewGuiController.refreshBrick(new ViewData(tempCurrentShape, tempPosX, tempPosY, tempNextShape));
        DownData.setViewData( new ViewData(tempCurrentShape , tempPosX, tempPosY, tempNextShape));
        GameController.viewGuiController.pauseButton.selectedProperty().setValue(true);

    }

    public  static  int [][] getGMatrix(){ return GameController.board.getBoardMatrix(); }
    public static ViewData getGViewData(){ return new ViewData(getGCurrentShape(), getGPosX(),getGPosY(),getGNextShape()); }

    public static int [][] getGCurrentShape(){ return GameController.board.getViewData().getBrickData(); }
    public static int [][] getGNextShape(){ return  GameController.board.getViewData().getBrickData(); }
    public static  int getGPosX(){ return GameController.board.getViewData().getxPosition(); }
    public static  int getGPosY(){ return GameController.board.getViewData().getyPosition(); }
    public static int getGScore(){
        final int value = GameController.board.getScore().scoreProperty().getValue();
        return value;
    }





























    public int[][] getBoard() { return board; }
    public void setBoard(int[][] board) { this.board = board; }

    public int[][] getCurrentShape() { return currentShape; }
    public void setCurrentShape(int[][] currentShape) { this.currentShape = currentShape; }

    public int[][] getComingShape() { return comingShape; }
    public void setComingShape(int[][] comingShape) { this.comingShape = comingShape; }

    public int getPos_x() { return pos_x; }
    public void setPos_x(int pos_x) { this.pos_x = pos_x; }

    public int getPos_y() { return pos_y; }
    public void setPos_y(int pos_y) { this.pos_y = pos_y; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}