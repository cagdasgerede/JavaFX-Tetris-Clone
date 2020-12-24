package com.quirko.logic.bricks;

import com.quirko.gui.DifficultyType;

import java.util.List;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

//This test control brickList consist correct number of its bricks.
public class BrickConfigurationTest {

    @Test
    public void testGetEmptyHardList() throws Exception {
        List<Brick> brickList = new ArrayList<>();
        HardConfiguration hardConfiguration = new HardConfiguration();
        Assert.assertEquals(16, hardConfiguration.getConfiguration().size());
    }

    @Test
    public void testGetEmptyMediumList() throws Exception {
        List<Brick> brickList = new ArrayList<>();
        MediumConfiguration mediumConfiguration = new MediumConfiguration();
        Assert.assertEquals(13, mediumConfiguration.getConfiguration().size());
    }

    @Test
    public void testGetEmptyEasyList() throws Exception {
        List<Brick> brickList = new ArrayList<>();
        EasyConfiguration easyConfiguration = new EasyConfiguration();
        Assert.assertEquals(13, easyConfiguration.getConfiguration().size());
    }

}