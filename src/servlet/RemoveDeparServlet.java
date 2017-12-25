package servlet;

import bo.UserBo;
import service.DepartmentService;
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
@WebServlet("/removeDeparServlet.action")
public class RemoveDeparServlet extends HttpServlet {

    private DepartmentService departmentService = new DepartmentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int deparId = Integer.parseInt(request.getParameter("deparId"));
        int parentId = Integer.parseInt(request.getParameter("parentId"));
        UserBo userBo = (UserBo) request.getSession().getAttribute("user");
        int newLeaderId = userBo.getId();
        // 调用service层删除部门并修改被删除部门员工的部门id
        departmentService.deleteDepartment(deparId, parentId, newLeaderId);
        // 重新赋值deparPageHelp
        PageHelp pageHelp = (PageHelp) request.getSession().getAttribute("deparPageHelp");
        PageHelp deparPageHelp = departmentService.selectDeparListByParentId(pageHelp.getPageNum(), pageHelp.getPageSize(), parentId, null, null);
        request.getSession().setAttribute("deparPageHelp", deparPageHelp);
        String path = "/leader/departmentsInfo.jsp";
        response.sendRedirect(request.getContextPath()+path);
    }
}
