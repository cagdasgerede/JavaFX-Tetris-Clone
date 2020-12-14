package com.quirko.logic.bricks;

import java.util.List;
import java.util.ArrayList;
    
public class MediumBricks {
    
    public List<Brick> brickList;

    public MediumBricks(){
        brickList = new ArrayList<>();
        brickList.add(new IBrick());
        brickList.add(new JBrick());
        brickList.add(new LBrick());
        brickList.add(new OBrick());
        brickList.add(new SBrick());
        brickList.add(new TBrick());
        brickList.add(new ZBrick());
    }

}
