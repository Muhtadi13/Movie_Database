package RawCode;


import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import server.Movie;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TableSceneController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TableView<Movie> mytable;
    public TableColumn<Movie, String> title;
    public TableColumn<Movie, Integer> year;
    public TableColumn<Movie, String> genre;
    public TableColumn<Movie, Integer> runtime;
    public TableColumn<Movie, String> pc;
    public TableColumn<Movie, Long> budget;
    public TableColumn<Movie, Long> revenue;
    private String prodcompany;

    public String getProdcompany() {
        return prodcompany;
    }

    public void setProdcompany(String prodcompany) {
        this.prodcompany = prodcompany;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        year.setCellValueFactory(new PropertyValueFactory<>("ReleaseYear"));
        genre.setCellValueFactory(new PropertyValueFactory<>("Genrestr"));
        runtime.setCellValueFactory(new PropertyValueFactory<>("RunningTime"));
        pc.setCellValueFactory(new PropertyValueFactory<>("ProductionCompany"));
        budget.setCellValueFactory(new PropertyValueFactory<>("Budget"));
        revenue.setCellValueFactory(new PropertyValueFactory<>("Revenue"));

    }

    public void obtainList(List<Movie> pcMovieList) {

        mytable.getItems().clear();

        for (Movie mvi : pcMovieList) {
            mytable.getItems().add(mvi);
        }
    }
}




