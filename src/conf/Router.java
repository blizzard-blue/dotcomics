package conf;

import controllers.Action;
import controllers.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jessica on 3/19/2016.
 */
public class Router extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Action action = ActionFactory.getInstance().getAction(request);
            String view = action.execute(request, response);

            String currentPage = request.getRequestURI();

            if(view == ""){
                response.sendRedirect("/");
            } else{
                if(!(view.equals(currentPage))){
                    request.getRequestDispatcher("/" + view + ".jsp").forward(request, response);
                }
            }
        }
        catch (Exception e) {
            throw new ServletException("Executing action failed.", e);
        }
    }
}
