package com.connectfour.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Class that controls GUI for main controller
 *
 * @author Saad
 */
public class mainController {

    @FXML
    private Button closeButton;

    /**
     * Close window on button click
     *
     * @author Saad
     * @param actionEvent
     */
    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Display the connector screen on button click
     *
     * @author Saad
     * @param actionEvent
     * @throws java.io.IOException
     */
    public void displayConnector(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/connectServer.fxml"));
        Scene scene = new Scene(root);
        Stage addressDisplayStage = new Stage();
        addressDisplayStage.setTitle("Connect Four Server: Connector");
        addressDisplayStage.initModality(Modality.APPLICATION_MODAL);
        addressDisplayStage.setScene(scene);
        addressDisplayStage.showAndWait();
    }
}
