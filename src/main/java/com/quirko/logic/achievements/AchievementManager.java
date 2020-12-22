package com.quirko.logic.achievements;

public class AchievementManager {
    
    public TotalLinesCleared totalCleared = new TotalLinesCleared();
    public LinesClearedAtOnce clearedAtOnce = new LinesClearedAtOnce();
    public Scored scored = new Scored();

    public AchievementManager() {
        totalCleared = new TotalLinesCleared();
        clearedAtOnce = new LinesClearedAtOnce();
        scored = new Scored();
    }

    public AchievementManager(int totalLines, int atOnce, int highestScore) {
        totalCleared = new TotalLinesCleared(totalLines);
        clearedAtOnce = new LinesClearedAtOnce(atOnce);
        scored = new Scored(highestScore);
    }

    public void checkTotalLinesCleared(int lines, AchievementFileIO fileIO) {
        boolean isAchieved = totalCleared.checkAchievement(lines);
        if (isAchieved) {
            fileIO.saveAchievements(UsernameInputWindow.username, this);
        }
    }

    public void checkClearedAtOnce(int lines, AchievementFileIO fileIO) {
        boolean isAchieved = clearedAtOnce.checkAchievement(lines);
        if (isAchieved) {
            fileIO.saveAchievements(UsernameInputWindow.username, this);
        }
    }

    public void checkScored(int score, AchievementFileIO fileIO) {
        boolean isAchieved = scored.checkAchievement(score);
        if (isAchieved) {
            fileIO.saveAchievements(UsernameInputWindow.username, this);
        }
    }

    public String getCompletionMessage() {
        if (!Achievements.notDisplayed.isEmpty())
            return Achievements.notDisplayed.poll();
        return null;
    }

    public boolean hasUndisplayed() {
        return !Achievements.notDisplayed.isEmpty();
    }

}
