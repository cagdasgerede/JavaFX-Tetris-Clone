package com.quirko.gui;

import com.quirko.logic.DownData;
import com.quirko.logic.ViewData;
import com.quirko.logic.events.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

public class GuiController2 implements Initializable {

    private static final int BRICK_SIZE = 20;

    @FXML
    private GridPane gamePanel;
    
    @FXML
    private GridPane gamePanel2;

    @FXML
    private Text scoreValue;

    @FXML
    private Text scoreValue2;

    @FXML
    private Group groupNotification;

    @FXML
    private GridPane nextBrick;
    @FXML
    private GridPane nextBrick2;

    @FXML
    private GridPane brickPanel;

    @FXML
    private GridPane brickPanel2;

    @FXML
    private ToggleButton pauseButton2;

    private Rectangle[][] displayMatrix;
    private Rectangle[][] displayMatrix2;

    private InputEventListener2 eventListener;

    private Rectangle[][] rectangles;
    private Rectangle[][] rectangles2;

    private Timeline timeLine;
    private Timeline timeLine2;

    private final BooleanProperty isPause = new SimpleBooleanProperty();

    private final BooleanProperty isGameOver = new SimpleBooleanProperty();
    private final BooleanProperty isGameOver2 = new SimpleBooleanProperty();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        Font.loadFont(getClass().getClassLoader().getResource("digital.ttf").toExternalForm(), 50);
        gamePanel.setFocusTraversable(true);
        gamePanel.requestFocus();
        gamePanel2.setFocusTraversable(true);
        gamePanel2.requestFocus();
        
        gamePanel.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (isPause.getValue() == Boolean.FALSE && isGameOver2.getValue()==Boolean.FALSE&&isGameOver.getValue() == Boolean.FALSE) {
                    if (keyEvent.getCode() == KeyCode.A||keyEvent.getCode() == KeyCode.LEFT) {
                        refreshBrick(eventListener.onLeftEvent(new MoveEvent(EventType.LEFT, EventSource.USER),keyEvent),keyEvent);

                        keyEvent.consume();
                    }
                    if (keyEvent.getCode() == KeyCode.D||keyEvent.getCode() == KeyCode.RIGHT) {
                        refreshBrick(eventListener.onRightEvent(new MoveEvent(EventType.RIGHT, EventSource.USER),keyEvent),keyEvent);
                        keyEvent.consume();
                    }
                    if (keyEvent.getCode() == KeyCode.W||keyEvent.getCode() == KeyCode.UP) {
                        refreshBrick(eventListener.onRotateEvent(new MoveEvent(EventType.ROTATE, EventSource.USER),keyEvent),keyEvent);

                        keyEvent.consume();
                    }
                    if (keyEvent.getCode() == KeyCode.S||keyEvent.getCode() == KeyCode.DOWN) {
                        moveDown(new MoveEvent(EventType.DOWN, EventSource.USER),keyEvent);
                        keyEvent.consume();
                    }
                }

                if (keyEvent.getCode() == KeyCode.N) {
                    newGame(null);
                }
            }
        });
        
        final Reflection reflection = new Reflection();
        reflection.setFraction(0.8);
        reflection.setTopOpacity(0.9);
        reflection.setTopOffset(-12);
        scoreValue.setEffect(reflection);

        pauseButton2.selectedProperty().bindBidirectional(isPause);
        pauseButton2.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    timeLine2.pause();
                    pauseButton2.setText("Resume");
                } else {
                    timeLine2.play();
                    pauseButton2.setText("Pause");
                }
            }
        });

        
        final Reflection reflection2 = new Reflection();
        reflection2.setFraction(0.8);
        reflection2.setTopOpacity(0.9);
        reflection2.setTopOffset(-12);
        scoreValue2.setEffect(reflection2);

        

    }

    public void initGameView(int[][] boardMatrix, ViewData brick) {
        displayMatrix = new Rectangle[boardMatrix.length][boardMatrix[0].length];
        for (int i = 2; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rectangle.setFill(Color.TRANSPARENT);
                displayMatrix[i][j] = rectangle;
                gamePanel.add(rectangle, j, i - 2);
            }
        }

        rectangles = new Rectangle[brick.getBrickData().length][brick.getBrickData()[0].length];
        for (int i = 0; i < brick.getBrickData().length; i++) {
            for (int j = 0; j < brick.getBrickData()[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rectangle.setFill(getFillColor(brick.getBrickData()[i][j]));
                rectangles[i][j] = rectangle;
                brickPanel.add(rectangle, j, i);
            }
        }
        brickPanel.setLayoutX(gamePanel.getLayoutX() + brick.getxPosition() * brickPanel.getVgap() + brick.getxPosition() * BRICK_SIZE);
        brickPanel.setLayoutY(-42 + gamePanel.getLayoutY() + brick.getyPosition() * brickPanel.getHgap() + brick.getyPosition() * BRICK_SIZE);

        generatePreviewPanel(brick.getNextBrickData());


        timeLine = new Timeline(new KeyFrame(
                Duration.millis(400),
                ae -> moveDown2(new MoveEvent(EventType.DOWN, EventSource.THREAD),KeyCode.DOWN)
        ));timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
       
    }
    public void initGameView2(int[][] boardMatrix, ViewData brick) {
        displayMatrix2 = new Rectangle[boardMatrix.length][boardMatrix[0].length];
        for (int i = 2; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rectangle.setFill(Color.TRANSPARENT);
                displayMatrix2[i][j] = rectangle;
                gamePanel2.add(rectangle, j, i - 2);
            }
        }

        rectangles2 = new Rectangle[brick.getBrickData().length][brick.getBrickData()[0].length];
        for (int i = 0; i < brick.getBrickData().length; i++) {
            for (int j = 0; j < brick.getBrickData()[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rectangle.setFill(getFillColor(brick.getBrickData()[i][j]));
                rectangles2[i][j] = rectangle;
                brickPanel2.add(rectangle, j, i);
            }
        }
       
        brickPanel2.setLayoutX(gamePanel2.getLayoutX() + brick.getxPosition() * brickPanel2.getVgap() + brick.getxPosition() * BRICK_SIZE);
        brickPanel2.setLayoutY(-42 + gamePanel2.getLayoutY() + brick.getyPosition() * brickPanel2.getHgap() + brick.getyPosition() * BRICK_SIZE);

        generatePreviewPanel2(brick.getNextBrickData());


        timeLine2 = new Timeline(new KeyFrame(
            Duration.millis(400),
            ae -> moveDown2(new MoveEvent(EventType.DOWN, EventSource.THREAD),KeyCode.S)
    ));
    
    timeLine2.setCycleCount(Timeline.INDEFINITE);
    timeLine2.play();
    }

    private Paint getFillColor(int i) {
        Paint returnPaint;
        switch (i) {
            case 0:
                returnPaint = Color.TRANSPARENT;
                break;
            case 1:
                returnPaint = Color.AQUA;
                break;
            case 2:
                returnPaint = Color.BLUEVIOLET;
                break;
            case 3:
                returnPaint = Color.DARKGREEN;
                break;
            case 4:
                returnPaint = Color.YELLOW;
                break;
            case 5:
                returnPaint = Color.RED;
                break;
            case 6:
                returnPaint = Color.BEIGE;
                break;
            case 7:
                returnPaint = Color.BURLYWOOD;
                break;
            default:
                returnPaint = Color.WHITE;
                break;
        }
        return returnPaint;
    }

    private void generatePreviewPanel(int[][] nextBrickData) {
        nextBrick.getChildren().clear();
        for (int i = 0; i < nextBrickData.length; i++) {
            for (int j = 0; j < nextBrickData[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                setRectangleData(nextBrickData[i][j], rectangle);
                if (nextBrickData[i][j] != 0) {
                    nextBrick.add(rectangle, j, i);
                }
            }
        }
    }

    private void generatePreviewPanel2(int[][] nextBrickData) {//2. oyuncu icin
        nextBrick2.getChildren().clear();
        for (int i = 0; i < nextBrickData.length; i++) {
            for (int j = 0; j < nextBrickData[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                setRectangleData(nextBrickData[i][j], rectangle);
                if (nextBrickData[i][j] != 0) {
                    nextBrick2.add(rectangle, j, i);
                }
            }
        }
    }

    private void refreshBrick(ViewData brick,KeyEvent key) {
        if(key.getCode()==KeyCode.A||key.getCode()==KeyCode.D||key.getCode()==KeyCode.S||key.getCode()==KeyCode.W){
        if (isPause.getValue() == Boolean.FALSE) {
            brickPanel.setLayoutX(gamePanel.getLayoutX() + brick.getxPosition() * brickPanel.getVgap() + brick.getxPosition() * BRICK_SIZE);
            brickPanel.setLayoutY(-42 + gamePanel.getLayoutY() + brick.getyPosition() * brickPanel.getHgap() + brick.getyPosition() * BRICK_SIZE);
            for (int i = 0; i < brick.getBrickData().length; i++) {
                for (int j = 0; j < brick.getBrickData()[i].length; j++) {
                    setRectangleData(brick.getBrickData()[i][j], rectangles[i][j]);
                }
            }
            generatePreviewPanel(brick.getNextBrickData());
        }}
        else{
            if (isPause.getValue() == Boolean.FALSE) {
                brickPanel2.setLayoutX(gamePanel2.getLayoutX() + brick.getxPosition() * brickPanel2.getVgap() + brick.getxPosition() * BRICK_SIZE);
                brickPanel2.setLayoutY(-42 + gamePanel2.getLayoutY() + brick.getyPosition() * brickPanel2.getHgap() + brick.getyPosition() * BRICK_SIZE);
                for (int i = 0; i < brick.getBrickData().length; i++) {
                    for (int j = 0; j < brick.getBrickData()[i].length; j++) {
                        setRectangleData(brick.getBrickData()[i][j], rectangles2[i][j]);
                    }
                }
                generatePreviewPanel2(brick.getNextBrickData());
            }
        }
    }
    private void refreshBrick2(ViewData brick,boolean first) {//Keyevent yerine boolean alıyor
        if(first){
        if (isPause.getValue() == Boolean.FALSE) {
                brickPanel.setLayoutX(gamePanel.getLayoutX() + brick.getxPosition() * brickPanel.getVgap() + brick.getxPosition() * BRICK_SIZE);
                brickPanel.setLayoutY(-42 + gamePanel.getLayoutY() + brick.getyPosition() * brickPanel.getHgap() + brick.getyPosition() * BRICK_SIZE);
                for (int i = 0; i < brick.getBrickData().length; i++) {
                    for (int j = 0; j < brick.getBrickData()[i].length; j++) {
                        setRectangleData(brick.getBrickData()[i][j], rectangles[i][j]);
                    }
                }
                generatePreviewPanel(brick.getNextBrickData());
            }
        }
        else{
                if (isPause.getValue() == Boolean.FALSE) {
                    brickPanel2.setLayoutX(gamePanel2.getLayoutX() + brick.getxPosition() * brickPanel2.getVgap() + brick.getxPosition() * BRICK_SIZE);
                    brickPanel2.setLayoutY(-42 + gamePanel2.getLayoutY() + brick.getyPosition() * brickPanel2.getHgap() + brick.getyPosition() * BRICK_SIZE);
                    for (int i = 0; i < brick.getBrickData().length; i++) {
                        for (int j = 0; j < brick.getBrickData()[i].length; j++) {
                            setRectangleData(brick.getBrickData()[i][j], rectangles2[i][j]);
                        }
                    }
                    generatePreviewPanel2(brick.getNextBrickData());
                }
        }
    }

    public void refreshGameBackground(int[][] board) {
        for (int i = 2; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                setRectangleData(board[i][j], displayMatrix[i][j]);
            }
        }
    }
    public void refreshGameBackground2(int[][] board) {//for second board
        for (int i = 2; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                setRectangleData(board[i][j], displayMatrix2[i][j]);
            }
        }
    }

    private void setRectangleData(int color, Rectangle rectangle) {
        rectangle.setFill(getFillColor(color));
        rectangle.setArcHeight(9);
        rectangle.setArcWidth(9);
    }

    private void moveDown(MoveEvent event,KeyEvent keyEvent) {
        if(keyEvent.getCode()==KeyCode.S){
        if (isPause.getValue() == Boolean.FALSE) {
            DownData downData = eventListener.onDownEvent(event,keyEvent);
            if (downData.getClearRow() != null && downData.getClearRow().getLinesRemoved() > 0) {
                NotificationPanel notificationPanel = new NotificationPanel("+" + downData.getClearRow().getScoreBonus());
                groupNotification.getChildren().add(notificationPanel);
                notificationPanel.showScore(groupNotification.getChildren());
            }
            refreshBrick2(downData.getViewData(),true);
        }
        gamePanel.requestFocus();}
        else{
            if (isPause.getValue() == Boolean.FALSE) {
                DownData downData = eventListener.onDownEvent(event,keyEvent);
                if (downData.getClearRow() != null && downData.getClearRow().getLinesRemoved() > 0) {
                    NotificationPanel notificationPanel = new NotificationPanel("+" + downData.getClearRow().getScoreBonus());
                    groupNotification.getChildren().add(notificationPanel);
                    notificationPanel.showScore(groupNotification.getChildren());
                }
                refreshBrick2(downData.getViewData(),false);
            }
            gamePanel2.requestFocus();
        }
    }

    private void moveDown2(MoveEvent event,KeyCode code) {
        if(code==KeyCode.S){
        if (isPause.getValue() == Boolean.FALSE) {
            DownData downData = eventListener.onDownEvent(event,code);
            if (downData.getClearRow() != null && downData.getClearRow().getLinesRemoved() > 0) {
                NotificationPanel notificationPanel = new NotificationPanel("+" + downData.getClearRow().getScoreBonus());
                groupNotification.getChildren().add(notificationPanel);
                notificationPanel.showScore(groupNotification.getChildren());
            }
            refreshBrick2(downData.getViewData(),true);
        }
        gamePanel.requestFocus();}
        else{

            if (isPause.getValue() == Boolean.FALSE) {
                DownData downData = eventListener.onDownEvent(event,code);
                if (downData.getClearRow() != null && downData.getClearRow().getLinesRemoved() > 0) {
                    NotificationPanel notificationPanel = new NotificationPanel("+" + downData.getClearRow().getScoreBonus());
                    groupNotification.getChildren().add(notificationPanel);
                    notificationPanel.showScore(groupNotification.getChildren());
                }
                refreshBrick2(downData.getViewData(),false);
            }
            gamePanel2.requestFocus();
        }
    }

    public void setEventListener(InputEventListener2 eventListener) {
        this.eventListener = eventListener;
    }
    

    public void bindScore(IntegerProperty integerProperty) {
        scoreValue.textProperty().bind(integerProperty.asString());
    }
    public void bindScore2(IntegerProperty integerProperty) {
        scoreValue2.textProperty().bind(integerProperty.asString());
    }

    public void gameOver() {
        timeLine.stop();
        isGameOver.setValue(Boolean.TRUE);
    }
    public void gameOver2() {
        timeLine2.stop();
        isGameOver.setValue(Boolean.TRUE);

    }

    public void newGame(ActionEvent actionEvent) {
        timeLine.stop();
        timeLine2.stop();
        eventListener.createNewGame(true);
        eventListener.createNewGame(false);
        gamePanel.requestFocus();
        gamePanel2.requestFocus();
        timeLine.play(); timeLine2.play();
        isPause.setValue(Boolean.FALSE);
        isGameOver.setValue(Boolean.FALSE);
        isGameOver2.setValue(Boolean.FALSE);
    
    }
    public void pauseGame(ActionEvent actionEvent) {
        gamePanel2.requestFocus();
        gamePanel.requestFocus();
    }
}
