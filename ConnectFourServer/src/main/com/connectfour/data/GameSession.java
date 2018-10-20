

package com.connectfour.data;

import java.io.IOException;
import java.net.Socket;

/**
 * class that handles the game logic
 *
 * @author Saad
 */
public class GameSession {
    private Connect4Connector connection;
    private Board game;
    private boolean playGame;

    /**
     * This will run one game within the code.
     *
     * @param player1 representing the player who made the move.
     * @author Saad
     */
    public GameSession(Socket player1) {
        this.connection = new Connect4Connector(player1);
        System.out.println("game session created");
        game = new Board();
        playGame = true;
        try {
            do {
                byte[] data = connection.receiveData();
                if (data[0] == PacketInfo.QUIT) {
                    System.out.println("Quitting game...");
                    playGame = false;
                } 
                else if (data[0] == PacketInfo.PLAY)
                {
                    game = new Board();
                    if (data[1] == PacketInfo.PLAYER_TWO)
                    {
                        firstMove();
                    }
                }
                else {
                    byte number = data[2];
                    System.out.println("Adding move at line " + number + " for player.");
                    serverMove(number);
                }
            } while (playGame);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will send the client the results of his movement.
     *
     * @param line reperesenting the line that player has chosen
     * @author Saad & Anthony
     */
    private void serverMove(byte line) throws IOException {
        byte first;
        byte second;
        byte third;
        game.addMove(line, PacketInfo.PLAYER_ONE);
        if (game.checkIfWin()) {
            System.out.println("Player is making a victory move.");
            first = PacketInfo.WIN;
            second = PacketInfo.PLAYER_ONE;
            third = PacketInfo.SPACE;
            playGame = false;
        } else if (game.isComplete()) {
            System.out.println("Player has made the game a tie.");
            first = PacketInfo.TIE;
            second = PacketInfo.PLAYER_ONE;
            third = PacketInfo.SPACE;
            playGame = false;
        } else {
            int decision = game.computerMove();
            game.addMove((byte) decision, PacketInfo.PLAYER_TWO);
            System.out.println("Adding move at line " + decision + " for computer.");
            if (game.checkIfWin()) {
                System.out.println("Computer is making a victory move.");
                first = PacketInfo.WIN;
                second = PacketInfo.PLAYER_TWO;
                third = (byte) decision;
                playGame = false;
            } else if (game.isComplete()) {
                System.out.println("Computer has made the game a tie.");
                first = PacketInfo.TIE;
                second = PacketInfo.PLAYER_TWO;
                third = (byte) decision;
                playGame = false;
            } else {
                System.out.println("Computer has not made " + "a victory or tie move.");
                first = PacketInfo.MOVE;
                second = PacketInfo.PLAYER_TWO;
                third = (byte) decision;
            }
            System.out.println("Computer is returning his move to client at line: " + decision);
        }
        connection.sendData(first, second, third);
    }
    
    /**
     * This will run when the computer goes first
     */
    private void firstMove() throws IOException{
        int decision = game.computerMove();
        game.addMove((byte) decision, PacketInfo.PLAYER_TWO);
        System.out.println("Computer is first : " + decision + " for computer.");
        connection.sendData(PacketInfo.MOVE,PacketInfo.PLAYER_TWO, (byte) decision);
    }
}

