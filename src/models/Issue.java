package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jessica on 3/30/2016.
 */
public class Issue implements java.io.Serializable {
    private String title;
    private String path;
    private String seriesTitle;
    private int numPages;
    //private String comicid;

   // public String getComicid(){ return this.comicid }

    public String getTitle(){
        return title;
    }

    public String getSeriesTitle(){
        return seriesTitle;
    }

    public int getNumPages(){
        return numPages;
    }

    public String getPath(){
        return path;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setSeriesTitle(String title){
        this.seriesTitle = title;
    }

    public void setPath(String path){
        this.path = path;
    }

    public void setNumPages(int pages){
        this.numPages = pages;
    }

   // public void setComicid(String comicid) { this.comicid = comicid; }
}
