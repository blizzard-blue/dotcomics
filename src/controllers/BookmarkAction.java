package controllers;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import dao.BookmarksDao;
import dao.UserDao;
import models.Bookmark;
import models.Series;
import models.UserAcct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by John on 4/25/2016.
 */
public class BookmarkAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Series s = (Series)request.getSession().getAttribute("series");
        UserAcct u = (UserAcct) request.getSession().getAttribute("user");
        BookmarksDao bd = new BookmarksDao(u.getUsername()); //use BkDao without constructor
        List<Bookmark> bookmarks = bd.getAllBookmarks();

        bd.bookmarkSeries(u.getUserid(), s.getTitle()); //bookmarks current series, backend does check if it alrdy exists

        boolean bookmarkExists = false;
        for(Bookmark b : bookmarks){
            if(b.getSeriesTitle().equals(s.getTitle()))
                bookmarkExists = true;
        }

        if(!bookmarkExists) {


            JSONObject json = new JSONObject();
            JSONObject bm = new JSONObject();

            String path = "/comic?series=" + s.getTitle();
            bm.put("seriesTitle", s.getTitle());
            bm.put("path", path);
            json.put("newbookmark", bm);
            System.out.println("new bookmark into jsonobj " + s.getTitle());

            PrintWriter pw = response.getWriter();
            pw.print(json.toString());
            pw.close();

        }
        return null;

    }
}

