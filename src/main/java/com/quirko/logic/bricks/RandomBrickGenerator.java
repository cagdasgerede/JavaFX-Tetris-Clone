package com.quirko.logic.bricks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.quirko.gui.DifficultyType;

public class RandomBrickGenerator implements BrickGenerator {

    private final List<Brick> brickList;

    private final Deque<Brick> nextBricks = new ArrayDeque<>();

    public RandomBrickGenerator(Enum diff) {
        brickList = new ArrayList<>();
        brickList.add(new IBrick());
        brickList.add(new JBrick());
        brickList.add(new LBrick());
        brickList.add(new OBrick());
        brickList.add(new SBrick());
        brickList.add(new TBrick());
        brickList.add(new ZBrick());
        if(diff != DifficultyType.EASY){//extra hard shape for increase difficulty
            brickList.add(new SBrick());
            brickList.add(new TBrick());
            brickList.add(new ZBrick());
        }
         if(diff != DifficultyType.HARD){//extra easy shape for decrease difficulty
            brickList.add(new IBrick());
            brickList.add(new JBrick());
            brickList.add(new LBrick());
            brickList.add(new OBrick());
        }
        //for medium level adding both of them for balancing
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
