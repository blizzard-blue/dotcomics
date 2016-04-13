package controllers;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import dao.SubscriptionsDao;
import models.Bookmark;
import models.Subscription;
import models.UserAcct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Jessica on 4/2/2016.
 */
public class GetSubscriptionsAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserAcct u = (UserAcct) request.getSession(true).getAttribute("user");
        SubscriptionsDao sb = new SubscriptionsDao(u.getUsername());
        List<Subscription> subscriptions = sb.getAllSubscriptions();

        JSONObject json = new JSONObject();
        JSONObject sbc =  new JSONObject();
        JSONArray sbcarray = new JSONArray();

        for(int i=0; i<subscriptions.size(); i++){
            Subscription s = subscriptions.get(i);
            if(s.isSubscribed()){
                JSONObject sb2 =  new JSONObject();
                sb2.put("author", s.getAuthor());
                sb2.put("path", s.getPath());
                sbcarray.put(sb2);
                System.out.println("subscriptions into jsonobj " + s.getAuthor());

            }
        }

        sbc.put("subscriptions", sbcarray);
        json.put("subscriptions_obj", sbc);

        PrintWriter pw = response.getWriter();
        pw.print(json.toString());
        pw.close();

        return null;
    }
}
