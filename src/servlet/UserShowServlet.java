package servlet;

import bean.User;
import bo.UserBo;
import dao.UserDao;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/12/17.
 */
@WebServlet("/userShow.action")
public class UserShowServlet extends HttpServlet {

    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

//    userShow.action?from=leader&userId=4
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("from")!=null && request.getParameter("from").equals("leader")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            // 根据userId封装staff
            UserBo staff = userService.packageUserBo(userId);
            request.getSession().setAttribute("staff", staff);
            request.getSession().setAttribute("from", "leader");
        } else {
            request.getSession().setAttribute("from", "self");
        }
        String path = "/user/userShow.jsp";
        response.sendRedirect(request.getContextPath()+path);
    }
}
