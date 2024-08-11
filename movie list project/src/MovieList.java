import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class MovieList{

    private static final String INPUT_FILE_NAME = "movies.txt";
    private static final String OUTPUT_FILE_NAME = "movies.txt";

    public static Movie movieByTitle(List <Movie> mList, String str)
    {
        for (Movie m : mList) {
            if (m.getTitle().equalsIgnoreCase(str)) {
                return m;
            }
        }
        return null;
    }

    public static List<Movie> moviesByYear(List<Movie> mList, int year)
    {

        List<Movie> movies=new ArrayList<>();

        for (Movie m : mList) {
            if (m.getReleaseYear() == year) {
                movies.add(m);
            }
        }
        return movies;
    }
    public static List<Movie> moviesByGenre(List<Movie> movieList,String str)
    {
        List<Movie> movies=new ArrayList<>();
        for (Movie m : movieList) {
            for (String s : m.getGenre()) {
                if (s.equalsIgnoreCase(str)) {
                    movies.add(m);
                    break;
                }
            }
        }
        return movies;
    }
    public static List<Movie> moviesByCompany(List<Movie> movieList,String str)
    {
        List<Movie> movies=new ArrayList<>();
        for (Movie m : movieList) {
            if (m.getProductionCompany().equalsIgnoreCase(str)) {
                movies.add(m);
            }
        }
        return movies;
    }
    public static List<Movie> moviesByRunTime(List<Movie> movieList,int mn,int mx)
    {
        List<Movie> movies=new ArrayList<>();
        for (Movie m : movieList) {
            if (m.getRunningTime() >= mn && m.getRunningTime() <= mx) {
                movies.add(m);
            }
        }
        return movies;
    }
    public static List<Movie> top10Movies(List<Movie> movieList)
    {
        List<Movie> movies=new ArrayList<>();
        List<Movie> copy = new ArrayList<>(movieList);
        int mx = 0;
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < copy.size(); i++) {
                Movie m = copy.get(i);
                if ((m.getRevenue() - m.getBudget()) >= (copy.get(mx).getRevenue() - copy.get(mx).getBudget())) {
                    mx = i;
                }
            }
            movies.add(copy.get(mx));
            copy.remove(mx);
        }
        return movies;
    }

    public static List<Movie> mostRecentMovies(List<Movie> movieList,String s)
    {
        List<Movie> movies=new ArrayList<>();
        int year = 0;

        for (Movie m : movieList) {
            if (m.getProductionCompany().equalsIgnoreCase(s)) {
                if (m.getReleaseYear() > year)
                    year = m.getReleaseYear();
            }
        }

        for (Movie m : movieList) {
            if (m.getProductionCompany().equalsIgnoreCase(s) && m.getReleaseYear() == year)
                movies.add(m);
        }
        return movies;
    }

    public static List<Movie> maxRevenueMovies(List<Movie> movieList,String str)
    {

        int maxrev =0;
        List<Movie> movies=new ArrayList<>();

        for (Movie m : movieList) {
            if (m.getProductionCompany().equalsIgnoreCase(str)) {


                if (m.getRevenue() > maxrev)
                    maxrev = m.getRevenue();

            }
        }

        for (Movie m : movieList) {

            if (m.getProductionCompany().equalsIgnoreCase(str) && m.getRevenue() == maxrev)
                movies.add(m);

        }
        return movies;

    }

    public static long totalProfit(List<Movie> movieList,String str)
    {
        long profit = 0;
        int found=0;
        for (Movie m : movieList) {
            if (m.getProductionCompany().equalsIgnoreCase(str)) {
                profit += m.getRevenue() - m.getBudget();
                found = 1;

            }
        }
        if(found==1)
            return profit;

        else
            return -1000000000;

    }

    public static HashMap<String, Integer> allCompany(List<Movie> movieList)
    {
        HashMap<String, Integer> map = new HashMap<>();


        for (Movie m : movieList) {
            if (map.containsKey(m.getProductionCompany()))
                map.put(m.getProductionCompany(), map.get(m.getProductionCompany()) + 1);

            else
                map.put(m.getProductionCompany(), 1);
        }

        return map;

    }


    public static void main(String[] args) throws Exception {

        List<Movie> movieList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String[] arrString = line.split(",");
            Movie m = new Movie(arrString);
            movieList.add(m);

        }
        br.close();

        while(true)
        {
            System.out.println();
            System.out.println("Main Menu");
            System.out.println("1) Search Movies");
            System.out.println("2) Search production companies");
            System.out.println("3) Add Movie");
            System.out.println("4) Exit");
            System.out.println();

            Scanner scnint = new Scanner(System.in);


            if(scnint.hasNextInt()) {

                int option = scnint.nextInt();
                if (option == 1) {
                    while (true) {
                        Scanner scnint2 = new Scanner(System.in);
                        System.out.println();
                        System.out.println("Movie Searching Options:");
                        System.out.println("1) By Movie Title");
                        System.out.println("2) By Release Year");
                        System.out.println("3) By Genre:");
                        System.out.println("4) By Production Company");
                        System.out.println("5) By Running Time");
                        System.out.println("6) Top 10 Movies");
                        System.out.println("7) Back to Main Menu");
                        System.out.println();


                        if (scnint2.hasNextInt()) {

                            int opt = scnint2.nextInt();
                            if (opt == 1) {
                                System.out.println("Enter Title of The Movie ");
                                Scanner scnline = new Scanner(System.in);
                                String str = scnline.nextLine();

                                Movie m=movieByTitle(movieList, str);
                                if(m==null) {
                                    System.out.println();
                                    System.out.println("No such movie with this title");
                                }else
                                    m.printInfo();
                            } else if (opt == 2) {
                                System.out.println("Enter Release Year of Movies");
                                int year;
                                while(true) {
                                    Scanner scn12=new Scanner(System.in);
                                    if(scn12.hasNextInt())
                                    {
                                        year = scn12.nextInt();
                                        break;
                                    }
                                    else
                                    {
                                        System.out.println("Enter a valid year");
                                    }
                                }
                                List<Movie> movies=moviesByYear(movieList, year);
                                if(movies.size()==0)
                                {
                                    System.out.println();
                                    System.out.println("No such movie with this release year");
                                    System.out.println();
                                    continue;
                                }
                                for(Movie m:movies)
                                    m.printInfo();

                            } else if (opt == 3) {
                                System.out.println("Enter Genre of Movies");
                                Scanner sc3 = new Scanner(System.in);
                                String str = sc3.nextLine();
                                if(str.equals(""))
                                {
                                    System.out.println();
                                    System.out.println("Enter a valid genre");
                                    System.out.println();
                                    continue;
                                }
                                List<Movie> movies=moviesByGenre(movieList, str);
                                if(movies.size()==0)
                                {
                                    System.out.println();
                                    System.out.println("No such movie with this genre");
                                    System.out.println();
                                    continue;
                                }
                                for(Movie m:movies)
                                    m.printInfo();
                            } else if (opt == 4) {
                                System.out.println("Enter Name of a production company ");
                                Scanner sc4 = new Scanner(System.in);
                                String str = sc4.nextLine();
                                List<Movie> movies=moviesByCompany(movieList, str);
                                if(movies.size()==0)
                                {
                                    System.out.println();
                                    System.out.println("No such movie with this production company");
                                    System.out.println();
                                    continue;
                                }
                                for(Movie m:movies)
                                    m.printInfo();

                            } else if (opt == 5) {
                                int mn;
                                int mx;
                                while(true) {
                                    Scanner sc5 = new Scanner(System.in);

                                    System.out.println("Enter Minimum Running time (in minutes)");
                                    if(sc5.hasNextInt())
                                    {
                                        mn = sc5.nextInt();
                                        System.out.println("Enter Maximum Running Time (in minutes)");
                                        if(sc5.hasNextInt())
                                        {
                                            mx = sc5.nextInt();
                                            break;
                                        }
                                        else {
                                            System.out.println("Wrong input format");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("Wrong input format");
                                    }
                                }
                                List<Movie> movies=moviesByRunTime(movieList,mn,mx);
                                if(movies.size()==0)
                                {
                                    System.out.println();
                                    System.out.println("No such movie with this run time");
                                    System.out.println();
                                    continue;
                                }
                                for(Movie m:movies)
                                    m.printInfo();

                            } else if (opt == 6) {
                                System.out.println("Top 10 movies are ");
                                for (Movie m:top10Movies(movieList))
                                    m.printInfo();

                            } else if (opt == 7) {
                                break;
                            } else {
                                System.out.println("Enter a number Between 1 to 7");
                            }
                        } else {
                            System.out.println();
                            System.out.println("Wrong input format");
                            System.out.println("Enter a number between 1 to 7");
                        }
                    }
                }
                else if (option == 2) {
                    while (true) {
                        System.out.println("Production Company Searching Options:");
                        System.out.println("1) Most Recent Movies");
                        System.out.println("2) Movies with the Maximum Revenue");
                        System.out.println("3) Total Profit");
                        System.out.println("4) List of Production Companies and the Count of their Produced Movies");
                        System.out.println("5) Back to Main Menu");
                        Scanner sc = new Scanner(System.in);
                        if(sc.hasNextInt()) {
                            int op = sc.nextInt();
                            if (op == 1) {
                                System.out.println("Enter Name of the Production Company");
                                Scanner scn21 = new Scanner(System.in);
                                String s = scn21.nextLine();
                                List<Movie> movies = mostRecentMovies(movieList, s);
                                if (movies.size() == 0) {
                                    System.out.println();
                                    System.out.println("No such production company with this name");
                                    System.out.println();
                                    continue;
                                }
                                for (Movie m : movies)
                                    m.printInfo();
                            }
                            else if (op == 2) {
                                System.out.println("Enter Name of the Production Company");
                                Scanner sc22 = new Scanner(System.in);
                                String str = sc22.nextLine();
                                List<Movie> movies = maxRevenueMovies(movieList, str);
                                if (movies.size() == 0) {
                                    System.out.println();
                                    System.out.println("No such production company with this name");
                                    System.out.println();
                                    continue;
                                }
                                for (Movie m : movies)
                                    m.printInfo();
                            }
                            else if (op == 3) {
                                System.out.println("Enter Name of the Production Company");
                                Scanner sc23 = new Scanner(System.in);
                                String str = sc23.nextLine();
                                long p = totalProfit(movieList, str);
                                if (p == -1000000000) {
                                    System.out.println();
                                    System.out.println("No such production company with this name");
                                    System.out.println();
                                    continue;
                                }
                                System.out.println("Total Profit : " + p+" dollars");
                            }
                            else if (op == 4) {
                                for (Map.Entry<String, Integer> m : allCompany(movieList).entrySet())
                                    System.out.println(m.getKey() + " , No of movies : " + m.getValue());
                            }
                            else if (op == 5) {
                                break;
                            }
                            else
                            {
                                System.out.println("Enter a number between 1 to 5");
                            }
                        }
                        else
                        {
                            System.out.println("Wrong input format");
                            System.out.println("Enter a number between 1 to 5");
                        }
                    }
                }
                else if (option == 3) {
                    String[] st = new String[9];
                    while(true) {

                        try {
                            Scanner sc32 = new Scanner(System.in);
                            System.out.println("Enter title of the movie : ");
                            st[0] = sc32.nextLine();
                            if(movieByTitle(movieList, st[0]) != null)
                            {
                                System.out.println("Can't add movie. Movie already exists");
                                System.out.println("Again input from beginning ");
                                continue;
                            }
                            System.out.println("Enter release year of the movie : ");

                            st[1] = sc32.nextLine();
                            Integer.parseInt(st[1]);

                            System.out.println("Enter three genres of the movie (press enter to skip genre one by one): ");
                            st[2] = sc32.nextLine();
                            st[3] = sc32.nextLine();
                            st[4] = sc32.nextLine();

                            System.out.println("Enter Running time of the movie :");
                            st[5] = sc32.nextLine();
                            Integer.parseInt(st[5]);

                            System.out.println("Enter production company of the movie : ");
                            st[6] = sc32.nextLine();

                            System.out.println("Enter budget of the movie : ");
                            st[7] = sc32.nextLine();
                            Integer.parseInt(st[7]);

                            System.out.println("Enter revenue of the movie : ");
                            st[8] = sc32.nextLine();
                            Integer.parseInt(st[8]);

                            System.out.println("Movie successfully added");
                            break;

                        } catch (NumberFormatException e) {
                            System.out.println();
                            System.out.println("wrong input format");
                            System.out.println("Enter again from beginning");
                            System.out.println();
                        }
                    }
                    Movie m = new Movie(st);
                    movieList.add(m);

                }
                else if (option == 4) {
                    break;

                }
                else
                {
                    System.out.println("Enter A Number Between 1 to 4");
                }
            }
            else
            {
                System.out.println("Wrong Input Format");
                System.out.println("Enter a number between 1 to 4");
            }

        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        int listSize = 0;
        while (listSize < movieList.size()) {

            Movie m = movieList.get(listSize);
            String text = m.getInfo();
            listSize++;
            bw.write(text);
            bw.write(System.lineSeparator());
        }
        bw.close();

    }
}