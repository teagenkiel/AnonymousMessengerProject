import java.io.IOException;
import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;

import java.net.InetAddress;

import java.net.Socket;

/**
 *
 */
public class Client {
    private Socket socket;

    private ObjectOutputStream output;

    private ObjectInputStream input;

    private String message;

    Client(String host, int port) throws IOException {

        socket = new Socket(InetAddress.getByName(host), port);

        output = new ObjectOutputStream(socket.getOutputStream());

        output.flush();

        input = new ObjectInputStream(socket.getInputStream());

        processConnection();

    }


    private void processConnection() throws IOException {

        while (message.equals("end")) {

            try {

                message = (String) input.readObject();

                System.out.println(message);

            } catch (ClassNotFoundException e) {

                e.printStackTrace();

            }

        }

    }

}
