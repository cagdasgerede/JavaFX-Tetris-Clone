package com.quirko.logic.bricks;

import java.util.List;
import java.util.ArrayList;

public class EasyConfiguration {

    private List<Brick> brickList;

    private HardBricks hardBricks;

    private MediumBricks mediumBricks;

    private EasyBricks easyBricks;

    /*In easy level add each easy type of bricks four times for decreased difficulty level
    and other types were added only one time. In every level each type of bricks is added.*/
    public EasyConfiguration(){
        brickList = new ArrayList<>();
        easyBricks = new EasyBricks();
        hardBricks = new HardBricks();
        mediumBricks = new MediumBricks();
        for (Brick b  : easyBricks.getBrickTypes()) {
            brickList.add(b);
            brickList.add(b);
            brickList.add(b);
            brickList.add(b);
          }
        for (Brick b  : mediumBricks.getBrickTypes()) {
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
