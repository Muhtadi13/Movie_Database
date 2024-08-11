
public class Movie
{
    private String Title;

    private int ReleaseYear;

    private String[] Genre =new String[3];

    private int RunningTime;

    private String ProductionCompany;

    private int Budget;

    private int Revenue;

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



}
