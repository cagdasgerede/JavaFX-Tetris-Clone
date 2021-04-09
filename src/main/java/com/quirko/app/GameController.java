package com.quirko.app;

import com.quirko.gui.GuiController;
import com.quirko.logic.*;
import com.quirko.logic.events.EventSource;
import com.quirko.logic.events.InputEventListener;
import com.quirko.logic.events.MoveEvent;

import com.quirko.achievements.*;

public class GameController implements InputEventListener {

    private Board board = new SimpleBoard(25, 10);

    private final GuiController viewGuiController;

    public static AchievementList achievements;

    public GameController(GuiController c) {
        viewGuiController = c;
        board.createNewBrick();
        viewGuiController.setEventListener(this);
        viewGuiController.initGameView(board.getBoardMatrix(), board.getViewData());
        viewGuiController.bindScore(board.getScore().scoreProperty());
        achievements=new AchievementList();
        achievements.add(new ScoreAchievement(0,20,false));
        achievements.add(new LinesDestroyedSimultaneouslyAchievement(0, 2, false));
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
                if(isLineDestroyedSimultaneouslyAchievementFound(clearRow.getLinesRemoved())!=null){
                    viewGuiController.getTimeline().pause();
                    new AchievementPopUp(isLineDestroyedSimultaneouslyAchievementFound(clearRow.getLinesRemoved()));
                    viewGuiController.getToggleButton().setText("Resume");
                    viewGuiController.getIsPause().setValue(true);
                }
                else if(isTotalLinesDestroyedAchievementFound(clearRow.getLinesRemoved())!=null){
                    viewGuiController.getTimeline().pause();
                    new AchievementPopUp(isTotalLinesDestroyedAchievementFound(clearRow.getLinesRemoved()));
                    viewGuiController.getToggleButton().setText("Resume");
                    viewGuiController.getIsPause().setValue(true);
                }
                else if(isScoreAchievementFound(board.getScore().getIntValue())!=null){
                    viewGuiController.getTimeline().pause();
                    new AchievementPopUp(isScoreAchievementFound(board.getScore().getIntValue()));
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
                if(isScoreAchievementFound(board.getScore().getIntValue())!=null){
                    viewGuiController.getTimeline().pause();
                    new AchievementPopUp(isScoreAchievementFound(board.getScore().getIntValue()));
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

    public Achievement isLineDestroyedSimultaneouslyAchievementFound(int lineDiff){
        for (int i=0;i<achievements.size();++i) {
            Achievement achievement=achievements.get(i);
            if(achievement.completed)
                continue;
            else if(lineDiff>=achievement.goal)
                return achievement;
        }
        return null;
    }
    public Achievement isTotalLinesDestroyedAchievementFound(int lineDiff){
        for (int i=0;i<achievements.size();++i) {
            Achievement achievement=achievements.get(i);
            if(achievement.completed)
                continue;
            else if(achievement.currentState<achievement.goal)
                achievement.currentState+=lineDiff;
            if(achievement.currentState>=achievement.goal)
                return achievement;
        }
        return null;
    }
    public Achievement isScoreAchievementFound(int score){
        for(int i=0;i<achievements.size();++i){
            Achievement achievement=achievements.get(i);
            if(achievement.completed)
                continue;
            else if(score>=achievement.goal && achievement.getClass()==ScoreAchievement.class)
                return achievement;
        }
        return null;   
    }
}
