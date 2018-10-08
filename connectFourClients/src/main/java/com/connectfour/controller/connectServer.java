package main.java.com.connectfour.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.java.com.connectfour.data.Board;
import main.java.com.connectfour.data.Connect4Connector;
import main.java.com.connectfour.data.PacketInfo;

public class connectServer {

    private final static Logger LOG = LoggerFactory.getLogger(Board.class);

    @FXML
    private TextField inputAddress;
    @FXML
    private TextField inputPort;
    @FXML
    private Button closeButton;
    @FXML
    private Button connectButton;

    public void establishConnection(ActionEvent actionEvent) {
        String address = inputAddress.getText();
        String port = inputPort.getText();
        if (validInput(address, port))
            connectToServer(address, port);
    }

    private void connectToServer(String server, String servPort) {
        Connect4Connector connection = new Connect4Connector(server, Integer.parseInt(servPort));
        try {
        byte[] data = connection.receiveData();
            System.out.println(data[2]);
            connection.sendData(PacketInfo.MOVE, PacketInfo.PLAYER_ONE, (byte) 2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean validInput(String address, String port) {
        return !address.isEmpty() && !port.isEmpty() && !address.equals(" ") && !port.equals(" ");
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
