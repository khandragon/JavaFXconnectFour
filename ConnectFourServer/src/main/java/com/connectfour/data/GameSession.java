package main.java.com.connectfour.data;

import java.io.IOException;
import java.net.Socket;

public class GameSession {
    private Connect4Connector connection;
    private Board game;
    private boolean playGame;

    public GameSession(Socket player1) {
        this.connection = new Connect4Connector(player1);
        System.out.println("game session created");
        try {
            game = new Board();
            playGame = true;
            do {
                serverMove();
                byte[] data = connection.receiveData();
                System.out.println(data[2]);
            } while (playGame);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void serverMove() throws IOException {
        connection.sendData(PacketInfo.MOVE, PacketInfo.PLAYER_TWO, (byte) 1);
        playGame = false;
    }


}
