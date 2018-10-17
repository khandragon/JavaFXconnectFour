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
                String info = new String(data);
                try
                {
                    //Fixed based on movement
                    int number = Integer.getInteger(info);          
                }
                catch(NumberFormatException | NullPointerException e)
                {
                        
                }
            } while (playGame);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void serverMove(int line) throws IOException {
        String results;
        if (game.checkIfPossibleWin(line, playerPiece))
        {      
            game.addMove(line, playerPiece);
            results = "win";         
        }
        else if (game.isComplete())
        {
            game.addMove(line, playerPiece);
            results = "tie";
        }
        else
        {
            
            game.addMove(line, playerPiece);
            int decision = game.computerMove();
            if (game.checkIfPossibleWin(decision, computerPiece))
            {
                results = "computerWin";
            }        
        }
       
            
        playGame = false;
    }


}
