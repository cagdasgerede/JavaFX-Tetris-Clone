package com.quirko.logic.bricks;

public interface BrickGenerator {

    Brick getBrick();

    Brick getBrick(int brick);

    Brick getNextBrick();
}
