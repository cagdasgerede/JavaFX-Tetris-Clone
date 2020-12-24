package com.quirko.gui;

import com.quirko.app.GameController;
import com.quirko.gui.DifficultySetPanel;
import com.quirko.gui.DifficultyType;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL location = getClass().getClassLoader().getResource("gameLayout.fxml");
        ResourceBundle resources = null;
        FXMLLoader fxmlLoader = new FXMLLoader(location, resources);
        Parent root = fxmlLoader.load();
        GuiController c = fxmlLoader.getController();
        DifficultySetPanel dfs= new DifficultySetPanel();
        DifficultyType difficultyLevel = dfs.getDifficultyLevel();
        while(difficultyLevel == null){
            difficultyLevel = dfs.getDifficultyLevel();
        }
        dfs.dispose();
        primaryStage.setTitle("TetrisJFX");
        Scene scene = new Scene(root, 400, 510);
        primaryStage.setScene(scene);
        primaryStage.show();
        new GameController(c, difficultyLevel);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
