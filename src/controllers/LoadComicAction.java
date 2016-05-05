package controllers;

import dao.ComicDao;
import models.Issue;
import models.Series;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by sicaz on 4/10/2016.
 */
public class LoadComicAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String issueTitle = request.getParameter("issue");
        if(issueTitle == null){
            issueTitle = "1";
        }

        String series = request.getParameter("series");
        ComicDao cd = new ComicDao();

        Series s = cd.getSeries(series);
        Issue i = cd.getIssue(series, issueTitle);

        int comicid = cd.getIssueId(s.getTitle(), issueTitle);
        TreeMap pages = cd.getPages(comicid);


        HttpSession session = request.getSession(true);
        session.setAttribute("issue", i);
        session.setAttribute("series", s);
        session.setAttribute("pages", pages);

        return "comic";
    }
}
