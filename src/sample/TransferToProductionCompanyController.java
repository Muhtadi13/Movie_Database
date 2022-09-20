package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.Movie;
import util.TranferUtil;

import java.io.IOException;

public class TransferToProductionCompanyController {

    @FXML
    private TextField transferText;

    private Main main;

    private Stage stage;

    private String prodCompany;

    private Movie movie;

    public void onReturnButtonClick(ActionEvent event) {
        stage.close();
    }

    public void onConfirmButtonClick(ActionEvent event) {


        System.out.println(movie.getTitle());
        String s=transferText.getText();
        System.out.println(s);
        movie.setProductionCompany(prodCompany);
        TranferUtil tu=new TranferUtil(movie,s,prodCompany);
        System.out.println(tu.getNewowner());
        System.out.println(((Movie)tu.getMovie()).getTitle());

        try {
            main.getNetworkUtil().write(tu);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.close();


    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void initiate(Movie m) {
        movie=m;
    }

    public void setProdCompany(String prodCompany) {
        this.prodCompany = prodCompany;
    }
}
