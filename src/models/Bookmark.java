package models;

/**
 * Created by sicaz on 4/11/2016.
 */
public class Bookmark implements java.io.Serializable {
    private String user;
    private String seriesTitle;
    private boolean bookmarked;
    private String path;

    public String getUser(){
        return user;
    }

    public String getSeriesTitle(){
        return seriesTitle;
    }

    public String getPath(){
        return path;
    }

    public boolean isBookmarked(){
        return bookmarked;
    }

    public void setUser(String username){
        this.user = username;
    }

    public void setPath(String path){
        this.path = path;
    }

    public void setSeriesTitle(String seriesTitle){
        this.seriesTitle = seriesTitle;
    }

    public void toggleBookmarked(){
        if(bookmarked)
            bookmarked = false;
        else
            bookmarked = true;
    }
}
