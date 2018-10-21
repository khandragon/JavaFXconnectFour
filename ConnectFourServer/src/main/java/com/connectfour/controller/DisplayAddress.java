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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that handles the display for the ip address and the port
 * 
 * @author Saad
 */
public class DisplayAddress {
    private final static Logger LOG = LoggerFactory.getLogger(DisplayAddress.class);

    @FXML
    private Label address;
    @FXML
    private Label port;
    @FXML
    private Button closeButton;
    @FXML
    private Button connectButton;


    /**
     * Initial class to run, displays ip address and port number
     *
     * @author Saad
     */
    public void initialize() {
        address.setText("IP Address: " + getIPAddress());
        port.setText("Port#: " + getPortNumber());
    }

    /**
     * When button is clicked open a socket and begin searching for a connection
     *
     * @author Saad
     * @author Anthony
     */
    @FXML
    private void beginConnectionSearch() {
        connectButton.setDisable(true);
        try {
            int servPort = Integer.parseInt(getPortNumber());
            ServerSocket servSock = new ServerSocket(servPort);
            while (true) {
                try (Socket player1 = servSock.accept()) {
                    LOG.info("Player found");
                    GameSession gs = new GameSession(player1);
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    /**
     * return string representing the port number
     *
     * @return string representing port number
     * @author Saad
     */
    private String getPortNumber() {
        return "5000";
    }

    /**
     * Returns the ip address as a string
     *
     * @return the ip address
     * @author Saad
     */
    private String getIPAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            LOG.error(e.getMessage());
            return "Failed to get address";
        }
    }

    /**
     * When close button is clicked close the current window
     *
     * @param actionEvent mouse clicking event
     * @author Saad
     */
    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
