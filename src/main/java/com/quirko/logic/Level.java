package com.quirko.logic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public final class Level {

    private final IntegerProperty level = new SimpleIntegerProperty(0);

    public IntegerProperty levelProperty() {
        return level;
    }

    public void add(int i){
        level.setValue(level.getValue() + i);
    }

    public void reset() {
        level.setValue(0);
    }
    public int getlevelValue(){
        int value = (int)level.getValue();
        return value;
    }
}
