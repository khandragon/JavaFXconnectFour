package com.connectfour.data;

import java.io.IOException;
import java.net.Socket;

public class GameSession {
    private Connect4Connector connection;
    private Board game;
    private char playerPiece = 'X';
    private char computerPiece = 'O';
    private boolean playGame;

    public GameSession(Socket player1) {
        this.connection = new Connect4Connector(player1);
        System.out.println("game session created");
        try {
            game = new Board();
            playGame = true;
            do {
                byte[] data = connection.receiveData();
                if (data[0] == PacketInfo.QUIT)
                {
                
                }
                else
                {
                    int number = (int) data[2];  
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
     * @throws IOException
     */
    private void serverMove(int line) throws IOException {
        byte first;
        byte second;
        byte third;
        
        if (game.checkIfPossibleWin(line, playerPiece))
        {      
            game.addMove(line, playerPiece);   
            first = PacketInfo.WIN;
            second = PacketInfo.PLAYER_ONE;
            third = PacketInfo.SPACE;
            playGame = false;
        }
        else if (game.isComplete())
        {
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
            if (game.checkIfPossibleWin(decision, computerPiece))
            {
                first = PacketInfo.WIN;
                second = PacketInfo.PLAYER_TWO;
                third = (byte) decision;
                playGame = false;
            }     
            else if (game.isComplete())
            {
                first = PacketInfo.TIE;
                second = PacketInfo.PLAYER_TWO;
                third = (byte) decision;
                playGame = false;
            }
            else
            {
                first = PacketInfo.PLAY;
                second = PacketInfo.PLAYER_TWO;
                third = (byte) decision;
            }
        }
        
        connection.sendData(first,second,third);
    }


}
