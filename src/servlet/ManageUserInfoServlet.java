package servlet;

import bean.User;
import bo.UserBo;
import service.UserService;
import util.PageHelp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/12/19.
 */
@WebServlet("/manageUserInfo.action")
public class ManageUserInfoServlet extends HttpServlet {

    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBo userBo = (UserBo) request.getSession().getAttribute("user");
        String action = request.getParameter("action");
        PageHelp pageHelp = (PageHelp) request.getSession().getAttribute("usersPageHelp");
        request.getSession().removeAttribute("usersPageHelp");
        if (action!=null&&action.length()!=0) {
            int pageNum = pageHelp.getPageNum();
            int pageSize = pageHelp.getPageSize();
            if ("after".equals(action)) {
                if (pageNum<pageHelp.getTotalPage()) {
                    pageHelp = userService.selectUserListByLeaderId(pageNum+1, pageSize, userBo.getId(), null, null);
                } else {
                    pageHelp = userService.selectUserListByLeaderId(pageHelp.getTotalPage(), pageSize, userBo.getId(), null, null);
                }
            } else if ("before".equals(action)) {
                if (pageNum>1) {
                    pageHelp = userService.selectUserListByLeaderId(pageNum-1, pageSize, userBo.getId(), null, null);
                } else {
                    pageHelp = userService.selectUserListByLeaderId(1, pageSize, userBo.getId(), null, null);
                }
            } else if ("first".equals(action)) {
                pageHelp = userService.selectUserListByLeaderId(1, pageSize, userBo.getId(), null, null);
            } else {
                pageHelp = userService.selectUserListByLeaderId(pageHelp.getTotalPage(), pageSize, userBo.getId(), null, null);
            }
        } else {
            int pageNum = Integer.parseInt(request.getParameter("pageNum"));
            int pageSize = Integer.parseInt(request.getParameter("pageSize"));
            if (pageNum<1) {
                pageNum = 1;
            }
            if (pageHelp!=null && pageNum>pageHelp.getTotalPage()) {
                pageNum= pageHelp.getTotalPage();
            }

            // 调用service层方法
            pageHelp = userService.selectUserListByLeaderId(pageNum, pageSize, userBo.getId(), null, null);
        }
        request.getSession().setAttribute("usersPageHelp", pageHelp);
        String path = "/leader/usersInfo.jsp";
        response.sendRedirect(request.getContextPath()+path);
    }
}
