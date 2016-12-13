package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by admin on 2016/11/20.
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");
        if("admin".equals(userName) && "123".equals(userPassword)){
            HttpSession session = req.getSession();
            session.setAttribute("userName",userName);
            session.setMaxInactiveInterval(60*60);
            Cookie cookie = new Cookie("userName",userName);
            cookie.setMaxAge(60*60);
//            resp.sendRedirect(req.getContextPath()+"/WEB-INF/success.jsp");
            req.getRequestDispatcher("/WEB-INF/success.jsp").forward(req,resp);
        }else {
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        }
    }
}
