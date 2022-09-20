package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.InfoUtil;
import server.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListViewController {
    @FXML
    private Label movieCount;
    @FXML
    private Label pcCompany;
    private Main main;
    private String prodCompany;

    private List<Movie> myMovieList=new ArrayList<>();

    public void setProdCompany(String prodCompany) {
        this.prodCompany = prodCompany;
    }

    public void onBackButtonClick(ActionEvent event) {
        try {
            main.showProdComPage(prodCompany);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private ListView<VBox> listView;

    public void initiate(List<Movie> movies) throws IOException {
        myMovieList=movies;
        pcCompany.setText(prodCompany);
        movieCount.setText(String.valueOf(myMovieList.size()));

        for (var m : movies) {
            m.setProductionCompany(prodCompany);
            FXMLLoader newLoader = new FXMLLoader();
            newLoader.setLocation(getClass().getResource("/FXML_files/MinMovieView.fxml"));
            newLoader.load();
            MinMovieDetailController mDetail = newLoader.getController();
            mDetail.setMain(main);
            listView.getItems().add(mDetail.initiate(m));
        }
    }

    public void onTitleButtonClick(ActionEvent event) {
        String s="Title";
        try {
            main.showMySearchScene(new Stage(),s,myMovieList);
        }catch (Exception e) {
            e.printStackTrace();
        }



    }

    public void onGenreButtonClick(ActionEvent event) {
        String s="Genre";
        try {
            main.showMySearchScene(new Stage(),s,myMovieList);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onReleaseYearButtonClick(ActionEvent event) {
        String s="Release Year";
        try {
            main.showMySearchScene(new Stage(),s,myMovieList);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onRunTimeButtonClick(ActionEvent event) {

        try {
            main.showMySearchTimeScene(new Stage(),myMovieList);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onListButtonClick(ActionEvent event) {
        String ins="searchpcmovies";
        String cmp=prodCompany;
        InfoUtil iu=new InfoUtil(ins,cmp);
        try {
            main.getNetworkUtil().write(iu);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

