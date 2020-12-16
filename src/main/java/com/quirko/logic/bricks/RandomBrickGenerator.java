package com.quirko.logic.bricks;

import com.quirko.gui.DifficultyType;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomBrickGenerator implements BrickGenerator {

    private final List<Brick> brickList;

    private final Deque<Brick> nextBricks = new ArrayDeque<>();
    
    private HardConfiguration hardBricks;

    private MediumConfiguration mediumBricks;

    private EasyConfiguration easyBricks;

    public RandomBrickGenerator(Enum diff) {
        brickList = new ArrayList<>();
        hardBricks = new HardConfiguration();
        mediumBricks = new MediumConfiguration();
        easyBricks = new EasyConfiguration();
        //According to every level configuration add brickList.
        if(diff == DifficultyType.MEDIUM){//for medium level adding each level for balancing
            brickList.addAll(mediumBricks.getConfiguration());
        }
        else if(diff == DifficultyType.HARD){//extra hard shape for increase difficulty
            brickList.addAll(hardBricks.getConfiguration());
            
        }
        else if(diff == DifficultyType.EASY){//extra easy shape for decrease difficulty
            brickList.addAll(easyBricks.getConfiguration());
        }
        nextBricks.add(brickList.get(ThreadLocalRandom.current().nextInt(brickList.size())));
        nextBricks.add(brickList.get(ThreadLocalRandom.current().nextInt(brickList.size())));
    }

    @Override
    public Brick getBrick() {
        if (nextBricks.size() <= 1) {
            nextBricks.add(brickList.get(ThreadLocalRandom.current().nextInt(brickList.size())));
        }
        return nextBricks.poll();
    }

    @Override
    public Brick getNextBrick() {
        return nextBricks.peek();
    }
    
    public List<Brick> getBrickList(){
        return this.brickList;
    }
    
}
