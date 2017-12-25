package servlet;

import bean.User;
import bo.UserBo;
import common.Const;
import service.UserService;
import util.PageHelp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/20.
 */
@WebServlet("/removeUser.action")
public class RemoveUserServlet extends HttpServlet {

    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBo userBo = (UserBo) request.getSession().getAttribute("user");
        PageHelp pageHelp = (PageHelp) request.getSession().getAttribute("usersPageHelp");
        int pageNum = pageHelp.getPageNum();
        int pageSize = pageHelp.getPageSize();

        int userId = Integer.parseInt(request.getParameter("userId"));
        User deleteUser = new User();
        deleteUser.setId(userId);
        deleteUser.setIsDelete(Const.IsDelete.DELETE);
        userService.updateBySelecter(deleteUser);

        // 重新对usersPageHelp赋值
        pageHelp = userService.selectUserListByLeaderId(1, pageSize, userBo.getId(), null, null);
        request.getSession().setAttribute("usersPageHelp", pageHelp);
        String path = "/leader/usersInfo.jsp";
        response.sendRedirect(request.getContextPath()+path);
    }
}
