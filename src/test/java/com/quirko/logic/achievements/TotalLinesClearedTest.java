package com.quirko.logic.achievements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TotalLinesClearedTest {
    
    TotalLinesCleared tlc;

    @Test
    public void testGetLastCompleted() {
        tlc = new TotalLinesCleared(20);
        assertEquals(20, tlc.getLastCompleted());
    }

    @Test
    public void testLastCompletedWithUnexistingMilestone() {
        tlc = new TotalLinesCleared(11);
        assertEquals(0, tlc.getLastCompleted());
    }

    @Test
    public void testLastCompletedWhenCompletedIsEmpty() {
        tlc = new TotalLinesCleared();
        assertEquals(0, tlc.getLastCompleted());
    }

    @Test
    public void testMultipleAchievementsCompletedAtOnce() {
        tlc = new TotalLinesCleared(100);
        assertEquals(5, tlc.completedCount());
        assertEquals(2, tlc.uncompletedCount());
        assertEquals(100, tlc.getLastCompleted());
    }

    @Test
    public void testAllCompletedAtOnce() {
        tlc = new TotalLinesCleared(500);
        assertEquals(7, tlc.completedCount());
        assertEquals(0, tlc.uncompletedCount());
        assertEquals(500, tlc.getLastCompleted());
    }

    @Test
    public void testNothingAchieved() {
        tlc = new TotalLinesCleared();
        assertFalse(tlc.checkAchievement(0));
    }

    @Test
    public void testAchieved() {
        tlc = new TotalLinesCleared();
        assertTrue(tlc.checkAchievement(12));
        assertEquals(10, tlc.getLastCompleted());
    }

}
