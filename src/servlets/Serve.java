package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import dao.ComicDao;
import dao.UserDao;
import models.Issue;
import models.Series;
import models.UserAcct;

/**
 * Created by Jessica on 4/28/2016.
 */
public class Serve extends HttpServlet {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BlobKey blobKey = new BlobKey(request.getParameter("blob-key"));
//        blobstoreService.serve(blobKey, response);
        ImagesService services = ImagesServiceFactory.getImagesService();
        ServingUrlOptions serve = ServingUrlOptions.Builder.withBlobKey(blobKey);
        String url = services.getServingUrl(serve);
        UserAcct u = new UserAcct();

        HttpSession session = request.getSession(true);

//        if(request.getParameter("uploadcomic").equals("true") && request.getParameter("uploadcomic") != null){
//            ComicDao cd = new ComicDao();
//            String series = request.getParameter("series");
//            String title = request.getParameter("title");
//            String genre = request.getParameter("genre");
//            String description = request.getParameter("description");
//            String email = request.getParameter("email");
//
//            Series s = cd.getSeries(series);
//            // check if series exists for current user in session, if not create one
//            if(s == null){
//                cd.addSeries(series, email, description, genre, url);
//                cd.addIssue(title, email, series);
//                int comicid = cd.getIssueId(series, title);
//                cd.addPage(comicid, 1, url);
//            } else{
//                Issue i = cd.getIssue(series, title);
//                if(i == null){
//                    cd.addIssue(title, email, series);
//                    int comicid = cd.getIssueId(series, title);
//                    cd.addPage(comicid, 1, url);
//                } else {
//                    int comicid = cd.getIssueId(series, title);
//                    int pagenum = cd.getNextIssuePage(comicid);
//                    cd.addPage(comicid, pagenum, url);
//                }
//            }
//            response.sendRedirect("/upload?series=" + series + "&issue=" + title);
//        }else{
            if(request.getParameter("email") != null){
                UserDao ud = new UserDao();
                ud.updateImg(url, request.getParameter("email"));
                u = ud.getUser(request.getParameter("email"));
                session.setAttribute("user", u);
                session.setAttribute("imgurl", u.getProfileImg());
            }

//            if(request.getParameter("updateImg").equals("true"))
//                response.sendRedirect("/account?author=" + u.getUsername());
//            else
                response.sendRedirect("/");
//        }
    }
}
