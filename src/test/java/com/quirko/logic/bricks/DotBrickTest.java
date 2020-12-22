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
}
