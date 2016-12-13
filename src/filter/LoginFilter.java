package filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by admin on 2016/11/20.
 */
public class LoginFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
