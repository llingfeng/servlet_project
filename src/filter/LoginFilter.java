package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2016/11/20.
 */
public class LoginFilter implements Filter{
    private FilterConfig config;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        String noLoginPaths = config.getInitParameter("NoLoginPaths");
        if(null != noLoginPaths && !"".equals(noLoginPaths)){
            String[] noLoginPathArr = noLoginPaths.split(";");
            for (String noLoginPath : noLoginPathArr) {
                if(null == noLoginPath || "".equals(noLoginPath)) return;
                if(request.getRequestURL().indexOf(noLoginPath) != -1){
                    filterChain.doFilter(req,resp);
                    return;
                }
            }
        }
        Object user = request.getSession().getAttribute("user");
        if(null != user){
            filterChain.doFilter(req,resp);
        }else {
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
