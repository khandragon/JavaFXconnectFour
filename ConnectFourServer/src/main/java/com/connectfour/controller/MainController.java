package main.java.com.connectfour.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private Button closeButton;

    public void displayServer(ActionEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../../../resources/fxml/displayAddress.fxml"));
        Scene scene = new Scene(root);
        Stage addressDisplayStage = new Stage();
        addressDisplayStage.setTitle("Connect Four Server: Connector");
        addressDisplayStage.initModality(Modality.APPLICATION_MODAL);
        addressDisplayStage.setScene(scene);
        addressDisplayStage.showAndWait();
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
