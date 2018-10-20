package com.connectfour.controller;

import com.connectfour.data.Board;
import com.connectfour.data.Connect4Connector;
import com.connectfour.data.PacketInfo;


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * class that controls the game screen
 */
public class GameScreenController {
    private Connect4Connector connection;
    private Board game;

    @FXML
    private Button closeButton;
    @FXML
    private GridPane gameGrid;
    @FXML
    private Label gameStatus;

    /**
     * intialize by starting a game and drawing a board
     * @author Saad
     */
    public void initialize() {
        startGame();
    }

    /**
     * start game
     * @author Saad
     */
    private void startGame() {
        this.game = new Board();
        displayGame();
    }

    /**
     * draw the board on grid plane
     * @author Saad
     */
    private void displayGame() {
        byte[][] board = game.getBoard();
        game.printBoard();

        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[1].length; j++) {
                Circle circ = (Circle) gameGrid.getChildren().get(41 - (i * 7 + Math.abs(j - 6)));
                if (board[i][j] == PacketInfo.PLAYER_ONE) {
                    //System.out.print("|red|");
                    circ.setFill(Color.RED);
                } else if (board[i][j] == PacketInfo.PLAYER_TWO) {
                    //System.out.print("|yello|");
                    circ.setFill(Color.YELLOW);
                } else {
                    //System.out.print("|black|");
                    circ.setFill(Color.BLACK);
                }
            }
            System.out.println();
        }
    }

    /**
     * create a connect4connector using a server and port
     * @param server
     * @param port
     * @author Saad
     */
    public void setConnector(String server, int port) {
        this.connection = new Connect4Connector(server, port);
    }

    /**
     * close window
     * @param actionEvent
     * @author Saad
     */
    public void closeWindow(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            connection.closeSocket();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * on click of button place piece on that part of the line
     * @param mouseEvent
     * @author Saad
     */
    public void dropPiece(MouseEvent mouseEvent) {
        String s = mouseEvent.getPickResult().getIntersectedNode().getId();
        int spot = Integer.parseInt(s.charAt(3) + "") - 1;
        System.out.println(spot);
        game.addMove((byte) spot, PacketInfo.PLAYER_ONE);
        displayGame();
        try {
            if (!game.checkIfWin()) {
                connection.sendData(PacketInfo.MOVE, PacketInfo.PLAYER_ONE, (byte) spot);
                processReceivedData();

            } else {
                connection.sendData(PacketInfo.WIN,PacketInfo.PLAYER_ONE,PacketInfo.SPACE);
                gameStatus.setText("You Win");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //data[0] move message type
    //data[1] who's move
    //data[2] where is move

    /**
     * proccess the data that was received and do the proper commands
     * @throws IOException
     * @author Saad
     */
    public void processReceivedData() throws IOException {
        byte[] data = connection.receiveData();
        switch (data[0]) {
            case PacketInfo.MOVE:
                game.addMove(data[2], data[1]);
                displayGame();
                //makeAMove(data[1],data[2]);
                break;
            case PacketInfo.PLAY:
                //playagain or reset board
                break;
            case PacketInfo.QUIT:
                connection.closeSocket();
                break;
            default:
                System.out.println("this should never occured in data processing");
        }
    }
}
