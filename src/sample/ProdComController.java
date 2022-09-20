package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.InfoUtil;

import java.io.IOException;


public class ProdComController {

    private Main main;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Label message;
    @FXML
    private ImageView image;

    private String productioncompany;

    public String getProductioncompany() {
        return productioncompany;
    }

    public void setProductioncompany(String productioncompany) {
        this.productioncompany = productioncompany;
    }






    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }






    /*public ObservableList<Movie> getpcMovie()
    {
        ObservableList<Movie> list= FXCollections.observableArrayList();
        list.addAll(pcMovieList);
        return list;
    }*/
    public void init(String msg) {
        message.setText(msg);
        String str = productioncompany;
        str=str.replaceAll(" ", "_");
        str=str.toUpperCase();

        str = "/CompanyLogo/" + str + ".jpg";
        System.out.println(str);

        if (Main.class.getResourceAsStream(str) == null)
            str = "/MovieThumbnails/Defaultposter.jpg";

        Image im = new Image(Main.class.getResourceAsStream(str));

        image.setImage(im);
    }


    public void switchToHello(ActionEvent event) throws IOException {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void onAddButtonClick(ActionEvent event) throws IOException {
        try {
            main.showAddMovieScene(productioncompany);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void onListButtonClick(ActionEvent event) throws IOException {
        String ins="searchpcmovies";
        String cmp=productioncompany;
        InfoUtil iu=new InfoUtil(ins,cmp);
        try {
            main.getNetworkUtil().write(iu);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setMessage(Label message) {
        this.message = message;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void onRecentButtonClick(ActionEvent event) {
        String ins="mostrecent";
        String cmp=productioncompany;
        InfoUtil iu=new InfoUtil(ins,cmp);
        try {
            main.getNetworkUtil().write(iu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onMaxRevenueClick(ActionEvent event) {
        String ins="maxrevenue";
        String cmp=productioncompany;
        InfoUtil iu=new InfoUtil(ins,cmp);
        try {
            main.getNetworkUtil().write(iu);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onProfitButtonClick(ActionEvent event) {
        String ins="totalprofit";
        String cmp=productioncompany;
        InfoUtil iu=new InfoUtil(ins,cmp);
        try {
            main.getNetworkUtil().write(iu);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}