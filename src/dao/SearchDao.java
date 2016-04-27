package dao;

import models.Series;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jessica on 4/25/2016.
 */
public class SearchDao {
    private List<Series> series;

    public SearchDao(){
        series = new ArrayList<Series>();
    }

    public List<Series> search(String query){
        if(query == null || query == ""){ // return all series
            return series;
        }
        // narrow down by query
        return series;
    }

    public List<Series> filterByGenre(String[] genres){
        return series;
    }

    public List<Series> sortByTitleAZ() {
        return series;
    }

    public List<Series> sortByTitleZA() {
        return series;
    }

    public List<Series> sortByDateMostRecent() {
        return series;
    }

    public List<Series> sortByDateLeastRecent() {
        return series;
    }
}
