package server;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MovieList {

    private static final String INPUT_FILE_NAME = "movies.txt";
    private static final String OUTPUT_FILE_NAME = "movies.txt";
    private static List<Movie> mvList=new ArrayList<>();

    public static List<Movie> getMovieList() {
        return mvList;
    }

    public static void setMovieList(List<Movie> movieList) {
        MovieList.mvList = movieList;
    }

    public static Movie movieByTitle(String str) {
        for (Movie m : mvList) {
            if (m.getTitle().equalsIgnoreCase(str)) {
                return m;
            }
        }
        return null;
    }

    public static List<Movie> moviesByYear( int year,List<Movie> movieList) {

        List<Movie> movies = new ArrayList<>();

        for (Movie m : movieList) {
            if (m.getReleaseYear() == year) {
                movies.add(m);
            }
        }
        return movies;
    }

    public static List<Movie> moviesByGenre( String str,List<Movie> movieList) {
        List<Movie> movies = new ArrayList<>();
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

    public static List<Movie> moviesByCompany(String str,List<Movie> movieList) {
        List<Movie> movies = new ArrayList<>();
        for (Movie m : movieList) {
            if (m.getProductionCompany().equalsIgnoreCase(str)) {
                movies.add(m);
            }
        }
        return movies;
    }

    public static List<Movie> moviesByRunTime(int mn, int mx,List<Movie> movieList) {
        List<Movie> movies = new ArrayList<>();
        for (Movie m : movieList) {
            if (m.getRunningTime() >= mn && m.getRunningTime() <= mx) {
                movies.add(m);
            }
        }
        return movies;
    }

    public static List<Movie> top10Movies(List<Movie> movieList) {
        List<Movie> movies = new ArrayList<>();
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

    public static List<Movie> searchPCMovies(String s,List<Movie> movieList) {

        List<Movie> searchList=new ArrayList<>();
        for (Movie m : movieList) {
            if (m.getProductionCompany().equalsIgnoreCase(s))
                searchList.add(m);
        }
        return searchList;


    }

    public static List<Movie> mostRecentMovies( String s,List<Movie> movieList) {

        List<Movie> movies = new ArrayList<>();
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

    public static List<Movie> maxRevenueMovies(String str,List<Movie> movieList) {

        int maxrev = 0;
        List<Movie> movies = new ArrayList<>();

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

    public static long totalProfit(String str,List<Movie> movieList) {
        long profit = 0;
        int found = 0;
        for (Movie m : movieList) {
            if (m.getProductionCompany().equalsIgnoreCase(str)) {
                profit += m.getRevenue() - m.getBudget();
                found = 1;

            }
        }
        if (found == 1)
            return profit;

        else
            return -1000000000;

    }

    public static HashMap<String, Integer> allCompany(List<Movie> movieList) {
        HashMap<String, Integer> map = new HashMap<>();


        for (Movie m : movieList) {
            if (map.containsKey(m.getProductionCompany()))
                map.put(m.getProductionCompany(), map.get(m.getProductionCompany()) + 1);

            else
                map.put(m.getProductionCompany(), 1);
        }

        return map;

    }


    public static void read(List<Movie> movieList) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String[] arrString = line.split(",");
            Movie m = new Movie(arrString);
            String str = m.getTitle();
            str = str.replaceAll("[^a-zA-Z0-9]", "");
            m.setImageSourcePoster("/MovieThumbnails/" + str + "poster.jpg");
            m.setImageSourceBackdrop("/MovieThumbnails/" + str + "backdrop.jpg");
            movieList.add(m);

        }
        br.close();

    }
    public static void readtrailer(List<Movie> movieList) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("trailers.txt"));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String[] arrString = line.split(",");
           for(int i=0;i<movieList.size();i++)
           {
               if(movieList.get(i).getTitle().equalsIgnoreCase(arrString[0]))
               {
                   movieList.get(i).setTrailer(arrString[1]);
                   break;
               }

           }
        }
        br.close();

    }
    public static void write(List<Movie> movieList) throws IOException {
        //movieList = new ArrayList<>();
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

