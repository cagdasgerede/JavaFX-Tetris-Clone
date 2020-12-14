package com.quirko.logic.bricks;

import java.util.List;
import java.util.ArrayList;

public class HardBricks {
    
    public List<Brick> brickList;
    
    public HardBricks(){
        brickList = new ArrayList<>();
        brickList.add(new SBrick());
        brickList.add(new SBrick());
        brickList.add(new TBrick());
        brickList.add(new TBrick());
        brickList.add(new ZBrick());
        brickList.add(new ZBrick());
    }

}
