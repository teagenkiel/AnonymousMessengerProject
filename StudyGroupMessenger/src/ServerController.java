/**
 *
 */
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.concurrent.Executor;

public class ServerController {
    private int port;
    private String message;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;
    private ServerSocket server;
    private Executor executor;

    @FXML
    private TextArea serverLogArea;

    @FXML
    public void runServer(){
        display("display works");
            try {
                message = "";
                server = new ServerSocket(12345);
                while (!message.equals("end")) {
                    connection = server.accept();
                    SocketThread socketThread = new SocketThread(connection);
                    display("connected to client");
                    executor.execute(socketThread);
                }
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    /*
    public void connectToClient() throws IOException{
        ServerSocket socket = new ServerSocket(12345);
        message = "";
        while(!message.equals("end")){
            connection = socket.accept();
            new Thread(new ServerController(port)).start();
        }
    }*/


/*    private void processConnection() throws IOException{

        try {
            message = (String) input.readObject();
            display(message);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    private void display(String message){
        serverLogArea.setText(message);

    }

    private void send(String message){
        try {
            output.writeObject(message);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPort(int port) {
        this.port = port;
    }
}
