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

    Server(Socket client) throws IOException {
        ServerSocket socket = new ServerSocket(12345, 100);

        while(!message.equals("end")){
            Socket connection = socket.accept();
            new Thread(new Server(connection)).start();
        }
    }

    public void run(){
        try {
            processConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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
