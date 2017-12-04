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
    private String message;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;
    private ServerSocket server;
    private Executor executor;
    private int counter = 0;




    @FXML
    private TextArea serverLogArea;

    @FXML
    public void runServer(){

        ExecutorService myExecutor = Executors.newFixedThreadPool(10);
        Task task = new Task<Void>() {

            @Override
            public Void call() throws Exception {

                display("display works");
                executor = new Executor() {
                    @Override
                    public void execute(Runnable command) {
                        new Thread(command).start();
                    }
                };
                SocketThread socketThreadArray[] = new SocketThread[100];
                try

                {
                    message = "";
                    int count = 0;
                    server = new ServerSocket(12346, 100);
                    while (!message.equals("end")) {

                        //connection = server.accept();
                        socketThreadArray[count] = new SocketThread(server.accept());
                        executor.execute(socketThreadArray[count]);
                        display("connected to client"+ Integer.toString(count));

                        count++;

                    }
                    connection.close();
                } catch (
                        IOException e)

                {
                    e.printStackTrace();
                }

                return null;
            }
        };

        myExecutor.submit(task);

    }



    /*
    public void connectToClient() throws IOException{
        ServerSocket socket = new ServerSocket(12345);
        message = "";
        while(!message.equals("end")){
            connection = socket.accept();
            new Thread(new ServerController(port)).start();
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
        serverLogArea.setText(message);

    }

    private void send(String message){
        try {
            output.writeObject(message);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPort(int port) {
        this.port = port;
    }
}
