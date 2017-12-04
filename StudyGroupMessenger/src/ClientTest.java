import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 *
 *
 */
public class ClientTest extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

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
