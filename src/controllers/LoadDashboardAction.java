package controllers;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import dao.ComicDao;
import models.Series;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Jessica on 4/2/2016.
 */
public class LoadDashboardAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ComicDao cd = new ComicDao();

        List<Series> series = cd.getAllSeries();

        JSONObject json = new JSONObject();
        JSONArray allseries = new JSONArray();

        for(Series s: series){
            JSONObject jo = new JSONObject();
            jo.put("title", s.getTitle());
            jo.put("author", s.getAuthor());
            jo.put("cover_img", s.getCoverimgpath());
            jo.put("description", s.getDescription());
            jo.put("genre", s.getGenre());
            allseries.put(jo);
        }

        json.put("all_series", allseries);

        PrintWriter pw = response.getWriter();
        pw.print(json.toString());
        pw.close();

        return null;
    }
}
