/**
 *
 */

import javax.swing.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class Server extends JFrame{
    private int port;
    private String message;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private JTextField chatArea;

    Server(int port) {
        this.port = port;
        setSize(500,500);

        chatArea = new JTextField(50);
        chatArea.setEditable(true);
        chatArea.setVisible(true);
        setVisible(true);
        add(chatArea);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void runServer() throws IOException {

        ServerSocket server = new ServerSocket(port, 100);
        while (message != "end") {
            Socket connection = server.accept();
            output = new ObjectOutputStream(connection.getOutputStream());
            input = new ObjectInputStream(connection.getInputStream());
            output.writeObject("connection successful");
            output.flush();
            processConnection(); // process connection
        }
    }

    private void processConnection() throws IOException{

        try {
            message = (String) input.readObject();
            display(message);
            System.out.println(message);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void display(String message){
        new Runnable(){

            @Override
            public void run() {
                chatArea.setText(message+'\n');
            }
        };
    }

    private void send(String message){
        try {
            output.writeObject(message);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
}
