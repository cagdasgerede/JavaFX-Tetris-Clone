package com.quirko.gui;

import com.quirko.app.GameController;
import com.quirko.gui.DifficultySetPanel;
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
        String diff = dfs.getDiff();
        while(diff.length()==0){
            diff = dfs.getDiff();

        }
      //  System.out.println(diff);
        dfs.dispose();
        primaryStage.setTitle("TetrisJFX");
        Scene scene = new Scene(root, 400, 510);
        primaryStage.setScene(scene);
        primaryStage.show();
       
        new GameController(c,diff);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
