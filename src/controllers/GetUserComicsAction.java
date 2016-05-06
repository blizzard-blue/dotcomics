package controllers;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import dao.ComicDao;
import models.Series;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Jessica on 5/5/2016.
 */
public class GetUserComicsAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(true);
        String author = request.getParameter("author");

        ComicDao cd = new ComicDao();
        List<Series> series = cd.getAuthorSeries(author);

        JSONObject json = new JSONObject();
        JSONArray authorseries = new JSONArray();

        for(Series s: series){
            JSONObject jo = new JSONObject();
            jo.put("title", s.getTitle());
            jo.put("author", s.getAuthor());
            jo.put("description", s.getDescription());
            jo.put("cover_img", s.getCoverimgpath());
            authorseries.put(jo);
        }

        json.put("author_series", authorseries);

        PrintWriter pw = response.getWriter();
        pw.print(json.toString());
        pw.close();

        return null;
    }
}
