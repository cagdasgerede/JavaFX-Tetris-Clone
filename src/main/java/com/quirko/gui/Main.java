package com.quirko.gui;

import com.quirko.app.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        URL location = getClass().getClassLoader().getResource("gameLayout.fxml");
        ResourceBundle resources = null;
        FXMLLoader fxmlLoader = new FXMLLoader(location, resources);
        Parent root = fxmlLoader.load();
        GuiController c = fxmlLoader.getController();

        this.primaryStage.setTitle("TetrisJFX");
        Scene scene = new Scene(root, 400, 510);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        new GameController(c);

    }

    public static Stage get_Stage(){
        return primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
