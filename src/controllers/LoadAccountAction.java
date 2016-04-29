package controllers;

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
//        String userid = author.getUserid();
//
//        File f = new File("img/user/" + userid + ".jpg");
//        if(f.exists() && !f.isDirectory()) {
//        }else{
//            userid = "default";
//        }
//
//        String authorimgpath = "/img/user/" + userid + ".jpg";
        System.out.println(author.getProfileImg());

        HttpSession session = request.getSession(true);
        session.setAttribute("author", author);
        session.setAttribute("authorimgpath", author.getProfileImg());

        return "account";
    }
}
