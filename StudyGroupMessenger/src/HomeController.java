import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.concurrent.Task;

/**
 *
 *
 */
public class HomeController {

    private ClientModel clientModel;

    @FXML
    private TextField messageField;

    @FXML
    private TextArea chatArea;

    @FXML
    private Button sendMessageButton;

    /**
     * Method to send a message after a user types something in the message field
     */
    @FXML
    public void sendMessage(){

        ExecutorService myExecutor = Executors.newFixedThreadPool(10);
        Task task = new Task<Void>() {

            @Override
            public Void call() throws Exception {

                String message = messageField.getText();

                clientModel.getClient().sendMessage(message);
                chatArea.appendText(message);
                messageField.clear();

                return null;
            }
        };

        myExecutor.submit(task);


        //send message to client, which will send to server
        //get entire new chat log from client, which gets it from server
    }

    public ClientModel getClientModel() {
        return clientModel;
    }

    public void setClientModel(ClientModel clientModel) {
        this.clientModel = clientModel;
    }
}
