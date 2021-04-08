package com.quirko.app;

import com.quirko.gui.GuiController;
import com.quirko.logic.*;
import com.quirko.logic.events.EventSource;
import com.quirko.logic.events.InputEventListener;
import com.quirko.logic.events.MoveEvent;


import java.util.ArrayList;

import com.quirko.achievements.*;

public class GameController implements InputEventListener {

    private Board board = new SimpleBoard(25, 10);

    private final GuiController viewGuiController;

    public int linesRemoved;

    public ArrayList<Achievement> achievements;

    public GameController(GuiController c) {
        viewGuiController = c;
        board.createNewBrick();
        viewGuiController.setEventListener(this);
        viewGuiController.initGameView(board.getBoardMatrix(), board.getViewData());
        viewGuiController.bindScore(board.getScore().scoreProperty());
        linesRemoved=0;
        achievements=new ArrayList<Achievement>();
        achievements.add(new ScoreAchievement(50, false));
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
                linesRemoved+=clearRow.getLinesRemoved();
                if(isAchievementFound(linesRemoved)!=null){
                    viewGuiController.getTimeline().pause();
                    new AchievementPopUp(isAchievementFound(linesRemoved));
                    viewGuiController.getToggleButton().setText("Resume");
                    viewGuiController.getIsPause().setValue(true);
                }
                else if(isAchievementFound(clearRow.getLinesRemoved())!=null){
                    viewGuiController.getTimeline().pause();
                    new AchievementPopUp(isAchievementFound(linesRemoved));
                    viewGuiController.getToggleButton().setText("Resume");
                    viewGuiController.getIsPause().setValue(true);
                }
            }
            if (board.createNewBrick()) {
                viewGuiController.gameOver();
            }

            viewGuiController.refreshGameBackground(board.getBoardMatrix());

        } else {
            if (event.getEventSource() == EventSource.USER) {
                board.getScore().add(1);
                if(isAchievementFound(board.getScore().getIntValue())!=null){
                    viewGuiController.getTimeline().pause();
                    new AchievementPopUp(isAchievementFound(board.getScore().getIntValue()));
                    viewGuiController.getToggleButton().setText("Resume");
                    viewGuiController.getIsPause().setValue(true);
                }
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

    public Achievement isAchievementFound(int goal){
        for (Achievement achievement : achievements) {
            if(achievement.completed){
                continue;
            }
            else if(goal>=achievement.goal && achievement.getClass()==ScoreAchievement.class){
                return achievement;
            }
            else if(goal>=achievement.goal && achievement.getClass()==TotalLinesDestroyedAchievement.class){
                return achievement;
            }
            else if(goal>=achievement.goal && achievement.getClass()==LinesDestroyedSimultaneouslyAchievement.class){
                return achievement;
            }
        }
        return null;
    }
}
