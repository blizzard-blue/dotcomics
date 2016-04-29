package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Jessica on 3/20/2016.
 */
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String path = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println(path);

        if (path.startsWith("/WEB-INF/") || path.startsWith("/img")) {
            // Just let container's default servlet do its job.
            chain.doFilter(req, resp);
        } else if (path.startsWith("/_ah")){
            chain.doFilter(req, resp);
        } else if(path.startsWith("/series")){
            chain.doFilter(req, resp);
        } else if(path.startsWith("/uploadprofpic")){
            chain.doFilter(req, resp);
        } else if(path.startsWith("/serve")){
            chain.doFilter(req, resp);
        } else {
            // Delegate to your front controller.
            request.getRequestDispatcher("/pages" + path).forward(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
