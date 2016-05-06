package controllers;

import dao.ComicDao;
import dao.UserDao;
import models.UserAcct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * Created by Jessica on 4/2/2016.
 */
public class LoadAccountAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String authorparam = request.getParameter("author");
        System.out.println("author parameter: " + authorparam);

        UserDao ud = new UserDao();
        UserAcct author = ud.getUserByUsername(authorparam);

        HttpSession session = request.getSession(true);
        session.setAttribute("author", author);
        session.setAttribute("authorimgpath", author.getProfileImg());

        return "account";
    }
}
