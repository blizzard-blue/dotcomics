package conf;

import controllers.*;

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
        actions.put("POST/login", new LoginPostAction());
        actions.put("GET/logout", new LogoutAction());
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
