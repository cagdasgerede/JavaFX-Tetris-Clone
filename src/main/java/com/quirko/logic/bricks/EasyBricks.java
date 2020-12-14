package com.quirko.logic.bricks;

import java.util.List;
import java.util.ArrayList;

public class EasyBricks{
    
    public List<Brick> brickList;
        
    public EasyBricks(){
        brickList = new ArrayList<>();
        brickList.add(new IBrick());
        brickList.add(new IBrick());
        brickList.add(new JBrick());
        brickList.add(new JBrick());
        brickList.add(new LBrick());
        brickList.add(new LBrick());
        brickList.add(new OBrick());
        brickList.add(new OBrick());
   }

}
