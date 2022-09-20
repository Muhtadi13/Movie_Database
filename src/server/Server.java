package server;

import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class Server {

    private ServerSocket serverSocket;
    public HashMap<String, String> userMap;
    public static HashMap<String, NetworkUtil> aliveSocket=new HashMap<>();

    private List<Movie> serverMovieList=new ArrayList<>();

    Server() throws IOException {
        userMap = new HashMap<>();
        MovieList.read(serverMovieList);
        MovieList.readtrailer(serverMovieList);

        for(Movie m:serverMovieList)
        {
            userMap.put(m.getProductionCompany().toLowerCase(Locale.ROOT),"p");

        }
        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
        MovieList.write(serverMovieList);
    }

    public void serve(Socket clientSocket) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThreadServer(userMap, networkUtil,serverMovieList);
    }

    public static void main(String[] args) throws IOException {
        new Server();

    }
}
