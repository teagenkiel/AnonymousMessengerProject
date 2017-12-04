
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


    Client(String host, int port) {

        this.host = host;
        this.port = port;
        runClient();
    }


    public void runClient(){
        try
        {
            connectToServer();
            processConnection();
        }
        catch (EOFException eofException) {
            message = "\nClient terminated connection";
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    private void connectToServer() throws IOException {
        message = "Attempting connection\n";

        socket = new Socket(InetAddress.getByName(host), port);

        message = "Connected to: " + socket.getInetAddress().getHostName();

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


    private void send(String message){
        try {
            output.writeObject(message);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
