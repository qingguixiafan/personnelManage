package servlet;

import bean.User;
import bo.UserBo;
import net.sf.json.JSONObject;
import service.UserService;
import util.PageHelp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/20.
 */
@WebServlet("/userUpdateBySelecterServlet.action")
public class UserUpdateBySelecterServlet extends HttpServlet {

    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();

        user.setId(Integer.parseInt(request.getParameter("id")));
        user.setRole(Integer.parseInt(request.getParameter("role")));
        user.setSalary(Double.parseDouble(request.getParameter("salary")));
        String id = request.getParameter("id");
        String role = request.getParameter("role");
        String salary = request.getParameter("salary");
        userService.updateBySelecter(user);
        // 更新pageHelp
        UserBo userBo = (UserBo) request.getSession().getAttribute("user");
        PageHelp pageHelp = (PageHelp) request.getSession().getAttribute("usersPageHelp");
        PageHelp updatePageHelp = userService.selectUserListByLeaderId(pageHelp.getPageNum(), pageHelp.getPageSize(), userBo.getId(), null, null);
        request.getSession().setAttribute("usersPageHelp", updatePageHelp);

        JSONObject jsonObject = JSONObject.fromObject(user);
        response.getWriter().write(jsonObject.toString());

        /*String path = "/leader/usersInfo.jsp";
        response.sendRedirect(request.getContextPath()+path);*/
    }
}
