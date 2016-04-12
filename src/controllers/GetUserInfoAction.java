package controllers;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

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
        JSONArray array = new JSONArray();
        JSONObject userinfo =  new JSONObject();
        JSONObject comics =  new JSONObject();
        String[] s = new String[5];
        s[0] = "hello";
        s[1] = "there";
        s[2] = "how";
        s[3] = "you";
        s[4] = "doin'?";

        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("user");

        userinfo.put("username", username);
        array.put(userinfo);
        array.put(comics);
        json.put("userinfo", array);

        PrintWriter pw = response.getWriter();
        pw.print(json.toString());
        pw.close();

        return null;
    }
}
