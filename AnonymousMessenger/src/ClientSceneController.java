import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.concurrent.Task;

/**
 *
 *
 */
public class ClientSceneController {

    private ClientModel clientModel;
    private ExecutorService clientThreadExecutor;

    @FXML
    private TextField messageField;

    @FXML
    private TextArea chatArea;

    @FXML
    private void initialize(){

        clientThreadExecutor = Executors.newFixedThreadPool(10);

        Task receiveMessagesTask = new Task<Void>() {

            @Override
            public Void call() throws Exception {

                String messageFromServer;

                do{

                    messageFromServer = clientModel.getClient().receiveMessageFromServer();
                    chatArea.setText(messageFromServer);

                }while(!messageFromServer.equals("TERMINATE"));

                return null;
            }
        };

        clientThreadExecutor.submit(receiveMessagesTask);

    }

    /**
     * Method to send a message after a user types something in the message field and hits 'send' button
     */
    @FXML
    public void sendMessage(){

        Task sendMessageTask = new Task<Void>() {

            @Override
            public Void call() throws Exception {

                String message = messageField.getText();

                clientModel.getClient().sendMessage(message);

                messageField.clear();

                return null;
            }
        };

        clientThreadExecutor.submit(sendMessageTask);


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
