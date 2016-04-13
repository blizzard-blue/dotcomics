package controllers;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import dao.BookmarksDao;
import models.Bookmark;
import models.UserAcct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Jessica on 4/2/2016.
 */
public class GetBookmarksAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserAcct u = (UserAcct) request.getSession(true).getAttribute("user");
        BookmarksDao bd = new BookmarksDao(u.getUsername());
        List<Bookmark> bookmarks = bd.getAllBookmarks();

        JSONObject json = new JSONObject();
        JSONObject bm =  new JSONObject();
        JSONArray bmarray = new JSONArray();

        for(int i=0; i<bookmarks.size(); i++){
            Bookmark b = bookmarks.get(i);
            if(b.isBookmarked()){
                JSONObject bm2 =  new JSONObject();
                bm2.put("seriesTitle", b.getSeriesTitle());
                bm2.put("path", b.getPath());
                bmarray.put(bm2);

            }
        }

        bm.put("bookmarks", bmarray);
        json.put("bookmarks_obj", bm);

        PrintWriter pw = response.getWriter();
        pw.print(json.toString());
        pw.close();

        return null;
    }
}
