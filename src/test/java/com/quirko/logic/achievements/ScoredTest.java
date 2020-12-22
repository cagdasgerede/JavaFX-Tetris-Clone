package com.quirko.logic.achievements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ScoredTest {
    
    Scored sc;

    @Test
    public void testGetLastCompleted() {
        sc = new Scored(500);
        assertEquals(500, sc.getLastCompleted());
    }

    @Test
    public void testLastCompletedWithUnexistingMilestone() {
        sc = new Scored(1);
        assertEquals(0, sc.getLastCompleted());
    }

    @Test
    public void testLastCompletedWhenCompletedIsEmpty() {
        sc = new Scored();
        assertEquals(0, sc.getLastCompleted());
    }

    @Test
    public void testMultipleAchievementsCompletedAtOnce() {
        sc = new Scored(3000);
        assertEquals(7, sc.completedCount());
        assertEquals(1, sc.uncompletedCount());
        assertEquals(3000, sc.getLastCompleted());
    }

    @Test
    public void testAllCompletedAtOnce() {
        sc = new Scored(5000);
        assertEquals(8, sc.completedCount());
        assertEquals(0, sc.uncompletedCount());
        assertEquals(5000, sc.getLastCompleted());
    }

    @Test
    public void testNothingAchieved() {
        sc = new Scored();
        assertFalse(sc.checkAchievement(0));
    }

    @Test
    public void testAchieved() {
        sc = new Scored();
        assertTrue(sc.checkAchievement(150));
        assertEquals(100, sc.getLastCompleted());
    }

}
