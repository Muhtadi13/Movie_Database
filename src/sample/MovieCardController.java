package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import server.Movie;

public class MovieCardController {


    @FXML
    private ImageView imageView;
    private Main main;
        private Stage stage;
        private Movie movie;
        private String pcCompany;



        @FXML
        private Label movieName;

        @FXML
        private Label genreName;

        @FXML
        private Label revenue;

        @FXML
        private Label runTime;

        @FXML
        private Label budget;

        @FXML
        private Label prodCompany;

        @FXML
        private Label releaseYear;



        public void setMain(Main main) {
            this.main = main;
        }

        public void setStage(Stage stage) {
            this.stage = stage;
        }

        public void initiate( Movie movie) {
            this.movie = movie;

            movieName.setText(movie.getTitle());
            prodCompany.setText(movie.getProductionCompany());
            releaseYear.setText(String.valueOf(movie.getReleaseYear()));
            genreName.setText(movie.getGenrestr());
            runTime.setText(String.valueOf(movie.getRunningTime()));
            releaseYear.setText(String.valueOf(movie.getReleaseYear()));
            budget.setText(String.valueOf(movie.getBudget()));
            revenue.setText(String.valueOf(movie.getRevenue()));

            String str=movie.getImageSourceBackdrop();

            System.out.println(str);

            if(Main.class.getResourceAsStream(str)==null)
               str="/MovieThumbnails/Defaultbackdrop.jpg";

            Image im=new Image(Main.class.getResourceAsStream(str));
            imageView.setImage(im);


        }

    public void onCloseButtonClick(ActionEvent event) {
            stage.close();
    }

    public void setPcCompany(String pcCompany) {
        this.pcCompany = pcCompany;
    }

    public void onTrailerButtonClick(ActionEvent event)  {
            //String s="http://www.google.com";
       try{
           main.showMyWebScene(new Stage(),movie);
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}

