package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import server.Movie;
import util.NetworkUtil;

import java.io.IOException;
import java.util.List;

public class Main extends Application {

    private Stage stage;
    private NetworkUtil networkUtil;
    private String productionCompany;

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        connectToServer();
        showLoginPage();
    }

    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        new ReadThread(this);
    }

    public void showLoginPage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML_files/login.fxml"));
        Parent root = loader.load();


        LoginController controller = loader.getController();
        controller.setMain(this);
        Image im = new Image(Main.class.getResourceAsStream("/Images/LoginPage.jpg"));
        controller.setImg(im);

        stage.setTitle("Login");
        stage.setResizable(false);

        stage.setScene(new Scene(root, 610, 345));
        stage.show();
    }


    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }

    public void showAlert2() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Empty List");
        alert.setHeaderText("NO SUCH MOVIE EXISTS");
        alert.setContentText("This Company Has No Such Movie With The Following Criteria");
        alert.showAndWait();
    }


    public void showTransferScene(List<Movie> pcmovielist) throws IOException {


        System.out.println(pcmovielist.isEmpty());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML_files/ListView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 937, 500);
        ListViewController lvController = fxmlLoader.getController();
        lvController.setProdCompany(productionCompany);
        lvController.setMain(this);
        lvController.initiate(pcmovielist);
        stage.setScene(scene);
        stage.show();

    }

    public void showProdComPage(String userName) throws IOException {
        productionCompany = userName;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML_files/production_company.fxml"));
        Parent root = loader.load();

        ProdComController controller = loader.getController();
        controller.setProductioncompany(userName);
        controller.init(userName);
        controller.setMain(this);

        stage.setTitle("Homepage Of Production Company");
        stage.setScene(new Scene(root, 794, 518));
        stage.show();

    }


    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }


    public void showMovieDetail(Stage stage, Movie movie) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML_files/MovieCard.fxml"));
        Parent root = loader.load();
        MovieCardController mcController = loader.getController();
        mcController.setMain(this);
        mcController.setStage(stage);
        mcController.setPcCompany(productionCompany);
        mcController.initiate(movie);
        Scene scene = new javafx.scene.Scene(root, 1000, 555);
        stage.setScene(scene);
        stage.setTitle("Movie Detail");
        stage.setResizable(false);
        if (!stage.isShowing())
            stage.show();

    }

    public void showAddMovieScene(String productioncompany) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML_files/AddMovieScene.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 795, 470);
        AddMovieSceneController amsController = fxmlLoader.getController();
        amsController.setpCompany(productioncompany);
        amsController.setMain(this);

        stage.setTitle("Add a Movie");
        stage.setScene(scene);
        stage.show();

    }

    public void showTransferCompanyScene(Stage stage, Movie m) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML_files/TransferToProductionCompany.fxml"));
        Parent root = loader.load();
        TransferToProductionCompanyController ttpcController = loader.getController();
        ttpcController.setMain(this);
        ttpcController.setStage(stage);
        ttpcController.initiate(m);
        ttpcController.setProdCompany(productionCompany);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Movie Detail");

        if (!stage.isShowing())
            stage.show();


    }

    public void showProfitScene(Long profit) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML_files/ProfitScene.fxml"));

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 723, 290);
        ProfitSceneController psController = fxmlLoader.getController();
        psController.setMain(this);
        psController.setPcCompany(productionCompany);
        psController.setProfitcount(profit);

        stage.setTitle("Profit");
        stage.setScene(scene);
        stage.show();

    }

    public void showMySearchScene(Stage stage, String s, List<Movie> myMovieList) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML_files/MySearch.fxml"));
        Parent root = loader.load();
        MySearchController msController = loader.getController();
        msController.setMain(this);
        msController.setStage(stage);
        msController.initiate(s, myMovieList);
        Scene scene = new javafx.scene.Scene(root);
        stage.setScene(scene);
        stage.setTitle("Filter");

        if (!stage.isShowing())
            stage.show();

    }

    public void showMySearchTimeScene(Stage stage, List<Movie> myMovieList) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML_files/MySearchByRuntime.fxml"));
        Parent root = loader.load();
        MySearchByRuntimeController msbrController = loader.getController();
        msbrController.setMain(this);
        msbrController.setStage(stage);
        msbrController.initiate(myMovieList);
        Scene scene = new javafx.scene.Scene(root);
        stage.setScene(scene);
        stage.setTitle("Filter");
        if (!stage.isShowing())
            stage.show();


    }

    public void closeScene() {
        stage.close();
    }

    public void showMyWebScene(Stage stage, Movie m) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML_files/MyWebView.fxml"));
        Parent root = loader.load();
        MyWebViewController mwvController = loader.getController();
        mwvController.setMain(this);
        mwvController.setStage(stage);
        mwvController.setPage(m.getTrailer());

        mwvController.setProdCompany(productionCompany);
        mwvController.initiate();
        Scene scene = new Scene(root,715,423);
        stage.setScene(scene);
        stage.setTitle("Trailer");
        stage.setResizable(false);

        if (!stage.isShowing())
            stage.show();
    }
}

