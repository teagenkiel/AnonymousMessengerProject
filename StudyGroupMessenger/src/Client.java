
import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.EventListener;
import java.io.EOFException;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


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

    @FXML
    TextField messageBox;

    @FXML
    Button send;

    @FXML
    TextArea messageDisplay;


    Client(String host, int port) throws IOException {

        this.host = host;
        this.port = port;

    }

    public void runClient() throws IOException{
        socket = new Socket(InetAddress.getByName(host), port);
        output = new ObjectOutputStream(socket.getOutputStream());
        output.flush();
        input = new ObjectInputStream(socket.getInputStream());
        processConnection();
    }

    public void processSend(ActionEvent event){

        if(!messageBox.getText().equals("")){
            display(messageBox.getText());
        }
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
                messageDisplay.setText(messageDisplay.getText() + message);
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
