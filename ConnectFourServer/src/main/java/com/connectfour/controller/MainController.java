package com.connectfour.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for main application
 *
 * @author Saad
 */
public class MainController {
    @FXML
    private Button closeButton;

    /**
     * When button is clicked open new stage that contains the ip address and the port number
     * as well as the button to start connection
     *
     * @param mouseEvent the mouse click
     * @author Saad
     * @throws java.io.IOException
     */
    public void displayServer(ActionEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/displayAddress.fxml"));
        Scene scene = new Scene(root);
        Stage addressDisplayStage = new Stage();
        addressDisplayStage.getIcons().add(new Image("file:icon.png")); 
        addressDisplayStage.setTitle("Connect Four Server: Connector");
        addressDisplayStage.initModality(Modality.APPLICATION_MODAL);
        addressDisplayStage.setScene(scene);
        addressDisplayStage.showAndWait();
    }

    /**
     * close current window on button click
     *
     * @param actionEvent button click
     * @author Saad
     */
    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
