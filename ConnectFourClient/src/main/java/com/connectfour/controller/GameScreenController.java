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
 *
 * @author Saad
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
     *
     * @author Saad
     */
    public void initialize() {
        startGame();
    }

    /**
     * start game
     *
     * @author Saad
     */
    private void startGame() {
        this.game = new Board();
        displayGame();
    }

    /**
     * draw the board on grid plane
     *
     * @author Saad
     */
    private void displayGame() {
        byte[][] board = game.getBoard();
        //game.printBoard();

        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[1].length; j++) {
                Circle circ = (Circle) gameGrid.getChildren().get(41 - (i * 7 + Math.abs(j - 6)));
                if (board[i][j] == PacketInfo.PLAYER_ONE) {
                    circ.setFill(Color.RED);
                } else if (board[i][j] == PacketInfo.PLAYER_TWO) {
                    circ.setFill(Color.YELLOW);
                } else {
                    circ.setFill(Color.BLACK);
                }
            }
        }
    }

    /**
     * create a connect4connector using a server and port
     *
     * @author Saad
     * @param server
     * @param port
     */
    public void setConnector(String server, int port) {
        this.connection = new Connect4Connector(server, port);
    }

    /**
     * close window
     *
     * @author Saad
     * @param actionEvent
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
     *
     * @author Saad
     * @param mouseEvent
     */
    public void dropPiece(MouseEvent mouseEvent) {
        String s = mouseEvent.getPickResult().getIntersectedNode().getId();
        int spot = Integer.parseInt(s.charAt(3) + "") - 1;
        //System.out.println(spot);
        // Check if the user has made a valid move
        System.out.println();
        this.game.printBoard();
        //System.out.println(game.addMove((byte) spot, PacketInfo.PLAYER_ONE));
        //game.addMove((byte) spot, PacketInfo.PLAYER_ONE);
            
        if(game.addMove((byte) spot, PacketInfo.PLAYER_ONE)){
            this.game.printBoard();
            //game.addMove((byte) spot, PacketInfo.PLAYER_ONE);
            displayGame();
            try {
                if (!game.checkIfWin()) {
                    connection.sendData(PacketInfo.MOVE, PacketInfo.PLAYER_ONE, (byte) spot);
                    processReceivedData();

                } else {
                    connection.sendData(PacketInfo.WIN, PacketInfo.PLAYER_ONE, PacketInfo.SPACE);
                    gameStatus.setText("You Win");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            this.game.printBoard();
        }
        
    }

    //data[0] move message type
    //data[1] who's move
    //data[2] where is move

    /**
     * proccess the data that was received and do the proper commands
     *
     * @author Saad
     * @throws java.io.IOException
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
                System.out.println("No received data");
        }
    }
}
