package controllers;

import conf.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jessica on 3/20/2016.
 */
public class LoginPostAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        request.getSession().setAttribute("username", username);

//        User user = userDAO.find(username, password);
//
//        if (user != null) {
//            request.getSession().setAttribute("user", user); // Login user.
//            return "home"; // Redirect to home page.
//        }
//        else {
//            request.setAttribute("error", "Unknown username/password. Please retry."); // Store error message in request scope.
//            return "login"; // Go back to redisplay login form with error.
//        }
        return "index";

    }
}
