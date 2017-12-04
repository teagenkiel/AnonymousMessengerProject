
import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;

import java.net.InetAddress;

import java.net.Socket;
import java.util.EventListener;

/**
 *
 */
public class Client {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String message;
    private String host;
    private int port;


    Client(String host, int port) throws IOException {
        this.host = host;
        this.port = port;

    }

    public void runClient() throws IOException {
        socket = new Socket(InetAddress.getByName(host), port);
        output = new ObjectOutputStream(socket.getOutputStream());
        output.flush();
        input = new ObjectInputStream(socket.getInputStream());
        output.writeObject("message from client");
        output.flush();
        processConnection();
    }


    private void processConnection() throws IOException {
        message = "";
        while (!message.equals("end")) {
            try {
                message = (String) input.readObject();
                System.out.println(message);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //private void display(String message){

    //}

    private void send(String message) {
        try {
            output.writeObject(message);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}