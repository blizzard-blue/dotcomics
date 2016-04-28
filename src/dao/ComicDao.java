package dao;

import db.db;
import models.Issue;
import models.Series;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jessica on 3/30/2016.
 */
public class ComicDao {
    List<Series> series;
    protected Connection conn;
    protected PreparedStatement stmt;
    protected ResultSet rs;

    public ComicDao(){
        series = new ArrayList<Series>();

        try{
            conn = db.getConnection();
            stmt = conn.prepareStatement("select * from Series");
            rs = stmt.executeQuery();
            Series s;
            Issue iss;
            int i = 0;
            while(rs.next()){
                s = new Series();
                s.setTitle(rs.getString("title"));
                s.setAuthor(rs.getString("author"));
                s.setDescription(rs.getString("description"));
                s.setGenre(rs.getString("genre"));
                series.add(i, s);
                i++;
            }

            stmt = conn.prepareStatement("select * from ComicBook");
            rs = stmt.executeQuery();

            while(rs.next()){
                iss = new Issue();
                iss.setTitle(Integer.toString(rs.getInt("issue")));
                iss.setSeriesTitle(rs.getString("series"));
                iss.setNumPages(rs.getInt("pages"));
                iss.setPath("/series" + "/" + iss.getSeriesTitle() + "/" + iss.getTitle());
                s = getSeries(iss.getSeriesTitle());
                s.addIssue(iss.getTitle(), iss);
            }
            conn.close();
            stmt.close();
            rs.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Series> getAllSeries(){
        return series;
    }

    public Issue getIssue(String seriesTitle, String issueTitle){
        Issue issue = null;
        for(int i=0; i<series.size(); i++){
            Series s = series.get(i);
            if(s.getTitle().equals(seriesTitle)){
                issue = s.getIssue(issueTitle);
                break;
            }
        }

        return issue;
    }

    public List<Issue> getIssues(String series){
        List<Issue> issues = new ArrayList<Issue>();

        return issues;
    }


    public Series getSeries(String title){
        Series s = null;
        for(int i=0; i<series.size(); i++){
            s = series.get(i);
            if(s.getTitle().equals(title)){
                return s;
    }
}

        return s;
    }
}
