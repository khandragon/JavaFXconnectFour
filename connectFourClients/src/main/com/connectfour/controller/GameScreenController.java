package com.connectfour.controller;

import com.connectfour.data.Board;
import com.connectfour.data.Connect4Connector;
import com.connectfour.data.PacketInfo;


import java.util.Arrays;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
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
        byte[][] board = game.getBoard();
        game.printBoard();

        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[1].length; j++) {
                Circle circ = (Circle) gameGrid.getChildren().get(41 - (i * 7 + Math.abs(j - 6)));
                if (board[i][j] == PacketInfo.PLAYER_ONE) {
                    System.out.print("|red|");
                    circ.setFill(Color.RED);
                } else if (board[i][j] == PacketInfo.PLAYER_TWO) {
                    System.out.print("|yello|");
                    circ.setFill(Color.YELLOW);
                } else {
                    System.out.print("|black|");
                    circ.setFill(Color.BLACK);
                }
            }
            System.out.println();
        }
        //}
    }

    public void setConnector(String server, int port) {
        //this.connection = new Connect4Connector(server, port);
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void dropPiece(MouseEvent mouseEvent) {
        String s = mouseEvent.getPickResult().getIntersectedNode().getId();
        int spot = Integer.parseInt(s.charAt(3) + "")-1;
        System.out.println(spot);
        game.addMove((byte) spot, PacketInfo.PLAYER_ONE);
        displayGame();
    }
}
