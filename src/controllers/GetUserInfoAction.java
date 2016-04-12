package controllers;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import models.UserAcct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * Created by sicaz on 4/11/2016.
 */
public class GetUserInfoAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=utf-8");

        JSONObject json = new JSONObject();
        JSONObject userinfo =  new JSONObject();

        HttpSession session = request.getSession(true);
        UserAcct u = (UserAcct) session.getAttribute("user");
        String username = u.getUsername();

        userinfo.put("username", username);
        userinfo.put("about", u.getAbout());
        userinfo.put("img", "/img/user/mckenna.jpg");
        json.put("userinfo", userinfo);

        PrintWriter pw = response.getWriter();
        pw.print(json.toString());
        pw.close();

        return null;
    }
}
