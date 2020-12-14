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
    
    private HardBricks hardBricks;

    private MediumBricks mediumBricks;

    private EasyBricks easyBricks;

    public RandomBrickGenerator(Enum diff) {
        brickList = new ArrayList<>();
        hardBricks = new HardBricks();
        mediumBricks = new MediumBricks();
        easyBricks = new EasyBricks();
        //for every level add every shape bricks, then according to difficulty level add extra bricks.
        brickList.add(new IBrick());
        brickList.add(new JBrick());
        brickList.add(new LBrick());
        brickList.add(new OBrick());
        brickList.add(new SBrick());
        brickList.add(new TBrick());
        brickList.add(new ZBrick());
        if(diff == DifficultyType.MEDIUM){//for medium level adding both of them for balancing
            brickList.addAll(mediumBricks.brickList);
        }
        else if(diff == DifficultyType.HARD){//extra hard shape for increase difficulty
            brickList.addAll(hardBricks.brickList);
            
        }
        else if(diff == DifficultyType.EASY){//extra easy shape for decrease difficulty
            brickList.addAll(easyBricks.brickList);
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
}
