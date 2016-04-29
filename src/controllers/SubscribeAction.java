package controllers;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import dao.BookmarksDao;
import dao.SubscriptionsDao;
import dao.UserDao;
import models.Bookmark;
import models.Series;
import models.Subscription;
import models.UserAcct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by John on 4/25/2016.
 */
public class SubscribeAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserAcct u = (UserAcct) request.getSession().getAttribute("user");
        SubscriptionsDao sd = new SubscriptionsDao(u.getUsername());
        List<Subscription> subscriptions = sd.getAllSubscriptions();
        UserAcct author = (UserAcct) request.getSession().getAttribute("author");

        sd.subscribeAuthor(u.getUserid(), author.getUsername()); //bookmarks current series, backend does check if it alrdy exists

        boolean subscriptionExists = false;
        for(Subscription s : subscriptions){
            if(s.getAuthor().equals(author.getUsername()))
                subscriptionExists = true;
        }

        if(!subscriptionExists) {


            JSONObject json = new JSONObject();
            JSONObject sm = new JSONObject();

            String path = "/account?author=" + author.getUsername();
            sm.put("authorName", author.getUsername());
            sm.put("path", path);
            json.put("newsubscription", sm);
            System.out.println("new subscription into jsonobj " + author.getUsername());

            PrintWriter pw = response.getWriter();
            pw.print(json.toString());
            pw.close();

        }
        return null;

    }
}

