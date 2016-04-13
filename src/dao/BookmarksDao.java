package dao;

import db.db;
import models.Bookmark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sicaz on 4/11/2016.
 */
public class BookmarksDao {
    private List<Bookmark> bookmarks;
    private List<Integer> seriesids;
    protected Connection conn;
    protected PreparedStatement stmt;
    protected ResultSet rs;

    public BookmarksDao(String username) {
        bookmarks = new ArrayList<Bookmark>();
        seriesids = new ArrayList<Integer>();

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement("select * from User where username = ?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            int userid = 0;
            if(rs.next())
                userid = rs.getInt("userid");

            stmt = conn.prepareStatement("select seriesid from Bookmark where userid = ?");
            stmt.setInt(1, userid);
            rs = stmt.executeQuery();
            while(rs.next()) {
                seriesids.add(rs.getInt("seriesid"));
            }

            for(int seriesid : seriesids) {
                stmt = conn.prepareStatement("select * from Series where seriesid = ?");
                stmt.setInt(1, seriesid);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    addBookmark(username, rs.getString("title"), "/comics?series=" + rs.getString("title"));
                    System.out.println(rs.getString("title"));
                }
            }

            conn.close();
            stmt.close();
            rs.close();
//            addBookmark("jessica", "lalala", "#");
//            addBookmark("jessica", "The Gingerbread Man", "#");
//            addBookmark("jessica", "Sugar Cookies", "#");
//            addBookmark("jessica", "Deadpool", "/comics?series=Deadpool");
//            addBookmark("jessica", "Super Duper Man", "#");
//            addBookmark("jessica", "Water", "#");
//            addBookmark("jessica", "Meh", "#");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Bookmark> getAllBookmarks(){
        return bookmarks;
    }

    public void addBookmark(String username, String seriesTitle, String path){
        Bookmark b = new Bookmark();
        b.setUser(username);
        b.setSeriesTitle(seriesTitle);
        b.setPath(path);
        b.toggleBookmarked();

        bookmarks.add(b);
    }
}
