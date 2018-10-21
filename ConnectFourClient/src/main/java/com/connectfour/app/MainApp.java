package com.connectfour.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application to commence the GUI
 *
 * @author Saad
 */
public class MainApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
        System.exit(0);
    }

    /**
     * Starting application to start the GUI
     * @author Saad
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Connect Four Client");
        primaryStage.show();
    }
}
