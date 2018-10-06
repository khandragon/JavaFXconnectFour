package main.java.com.connectfour.controller;

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
        try {
            int servPort = Integer.parseInt(getPortNumber());
            ServerSocket servSock = new ServerSocket(servPort);

            int recvMsgSize;
            byte[] byteBuffer = new byte[32];
            while (true) {
                Socket socket = servSock.accept();
                System.out.println("waiting for connection...");

                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();

                while ((recvMsgSize = in.read(byteBuffer)) != -1) {
                    out.write(byteBuffer, 0, recvMsgSize);
                }
                socket.close();
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
            String hi = InetAddress.getLocalHost().getHostAddress();
            return hi;
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
