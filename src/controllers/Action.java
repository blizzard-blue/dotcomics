package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jessica on 3/19/2016.
 */
public interface Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
