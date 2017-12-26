package service;

import bean.User;
import bo.DepartmentBo;
import dao.DepartmentDao;
import dao.UserDao;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/25.
 */
public class ExportService {

    private DepartmentDao departmentDao = new DepartmentDao();

    private UserDao userDao = new UserDao();

    public HSSFWorkbook excelDepartments(int parentId) {
        List<DepartmentBo> list = null;
        try {
            list = departmentDao.selectDeparListByParentId(parentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 定义表头
        String[] excelHeader = {"部门编号", "部门名称", "上级部门编号", "部门主管编号", "部门主管姓名", "创建时间", "最后修改时间"};
        // 为了兼顾老版本的excel，新版本使用XSSFWorkBook
        HSSFWorkbook wb = new HSSFWorkbook();
        // 生成一个工作表
        Sheet sheet = wb.createSheet();
        // 创建第一行
        Row row = sheet.createRow(0);
        // 插入第一行数据
        Cell cell = null;
        for (int i = 0; i < excelHeader.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
        }
        for (int i=1; i<=list.size(); i++) {
            Row nextrow = sheet.createRow(i);
            Cell cell2 = nextrow.createCell(0);
            cell2.setCellValue(list.get(i-1).getId());
            cell2 = nextrow.createCell(1);
            cell2.setCellValue(list.get(i-1).getName());
            cell2 = nextrow.createCell(2);
            cell2.setCellValue(list.get(i-1).getParentId());
            cell2 = nextrow.createCell(3);
            cell2.setCellValue(list.get(i-1).getHostId());
            cell2 = nextrow.createCell(4);
            cell2.setCellValue(list.get(i-1).getHostName());


            Date createTime = new Date(2008,5,5);
            cell2 = nextrow.createCell(5);
            cell2.setCellValue(createTime);
            HSSFCellStyle dataStyle = wb.createCellStyle();
            HSSFDataFormat format= wb.createDataFormat();
            dataStyle.setDataFormat(format.getFormat("yyyy年m月d日")); //yyyy/mm/dd hh:mm:ss
            cell.setCellStyle(dataStyle);

            cell2 = nextrow.createCell(6);
            cell2.setCellValue(list.get(i-1).getUpdateTime());
        }
        return wb;
    }

    public HSSFWorkbook excelUsers (int leaderId) {
        String[] excelHeader = {"编号","姓名","性别","角色","电话","邮箱","工资"};
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFRow row = sheet.createRow(0);
        Cell cell = null;
        for (int i = 0; i < excelHeader.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
        }
        List<User> users = null;
        try {
            users = userDao.selectUserListByLeaderId(leaderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i=1; i<=users.size(); i++) {
            Row nextrow = sheet.createRow(i);
            Cell cell2 = nextrow.createCell(0);
            cell2.setCellValue(users.get(i-1).getId());
            cell2 = nextrow.createCell(1);
            cell2.setCellValue(users.get(i-1).getName());
            cell2 = nextrow.createCell(2);
            if (users.get(i-1).getSex()>0) {
                cell2.setCellValue("男");
            } else {
                cell2.setCellValue("女");
            }
            cell2 = nextrow.createCell(3);
            if (users.get(i-1).getRole()>0) {
                cell2.setCellValue("部门主管");
            } else {
                cell2.setCellValue("普通员工");
            }
            cell2 = nextrow.createCell(4);
            cell2.setCellValue(users.get(i-1).getPhone());
            cell2 = nextrow.createCell(5);
            cell2.setCellValue(users.get(i-1).getEmail());
            cell2 = nextrow.createCell(6);
            cell2.setCellValue(users.get(i-1).getSalary());
        }
        return wb;
    }

}
