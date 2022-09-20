package server;

import util.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    public HashMap<String, String> userMap;


    private List<Movie>readMovieList;
    public String capitalizeWord(String str){
        String words[]=str.split("\\s");
        String cWord="";
        for(String w:words){
            String first=w.substring(0,1);
            String after=w.substring(1);
            cWord+=first.toUpperCase()+after.toLowerCase()+" ";
        }
        return cWord.trim();
    }

    public ReadThreadServer(HashMap<String, String> map, NetworkUtil networkUtil,List<Movie>serverMovieList) throws IOException {
        this.userMap = map;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        readMovieList=serverMovieList;
        thr.start();
    }

    public void run() {

        try {
            while (true) {
                Object o = networkUtil.read();

                //MovieList.setMovieList(new ArrayList<>());

                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        String password = userMap.get(loginDTO.getUserName().toLowerCase(Locale.ROOT));
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        networkUtil.write(loginDTO);
                    }

                    else if(o instanceof InfoUtil){

                        InfoUtil iu = (InfoUtil) o;
                        String str = (String) iu.getInfo();

                        List<Movie> mlist ;
                        Server.aliveSocket.put(str.toLowerCase(Locale.ROOT),networkUtil);


                        if(iu.getInstruction().equals("searchpcmovies"))

                        {
                            mlist=MovieList.moviesByCompany(str,readMovieList);
                            iu.setInfo(mlist);
                            networkUtil.write(iu);
                        }

                        else if(iu.getInstruction().equals("totalprofit"))
                        {
                            Long profit=MovieList.totalProfit(str,readMovieList);
                            iu.setInfo(profit);
                            networkUtil.write(iu);
                        }
                        else if(iu.getInstruction().equals("mostrecent"))
                        {
                            mlist=MovieList.mostRecentMovies(str,readMovieList);
                            iu.setInfo(mlist);
                            networkUtil.write(iu);
                        }
                        else if(iu.getInstruction().equals("maxrevenue"))
                        {
                            mlist=MovieList.maxRevenueMovies(str,readMovieList);
                            iu.setInfo(mlist);
                            networkUtil.write(iu);
                        }



                    }

                    else if(o instanceof TranferUtil)
                    {


                        TranferUtil tu=(TranferUtil) o;
                        Movie movie=(Movie) tu.getMovie();
                        String newowner=(String) tu.getNewowner();
                        newowner=capitalizeWord(newowner);
                        String oldowner=(String) tu.getOldowner();
                        oldowner=capitalizeWord(oldowner);
                        Server.aliveSocket.put(oldowner.toLowerCase(Locale.ROOT),networkUtil);

                        for(int i=0;i<readMovieList.size();i++)
                        {
                            if(readMovieList.get(i).getTitle().equals(movie.getTitle()))
                            {
                                readMovieList.get(i).setProductionCompany(newowner);
                                break;
                            }

                        }


                        //tu.setOwner(movie.getProductionCompany());
                        movie.setProductionCompany(newowner);

                        List<Movie> mvlist;
                        mvlist=MovieList.moviesByCompany(oldowner,readMovieList);

                        tu.setMovie(mvlist);
                        networkUtil.write(tu);

                        if(Server.aliveSocket.get(newowner.toLowerCase(Locale.ROOT))!=null)
                        {
                            mvlist.clear();
                            mvlist=MovieList.moviesByCompany(newowner,readMovieList);
                            tu.setMovie(mvlist);
                            Server.aliveSocket.get(newowner.toLowerCase()).write(tu);
                        }
                        MovieList.write(readMovieList);
                    }

                    else if(o instanceof AdderUtil)
                    {

                        AdderUtil au=(AdderUtil) o;
                        Movie mv=(Movie) au.getData();
                        String name=au.getProdCompany();
                        name=capitalizeWord(name);
                        mv.setProductionCompany(name);
                        readMovieList.add(mv);

                        List<Movie> mvlist= MovieList.moviesByCompany(name,readMovieList);

                        au.setData(mvlist);

                        networkUtil.write(au);

                        MovieList.write(readMovieList);


                    }
                    else if(o instanceof InactiveDTO)
                    {
                        String s=((InactiveDTO)o).getUserName().toLowerCase();
                        Server.aliveSocket.remove(s);

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
                //MovieList.write();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



