package servlet;

import bo.UserBo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import service.DepartmentService;
import service.ExportService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/12/25.
 */
@WebServlet("/exportDeparServlet.action")
public class ExportDeparServlet extends HttpServlet {

    private ExportService exportService = new ExportService();

    private DepartmentService departmentService = new DepartmentService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBo userBo = (UserBo) request.getSession().getAttribute("user");
        int parentId = departmentService.selectIdByHostId(userBo.getId());
        HSSFWorkbook wb = exportService.excelDepartments(parentId);
        // 文件保存位置与浏览器有关
        response.setContentType("application/vnd.ms-excel");
        // 设置为低版本的excel文件
        response.setHeader("Content-disposition", "attachment;filename=departments.xls");
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }
}
