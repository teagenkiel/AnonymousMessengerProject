import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 *
 *
 */
public class HomeController {


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

        String message = messageField.getText();


        //send message to client, which will send to server
        //get entire new chat log from client, which gets it from server
        chatArea.setText(message);

        messageField.clear();

    }





}
