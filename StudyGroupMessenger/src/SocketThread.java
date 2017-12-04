import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.scene.control.TextArea;

public class SocketThread implements Runnable {

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket socket;
    private TextArea serverLogArea;
    private int threadNumber;

    public SocketThread(Socket socket, int threadNumber, TextArea serverLogArea) {


        try {
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            serverLogArea.appendText("Error setting up I/O streams in socket thread #" + threadNumber + ". " + e);
        }

        this.socket = socket;
        this.serverLogArea = serverLogArea;
        this.threadNumber = threadNumber + 1;

        serverLogArea.appendText("Connected to socket thread #" + this.threadNumber + ".\n");
    }

    @Override
    public void run() {

        String message = "";

        while (!message.equals("TERMINATE")) { //exit out of loop to be implemented

            try {

                message = (String) input.readObject();
                serverLogArea.appendText("Thread #" + threadNumber + ": " + message + '\n');


            } catch (ClassNotFoundException e) {
                serverLogArea.appendText("Class not found in Client's message from thread #" + threadNumber + e);
            } catch (IOException e) {
                serverLogArea.appendText("Error in thread #" + threadNumber);
            }

        }

        try {
            input.close();
            output.close();
        } catch (IOException e) {
            serverLogArea.appendText("Error closing I/O streams in thread #" + threadNumber);
        }

        serverLogArea.appendText("Thread #" + threadNumber + " closed.\n");

    }

    private void sendData(String message) throws IOException {
        output.writeObject(message);
        output.flush();
    }
}
