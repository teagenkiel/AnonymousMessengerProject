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

    Server(int port){
        this.port = port;
    }

    public void runServer() throws IOException {

        ServerSocket server = new ServerSocket(5000, 100);
        while (true) {
            try {
                Socket connection = server.accept();

                ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
                ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());

                {
                    output.writeObject("connection successful");
                    output.flush(); // flush output to client

                    while(true){
                        String message = (String) input.readObject(); // read new message
                    }

                } catch (IOException ioException) {

                }
                processConnection(); // process connection
        }
    }



    
}
