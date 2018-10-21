package com.connectfour.controller;

import com.connectfour.data.GameSession;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * class that handles the display for the ip address and the port
 */
public class DisplayAddress {

    @FXML
    private Label address;
    @FXML
    private Label port;
    @FXML
    private Button closeButton;
    @FXML
    private Button connectButton;


    /**
     * intial class to run, displays ip address and port number
     *
     * @author Saad
     */
    public void initialize() {
        address.setText("IP Address: " + getIPAddress());
        port.setText("Port#: " + getPortNumber());
    }

    /**
     * when button is clicked open a socket and begin searching for a connection
     *
     * @author Saad
     */
    @FXML
    private void beginConnectionSearch() {
        connectButton.setDisable(true);
        try {
            int servPort = Integer.parseInt(getPortNumber());
            ServerSocket servSock = new ServerSocket(servPort);
            while (true) {
                Socket player1 = servSock.accept();
                System.out.println("player found...");
                GameSession gs = new GameSession(player1);
                player1.close();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * return string representing the port number
     *
     * @return string represnting port number
     * @author Saad
     */
    private String getPortNumber() {
        return "5000";
    }

    /**
     * retruns the ip address as a string
     *
     * @return the ip address
     * @author Saad
     */
    private String getIPAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "falure to get address";
        }
    }

    /**
     * When close btn is clicked close the current window
     *
     * @param actionEvent mouse clicking event
     * @author Saad
     */
    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
