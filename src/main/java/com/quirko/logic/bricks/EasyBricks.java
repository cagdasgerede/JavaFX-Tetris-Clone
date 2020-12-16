package com.quirko.logic.bricks;

import java.util.List;
import java.util.ArrayList;

public class EasyBricks{
    
    private List<Brick> brickListTypes;
        
    public EasyBricks(){
        brickListTypes = new ArrayList<>();
        brickListTypes.add(new IBrick());
        brickListTypes.add(new OBrick());
    }
    
    public List<Brick> getBrickTypes(){
        return this.brickListTypes;
    }
    
}
