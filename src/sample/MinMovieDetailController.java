

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.Movie;

import java.io.IOException;

public class MinMovieDetailController {
    private Movie movie;
    private Main main;
    @FXML
    private ImageView imView;


    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private Label movieName;


    @FXML
    private VBox moviePane;



    public VBox initiate(Movie m) {
        movie = m;
        movieName.setText(m.getTitle());
        String str = movie.getImageSourcePoster();
        System.out.println(str);

        if (Main.class.getResourceAsStream(str) == null)
            str = "/MovieThumbnails/Defaultposter.jpg";

        Image im = new Image(Main.class.getResourceAsStream(str));
        imView.setImage(im);

        return moviePane;
    }


    @FXML
    void showMovieDetails(ActionEvent event) throws IOException {
        main.showMovieDetail(new Stage(), movie);
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @FXML
    void DoTransferAction(ActionEvent event) throws IOException {

        main.showTransferCompanyScene(new Stage(), movie);


    }
}
