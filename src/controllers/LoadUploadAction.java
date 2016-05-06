package controllers;

import com.google.appengine.labs.repackaged.com.google.common.collect.HashMultimap;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import dao.ComicDao;
import models.Series;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

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
            TreeMap pageurls = cd.getPages(comicid);
            Set set = pageurls.entrySet();
            Iterator i = set.iterator();
            while(i.hasNext()){
                Map.Entry me = (Map.Entry)i.next();
                JSONObject jo = new JSONObject();
                jo.put("pagenumber", me.getKey());
                jo.put("url", me.getValue());
                pages.put(jo);
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
