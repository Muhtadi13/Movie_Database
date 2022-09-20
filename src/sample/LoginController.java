package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.LoginDTO;

import java.io.IOException;


public class LoginController {

    @FXML
    private ImageView image;
    private Main main;
    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    void loginAction(ActionEvent event) {
        String userName = userText.getText();
        String password = passwordText.getText();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(userName);
        loginDTO.setPassword(password);
        try {
            main.getNetworkUtil().write(loginDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void resetAction(ActionEvent event) {
        userText.setText(null);
        passwordText.setText(null);
    }

    void setMain(Main main) {
        this.main = main;
    }

    public void setImg(Image img) {
        image.setImage(img);
    }

    public void exitAction(ActionEvent event) {
        main.closeScene();
    }
}
