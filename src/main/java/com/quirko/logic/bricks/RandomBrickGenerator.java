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
    
    public RandomBrickGenerator(DifficultyType difficultyLevel) {
        brickList = new ArrayList<>();
        //According to every level configuration add brickList.
        switch(difficultyLevel){
            case MEDIUM://for medium level adding each level for balancing
                MediumConfiguration mediumBricks = new MediumConfiguration();
                brickList.addAll(mediumBricks.getConfiguration());
                break;
            case HARD://extra hard shape for increase difficulty
                HardConfiguration hardBricks = new HardConfiguration();
                brickList.addAll(hardBricks.getConfiguration());
                break;
            case EASY://extra easy shape for decrease difficulty
                EasyConfiguration easyBricks = new EasyConfiguration();
                brickList.addAll(easyBricks.getConfiguration());
                break;
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
