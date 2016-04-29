package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import dao.UserDao;
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

        UserDao ud = new UserDao();
        ud.updateImg(url, request.getParameter("email"));

        UserAcct u = ud.getUser(request.getParameter("email"));
        HttpSession session = request.getSession(true);
        session.setAttribute("user", u);
        session.setAttribute("imgurl", u.getProfileImg());

        response.sendRedirect("/");
    }
}
