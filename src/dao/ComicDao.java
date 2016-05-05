package dao;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import db.db;
import models.Issue;
import models.Series;
import models.UserAcct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
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
                iss.setTitle(rs.getString("title"));
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
        Series s = getSeries(seriesTitle);
        System.out.println("Series title: " + s.getTitle());
        Issue issue = s.getIssue(issueTitle);
        if(issue != null)
            return issue;

        return null;
    }

    public int getIssueId(String seriesTitle, String issueTitle){
        int id = 0;

        try{
            conn = db.getConnection();
            stmt = conn.prepareStatement("select comicid from ComicBook where title=? and series=?");
            stmt.setString(1, issueTitle);
            stmt.setString(2, seriesTitle);
            rs = stmt.executeQuery();

            while(rs.next()){
                id = rs.getInt("comicid");
            }
            conn.close();
            stmt.close();
            rs.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return id;
    }


    public Series getSeries(String title){
        Series s = null;
        for(int i=0; i<series.size(); i++){
            s = series.get(i);
            if(s.getTitle().equals(title)){
                return s;
            }
        }

        return null;
    }

    public void addSeries(String title, String email, String description, String genre, String cipath){
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        UserDao ud = new UserDao();
        UserAcct u = ud.getUser(email);
        String username = u.getUsername();

        try{
            conn = db.getConnection();

            stmt = conn.prepareStatement("insert into Series(title, author, description, genre, coverimgpath) values (?, ?, ?, ?, ?)");
            stmt.setString(1, title);
            stmt.setString(2, username);
            stmt.setString(3, description);
            stmt.setString(4, genre);
            stmt.setString(5, cipath);
            stmt.executeUpdate();

            conn.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        Series s = new Series();
        s.setTitle(title);
        s.setAuthor(username);
        s.setDescription(description);
        s.setGenre(genre);
        s.setCoverimgpath(cipath);
        series.add(s);
    }

    public void addIssue(String title, String email, String seriesTitle){
        UserDao ud = new UserDao();
        UserAcct u = ud.getUser(email);
        String username = u.getUsername();

        try{
            conn = db.getConnection();

            stmt = conn.prepareStatement("insert into ComicBook(title, author, series) values (?, ?, ?)");
            stmt.setString(1, title);
            stmt.setString(2, username);
            stmt.setString(3, seriesTitle);
            stmt.executeUpdate();

            conn.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        Series s = getSeries(seriesTitle);
        Issue i = new Issue();
        i.setTitle(title);
        i.setSeriesTitle(seriesTitle);
        s.addIssue(title, i);

    }

    public void addPage(int comicid, int pagenum, String pagepath){
        try{
            conn = db.getConnection();

            stmt = conn.prepareStatement("insert into Page(comicid, pagenumber, filepath) values (?, ?, ?)");
            stmt.setInt(1, comicid);
            stmt.setInt(2, pagenum);
            stmt.setString(3, pagepath);
            stmt.executeUpdate();

            conn.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public HashMap getPages(int comicid){
        HashMap pages = new HashMap();

        try{
            conn = db.getConnection();
            stmt = conn.prepareStatement("select filepath from Page where comicid=?");
            stmt.setInt(1, comicid);
            rs = stmt.executeQuery();

            while(rs.next()){
                //pages.add(rs.getString("filepath"));
            }
            conn.close();
            stmt.close();
            rs.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return pages;
    }

    public int getNextIssuePage(int comicid){
        int page = -1;

        try{
            conn = db.getConnection();

            stmt = conn.prepareStatement("select * from Page where comicid=?");
            stmt.setInt(1, comicid);
            rs = stmt.executeQuery();

            while(rs.next()){
                int num = rs.getInt("pagenumber");
                if(num > page)
                    page = num;
            }

            rs.close();
            conn.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return page;
    }
}
