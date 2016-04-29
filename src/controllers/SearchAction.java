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
//                for(String genre : genres){
//                    if(genre.equals("Action"))
//                        request.setAttribute("Action", "checked");
//                }
            }

            // sort the results
            if(sortby != null) {
                if (sortby.equals("AZ"))
                    results = sd.sortByTitleAZ();
                else if (sortby.equals("ZA"))
                    results = sd.sortByTitleZA();
                else if (sortby.equals("MostRecent"))
                    results = sd.sortByDateMostRecent();
                else if (sortby.equals("LeastRecent"))
                    results = sd.sortByDateLeastRecent();
            }else{
                results = sd.sortByDateMostRecent();
            }

            // convert result into json
            JSONObject json = new JSONObject();
            JSONArray array = new JSONArray();
            for (Series s : results) {
                JSONObject o = new JSONObject();
                o.put("title", s.getTitle());
                o.put("author", s.getAuthor());
                o.put("img", ("/series/" + s.getTitle() + "/thumbnail.jpg"));
                o.put("description", s.getDescription());
                array.put(o);
            }
            json.put("series", array);

            PrintWriter pw = response.getWriter();
            pw.print(json.toString());
            pw.close();

//            session.removeAttribute("search");
//            session.removeAttribute("genres");
//            session.removeAttribute("sortby");

            return null;
        }

        session.setAttribute("search", request.getParameter("search"));
        session.setAttribute("genres", request.getParameterValues("genres"));
        session.setAttribute("sortby", request.getParameter("sortby"));

        return "search";
    }
}
