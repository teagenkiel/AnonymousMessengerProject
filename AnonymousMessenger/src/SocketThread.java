import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.concurrent.Task;
import javafx.scene.control.TextArea;

public class SocketThread implements Runnable {

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket socket;
    private TextArea serverLogArea;
    private int threadNumber;
    private ServerLogModel serverLogModel;

    public SocketThread(Socket socket, int threadNumber, TextArea serverLogArea, ServerLogModel serverLogModel) {


        try {
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            serverLogArea.appendText("Error setting up I/O streams in socket thread #" + threadNumber + ". " + e);
        }

        this.socket = socket;
        this.serverLogArea = serverLogArea;
        this.threadNumber = threadNumber + 1;
        this.serverLogModel = serverLogModel;

        serverLogArea.appendText("Connected to socket thread #" + this.threadNumber + ".\n");
    }

    @Override
    public void run() {

        updateChatLog();

        String message = "";

        while (!message.equals("TERMINATE")) { //exit out of loop to be implemented

            try {

                message = (String) input.readObject();
                serverLogArea.appendText("User #" + threadNumber + ": " + message + '\n');

                serverLogModel.addToChatLog("User #" + threadNumber + ": " + message + '\n');


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

    private void updateChatLog(){

        ExecutorService updateExecutor = Executors.newSingleThreadExecutor();

        Task chatLogUpdateTask = new Task<Void>() {

            @Override
            public Void call() throws Exception {

                String chatLog = "";

                while(!chatLog.equals("TERMINATE")) {

                    chatLog = serverLogModel.getChatLog();

                    output.writeObject(chatLog);
                    output.flush();
                }

                return null;
            }
        };

        updateExecutor.submit(chatLogUpdateTask);
    }
}
