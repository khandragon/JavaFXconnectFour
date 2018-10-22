package com.connectfour.controller;

import com.connectfour.data.Board;
import com.connectfour.data.Connect4Connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Class that handles the connector GUI
 */
public class connectServer {
    private final static Logger LOG = LoggerFactory.getLogger(Board.class);
    Board game;
    Connect4Connector connection;

    @FXML
    private GridPane gameGrid;
    @FXML
    private TextField inputAddress;
    @FXML
    private TextField inputPort;
    @FXML
    private Button closeButton;
    @FXML
    private Button connectButton;
    @FXML 
    private Text connectionStatus;
    /**
     * Take the ip address and port number inputted, 
     * validate and then make connection to the server
     * 
     * @author Saad
     * @author Seb
     * @param actionEvent
     */
    public void establishConnection(ActionEvent actionEvent) {
        String address = inputAddress.getText();
        String port = inputPort.getText();
        try{
            if (validInput(address, port))
                connectToServer(address, port);
        } catch(Exception e){
            connectionStatus.setText("Could not connect to the server.");
        }
    }

    /**
     * Establish connection to server and display gui
     *
     * @param server   ip address of server
     * @param servPort port number of server
     * @author Saad
     */
    private void connectToServer(String server, String servPort) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/gameScreen.fxml"));
            loader.load();
            GameScreenController gsc = loader.getController();
            gsc.setConnector(server, Integer.parseInt(servPort));
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.getIcons().add(new Image("file:icon.png")); 
            stage.setTitle("Connect Four Server: Connector");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }

    }

    /**
     * validates ip address and port number
     *
     * @author Saad
     */
    private boolean validInput(String address, String port) {
        return !address.isEmpty() && !port.isEmpty() && !address.equals(" ") && !port.equals(" ");
    }

    /**
     * close window on button click
     *
     * @author Saad
     * @param actionEvent
     */
    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
