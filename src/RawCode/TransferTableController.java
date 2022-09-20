package RawCode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;
import util.InactiveDTO;
import server.Movie;
import util.TranferUtil;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TransferTableController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Main main;

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

    @FXML
    private TextField prodcomfield;

    private Movie currentmovie;

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
    public void onTransferSureButtonClick(ActionEvent event) {
        //ObservableList<Movie> mlist;
        Movie m=mytable.getSelectionModel().getSelectedItem();

        System.out.println(m.getTitle());
        String s=prodcomfield.getText();
        System.out.println(s);
        TranferUtil tu=new TranferUtil(m,s,prodcompany);
        System.out.println(tu.getNewowner());
        System.out.println(((Movie)tu.getMovie()).getTitle());
        try {
            main.getNetworkUtil().write(tu);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void onBackFromTransferButtonClick(ActionEvent event) {

        InactiveDTO idto=new InactiveDTO();
        idto.setUserName(prodcompany);
        try {
            main.getNetworkUtil().write(idto);
            main.showProdComPage(prodcompany);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
