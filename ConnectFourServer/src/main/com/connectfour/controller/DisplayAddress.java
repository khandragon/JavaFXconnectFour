package com.connectfour.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import com.connectfour.data.GameSession;

public class DisplayAddress {

    @FXML
    private Label address;
    @FXML
    private Label port;
    @FXML
    private Button closeButton;
    @FXML
    private Button connectButton;


    public void initialize() {
        address.setText("IP Address: " + getIPAddress());
        port.setText("Port#: " + getPortNumber());
    }

    @FXML
    private void beginConnectionSearch() {
        connectButton.setDisable(true);
        try {
            int servPort = Integer.parseInt(getPortNumber());
            ServerSocket servSock = new ServerSocket(servPort);

            int recvMsgSize;
            byte[] byteBuffer = new byte[3];
            while (true) {
                Socket player1 = servSock.accept();
                System.out.println("player found...");

                GameSession gs = new GameSession(player1);
                //InputStream in = player1.getInputStream();
                //OutputStream out = player1.getOutputStream();

               // while ((recvMsgSize = in.read(byteBuffer)) != -1) {
                    //out.write(byteBuffer, 0, recvMsgSize);
                //}
                player1.close();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String getPortNumber() {
        return "5000";
    }

    //192.168.10.1
    private String getIPAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "falure to get address";
        }
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
