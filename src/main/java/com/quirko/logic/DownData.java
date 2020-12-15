package com.quirko.logic;

public final class DownData {
    private final ClearRow clearRow;
    private final ViewData viewData;
    private final Achievements achievement;

    public DownData(ClearRow clearRow, ViewData viewData, Achievements achievement) {
        this.clearRow = clearRow;
        this.viewData = viewData;
        this.achievement = achievement;
    }

    public ClearRow getClearRow() {
        return clearRow;
    }

    public ViewData getViewData() {
        return viewData;
    }

    public Achievements getAchievements() {
        return achievement;
    }
}
