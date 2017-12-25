package servlet;

import bean.User;
import bo.UserBo;
import common.Const;
import common.ResponseCode;
import common.ServerResponse;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/14.
 */
@WebServlet("/login.action")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = null;
        String msg = null;
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        // 判断用户名密码是否为空
//        if ((username == null || "".equals(username)) || (password == null || "".equals(password))) {
//            path = "/login.jsp";
//            msg = "请输入用户名和密码";
//            request.getSession().setAttribute("msg", msg);
//            response.sendRedirect(request.getContextPath()+path);
//        }
        UserBo userBo = userService.login(username, password);
        if (userBo!=null) {
            // 成功
            request.getSession().setAttribute("from", "self");
            request.getSession().setAttribute("user", userBo);
            path = "/user/userShow.jsp";
        } else {
            // 用户名密码错误
            path = "/login.jsp";
            msg = "密码错误";
        }
        request.getSession().setAttribute("msg", msg);
        response.sendRedirect(request.getContextPath()+path);
    }
}
