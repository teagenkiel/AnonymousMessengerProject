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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.concurrent.Task;

public class ServerController {

    private int port;
    private int counter;
    private ServerSocket server;
    private SocketThread[] socketThreadArray;
    private ExecutorService socketThreadExecutor;
    private ExecutorService serverThreadExecutor;

    private static final int MAX_SOCKETTHREAD_SIZE = 100;
    private static final int MIN_SOCKETTHREAD_SIZE = 0;



    @FXML
    private TextArea serverLogArea;

    @FXML
    private void initialize(){

        port = 12346;

        counter = MIN_SOCKETTHREAD_SIZE;

        socketThreadArray = new SocketThread[MAX_SOCKETTHREAD_SIZE];
        this.socketThreadExecutor = Executors.newFixedThreadPool(MAX_SOCKETTHREAD_SIZE);
        this.serverThreadExecutor = Executors.newSingleThreadExecutor();



        try {
            server = new ServerSocket(port, MAX_SOCKETTHREAD_SIZE);
        } catch (IOException e) {
            serverLogArea.appendText("Not able to establish server socket\n");
        }
    }


    @FXML
    public void runServer(){

        Task runServerTask = new Task<Void>() {

            @Override
            public Void call() throws Exception {


                try {

                    while (true) {

                        serverLogArea.appendText("Waiting for new connection\n");
                        socketThreadArray[counter] = new SocketThread(server.accept(), serverLogArea);

                        socketThreadExecutor.submit(socketThreadArray[counter]);
                        serverLogArea.appendText("Connected to client #"+ counter + "\n");

                        counter++;
                    }

                }
                catch (IOException e){

                    serverLogArea.appendText("Error while running server \n" + e);
                }
                finally {

                    socketThreadExecutor.shutdownNow(); //close all currently running socket threads
                }

                return null;
            }
        };

        serverThreadExecutor.submit(runServerTask);

    }


    private void display(String message){

        serverLogArea.appendText(message);

    }

    public void setPort(int port) {
        this.port = port;
    }
}
