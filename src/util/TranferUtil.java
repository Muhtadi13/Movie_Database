package util;

import java.io.Serializable;

public class TranferUtil implements Serializable {
    Object movie;

    public String getNewowner() {
        return newowner;
    }

    public void setNewowner(String newowner) {
        this.newowner = newowner;
    }

    public String getOldowner() {
        return oldowner;
    }

    public void setOldowner(String oldowner) {
        this.oldowner = oldowner;
    }

    String newowner;
    String oldowner;

    public TranferUtil(Object movie,String newowner,String oldowner) {
        this.movie = movie;
        this.newowner = newowner;
        this.oldowner=oldowner;
    }

    public Object getMovie() {
        return movie;
    }

    public void setMovie(Object movie) {
        this.movie = movie;
    }


}


