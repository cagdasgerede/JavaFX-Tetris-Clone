package com.quirko.logic.bricks;

import org.junit.Assert;
import org.junit.Test;

public class IBrickTest {

    @Test
    public void testGetShapeMatrix() throws Exception {
        Brick brick = new IBrick();
        brick.getShapeMatrix().get(0)[0][0] = 2;
        brick.getShapeMatrix().get(0)[1][0] = 3;
        Assert.assertEquals(0, brick.getShapeMatrix().get(0)[0][0]);
        Assert.assertEquals(1, brick.getShapeMatrix().get(0)[1][0]);
    }

    @Test
    public void testGetShapeMatrixList() throws Exception {
        Brick brick = new IBrick();
        brick.getShapeMatrix().remove(0);
        Assert.assertEquals(2, brick.getShapeMatrix().size());
    }

    @Test
    public void testGetBrickShape() throws Exception {
        RandomBrickGenerator randomBrickGenerator = new RandomBrickGenerator();
        Brick brick = randomBrickGenerator.getBrick(1);
        Brick testBrick = new IBrick();
        Assert.assertEquals(testBrick.getClass(), brick.getClass());
    }
}