import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

/**
 *
 *
 *
 */
public class ServerSceneController {

    private Server server;

    @FXML
    private TextArea serverLogArea;

    public void setAndRunServer() {

        Server server = new Server(12345);
        /*this.server = server;

        try {
            this.server.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public TextArea getServerLogArea() {
        return serverLogArea;
    }
}
