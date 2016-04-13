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
 * Created by Jessica on 4/2/2016.
 */
public class SignupAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String desired_un = request.getParameter("desired-un");
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        if(desired_un == null){
            return "signup";
        }else{
            UserDao ud = new UserDao();
            boolean usernameExists = ud.usernameExists(desired_un);
            if(usernameExists) {
                return "signup";
            }else{
                ud.createUser(desired_un);
                UserAcct u = ud.getUser(user.getEmail());
                HttpSession session = request.getSession(true);
                session.setAttribute("user", u);
                response.sendRedirect("/");
            }
        }

        return null;


    }
}
