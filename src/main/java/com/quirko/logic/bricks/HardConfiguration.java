package com.quirko.logic.bricks;

import java.util.List;
import java.util.ArrayList;

public class HardConfiguration {

    private List<Brick> brickList;

    private HardBricks hardBricks;

    private MediumBricks mediumBricks;

    private EasyBricks easyBricks;

    
    /*In hard level add each hard type of bricks four times for increased difficulty level
    and other types were added only one time. In every level each type of bricks is added.*/
    public HardConfiguration(){
        brickList = new ArrayList<>();
        easyBricks = new EasyBricks();
        hardBricks = new HardBricks();
        mediumBricks = new MediumBricks();
        for (Brick b  : hardBricks.getBrickTypes()) {
            brickList.add(b);
            brickList.add(b);
            brickList.add(b);
            brickList.add(b);
          }
        for (Brick b  : mediumBricks.getBrickTypes()) {
            brickList.add(b);
        }
        for (Brick b  : easyBricks.getBrickTypes()) {
            brickList.add(b);
        }
    }

    public List<Brick> getConfiguration() {
        return this.brickList;
    }

}
