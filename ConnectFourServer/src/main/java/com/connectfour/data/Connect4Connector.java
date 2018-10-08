package main.java.com.connectfour.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connect4Connector {
    private Socket servSocket;
    private InputStream in;
    private OutputStream out;

    public Connect4Connector(String server, int port) {
        try {
            this.servSocket = new Socket(server, port);
            in = servSocket.getInputStream();
            out = servSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connect4Connector(Socket player1) {
        this.servSocket = player1;
        try {
            in = servSocket.getInputStream();
            out = servSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendData(byte first, byte secound, byte third) throws IOException {
        byte[] messages = {first, secound, third};
        out.write(messages);
    }

    public byte[] receiveData() throws IOException {
        byte[] receivedData = new byte[3];
        int receivedBytes = 0;
        while (receivedBytes < 3) {
            receivedBytes = in.read(receivedData);
        }
        return receivedData;
    }

    //data[0] move message type
    //data[1] who's move
    //data[2] where is move
    public void processReceivedData() throws IOException {
        byte[] data = receiveData();
        switch (data[0]) {
            case PacketInfo.MOVE:
                //makeAMove(data[1],data[2]);
                break;
            case PacketInfo.PLAY:
                //playagain or reset board
                break;
            case PacketInfo.QUIT:
                closeSocket();
                break;
            default:
                System.out.println("this should never occured in data processing");
        }
    }

    public void closeSocket() throws IOException {
        servSocket.close();
    }

}
