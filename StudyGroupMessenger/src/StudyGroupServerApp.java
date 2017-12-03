/**
 *
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class StudyGroupServerApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Parent root = null;
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(getClass().getResource("BoardScene.fxml"));
            root = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Server studyGroupServer = null;
        studyGroupServer = new Server(12345);

        ServerSceneController controller = loader.getController();
       // controller.setAndRunServer(studyGroupServer);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
