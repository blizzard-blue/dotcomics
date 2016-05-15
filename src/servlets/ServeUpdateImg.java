package servlets;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import dao.UserDao;
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
public class ServeUpdateImg extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BlobKey blobKey = new BlobKey(request.getParameter("blob-key"));
        ImagesService services = ImagesServiceFactory.getImagesService();
        ServingUrlOptions serve = ServingUrlOptions.Builder.withBlobKey(blobKey);
        String url = services.getServingUrl(serve);
        UserAcct u = new UserAcct();
        if(request.getParameter("email") != null){
            UserDao ud = new UserDao();
            ud.updateImg(url, request.getParameter("email"));
            u = ud.getUser(request.getParameter("email"));
            HttpSession session = request.getSession(true);
            session.setAttribute("imgurl", u.getProfileImg());
        }

        response.sendRedirect("/account?author=" + u.getUsername());
    }
}
