import java.io.IOException;

/**
 *
 *
 *
 */
public class ServerTest {

    public static void main(String[] args){

        Server myServer = new Server(5000);

        try {
            myServer.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
