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
        actions.put("GET/dashboardcontent", new LoadDashboardAction());
        actions.put("GET/login", new LoginAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/signup", new SignupAction());
        actions.put("GET/draw", new LoadDrawAction());
        actions.put("GET/upload", new LoadUploadAction());
        actions.put("GET/account", new LoadAccountAction());
        actions.put("GET/getbookmarks", new GetBookmarksAction());
        actions.put("GET/getsubscriptions", new GetSubscriptionsAction());
        actions.put("GET/search", new SearchAction());
        actions.put("POST/search", new SearchAction());
        actions.put("GET/comic", new LoadComicAction());
        actions.put("GET/author", new LoadAuthorAction());
        actions.put("GET/userinfo", new GetUserInfoAction());
        actions.put("GET/bookmark", new BookmarkAction());
        actions.put("GET/updatebio", new UpdateBioAction());
        actions.put("GET/subscribe", new SubscribeAction());
        actions.put("POST/test", new TestAction());
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
