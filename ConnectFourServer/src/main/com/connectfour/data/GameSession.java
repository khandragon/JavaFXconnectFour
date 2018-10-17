package com.connectfour.data;

import java.io.IOException;
import java.net.Socket;

public class GameSession {
    private Connect4Connector connection;
    private Board game;
    private char playerPiece = 'X';
    private char computerPiece = 'O';
    private boolean playGame;

    /**
     * This will run one game within the code.
     * 
     * @param player1 representing the player who made the move. 
     */
    public GameSession(Socket player1) {
        this.connection = new Connect4Connector(player1);
        System.out.println("game session created");
        game = new Board();
        playGame = true;
        do {
            byte[] data = connection.receiveData();
            if (data[0] == PacketInfo.QUIT)
            {
                System.out.println("Quitting game...");
                playGame = false;
            }
            else
            {
                int number = (int) data[2];  
                System.out.println("Adding move at line " + number + " for player.");
                try
                {
                    serverMove(number);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } while (playGame);
    }     
    
    /**
     * This method will send the client the results of his movement.
     * 
     * @param line reperesenting the line that player has chosen
     * @throws IOException
     */
    private void serverMove(int line) throws IOException {
        byte first;
        byte second;
        byte third;
        
        if (game.checkIfPossibleWin(line, playerPiece))
        {      
            System.out.println("Player is making a victory move.");
            game.addMove(line, playerPiece);   
            first = PacketInfo.WIN;
            second = PacketInfo.PLAYER_ONE;
            third = PacketInfo.SPACE;
            playGame = false;
        }
        else if (game.isComplete())
        {
            System.out.println("Player has made the game a tie.");
            game.addMove(line, playerPiece);
            first = PacketInfo.TIE;
            second = PacketInfo.PLAYER_ONE;
            third = PacketInfo.SPACE;
            playGame = false;
        }
        else
        {  
            game.addMove(line, playerPiece);
            int decision = game.computerMove();
            System.out.println("Adding move at line " + decision + " for computer.");
            if (game.checkIfPossibleWin(decision, computerPiece))
            {
                System.out.println("Computer is making a victory move.");
                first = PacketInfo.WIN;
                second = PacketInfo.PLAYER_TWO;
                third = (byte) decision;
                playGame = false;
            }     
            else if (game.isComplete())
            {
                System.out.println("Computer has made the game a tie.");
                first = PacketInfo.TIE;
                second = PacketInfo.PLAYER_TWO;
                third = (byte) decision;
                playGame = false;
            }
            else
            {
                System.out.println("Computer has not made "
                        + "a victory or tie move.");
                first = PacketInfo.PLAY;
                second = PacketInfo.PLAYER_TWO;
                third = (byte) decision;
            }
            
            game.addMove(line, computerPiece);
            System.out.println("Computer is returning his move to client: " 
                    + decision); 
        }       
        connection.sendData(first,second,third);
    }


}
