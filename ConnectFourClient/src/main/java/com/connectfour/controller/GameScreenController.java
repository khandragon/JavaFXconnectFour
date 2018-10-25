package com.connectfour.controller;

import com.connectfour.data.Board;
import com.connectfour.data.Connect4Connector;
import com.connectfour.data.PacketInfo;
import static com.connectfour.data.PacketInfo.LOCK_BUTTONS;
import static com.connectfour.data.PacketInfo.UNLOCK_BUTTONS;

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
import org.slf4j.LoggerFactory;

/**
 * class that controls the game screen
 *
 * @author Saad
 * @author Seb 
 */
public class GameScreenController {
    
    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(GameScreenController.class);
    private Connect4Connector connection;
    private Board game;
    private boolean canClick = true;
    @FXML private Button closeButton;
    @FXML private GridPane gameGrid;
    @FXML private Label gameStatus;
    @FXML private Button col6;
    @FXML private Button col7;
    @FXML private Button col4;
    @FXML private Button col5;
    @FXML private Button col2;
    @FXML private Button col3;
    @FXML private Button col1;

    /**
     * Initialize by starting a game and drawing a board
     *
     * @author Saad
     */
    public void initialize() {
        startGame();
    }

    /**
     * Start a new game
     *
     * @author Saad
     * @author Seb
     */
    private void startGame() {
        gameStatus.setText("A Game Has Begun!");
        this.game = new Board();
        changePlayButtonsState(UNLOCK_BUTTONS);
        displayGame();
    }
    
    /**
     * Restart the boards for a reset game
     * 
     * @author Seb
     */
    @FXML
    private void restartGame(){
        gameStatus.setText("A New Game Has Begun!");
        this.game = new Board();
        changePlayButtonsState(UNLOCK_BUTTONS);
        try {
            connection.sendData(PacketInfo.PLAY, PacketInfo.PLAYER_ONE, PacketInfo.SPACE);
        } catch (IOException ex) {
            LOG.error(ex.getMessage());
        }
        displayGame();
    }

    /**
     * draw the board on grid plane
     *
     * @author Saad
     */
    private void displayGame() {
        byte[][] board = game.getBoard();
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
            connection.sendData(PacketInfo.QUIT, PacketInfo.PLAYER_ONE, PacketInfo.SPACE);
            connection.closeSocket();
            stage.close();
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    /**
     * on click of button place piece on that part of the line
     *
     * @author Seb
     * @author Saad
     * @param mouseEvent
     */
    public void dropPiece(MouseEvent mouseEvent) {
        if (canClick)
        {
            canClick = false;
            String s = mouseEvent.getPickResult().getIntersectedNode().getId();
            int spot = Integer.parseInt(s.charAt(3) + "") - 1;
            // Check if the user has made a valid move and if a move can be made
            if(!checkEndGame() && game.addMove((byte) spot, PacketInfo.PLAYER_ONE)){
                displayGame();
                try {
                    if (!checkEndGame()) {
                        connection.sendData(PacketInfo.MOVE, PacketInfo.PLAYER_ONE, (byte) spot);
                        processReceivedData();
                    } else {
                        connection.sendData(PacketInfo.WIN, PacketInfo.PLAYER_ONE, PacketInfo.SPACE);
                        checkEndGame();
                    }
                } catch (IOException e) {
                    LOG.error(e.getMessage());
                }
            } 
            canClick = true;
        }
    }

    /**
     * Checks if the player has achieved an endgame
     * 
     * @author Seb
     * @return true if there is an endgame
     */
    private boolean checkEndGame(){
        if (game.checkIfWin()){
            gameStatus.setText("You Win! Press 'New Game' to play again!");
            changePlayButtonsState(LOCK_BUTTONS);
            return true;
        } else if(game.checkIfTie()){
            gameStatus.setText("Tie! Press 'New Game' to play again!");
            changePlayButtonsState(LOCK_BUTTONS);
            return true;
        }
        return false;
    }
    
    /**
     * Checks if the computer has achieved an endgame
     * 
     * @author Seb
     */
    private void computerWin(){
        gameStatus.setText("You Lost! Press 'New Game' to play again!");
        changePlayButtonsState(LOCK_BUTTONS);
    }
    
    /**
     * Changes the clickable state of the buttons necessary to play the game
     * 
     * @author Seb
     * @param deciding to enable or disable buttons
     */
    private void changePlayButtonsState(boolean state){
        // Change all the buttons to the given state
        col1.setDisable(state);
        col2.setDisable(state);
        col3.setDisable(state);
        col4.setDisable(state);
        col5.setDisable(state);
        col6.setDisable(state);
        col7.setDisable(state);
    }
    
    /**
     * process the data that was received and do the proper commands
     *
     * @author Seb
     * @author Saad
     * @throws java.io.IOException
     */
    public void processReceivedData() throws IOException {
        byte[] data = connection.receiveData();
        //data[0]: move message type
        //data[1]: player's turn
        //data[2]: move location
        switch (data[0]) {
            case PacketInfo.MOVE:
                game.addMove(data[2], data[1]);
                displayGame();
                break;
            case PacketInfo.PLAY:
                restartGame();
                break;
            case PacketInfo.QUIT:
                connection.closeSocket();
                break;
            case PacketInfo.WIN:
                game.addMove(data[2], data[1]);
                displayGame();
                computerWin();
                break;
            case PacketInfo.TIE:
                game.addMove(data[2], data[1]);
                displayGame();
                checkEndGame();
                break;
            default:
                LOG.info("No Data Received From Server");
        }
    }
}
