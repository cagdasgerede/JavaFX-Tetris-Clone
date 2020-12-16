package com.quirko.logic.bricks;

import java.util.List;
import java.util.ArrayList;

public class HardBricks {
    
    private List<Brick> brickListTypes;
    
    public HardBricks(){
        brickListTypes = new ArrayList<>();
        brickListTypes.add(new SBrick());
        brickListTypes.add(new TBrick());
        brickListTypes.add(new ZBrick());
    }
    
    public List<Brick> getBrickTypes(){
        return this.brickListTypes;
    }

}
