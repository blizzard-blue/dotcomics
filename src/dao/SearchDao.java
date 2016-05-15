package dao;

import db.db;
import models.Series;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * Created by Jessica on 4/25/2016.
 */
public class SearchDao {
    private List<Series> series;

    public SearchDao(){
        series = new ArrayList<Series>();
    }

    protected Connection conn;
    protected PreparedStatement stmt;
    protected ResultSet rs;

    public List<Series> search(String query){

        try{
            conn = db.getConnection();

            if(query == null || query == "") {
                // return all series
                stmt = conn.prepareStatement("select * from Series");
                rs = stmt.executeQuery();

            }else{
                //tries to match query with author or title column in Series
                stmt = conn.prepareStatement("select * from Series where title like ? or author like ?");
                stmt.setString(1, "%" + query + "%");
                stmt.setString(2, "%" + query + "%");
                rs = stmt.executeQuery();

            }
            while(rs.next()){
                Series s = new Series();
                s.setTitle(rs.getString("title"));
                s.setAuthor(rs.getString("author"));
                String desc = rs.getString("description");
                s.setDescription(desc);
                s.setGenre(rs.getString("genre"));
                s.setCoverimgpath(rs.getString("coverimgpath"));
                series.add(s);
            }

            rs.close();
            conn.close();
            stmt.close();
            return series;

        }catch(Exception e){
            e.printStackTrace();
        }
        return series;
    }

    public List<Series> filterByGenre(String[] genres){
        List<Series> filteredseries = new ArrayList<Series>();

        for(Series s : series){
            for(String genre : genres){
                if(genre.equalsIgnoreCase(s.getGenre()))
                    filteredseries.add(s);
            }
        }

        series = filteredseries;
        return series;
    }

    public List<Series> sortByTitleAZ() {
        List<Series> azseries = new ArrayList<Series>();
        List<String> titles = new ArrayList<String>();

        for(Series s : series){
            titles.add(s.getTitle());
        }

        Collections.sort(titles);

        for(String title : titles){
            for(Series s : series){
                if(title.equals(s.getTitle()))
                    azseries.add(s);
            }
        }

        series = azseries;
        return series;
    }

    public List<Series> sortByTitleZA() {
        List<Series> zaseries = new ArrayList<Series>();
        List<String> titles = new ArrayList<String>();
        List<String> zatitles = new ArrayList<String>();

        for(Series s : series){
            titles.add(s.getTitle());
        }

        Collections.sort(titles, Collections.<String>reverseOrder());



        for(String title : titles){
            for(Series s : series){
                if(title.equals(s.getTitle()))
                    zaseries.add(s);
            }
        }

        series = zaseries;
        return series;
    }

    public List<Series> sortByDateMostRecent() {
        List<Series> filteredseries = new ArrayList<Series>();

        try{
            conn = db.getConnection();
            stmt = conn.prepareStatement("select * from Series order by seriesid desc");
            rs = stmt.executeQuery();

            while(rs.next()){
                for(Series s : series){
                    if(s.getTitle().equals(rs.getString("title")))
                        filteredseries.add(s);
                }
            }

            series = filteredseries;
            rs.close();
            stmt.close();
            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return series;
    }

    public List<Series> sortByDateLeastRecent() {
        List<Series> filteredseries = new ArrayList<Series>();

        try{
            conn = db.getConnection();
            stmt = conn.prepareStatement("select * from Series order by seriesid asc");
            rs = stmt.executeQuery();

            while(rs.next()){
                for(Series s : series){
                    if(s.getTitle().equals(rs.getString("title")))
                        filteredseries.add(s);
                }
            }

            series = filteredseries;
            rs.close();
            stmt.close();
            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return series;
    }
}
