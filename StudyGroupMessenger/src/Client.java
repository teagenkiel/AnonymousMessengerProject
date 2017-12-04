import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.io.EOFException;
import java.io.IOException;
import java.net.UnknownHostException;


/**
 *
 */
public class Client {

    private ObjectOutputStream output;
    private ObjectInputStream input;


    Client(String serverHostAddress, int serverPortNumber) throws IOException {

        Socket socket = new Socket(InetAddress.getByName(serverHostAddress), serverPortNumber);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());

    }

    public String receiveMessageFromServer() {

        String receivedMessage;

        try {

            receivedMessage = (String) input.readObject();
        } catch (ClassNotFoundException e) {

            receivedMessage = "Error: class of received message not found";
        } catch (IOException e) {

            receivedMessage = "Error retrieving message from server";
        }

        return receivedMessage;

    }


    public void sendMessage(String message) throws IOException {

        output.writeObject(message);
        output.flush();

    }


}
