package main.java.com.connectfour.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
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


    public void initialize() {
        //192.168.10.1
        address.setText("IP Address: " + getIPAddress());
        port.setText("Port#: "+ getPortNumber());
        beginConnectionSearch();
    }

    private void beginConnectionSearch() {
        try {
            int servPort = Integer.parseInt(getPortNumber());
            ServerSocket servSock = new ServerSocket(servPort);

            int recvMsgSize;
            char[] charBuffer = new char[1];
            while (true){
                System.out.println("waiting for connection...");
                
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
