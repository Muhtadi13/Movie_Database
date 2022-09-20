package sample;

import server.Movie;

import java.util.ArrayList;
import java.util.List;

public class MySearchHelper {
    public List<Movie> moviesByRunTime(int mn, int mx,List<Movie> movieList) {
        List<Movie> movies = new ArrayList<>();
        for (Movie m : movieList) {
            if (m.getRunningTime() >= mn && m.getRunningTime() <= mx) {
                movies.add(m);
            }
        }
        return movies;
    }
    public List<Movie> moviesByGenre( String str,List<Movie> movieList) {
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
    public Movie movieByTitle(String str,List<Movie> movieList) {
        for (Movie m : movieList) {
            if (m.getTitle().equalsIgnoreCase(str)) {
                return m;
            }
        }
        return null;
    }
    public List<Movie> moviesByYear( int year,List<Movie> movieList) {

        List<Movie> movies = new ArrayList<>();

        for (Movie m : movieList) {
            if (m.getReleaseYear() == year) {
                movies.add(m);
            }
        }
        return movies;
    }
}
