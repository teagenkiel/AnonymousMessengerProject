
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
public class Client extends JFrame {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String message;
    private String host;
    private int port;
    private JTextArea chatArea;
    private JButton button;

    Client(String host, int port) throws IOException {
        this.host = host;
        this.port = port;

        setLayout(new FlowLayout());

        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setSize(500,400);
        chatArea.setVisible(true);
        chatArea.setEditable(true);
        add(chatArea);

        button = new JButton("Enter");
        button.setVisible(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message = chatArea.getText();
                send(message);
            }
        });

        add(button);

    }

    public void runClient() throws IOException{
        socket = new Socket(InetAddress.getByName(host), port);
        output = new ObjectOutputStream(socket.getOutputStream());
        output.flush();
        input = new ObjectInputStream(socket.getInputStream());
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

    private void display(String message){
        new Runnable(){

            @Override
            public void run() {
                chatArea.append(message+'\n');
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
