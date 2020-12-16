package com.quirko.logic.bricks;

import org.junit.Assert;
import org.junit.Test;

import jdk.jfr.Timestamp;

import com.quirko.logic.Board;
import com.quirko.logic.SimpleBoard;

public class DotBrickTest {

    @Test
    public void testIsShapeDot() throws Exception {
        Brick brick = new DotBrick();
        int brickCounter = 0;
        boolean isBrickDotShaped = false;
        for (int i = 0; i < brick.getShapeMatrix().get(0).length; i++) {
            for (int j = 0; j < brick.getShapeMatrix().get(0)[i].length; j++) {
                if (brick.getShapeMatrix().get(0)[i][j] > 0) {
                    brickCounter += 1; 
                }
            } 
        }
        if (brickCounter == 1) {
            isBrickDotShaped = true;
        }
        Assert.assertTrue(isBrickDotShaped);
    }

    @Test
    public void testGetShapeMatrixList() throws Exception {
        Brick brick = new DotBrick();
        Assert.assertEquals(1, brick.getShapeMatrix().size());
    }

    /**Tests changeBrick method in SimpleBoard Class */
    @Test
    public void testChangeBrick() throws Exception {
        Board board = new SimpleBoard(25, 10);
        Brick brick = new DotBrick();
        board.setCurrentBrickAsDotBrick();
        Assert.assertTrue(board.changeBrick());
    }

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
