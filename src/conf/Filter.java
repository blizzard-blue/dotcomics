package conf;

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

        if (path.startsWith("/WEB-INF/")) {
            // Just let container's default servlet do its job.
            chain.doFilter(req, resp);
        } else {
            // Delegate to your front controller.
            request.getRequestDispatcher("/pages" + path).forward(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
