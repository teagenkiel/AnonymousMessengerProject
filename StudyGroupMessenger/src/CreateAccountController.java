import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 *
 *
 */
public class CreateAccountController {


    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField errorMessage;

    private Profile userProfile;


    @FXML
    public void createAccountButton(ActionEvent event){

        errorMessage.setVisible(false);

        if(password.getText().equals(confirmPassword.getText())){
            userProfile = new Profile(username.getText(), password.getText());
        }
        else{
            errorMessage.setText("Password does not match. Please re-type");
            errorMessage.setVisible(true);
        }
    }

}
