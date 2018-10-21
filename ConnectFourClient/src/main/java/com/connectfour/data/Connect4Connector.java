package com.connectfour.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * class handles all methods relating to sending and receiving from a connection
 *
 * @author Saad
 */
public class Connect4Connector {
    private Socket servSocket;
    private InputStream in;
    private OutputStream out;

    /**
     * Primary Constructor for this class which takes an string and int in case of only having those
     * two available specifically in the client
     *
     * @param server takes a string representing the ip address of the server
     * @param port   takes an int that represents the port number
     * @author Saad
     */
    public Connect4Connector(String server, int port) {
        try {
            this.servSocket = new Socket(server, port);
            in = servSocket.getInputStream();
            out = servSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Secondary constructor for this class used in the case of already having socket specifically
     * in the Server
     *
     * @author Saad
     */
    public Connect4Connector(Socket player1) {
        this.servSocket = player1;
        try {
            in = servSocket.getInputStream();
            out = servSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to send data from one end to the other
     *
     * @param first   byte that represents the category of the send (Move, Quit, Play, Win, Tie)
     * @param secound byte that represents the player that is doing the command
     * @param third   byte that represents the line to place piece or space by default
     * @author Saad
     */
    public void sendData(byte first, byte secound, byte third) throws IOException {
        byte[] messages = {first, secound, third};
        out.write(messages);
    }

    /**
     * waits to receive data from one end of the connection
     *
     * @return byte[] of the data recieved
     * @author Saad
     */
    public byte[] receiveData() throws IOException {
        byte[] receivedData = new byte[3];
        int receivedBytes = 0;
        while (receivedBytes < 3) {
            receivedBytes = in.read(receivedData);
        }
        return receivedData;
    }

    /**
     * close the connection
     *
     * @author Saad
     */
    public void closeSocket() throws IOException {
        servSocket.close();
    }

}
