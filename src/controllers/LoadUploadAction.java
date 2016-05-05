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
public class LoadUploadAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String series = request.getParameter("series");
        String issue = request.getParameter("issue");
        String loadpages = request.getParameter("loadpage");

        ComicDao cd = new ComicDao();
        if(series != null && !series.equals("false")){
            Series s = cd.getSeries(series);
            System.out.println("Series: " + series);
            String genre = s.getGenre();
            String description = s.getDescription();
            request.setAttribute("genre", genre);
            request.setAttribute("description", description);
        }

        if(loadpages != null){
            JSONObject json = new JSONObject();
            JSONArray pages = new JSONArray();

            int comicid = cd.getIssueId(series, issue);
            List<String> pageurls = cd.getPages(comicid);
            for(String s: pageurls){
                pages.put(s);
            }

            json.put("page_urls", pages);

            PrintWriter pw = response.getWriter();
            pw.print(json.toString());
            pw.close();

            return null;
        } else {
            return "upload";
        }
    }
}
