package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MySearchByRuntimeController {

   @FXML
    private TextField maxTextBox;
    @FXML
    private TextField minTextBox;
    private Main main;
    private Stage stage;
    private List<Movie> mySearchList=new ArrayList<>();



    @FXML
    public void onSearchButtonClick(ActionEvent event) {
        List<Movie> myList = new ArrayList<>();
        MySearchHelper searcher = new MySearchHelper();
        int flag=0;
        try {
            Integer mn = Integer.valueOf(minTextBox.getText());
            Integer mx = Integer.valueOf(maxTextBox.getText());
            myList.addAll(searcher.moviesByRunTime(mn, mx, mySearchList));
        }catch (Exception e)
        {
            main.showAlert2();
            flag=1;
        }
        if(flag==0) {
            try {
                main.showTransferScene(myList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        stage.close();

    }
@FXML
    public void onReturnButtonClick(ActionEvent event) {
        stage.close();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Main getMain() {
        return main;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void initiate(List<Movie> myMovieList) {

        mySearchList.addAll(myMovieList);
    }
}
