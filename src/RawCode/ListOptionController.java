package RawCode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Main;
import util.InfoUtil;

import java.io.IOException;

public class ListOptionController {

    private String company;

    private Main main;
    @FXML

    public void onTopButtonClick(ActionEvent event) {
    }
    @FXML

    public void onRecentButtonClick(ActionEvent event) {
    }
    @FXML

    public void onFullListButtonClick(ActionEvent event) {
        String ins="searchpcmovies";
        String cmp=company;
        InfoUtil iu=new InfoUtil(ins,cmp);
        try {
            main.getNetworkUtil().write(iu);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML

    public void onMaxButtonClick(ActionEvent event) {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
