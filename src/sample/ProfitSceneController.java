package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfitSceneController {
    @FXML
    private Label company;
    @FXML
    private Label profitcount;

    private Main main;
    private String pcCompany;

    public void onBackButtonClick(ActionEvent event) {

        try {
            main.showProdComPage(pcCompany);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void setProfitcount(Long profitcount) {
        if(profitcount==-1000000000)
            profitcount= Long.valueOf(0);

        this.profitcount.setText(String.valueOf(profitcount));
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }


    public void setPcCompany(String pcCompany) {

        this.pcCompany = pcCompany;
        company.setText(pcCompany);
    }
}
