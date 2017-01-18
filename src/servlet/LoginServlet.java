package servlet;

import entity.User;
import util.CommonUtil;

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
        String userPassword = req.getParameter("text_password");
        String remember = req.getParameter("remember");
        String error = "用户名或密码错误";
        if(userName==null || "".equals(userName) || userPassword == null || "".equals(userPassword)){
            error = "用户名和密码不能为空";
        }else {
//            String  randomStr = (String) req.getSession().getAttribute("randomStr");
//            String md5Str = CommonUtil.getMD5Str("admin" + randomStr, "utf-8");
            String md5Str = CommonUtil.getMD5Str("admin","utf-8");
            if("admin@qq.com".equals(userName) && md5Str.equals(userPassword)){
                User user = new User();
                user.setUserName(userName);
                user.setUserPassword(userPassword);
                HttpSession session = req.getSession();
                session.setAttribute("user",user);
                session.setMaxInactiveInterval(60*60);
                //记住密码
                if (remember != null){
                    Cookie cookie = new Cookie("user",user.getUserName()+"^"+ md5Str+"^"+remember);
                    cookie.setMaxAge(60*60);
                    cookie.setPath(req.getContextPath());
                    resp.addCookie(cookie);
                }else {//清除cookie
                    Cookie[] cookies = req.getCookies();
                    for (Cookie cookie : cookies) {
                        if("user".equals(cookie.getName())){
                            cookie.setMaxAge(0);
                            resp.addCookie(cookie);
                        }
                    }
                }
                error = "pass";
            }
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        resp.getWriter().write("{\"error\":\""+error+"\"}");
    }
}
