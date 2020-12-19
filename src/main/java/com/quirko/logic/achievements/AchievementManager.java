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

    public void checkTotalLinesCleared(int lines) {
        totalCleared.checkAchievement(lines);
    }

    public void checkClearedAtOnce(int lines) {
        clearedAtOnce.checkAchievement(lines);
    }

    public void checkScored(int score) {
        scored.checkAchievement(score);
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
