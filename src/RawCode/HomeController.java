package RawCode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class HomeController {

    private Main main;

    @FXML
    private Label message;

    @FXML
    private ImageView image;


    private String companyname;

    public void init(String msg) {
        message.setText(msg);
        Image img = new Image(Main.class.getResourceAsStream("1.png"));
        image.setImage(img);
    }

    @FXML
    void logoutAction(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setMain(Main main) {
        this.main = main;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
}
