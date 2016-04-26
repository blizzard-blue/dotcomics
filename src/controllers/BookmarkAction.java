package controllers;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import dao.BookmarksDao;
import dao.UserDao;
import models.Series;
import models.UserAcct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by John on 4/25/2016.
 */
public class BookmarkAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Series s = (Series)request.getSession().getAttribute("series");
        UserAcct u = (UserAcct) request.getSession().getAttribute("user");
        BookmarksDao bd = new BookmarksDao();

        bd.bookmarkSeries(u.getUserid(), s.getTitle());

        return null;

    }
}

