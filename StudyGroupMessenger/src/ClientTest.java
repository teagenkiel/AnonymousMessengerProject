import java.io.IOException;

/**
 *
 *
 *
 */
public class ClientTest {

    public static void main(String args[]){
        Client myClient;
        try {
            myClient = new Client("127.0.0.1", 12345);
            myClient.runClient();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
