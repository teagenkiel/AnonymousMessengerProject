/**
 *
 */

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class Server {


    public static void main(String[] args){



    }

    public void runServer() throws IOException {

        ServerSocket server = new ServerSocket(5000, 100);


        while (true) {
            try {
                Socket connection = server.accept();

                ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
                ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());

                try // send object to client
                {
                    output.writeObject("connection successful");
                    output.flush(); // flush output to client

                    while(true){

                        try // read message and display it
                        {
                            String message = (String) input.readObject(); // read new message
                            displayMessage("\n" + message); // display message
                        } catch (ClassNotFoundException classNotFoundException) {
                            displayMessage("\nUnknown object type received");
                        }
                    }

                } catch (IOException ioException) {

                }
                processConnection(); // process connection
            } catch (EOFException eofException) {
                displayMessage("\nServer terminated connection");
            } finally {
                closeConnection(); //  close connection
                ++counter;
            }
        }

        try // send object to client
        {
            output.writeObject("SERVER>>> " + message);
            output.flush(); // flush output to client
            displayMessage("\nSERVER>>> " + message);
        } catch (IOException ioException) {
            displayArea.append("\nError writing object");
        }



    }



    
}
