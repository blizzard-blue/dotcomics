package controllers;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import dao.SearchDao;
import models.Series;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Jessica on 4/25/2016.
 */
public class SearchAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(true);

        if("GET".equals(request.getMethod())){
            String query = (String) session.getAttribute("search");
            String[] genres = (String[]) session.getAttribute("genres");
            String sortby = (String) session.getAttribute("sortby");
            System.out.println(sortby);

            SearchDao sd = new SearchDao();

            // narrow down search results by query
            List<Series> results = sd.search(query);

            // if genre filters are chosen, filter results by genre
            if(genres != null){
                results = sd.filterByGenre(genres);
            }

            // sort the results
            if(sortby == null)
                results = sd.sortByTitleAZ();
            else{
                if(sortby.equals("sortByTitleAZ"))
                    results = sd.sortByTitleAZ();
                else if(sortby.equals("sortByTitleZA"))
                    results = sd.sortByTitleZA();
                else if(sortby.equals("sortByDateMostRecent"))
                    results = sd.sortByDateMostRecent();
                else if(sortby.equals("sortByDateLeastRecent"))
                    results = sd.sortByDateLeastRecent();
            }

            // convert result into json
            JSONObject json = new JSONObject();
            JSONArray array = new JSONArray();
            for (Series s : results) {
                JSONObject o = new JSONObject();
                o.put("title", s.getTitle());
                o.put("author", s.getAuthor());
                o.put("img", s.getCoverImg());
                o.put("description", s.getDescription());
                array.put(o);
            }
            json.put("series", array);

            PrintWriter pw = response.getWriter();
            pw.print(json.toString());
            pw.close();

            session.removeAttribute("search");
            session.removeAttribute("genres");
            session.removeAttribute("sortby");

            return null;
        }

        session.setAttribute("search", request.getParameter("search"));
        session.setAttribute("genres", request.getParameterValues("genres"));
        session.setAttribute("sortby", request.getParameter("sortby"));

        return "search";
    }
}
