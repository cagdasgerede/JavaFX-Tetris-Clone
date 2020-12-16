package com.quirko.logic.bricks;

import java.util.List;
import java.util.ArrayList;
    
public class MediumBricks {
    
    private List<Brick> brickListTypes;

    public MediumBricks(){
        brickListTypes = new ArrayList<>();
        brickListTypes.add(new JBrick());
        brickListTypes.add(new LBrick());
    }

    public List<Brick> getBrickTypes(){
        return this.brickListTypes;
    }

}
