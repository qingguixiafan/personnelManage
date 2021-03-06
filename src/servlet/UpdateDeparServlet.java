package servlet;

import bean.Department;
import bean.User;
import bo.UserBo;
import common.Const;
import service.DepartmentService;
import service.UserService;
import util.PageHelp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/21.
 */
@WebServlet("/updateDeparServlet.action")
public class UpdateDeparServlet extends HttpServlet {

    private DepartmentService deparService = new DepartmentService();

    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBo userBo = (UserBo) request.getSession().getAttribute("user");
        Department department = new Department();
        int deparId = Integer.parseInt(request.getParameter("deparId"));
        department.setId(deparId);
        department.setName(request.getParameter("deparName"));
        String path = "/leader/departmentsInfo.jsp";
        if (request.getParameter("hostId")!=null){
            int userId = Integer.parseInt(request.getParameter("hostId"));
            if (!userService.isExist(userBo.getId(), userId)) {
                String msg = "该员工不存在";
                path = "/leader/createDepar.jsp";
                request.getSession().setAttribute("msg", msg);
            } else {
                department.setHostId(Integer.parseInt(request.getParameter("hostId")));
                // 更新部门信息
                int updateCount = deparService.updateBySelecter(department);
                if (updateCount>0) {
                    String msg = "部门名已经存在";
                    path = "/leader/createDepar.jsp";
                } else {
                    // 把新主管的身份置为1
                    User user = new User();
                    user.setId(Integer.parseInt(request.getParameter("hostId")));
                    user.setRole(Const.Role.ROLE_ADMIN);
                    userService.updateBySelecter(user);
                    // 更改部门员工的leaderId
                    Integer oldLeaderId = deparService.selectById(deparId).getHostId();
                    userService.updateUsersLeaderId(oldLeaderId, Integer.parseInt(request.getParameter("hostId")));
                }
            }
        }

        // 重新赋值deparPageHelp
        PageHelp pageHelp = (PageHelp) request.getSession().getAttribute("deparPageHelp");
        int parentId = deparService.selectIdByHostId(userBo.getId());
        PageHelp deparPageHelp = deparService.selectDeparListByParentId(pageHelp.getPageNum(), pageHelp.getPageSize(), parentId, null, null);
        request.getSession().setAttribute("deparPageHelp", deparPageHelp);
        response.sendRedirect(request.getContextPath()+path);
    }
}
