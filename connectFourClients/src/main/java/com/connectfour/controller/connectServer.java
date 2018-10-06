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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class connectServer {

    @FXML
    private TextField inputAddress;
    @FXML
    private TextField inputPort;
    @FXML
    private Button closeButton;
    @FXML
    private Button connectButton;

    public void establishConnection(ActionEvent actionEvent){
        String address = inputAddress.getText();
        String port = inputPort.getText();
        System.out.println(address);
        System.out.println(port);
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
