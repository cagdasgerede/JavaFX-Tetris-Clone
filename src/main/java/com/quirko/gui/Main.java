package com.quirko.gui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import java.io.IOException;
import com.quirko.app.GameController;
import com.quirko.app.GameController2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application {
    private Stage primaryStage;
    Parent root;
    private int kisi;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TetrisJFX");
        Coop();
        
        

    }
    public void OnePLayer() {
        try {
            URL location = getClass().getClassLoader().getResource("gameLayout.fxml");
            ResourceBundle resources = null;
            FXMLLoader fxmlLoader = new FXMLLoader(location, resources);
            root = fxmlLoader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(root, 420, 510);
            primaryStage.setScene(scene);
            primaryStage.show();
            GuiController c = fxmlLoader.getController();
            new GameController(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void TwoPlayer() {
        try {
        URL location = getClass().getClassLoader().getResource("gameLayout2.fxml");
        ResourceBundle resources = null;
        FXMLLoader fxmlLoader = new FXMLLoader(location, resources);
        root = fxmlLoader.load();
        
        Scene scene = new Scene(root, 820, 510);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TetrisJFX");
        primaryStage.show();
        GuiController2 c = fxmlLoader.getController();
       
        new GameController2(c);

    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    public void Coop() {
        
        Button two = new Button("Two Player");
        Button one= new Button("One PLayer");
      
        one.setPrefSize(200,50);
        two.setPrefSize(200,50);
        
        HBox hbox = new HBox();
       hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        

        EventHandler <ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                TwoPlayer();
            }
        };

        EventHandler <ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                OnePLayer();
            }
        };

        two.setOnAction(event);
        one.setOnAction(event2);
        hbox.getChildren().addAll(one, two);
        Scene sc = new Scene(hbox, 400, 150);
        //sc.fill()
        primaryStage.setScene(sc);
  
        primaryStage.show();
        
    }
    public void ikincibuton(){
        kisi=2;
    }
    public void birincibuton(){
        kisi=1;
    }

    public static void main(String[] args) {
        launch(args);
    }
}