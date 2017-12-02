import java.io.IOException;

/**
 *
 *
 *
 */
public class ClientTest {

    public static void main(String args[]){

        try {
            Client myClient = new Client("127.0.0.1", 5000);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}
