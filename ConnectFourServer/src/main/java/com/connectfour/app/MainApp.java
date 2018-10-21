package com.connectfour.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class to begin JavaFx
 * 
 * * @author Saad
 */
public class MainApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
        System.exit(0);
    }

    /**
     * Class that begins display of GUI
     *
     * @param primaryStage default starter Stage
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Connect Four Server");
        primaryStage.show();
    }
}
