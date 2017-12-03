import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WrkrRunnable implements Runnable {
    private Socket client = null;
    private String message = null;

    WrkrRunnable(Socket client, String message) {
        this.client = client;
        this.message = message;
    }

    public void run(){
        try{
            InputStream input = client.getInputStream();
            OutputStream output = client.getOutputStream();

            output.close();
            input.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
