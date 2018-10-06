package main.java.com.connectfour.controller;

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

public class connectServer {

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
        final Socket socket;
        final DataInputStream in;
        final OutputStream out;

        try {
            // Create socket and fetch I/O streams
            socket = new Socket(server, Integer.parseInt(servPort));

            in = new DataInputStream(socket.getInputStream());
            out = socket.getOutputStream();

            Stage stage = (Stage) closeButton.getScene().getWindow();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    try {
                        socket.close();
                    } catch (Exception exception) {
                    }
                    System.exit(0);
                }
            });

        } catch (SocketException e) {
        } catch (IOException e) {
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
