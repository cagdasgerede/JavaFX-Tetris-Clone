package com.quirko.gui;

import com.quirko.app.GameController;
import com.quirko.logic.achievements.AchievementFileIO;
import com.quirko.logic.achievements.AchievementManager;
import com.quirko.logic.achievements.UsernameInputWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application {

    private static UsernameInputWindow usernameInput;

    @Override
    public void start(Stage primaryStage) throws Exception {

        URL location = getClass().getClassLoader().getResource("gameLayout.fxml");
        ResourceBundle resources = null;
        FXMLLoader fxmlLoader = new FXMLLoader(location, resources);
        Parent root = fxmlLoader.load();
        GuiController c = fxmlLoader.getController();

        primaryStage.setTitle("TetrisJFX");
        Scene scene = new Scene(root, 400, 510);
        primaryStage.setScene(scene);
        primaryStage.show();

        AchievementFileIO achievementFileIO = new AchievementFileIO();
        String username = UsernameInputWindow.username;
        AchievementManager achievements;
        if (achievementFileIO.userExists(username)) {
            int[] userAchievements = achievementFileIO.getUserAchievements(username);
            achievements = new AchievementManager(userAchievements[0], userAchievements[1], userAchievements[2]);
        }
        else
            achievements = new AchievementManager();
            
        new GameController(c, achievements, achievementFileIO);
    }


    public static void main(String[] args) {
        if (usernameInput == null) 
            usernameInput = new UsernameInputWindow(args);
        else
            launch(args);
    }
}
