package controllers;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import dao.BookmarksDao;
import dao.UserDao;
import models.Bookmark;
import models.Series;
import models.UserAcct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by John on 4/26/2016.
 */
public class UpdateBioAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserAcct u = (UserAcct) request.getSession().getAttribute("user");
        UserDao ud = new UserDao();
        String bio = request.getParameter("bio");
        ud.updateBio(bio);
        u.setAbout(bio);

        return null;

    }
}

