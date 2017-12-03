/**
 *
 */


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class Server implements Runnable{
    private int port;
    private String message;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;
    private ServerSocket server;

    Server(int port) {
        this.port = port;
    }

    public void run(){
        try {
            server = new ServerSocket(port);
            while (!message.equals("end")) {
                connection = server.accept();
                new Thread(new WrkrRunnable(connection, "this is a multithreaded server")).start();
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
            new Thread(new Server(port)).start();
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
        System.out.println(message);

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
