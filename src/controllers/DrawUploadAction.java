package controllers;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.common.util.Base64;
import dao.ComicDao;
import models.Issue;
import models.Series;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.channels.Channels;

/**
 * Created by Jessica on 5/15/2016.
 */
public class DrawUploadAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String series = request.getParameter("series");
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String description = request.getParameter("description");
        String url = request.getParameter("url");

        ComicDao cd = new ComicDao();
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        String email = user.getEmail();

        Series s = cd.getSeries(series);
        // check if series exists for current user in session, if not create one
        if(s == null){
            cd.addSeries(series, email, description, genre, url);
            cd.addIssue(title, email, series);
            int comicid = cd.getIssueId(series, title);
            cd.addPage(comicid, 1, url);
        } else{
            Issue i = cd.getIssue(series, title);
            if(i == null){
                cd.addIssue(title, email, series);
                int comicid = cd.getIssueId(series, title);
                cd.addPage(comicid, 1, url);
            } else {
                int comicid = cd.getIssueId(series, title);
                int pagenum = cd.getNextIssuePage(comicid);
                cd.addPage(comicid, pagenum, url);
            }
        }

        response.sendRedirect("/upload?series=" + series + "&issue=" + title);

        return null;
    }
}
