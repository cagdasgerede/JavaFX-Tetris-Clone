package com.quirko.logic.bricks;

public interface BrickGenerator {

    Brick getBrick(int i, int j);

    Brick getNextBrick();
}
