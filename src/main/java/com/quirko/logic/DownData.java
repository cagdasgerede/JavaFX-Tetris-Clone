package com.quirko.logic;

public final class DownData {
    private final ClearRow clearRow;
    private static ViewData viewData;

    public DownData(ClearRow clearRow, ViewData view) {
        this.clearRow = clearRow;
        viewData = view;
    }

    public ClearRow getClearRow() {
        return clearRow;
    }

    public ViewData getViewData() {
        return viewData;
    }

    public static void setViewData(ViewData view) { viewData = view; }
}
