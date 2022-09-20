package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MyWebViewController {

    @FXML
    private WebView myWebView;
    private Main main;
    private Stage stage;
    private String prodCompany;

    private String page;

    private WebEngine engine;


//    public void initializable(String s) {
//
//    }

    public void setMain(Main main) {
        this.main=main;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void setProdCompany(String prodCompany) {
        this.prodCompany = prodCompany;
    }

    public String getProdCompany() {
        return prodCompany;
    }

    public void setPage(String page) {
        this.page = page;
    }

   // @Override
    public void initiate() {

        engine=myWebView.getEngine();
        engine.load(page);
        System.out.println(page);


    }

    public void closeAction(ActionEvent event) {
        engine.reload();
        stage.close();
    }
}
