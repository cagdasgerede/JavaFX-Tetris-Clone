package com.quirko.logic.bricks;

import com.quirko.gui.DifficultyType;

import java.util.List;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

//This test control brickList consist correct number of its bricks.
public class RandomBrickGeneratorTest {

    @Test
    public void testGetEasyDifficultyTypes() throws Exception {
        List<Brick> brickList = new ArrayList<>();
        RandomBrickGenerator brickGenerator = new RandomBrickGenerator(DifficultyType.EASY);
        int easyBricksNumber = 0;
        for(Brick b : brickGenerator.getBrickList()){
            if(b instanceof IBrick || b instanceof OBrick)
                easyBricksNumber++;
        }
        Assert.assertEquals(8, easyBricksNumber);
    }

    @Test
    public void testGetMediumDifficultyTypes() throws Exception {
        List<Brick> brickList = new ArrayList<>();
        RandomBrickGenerator brickGenerator = new RandomBrickGenerator(DifficultyType.MEDIUM);
        int mediumBricksNumber = 0;
        for(Brick b : brickGenerator.getBrickList()){
            if(b instanceof JBrick || b instanceof LBrick)
                mediumBricksNumber++;
        }
        Assert.assertEquals(8, mediumBricksNumber);
    }

    @Test
    public void testGetHardDifficultyTypes() throws Exception {
        List<Brick> brickList = new ArrayList<>();
        RandomBrickGenerator brickGenerator = new RandomBrickGenerator(DifficultyType.HARD);
        int hardBricksNumber = 0;
        for(Brick b : brickGenerator.getBrickList()){
            if(b instanceof ZBrick || b instanceof TBrick || b instanceof SBrick)
                hardBricksNumber++;
        }
        Assert.assertEquals(12, hardBricksNumber);
    }

}
