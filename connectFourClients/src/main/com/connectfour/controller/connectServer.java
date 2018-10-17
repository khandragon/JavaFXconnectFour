package com.connectfour.controller;

import com.connectfour.data.Board;
import com.connectfour.data.Connect4Connector;
import com.connectfour.data.PacketInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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

    public void establishConnection(ActionEvent actionEvent) {
        String address = inputAddress.getText();
        String port = inputPort.getText();
        if (validInput(address, port))
            connectToServer(address, port);
    }

    private void connectToServer(String server, String servPort) {
        this.connection = new Connect4Connector(server, Integer.parseInt(servPort));
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/gameScreen.fxml"));
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            startGame();

//            byte[] data = connection.receiveData();
//            System.out.println(data[2]);
//            connection.sendData(PacketInfo.MOVE, PacketInfo.PLAYER_ONE, (byte) 2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void startGame() {
        this.game = new Board();
        displayGame();
    }

    private void displayGame() {
        int col = 1;
        int row = 43;
        ObservableList<Node> girdData = gameGrid.getChildren();
    }

    private boolean validInput(String address, String port) {
        return !address.isEmpty() && !port.isEmpty() && !address.equals(" ") && !port.equals(" ");
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
