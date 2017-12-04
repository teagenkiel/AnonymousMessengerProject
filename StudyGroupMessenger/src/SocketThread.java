import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketThread implements Runnable {
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket socket;
    private String message = "";

    SocketThread(Socket socket){
        this.socket = socket;
    }

    public void run() {
        // while (true) {
        System.out.println("thread is running");
        try {
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
            processConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("got IO streams");

        System.out.println("read messages");
            try {
                output.writeObject("message from Server");
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("wrote messages");
            try {
                input.close();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("connection closed");

      //  }

    }

    private void processConnection()throws IOException{
        System.out.println("program entered processConnection");
       // while(!message.equals("end")) {
            try {
                message = (String) input.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("message received");
            System.out.println(message);
       // }
        System.out.print("program finished processConnection");

    }
}
