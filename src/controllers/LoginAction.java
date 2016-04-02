package controllers;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import dao.UserDao;
import models.UserAcct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Jessica on 3/19/2016.
 */
public class LoginAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        if(user == null) {
            response.sendRedirect(userService.createLoginURL("/login"));
        } else{
            UserDao ud = new UserDao();
            String nickname = user.getNickname();
            boolean userExists = ud.userExists(nickname);
            if(userExists){
                UserAcct u = ud.getUser(nickname);
                HttpSession session = request.getSession(true);
                session.setAttribute("user", u);
                response.sendRedirect("/");
            } else{
                response.sendRedirect("/signup");
            }
        }

        return null;
    }

}
