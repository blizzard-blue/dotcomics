package dao;

import models.Bookmark;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sicaz on 4/11/2016.
 */
public class BookmarksDao {
    private List<Bookmark> bookmarks;

    public BookmarksDao(){
        bookmarks = new ArrayList<Bookmark>();

        addBookmark("jessica", "lalala", "#");
        addBookmark("jessica", "The Gingerbread Man", "#");
        addBookmark("jessica", "Sugar Cookies", "#");
        addBookmark("jessica", "Deadpool", "/comics?series=Deadpool");
        addBookmark("jessica", "Super Duper Man", "#");
        addBookmark("jessica", "Water", "#");
        addBookmark("jessica", "Meh", "#");
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
