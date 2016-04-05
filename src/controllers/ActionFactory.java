package controllers;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jessica on 3/19/2016.
 */
public class ActionFactory {
    private static Map<String, Action> actions;
    private static ActionFactory instance;

    public ActionFactory() {
        actions = new HashMap<>();
        actions.put("GET/", new StartupAction());
        actions.put("GET/login", new LoginAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/signup", new SignupAction());
        actions.put("GET/draw", new LoadPageAction());
        actions.put("GET/upload", new LoadPageAction());
        actions.put("GET/account", new LoadAccountAction());
        actions.put("GET/getbookmarks", new GetBookmarksAction());
        actions.put("GET/getsubscriptions", new GetSubscriptionsAction());
        actions.put("GET/search", new LoadSearchAction());
    }

    public static ActionFactory getInstance() {
        if (instance == null) {
            instance = new ActionFactory();
            return instance;
        } else {
            return instance;
        }
    }
    public static Action getAction(HttpServletRequest request) {
        if(request.getPathInfo() == null)
            return actions.get(request.getMethod() + "/");
        else
            return actions.get(request.getMethod() + request.getPathInfo());
    }
}
