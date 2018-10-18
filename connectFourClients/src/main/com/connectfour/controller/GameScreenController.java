package com.connectfour.controller;

import com.connectfour.data.Board;
import com.connectfour.data.Connect4Connector;
import com.connectfour.data.PacketInfo;


import java.util.Arrays;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
        game.addMove(0, '1');
        char[][] board = game.getBoard();
        game.printBoard();
        for (Node child : gameGrid.getChildren()) {
            if (child instanceof Circle) {
                Circle circ = (Circle) child;
                // for (char c : chars) {
//                        if (c == '1') {
//                            System.out.println("red");
//                            circ.setFill(Color.RED);
//                        } else if (c == '2') {
//                            System.out.println("yello");
//                            circ.setFill(Color.YELLOW);
//                        } else {
//                            circ.setFill(Color.BLACK);
//                    }


            }
        }
    }

    public void setConnector(String server, int port) {
        //this.connection = new Connect4Connector(server, port);
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
