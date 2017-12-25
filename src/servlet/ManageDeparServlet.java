package servlet;

import bean.Department;
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
 * Created by Administrator on 2017/12/19.
 */
@WebServlet("/manageDeaprInfo.action")
public class ManageDeparServlet extends HttpServlet {

    private DepartmentService deparService = new DepartmentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBo userBo = (UserBo) request.getSession().getAttribute("user");
        PageHelp pageHelp = (PageHelp) request.getSession().getAttribute("deparPageHelp");
        String action = request.getParameter("action");

        int parentId = deparService.selectIdByHostId(userBo.getId());
        if (action!=null&&action.length()!=0) {
            int pageNum = pageHelp.getPageNum();
            int pageSize = pageHelp.getPageSize();
            if ("after".equals(action)) {
                if (pageNum<pageHelp.getTotalPage()) {
                    // parent_id 部门表中的parent_id
                    pageHelp = deparService.selectDeparListByParentId(pageNum+1, pageSize, parentId, null, null);
                } else {
                    pageHelp = deparService.selectDeparListByParentId(pageHelp.getTotalPage(), pageSize, parentId, null, null);
                }
            } else if ("before".equals(action)) {
                if (pageNum>1) {
                    pageHelp = deparService.selectDeparListByParentId(pageNum-1, pageSize, parentId, null, null);
                } else {
                    pageHelp = deparService.selectDeparListByParentId(1, pageSize, parentId, null, null);
                }
            } else if ("first".equals(action)) {
                pageHelp = deparService.selectDeparListByParentId(1, pageSize, parentId, null, null);
            } else {
                pageHelp = deparService.selectDeparListByParentId(pageHelp.getTotalPage(), pageSize, parentId, null, null);
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
            pageHelp = deparService.selectDeparListByParentId(pageNum, pageSize, parentId, null, null);

        }

        request.getSession().setAttribute("deparPageHelp", pageHelp);
        String path = "/leader/departmentsInfo.jsp";
        response.sendRedirect(request.getContextPath()+path);
    }
}
