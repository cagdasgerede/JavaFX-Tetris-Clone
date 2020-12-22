package com.quirko.logic.bricks;

import org.junit.Assert;
import org.junit.Test;

import jdk.jfr.Timestamp;

import com.quirko.logic.Board;
import com.quirko.logic.SimpleBoard;

public class SimpleBoardTest {

    /**Tests changeBrick method in SimpleBoard Class */
    @Test
    public void testChangeBrick() throws Exception {
        Board board = new SimpleBoard(25, 10);
        Brick brick = new DotBrick();
        board.setCurrentBrickAsDotBrick();
        Assert.assertTrue(board.changeBrick());
    }
}
