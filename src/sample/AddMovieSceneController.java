package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import util.AdderUtil;
import server.Movie;

import java.io.IOException;

public class AddMovieSceneController {

    @FXML
    private Label pcCompany;
    private String pCompany;

    private Main main;

    @FXML
    private TextArea nameBox;
    @FXML
    private TextArea genreBox1;
    @FXML
    private TextArea genreBox2;
    @FXML
    private TextArea genreBox3;
    @FXML
    private TextArea revenueBox;
    @FXML
    private TextArea runTimeBox;
    @FXML
    private TextArea releaseBox;
    @FXML
    private TextArea budgetBox;

    @FXML
    public void onAddMovieButtonClick(ActionEvent event)  {
        String[] strings = new String[9];


        strings[0]=""+nameBox.getText();

        strings[1]=""+releaseBox.getText();
        strings[2]=""+genreBox1.getText();
        strings[3]=""+genreBox2.getText();
        strings[4]=""+genreBox3.getText();
        strings[5]=""+runTimeBox.getText();
        strings[6]=""+pCompany;
        strings[7]=""+budgetBox.getText();
        strings[8]=""+revenueBox.getText();

        Movie m=new Movie(strings);

        AdderUtil au=new AdderUtil();
        au.setData(m);
        au.setProdCompany(this.pCompany);

        try {
            main.getNetworkUtil().write(au);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void onGoBackButtonClick(ActionEvent event) {

        try {
            main.showProdComPage(pCompany);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void setpCompany(String pCompany) {
        this.pCompany = pCompany;
        pcCompany.setText(pCompany);
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
