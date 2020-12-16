package com.quirko.logic.bricks;

import java.util.List;

public interface BrickGenerator {

    Brick getBrick();

    Brick getNextBrick();

    Brick changeNextBrick();

    List<Brick> getList();
}
