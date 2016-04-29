package servlets;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import dao.UserDao;

/**
 * Created by Jessica on 4/28/2016.
 */
public class UploadImg extends HttpServlet {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        String desired_un = request.getParameter("displayName");
        String bio = request.getParameter("bio");

        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(request);
        List<BlobKey> blobKeys = blobs.get("profilePic");

        if((desired_un == null) || (bio == null) || (blobKeys == null) || (blobKeys.isEmpty())){ //if no username inputted
            response.sendRedirect("/signup");
        } else{
            UserDao ud = new UserDao();
            boolean usernameExists = ud.usernameExists(desired_un);
            if(usernameExists) { //if username inputted already exists
                response.sendRedirect("/signup");
            }else{              //SUCCESS, insert new user into database
                ud.createUser(desired_un, bio);
                response.sendRedirect("/serve?blob-key=" + blobKeys.get(0).getKeyString() + "&email=" + user.getEmail());
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
