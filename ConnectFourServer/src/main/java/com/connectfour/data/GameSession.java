package com.connectfour.data;

import java.io.IOException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * class that handles the game logic
 *
 * @author Saad
 */
public class GameSession implements Runnable {
    private final static Logger LOG = LoggerFactory.getLogger(GameSession.class);
    private Connect4Connector connection;
    private Board game;
    private boolean playGame;
    private String playerName;

    /**
     * This will run one game within the code.
     *
     * @param player1 representing the player who made the move.
     * @author Saad
     * @author Seb
     */
    public GameSession(Socket player1) {
        this.connection = new Connect4Connector(player1);
        this.playerName = player1.getInetAddress().toString();
        LOG.info("Connected with user from : " + playerName);
    }

    /**
     * This method will send the client the results of his movement.
     *
     * @param line representing the line that player has chosen
     * @author Saad
     * @author Anthony
     * @author Seb
     */
    private void serverMove(byte line) throws IOException {
        byte first;
        byte second;
        byte third;
        game.addMove(line, PacketInfo.PLAYER_ONE);
        if (game.checkIfWin()) {
            LOG.info(playerName + " is making a victory move.");
            first = PacketInfo.WIN;
            second = PacketInfo.PLAYER_ONE;
            third = PacketInfo.SPACE;

        } else if (game.isComplete()) {
            LOG.info(playerName + " has made the game a tie.");
            first = PacketInfo.TIE;
            second = PacketInfo.PLAYER_ONE;
            third = PacketInfo.SPACE;
        } else {
            int decision = game.computerMove();
            game.addMove((byte) decision, PacketInfo.PLAYER_TWO);
            LOG.info("Adding move at line " + decision + " for computer.");
            if (game.checkIfWin()) {
                LOG.info("Computer is making a victory move.");
                first = PacketInfo.WIN;
                second = PacketInfo.PLAYER_TWO;
                third = (byte) decision;
            } else if (game.isComplete()) {
                LOG.info("Computer has made the game a tie.");
                first = PacketInfo.TIE;
                second = PacketInfo.PLAYER_TWO;
                third = (byte) decision;
            } else {
                LOG.info("Computer has not made " + "a victory or tie move.");
                first = PacketInfo.MOVE;
                second = PacketInfo.PLAYER_TWO;
                third = (byte) decision;
            }
            LOG.info("Computer is returning his move to client at line: " + decision);
        }
        connection.sendData(first, second, third);
    }

    /**
     * This will run when the computer goes first
     *
     * @author Saad
     */
    private void firstMove() throws IOException {
        int decision = game.computerMove();
        game.addMove((byte) decision, PacketInfo.PLAYER_TWO);
        System.out.println("Computer is first : " + decision + " for computer.");
        connection.sendData(PacketInfo.MOVE, PacketInfo.PLAYER_TWO, (byte) decision);
    }

    @Override
    public void run() {
        LOG.info("Game session created");
        game = new Board();
        playGame = true;
        try {
            do {
                LOG.info("Trying to receive data");
                if (this.connection == null) {
                    LOG.info("AFSDFASDFASDFASDFASDFASDFASDFASDF");
                }
                byte[] data = this.connection.receiveData();
                LOG.info("Received data from " + playerName);
                if (data[0] == PacketInfo.QUIT) {
                    LOG.info("Quitting game...");
                    playGame = false;
                } else if (data[0] == PacketInfo.PLAY) {
                    game = new Board();
                    if (data[1] == PacketInfo.PLAYER_TWO) {
                        firstMove();
                    }
                } else if (data[0] == PacketInfo.WIN) {
                    game = new Board();
                } else {
                    byte number = data[2];
                    LOG.info("Adding move at line " + number + " for player.");
                    serverMove(number);
                }
            } while (playGame);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

