package com.quirko.logic;

public final class DownData {
    private static ClearRow clearRow;
    private static ViewData viewData;

    public DownData(ClearRow clear, ViewData view) {
        clearRow = clear;
        viewData = view;
    }

    public ClearRow getClearRow() {
        return clearRow;
    }

    public static ViewData getViewData() {
        return viewData;
    }
    public static void setViewData(ViewData view)
    {
        viewData = view;
    }
}
