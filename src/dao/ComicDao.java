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
//        Series s1 = new Series();
//        s1.setTitle("Deadpool");
//        Issue i1 = new Issue();
//        i1.setTitle("1"); i1.setPath("/series/Deadpool/1"); i1.setSeriesTitle(s1.getTitle()); i1.setNumPages(5);
//        s1.addIssue("1", i1);
//        Issue i2 = new Issue();
//        i2.setTitle("2"); i2.setPath("/series/Deadpool/2"); i2.setSeriesTitle(s1.getTitle()); i2.setNumPages(5);
//        s1.addIssue("2", i2);
//        series.add(0, s1);
//
//        Series s2 = new Series();
//        s2.setTitle("Guardians of the Galaxy");
//        Issue i3 = new Issue();
//        i3.setTitle("1"); i3.setPath("/series/Guardians%20of%20the%20Galaxy/1"); i3.setSeriesTitle(s2.getTitle()); i3.setNumPages(2);
//        s2.addIssue("1", i3);
//        Issue i3_1 = new Issue();
//        i3_1.setTitle("2"); i3_1.setPath("/series/Guardians%20of%20the%20Galaxy/2"); i3_1.setSeriesTitle(s2.getTitle()); i3_1.setNumPages(4);
//        s2.addIssue("2", i3_1);
//        series.add(1, s2);
//
//        Series s3 = new Series();
//        s3.setTitle("Judge Dredd");
//        Issue i4 = new Issue();
//        i4.setTitle("1"); i4.setPath("/series/Judge%20Dredd/1"); i4.setSeriesTitle(s3.getTitle()); i4.setNumPages(1);
//        s3.addIssue("1", i4);
//        series.add(2, s3);
//
//        Series s4 = new Series();
//        s4.setTitle("The Avengers");
//        Issue i5 = new Issue();
//        i5.setTitle("1"); i5.setPath("/series/The%20Avengers/1"); i5.setSeriesTitle(s4.getTitle()); i5.setNumPages(1);
//        s4.addIssue("1", i5);
//        series.add(3, s4);
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
