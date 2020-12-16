package com.quirko.logic.bricks;

import java.util.List;
import java.util.ArrayList;

public class MediumConfiguration {

    private List<Brick> brickList;

    private HardBricks hardBricks;

    private MediumBricks mediumBricks;

    private EasyBricks easyBricks;

    /*In medium level add each medium type of bricks four times for balanced difficulty level.
    In every level each type of bricks is added.*/
    public MediumConfiguration(){
        brickList = new ArrayList<>();
        easyBricks = new EasyBricks();
        hardBricks = new HardBricks();
        mediumBricks = new MediumBricks();
        for (Brick b  : easyBricks.getBrickTypes()) {
            brickList.add(b);
          }
        for (Brick b  : mediumBricks.getBrickTypes()) {
            brickList.add(b);
            brickList.add(b);
            brickList.add(b);
            brickList.add(b);
        }
        for (Brick b  : hardBricks.getBrickTypes()) {
            brickList.add(b);
        }
    }

    public List<Brick> getConfiguration() {
        return this.brickList;
    }

}
