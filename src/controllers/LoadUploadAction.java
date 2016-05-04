package controllers;

import dao.ComicDao;
import models.Series;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jessica on 4/2/2016.
 */
public class LoadUploadAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String series = request.getParameter("series");
        String issue = request.getParameter("issue");

        ComicDao cd = new ComicDao();
        if(series != null){
            Series s = cd.getSeries(series);
            String genre = s.getGenre();
            String description = s.getDescription();
            request.setAttribute("genre", genre);
            request.setAttribute("description", description);


        }

        return "upload";
    }
}
