/**
 *
 */

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class Server {
    private int port;
    private String message;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    Server(int port){
        this.port = port;
    }

    public void runServer() throws IOException {

        ServerSocket server = new ServerSocket(port, 100);
        while (message != "end") {
            Socket connection = server.accept();
            ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
            output.writeObject("connection successful");
            output.flush();
            //processConnection(); // process connection
        }
    }

   /* private void processConnection() throws IOException{
        try {
            message = (String) input.readObject();
            System.out.print(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/



    
}
