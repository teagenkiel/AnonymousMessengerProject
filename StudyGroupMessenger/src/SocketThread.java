import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketThread implements Runnable {
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket socket;

    SocketThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try {
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String message = null;
        try {
            message = (String) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            output.writeObject("message from Server");
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(message);
        try {
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
