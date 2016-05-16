package controllers;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import dao.ComicDao;
import models.Series;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jessica on 4/2/2016.
 */
public class LoadDashboardAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ComicDao cd = new ComicDao();

        List<Series> series = cd.getAllSeries();

        List<Series> ActionSeries = cd.filterByGenre("action");
        List<Series> HorrorSeries = cd.filterByGenre("horror");
        List<Series> SciFiSeries = cd.filterByGenre("scifi");
        List<Series> RomanceSeries = cd.filterByGenre("romance");
        List<Series> SuperHeroSeries = cd.filterByGenre("superhero");
        List<Series> ComedySeries = cd.filterByGenre("comedy");
        List<Series> FeaturedSeries = new ArrayList<Series>();

        JSONObject json = new JSONObject();
        JSONArray action = new JSONArray();
        JSONArray horror = new JSONArray();
        JSONArray scifi = new JSONArray();
        JSONArray romance = new JSONArray();
        JSONArray superhero = new JSONArray();
        JSONArray comedy = new JSONArray();
        JSONArray featured = new JSONArray();

        FeaturedSeries.add(cd.getSeries("DEERMAN"));
        FeaturedSeries.add(cd.getSeries("Dankman"));
        FeaturedSeries.add(cd.getSeries("Kenopsia"));


        for(Series s: FeaturedSeries){
            JSONObject jo = new JSONObject();
            jo.put("title", s.getTitle());
            jo.put("author", s.getAuthor());
            jo.put("cover_img", s.getCoverimgpath());
            jo.put("description", s.getDescription());
            jo.put("genre", s.getGenre());
            featured.put(jo);
        }

        json.put("featured_series" , featured);

        for(Series s: ActionSeries){
            JSONObject jo = new JSONObject();
            jo.put("title", s.getTitle());
            jo.put("author", s.getAuthor());
            jo.put("cover_img", s.getCoverimgpath());
            jo.put("description", s.getDescription());
            jo.put("genre", s.getGenre());
            action.put(jo);
        }

        json.put("action_series", action);

        for(Series s: HorrorSeries){
            JSONObject jo = new JSONObject();
            jo.put("title", s.getTitle());
            jo.put("author", s.getAuthor());
            jo.put("cover_img", s.getCoverimgpath());
            jo.put("description", s.getDescription());
            jo.put("genre", s.getGenre());
            horror.put(jo);
        }

        json.put("horror_series", horror);

        for(Series s: SciFiSeries){
            JSONObject jo = new JSONObject();
            jo.put("title", s.getTitle());
            jo.put("author", s.getAuthor());
            jo.put("cover_img", s.getCoverimgpath());
            jo.put("description", s.getDescription());
            jo.put("genre", s.getGenre());
            scifi.put(jo);
        }

        json.put("scifi_series", scifi);

        for(Series s: RomanceSeries){
            JSONObject jo = new JSONObject();
            jo.put("title", s.getTitle());
            jo.put("author", s.getAuthor());
            jo.put("cover_img", s.getCoverimgpath());
            jo.put("description", s.getDescription());
            jo.put("genre", s.getGenre());
            romance.put(jo);
        }

        json.put("romance_series", romance);

        for(Series s: SuperHeroSeries){
            JSONObject jo = new JSONObject();
            jo.put("title", s.getTitle());
            jo.put("author", s.getAuthor());
            jo.put("cover_img", s.getCoverimgpath());
            jo.put("description", s.getDescription());
            jo.put("genre", s.getGenre());
            superhero.put(jo);
        }

        json.put("superhero_series", superhero);

        for(Series s: ComedySeries){
            JSONObject jo = new JSONObject();
            jo.put("title", s.getTitle());
            jo.put("author", s.getAuthor());
            jo.put("cover_img", s.getCoverimgpath());
            jo.put("description", s.getDescription());
            jo.put("genre", s.getGenre());
            comedy.put(jo);
        }

        json.put("comedy_series", comedy);

        PrintWriter pw = response.getWriter();
        pw.print(json.toString());
        pw.close();



        return null;
    }
}
