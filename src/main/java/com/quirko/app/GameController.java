package com.quirko.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.quirko.gui.GameState;
import com.quirko.gui.GuiController;
import com.quirko.logic.*;
import com.quirko.logic.bricks.*;

import com.quirko.logic.events.EventSource;
import com.quirko.logic.events.InputEventListener;
import com.quirko.logic.events.MoveEvent;
import com.quirko.logic.rotator.BrickRotator;
import com.quirko.logic.rotator.NextShapeInfo;

public class GameController implements InputEventListener {

    private static Board board = new SimpleBoard(25, 10);
    private static GameState gameState;

    private static GuiController viewGuiController;

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
        //System.out.println(board.getViewData().getxPosition() + " AAAAAAAA");
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
        
        String boardMatrix = "", currentShape = "", nextShape = "", posX = "" + getPosX(), posY = "" +  getPosY(), score = "" +  getScore();
        for(int[] i : getBoardMatrix())
        {
            for(int j : i)
                boardMatrix += j;
            boardMatrix += "\n";
        }
        
        
        for(int[] i : getCurrentShape())
        {
            for(int j : i)
                currentShape += j;
            currentShape += "\n";
        }

        for(int[] i : getNextShape())
        {
            for(int j : i)
                nextShape += j;
            nextShape += "\n";
        }

        new File("saves" + File.separator + fileName);
        
        try {
            FileWriter myWriter = new FileWriter("saves" + File.separator + fileName, true);
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

    public static void loadGame(String fileName)
    {
        String data = "";
        fileName = "saves" + File.separator + fileName;
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


        int[][] intBoardMatrix = new int[board.getBoardMatrix().length][board.getBoardMatrix()[0].length];
        for(int i = 0; i < intBoardMatrix.length; i++)
            for(int j = 0; j < intBoardMatrix[i].length; j++)
                intBoardMatrix[i][j] = (int)(boardMatrix.charAt((i * intBoardMatrix[i].length) + j)) - 48;

        int[][] intCurrentShape = new int[board.getViewData().getBrickData().length][board.getViewData().getBrickData()[0].length];
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

        setGameState( new GameState(intBoardMatrix, intCurrentShape, intNextShape, intPosX, intPosY, score));
        viewGuiController.refreshGameBackground(intBoardMatrix);
        viewGuiController.refreshBrick(new ViewData(intCurrentShape, intPosX, intPosY, intNextShape));
        DownData.setViewData(new ViewData(intCurrentShape, intPosX, intPosY, intNextShape));
        viewGuiController.pauseButton.selectedProperty().setValue(true);
    }
    @Override
    public void createNewGame() {
        board.newGame();
        viewGuiController.refreshGameBackground(board.getBoardMatrix());
    }

    public static int[][] getBoardMatrix(){
        return board.getBoardMatrix();
    }

    public static ViewData gViewData()
    {
        return new ViewData(getCurrentShape(), getPosX(), getPosY(), getNextShape());
    }
    
    public static int[][] getCurrentShape(){
        return board.getViewData().getBrickData();
    }

    
    public static int[][] getNextShape(){
        return board.getViewData().getNextBrickData();
    }

    
    public static int getPosX(){
        return board.getViewData().getxPosition();
    }

    
    public static int getPosY(){
        return board.getViewData().getyPosition();
    }
    
    public static int getScore(){
        return board.getScore().scoreProperty().getValue().intValue();
    }

    public static void initGameState(){
        gameState = new GameState(getBoardMatrix(), getCurrentShape(), getNextShape(), getPosX(), getPosY(), getScore());
    }
    
    public GameState getGameState(){
        return gameState;
    }

    public static void setGameState(GameState gameState){
        board.setBoardMatrix(gameState.getBoardMatrix());
        board.setViewData(gameState.getCurrentShape(), gameState.getPosX(), gameState.getPosY(), gameState.getNextShape());
        int currentShape = 0, nextShape = 0;
        
        for(int i = 0; i < gameState.getNextShape().length * gameState.getNextShape()[0].length; i++)
            if(gameState.getNextShape()[i / gameState.getNextShape().length][i % gameState.getNextShape().length] != nextShape)
            {
                nextShape = gameState.getNextShape()[i / gameState.getNextShape().length][i % gameState.getNextShape().length];
                break;
            }
        for(int i = 0; i < gameState.getCurrentShape().length * gameState.getCurrentShape()[0].length; i++)
            if(gameState.getCurrentShape()[i / gameState.getCurrentShape().length][i % gameState.getCurrentShape().length] != currentShape)
            {
                currentShape = gameState.getCurrentShape()[i / gameState.getCurrentShape().length][i % gameState.getCurrentShape().length];
                break;
            }
        SimpleBoard.setBrick(currentShape);
        RandomBrickGenerator.addBrick(nextShape);
    }
}
