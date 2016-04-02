package controllers;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Jessica on 3/20/2016.
 */
public class LogoutAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        if(user != null) {
            HttpSession session = request.getSession(true);
            session.removeAttribute("user");
            response.sendRedirect(userService.createLogoutURL("/"));
        }

        return null;

    }
}
