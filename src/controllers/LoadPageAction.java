package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jessica on 4/2/2016.
 */
public class LoadPageAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String view = request.getPathInfo();
        return view;
    }
}
