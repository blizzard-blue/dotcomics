package servlets;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import dao.ComicDao;
import dao.UserDao;
import models.Issue;
import models.Series;
import models.UserAcct;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Jessica on 5/14/2016.
 */
public class ServeUploadComic extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BlobKey blobKey = new BlobKey(request.getParameter("blob-key"));
        ImagesService services = ImagesServiceFactory.getImagesService();
        ServingUrlOptions serve = ServingUrlOptions.Builder.withBlobKey(blobKey);
        String url = services.getServingUrl(serve);

        ComicDao cd = new ComicDao();
        String series = request.getParameter("series");
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String description = request.getParameter("description");
        String email = request.getParameter("email");

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
    }
}
