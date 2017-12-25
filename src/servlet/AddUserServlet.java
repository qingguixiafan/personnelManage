package servlet;

import bean.User;
import bo.UserBo;
import common.Const;
import service.DepartmentService;
import service.UserService;
import sun.rmi.server.InactiveGroupException;
import util.MD5Util;
import util.PageHelp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/24.
 */
@WebServlet("/addUserServlet.action")
public class AddUserServlet extends HttpServlet {

    private  DepartmentService departmentService = new DepartmentService();

    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBo currUser = (UserBo) request.getSession().getAttribute("user");
        PageHelp pageHelp = (PageHelp) request.getSession().getAttribute("usersPageHelp");
        String username = request.getParameter("username").trim();
        String sex = request.getParameter("sex");
        String salary = request.getParameter("salary");
        String role = request.getParameter("role");

        User user = new User();
        user.setSex(Integer.parseInt(sex));
        user.setName(username);
        user.setRole(Integer.parseInt(role));
        user.setSalary(Double.parseDouble(salary));
        user.setPassowrd(MD5Util.getMD5(Const.PASSWORD));
        user.setImage(Const.ICON);
        user.setDepartmentId(departmentService.selectIdByHostId(currUser.getId()));
        user.setLeaderId(currUser.getId());

        int id = userService.add(user);
        System.out.println("新增用户的id为："+id);
        // 页面跳转

        pageHelp = userService.selectUserListByLeaderId(1, pageHelp.getPageSize(), currUser.getId(), null, null);
        request.getSession().setAttribute("usersPageHelp", pageHelp);
        String path = "/leader/usersInfo.jsp";
        response.sendRedirect(request.getContextPath()+path);

    }
}
