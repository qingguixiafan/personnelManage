package servlet;

import bean.Department;
import bo.UserBo;
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
 * Created by Administrator on 2017/12/24.
 */
@WebServlet("/addDepartmentServlet.action")
public class AddDepartmentServlet extends HttpServlet {

    private DepartmentService deparService = new DepartmentService();

    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        UserBo currUser = (UserBo) request.getSession().getAttribute("user");
        String depar_name = request.getParameter("depar_name");
        int host_id = Integer.parseInt(request.getParameter("host_id"));
        int parent_id = deparService.selectIdByHostId(currUser.getId());
        String path = null;
        if (!userService.isExist(currUser.getId(), host_id)) {
            String msg = "该员工不存在";
            request.getSession().setAttribute("msg", msg);
            path = "/leader/createDepar.jsp";
        } else {
            Department department = new Department();
            department.setName(depar_name);
            department.setHostId(host_id);
            department.setParentId(parent_id);
            System.out.println(department);
            deparService.add(department);
            path = "/leader/departmentsInfo.jsp";
        }
        PageHelp pageHelp = (PageHelp) request.getSession().getAttribute("deparPageHelp");
        pageHelp = deparService.selectDeparListByParentId(pageHelp.getPageNum(), pageHelp.getPageSize(), parent_id, null, null);
        request.getSession().setAttribute("deparPageHelp", pageHelp);
        response.sendRedirect(request.getContextPath()+path);
    }
}
