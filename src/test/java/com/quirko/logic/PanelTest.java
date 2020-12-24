package com.quirko.logic;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import com.quirko.gui.MenuPanel;
import org.junit.Test;

public class PanelTest {

    @Test
    public void testSettingsButtonPressed() {
        MenuPanel menuPanel = spy(new MenuPanel());
        menuPanel.getSettingsButton().doClick();
        menuPanel.createSettingsMenu();
        verify(menuPanel, times(1)).createSettingsMenu();
    }

    @Test
    public void testLoadButtonPressed() throws IOException {
        MenuPanel menuPanel = spy(new MenuPanel());
        menuPanel.getLoadButton().doClick();
        menuPanel.load();
        verify(menuPanel, times(1)).load();
    }

    @Test
    public void testSaveButtonPressed() throws IOException {
        MenuPanel menuPanel = spy(new MenuPanel());
        menuPanel.getSaveButton().doClick();
        menuPanel.save();
        verify(menuPanel, times(1)).save();
    }

    @Test
    public void testScoreBoardButtonPressed() throws IOException {
        MenuPanel menuPanel = spy(new MenuPanel());
        menuPanel.getScoreBoardButton().doClick();
        menuPanel.loadScore();
        verify(menuPanel, times(1)).loadScore();
    }
}
