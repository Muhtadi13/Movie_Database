package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MySearchController {
    @FXML
    private TextField searchTextBox;
    @FXML
    private Label searchTitle;
    private Main main;
    private Stage stage;

    private List<Movie> mySearchList=new ArrayList<>();

    @FXML
    public void onSearchButtonClick(ActionEvent event) {
        String search=searchTextBox.getText();
        MySearchHelper searcher=new MySearchHelper();
        String s=searchTitle.getText();
        List<Movie> newList=new ArrayList<>();
        int flag=0;

        if(s.equals("Title"))
            if ((searcher.movieByTitle(search, mySearchList)) == null)
           flag=1;
        else
            newList.add(searcher.movieByTitle(search, mySearchList));
        else if (s.equals("Genre")) {

                if((searcher.moviesByGenre(search,mySearchList)).isEmpty())
                   flag=1;
                else
                    newList.addAll(searcher.moviesByGenre(search, mySearchList));


        } else if (s.equals("Release Year")) {


               try{
                   Integer i = Integer.valueOf(search);
                   if ((searcher.moviesByYear(i, mySearchList)).isEmpty())
                       flag=1;
                   else
                       newList.addAll(searcher.moviesByYear(i, mySearchList));

               }
               catch (NumberFormatException e)
               {
                   main.showAlert2();
                   flag=1;
               }


        }
        if(flag==1)
            main.showAlert2();
        else {

            try {
                main.showTransferScene(newList);
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

    public Stage getStage() {
        return stage;
    }

    public void initiate(String s, List<Movie> myMovieList) {
        searchTitle.setText(s);
        mySearchList.addAll(myMovieList);
    }
}
