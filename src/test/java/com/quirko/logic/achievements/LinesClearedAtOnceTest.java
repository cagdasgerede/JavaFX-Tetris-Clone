package com.quirko.logic.achievements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LinesClearedAtOnceTest {
    
    LinesClearedAtOnce lcao;

    @Test
    public void testGetLastCompleted() {
        lcao = new LinesClearedAtOnce(3);
        assertEquals(3, lcao.getLastCompleted());
    }

    @Test
    public void testLastCompletedWithUnexistingMilestone() {
        lcao = new LinesClearedAtOnce(15);
        assertEquals(0, lcao.getLastCompleted());
    }

    @Test
    public void testLastCompletedWhenCompletedIsEmpty() {
        lcao = new LinesClearedAtOnce();
        assertEquals(0, lcao.getLastCompleted());
    }

    @Test
    public void testMultipleAchievementsCompletedAtOnce() {
        lcao = new LinesClearedAtOnce(3);
        assertEquals(2, lcao.completedCount());
        assertEquals(1, lcao.uncompletedCount());
        assertEquals(3, lcao.getLastCompleted());
    }

    @Test
    public void testAllCompletedAtOnce() {
        lcao = new LinesClearedAtOnce(4);
        assertEquals(3, lcao.completedCount());
        assertEquals(0, lcao.uncompletedCount());
        assertEquals(4, lcao.getLastCompleted());
    }

    @Test
    public void testNothingAchieved() {
        lcao = new LinesClearedAtOnce();
        assertFalse(lcao.checkAchievement(0));
    }

    @Test
    public void testAchieved() {
        lcao = new LinesClearedAtOnce();
        assertTrue(lcao.checkAchievement(3));
        assertEquals(3, lcao.getLastCompleted());
    }

}
