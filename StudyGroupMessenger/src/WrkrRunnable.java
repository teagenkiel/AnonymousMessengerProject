import java.io.*;
import java.net.Socket;

public class WrkrRunnable implements Runnable {
    private Socket client = null;
    private String message = null;

    WrkrRunnable(Socket client) {
        this.client = client;
        this.message = message;
    }

    public void run(){
        try{
            ObjectInputStream input = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
            try {
                message = (String) input.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(message);
            output.close();
            input.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
