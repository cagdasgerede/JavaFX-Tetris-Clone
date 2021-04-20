package com.quirko.logic.events;

import com.quirko.logic.ViewData;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import com.quirko.logic.DownData;

public interface InputEventListener2 {

    DownData onDownEvent(MoveEvent event,KeyCode code);

    DownData onDownEvent(MoveEvent event,KeyEvent key);

    ViewData onLeftEvent(MoveEvent event,KeyEvent key);

    ViewData onRightEvent(MoveEvent event,KeyEvent key);

    ViewData onRotateEvent(MoveEvent event,KeyEvent key);

    void createNewGame(boolean first);
}
