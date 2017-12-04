import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientController {

    //private Client client = new Client("127.0.0.1", 12346);

    @FXML
    private Button send;

    @FXML
    private TextField messageBox;

    @FXML
    private TextArea messageDisplay;

    private void processSendButton(ActionEvent event){
        if(!messageBox.getText().equals("")){
            display(messageBox.getText());
        }
    }

    private void display(String message){
        new Runnable(){

            @Override
            public void run() {
                messageDisplay.setText(messageDisplay.getText() + "\n" + messageBox.getText());
            }
        };
    }
}
