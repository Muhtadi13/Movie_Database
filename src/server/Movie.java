package server;

import java.io.Serializable;
import java.util.Arrays;

public class Movie implements Serializable
{


    private String Title;

    private int ReleaseYear;

    private String[] Genre =new String[3];

    private int RunningTime;

    private String ProductionCompany;

    private int Budget;

    private int Revenue;

    private String imageSourcePoster;

    private String imageSourceBackdrop;

    private String trailer;

    public Movie(String[] strings) {
        Title = strings[0];
        ReleaseYear = Integer.parseInt(strings[1]);

        int j;
        for(j=2;j<5;j++)
            Genre[j-2] =strings[j];

        RunningTime = Integer.parseInt(strings[5]);
        ProductionCompany =strings[6];
        Budget = Integer.parseInt(strings[7]);
        Revenue = Integer.parseInt(strings[8]);
        imageSourcePoster="/MovieThumbnails/Defaultposter.jpg";
        imageSourceBackdrop="/MovieThumbnails/Defaultbackdrop.jpg";
        trailer="https://www.google.com";
    }

    public Movie() {

    }

    public String getTitle() {
        return Title;
    }

    public int getReleaseYear() {
        return ReleaseYear;
    }


    public String[] getGenre() {
        return Genre;
    }

    public String getGenrestr()
    {
        return Arrays.toString(Genre);
    }


    public int getRunningTime() {
        return RunningTime;
    }


    public String getProductionCompany() {
        return ProductionCompany;
    }


    public int getBudget() {
        return Budget;
    }


    public int getRevenue() {
        return Revenue;
    }


    public void printInfo() {
        System.out.println("Name of the Movie :"+Title);
        System.out.println("The Movie was released in "+ReleaseYear);
        System.out.print("Genres of the Movie : ");
        for(String s:Genre)
        {
            System.out.print(s+" ");

        }
        System.out.println();
        System.out.println("running time of the Movie :"+RunningTime+" minutes");
        System.out.println("Name of the Production Company of the Movie :"+ProductionCompany);
        System.out.println("Budget of the Movie :"+Budget+" dollars");
        System.out.println("Revenue from the Movie :"+Revenue+" dollars");
        System.out.println();
        System.out.println();
    }

    public String getInfo(){

        StringBuffer sb=new StringBuffer();
        sb.append(Title).append(",").append(ReleaseYear).append(",");
        for(String s:Genre)
        {
            sb.append(s).append(",");
        }
        sb.append(RunningTime).append(",").append(ProductionCompany).append(",").append(Budget).append(",").append(Revenue);
        return sb.toString();


    }
    public void setTitle(String title) {
        Title = title;
    }

    public void setReleaseYear(int releaseYear) {
        ReleaseYear = releaseYear;
    }

    public void setGenre(String[] genre) {
        Genre = genre;
    }

    public void setRunningTime(int runningTime) {
        RunningTime = runningTime;
    }

    public void setProductionCompany(String productionCompany) {
        ProductionCompany = productionCompany;
    }

    public void setBudget(int budget) {
        Budget = budget;
    }

    public void setRevenue(int revenue) {
        Revenue = revenue;
    }




    public String getImageSourcePoster() {
        return imageSourcePoster;
    }

    public void setImageSourcePoster(String imageSourcePoster) {
        this.imageSourcePoster = imageSourcePoster;
    }

    public String getImageSourceBackdrop() {
        return imageSourceBackdrop;
    }

    public void setImageSourceBackdrop(String imageSourceBackdrop) {
        this.imageSourceBackdrop = imageSourceBackdrop;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
}
