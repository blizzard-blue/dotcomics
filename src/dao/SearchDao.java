package dao;

import db.db;
import models.Series;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        try{
//            conn = db.getConnection();
//            stmt = conn.prepareStatement("select * from")
        }catch(Exception e){
            e.printStackTrace();
        }
        return series;
    }

    public List<Series> sortByTitleAZ() {
        List<Series> azseries = new ArrayList<Series>();


        return series;
    }

    public List<Series> sortByTitleZA() {
        return series;
    }

    public List<Series> sortByDateMostRecent() {
        try{
//            conn = db.getConnection();
//            stmt = conn.prepareStatement("select * from")
        }catch(Exception e){
            e.printStackTrace();
        }
        return series;
    }

    public List<Series> sortByDateLeastRecent() {
        return series;
    }
}
