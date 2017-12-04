import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StudyGroupApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        ClientModel appClientModel = new ClientModel("127.0.0.1", 12346);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeScene.fxml"));
        Parent root = (Parent)loader.load();
        HomeController controller = loader.getController();

        controller.setClientModel(appClientModel);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        appClientModel.getClient().runClient();

    }
}
