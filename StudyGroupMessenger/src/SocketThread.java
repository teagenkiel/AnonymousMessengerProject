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
    private String message = "";

    public SocketThread(Socket socket, TextArea serverLogArea){
        this.socket = socket;
        this.serverLogArea = serverLogArea;
    }

    public void run() {
        System.out.println("thread is running");
        try {
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
            processConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            sendData("message from server");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("read messages");

            System.out.println("wrote messages");
            try {
                input.close();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("connection closed");



    }

    private void processConnection()throws IOException{
        System.out.println("program entered processConnection");
        while(!message.equals("end")) {
            try {
                message = (String) input.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("message received");
            serverLogArea.appendText(message);
        }
        System.out.print("program finished processConnection");
    }

    private void sendData(String message)throws IOException{
        output.writeObject(message);
        output.flush();
    }
}
