package com.connectfour.controller;

import com.connectfour.data.Board;
import com.connectfour.data.Connect4Connector;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameScreenController {
    private Connect4Connector connection;
    private Board game;

    @FXML
    private Button closeButton;
    @FXML
    private GridPane gameGrid;

    public void initialize() {
        startGame();
    }

    private void startGame() {
        this.game = new Board();
        displayGame();
    }

    private void displayGame() {
        int col = 1;
        int lastIndex = 41;
        ObservableList<Node> gridData = gameGrid.getChildren();
        char[][] board = game.getBoard();
    }
    public void setConnector(String server, int port) {
        this.connection = new Connect4Connector(server, port);
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
