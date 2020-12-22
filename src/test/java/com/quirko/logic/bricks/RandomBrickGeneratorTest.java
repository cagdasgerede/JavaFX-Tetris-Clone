package com.quirko.logic.bricks;

import org.junit.Assert;
import org.junit.Test;

import jdk.jfr.Timestamp;

import com.quirko.logic.Board;
import com.quirko.logic.SimpleBoard;

public class RandomBrickGeneratorTest {

    /**Tests changeNextBrick method in RandomBrickGenerator Class */
    @Test
    public void testChangeNextBrick() throws Exception {
        RandomBrickGenerator generate = new RandomBrickGenerator();
        Brick currentBrick = generate.getBrick();
        Brick next = generate.changeNextBrick();
        boolean isChanged = next.getClass().equals(currentBrick.getClass()) ? false : true;
        Assert.assertTrue(isChanged);
    }
}
