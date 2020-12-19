package com.quirko.logic;

import com.quirko.logic.achievements.AchievementManager;

public final class DownData {
    private final ClearRow clearRow;
    private final ViewData viewData;
    private final AchievementManager achievements;

    public DownData(ClearRow clearRow, ViewData viewData, AchievementManager achievements) {
        this.clearRow = clearRow;
        this.viewData = viewData;
        this.achievements = achievements;
    }

    public ClearRow getClearRow() {
        return clearRow;
    }

    public ViewData getViewData() {
        return viewData;
    }

    public AchievementManager getAchievements() {
        return achievements;
    }
}
