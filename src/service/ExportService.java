package service;

import bo.DepartmentBo;
import dao.DepartmentDao;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/12/25.
 */
public class ExportService {

    private DepartmentDao departmentDao = new DepartmentDao();

    public HSSFWorkbook excelDepartment(int parentId) {
        List<DepartmentBo> list = null;
        try {
            list = departmentDao.selectDeparListByParentId(parentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 定义表头
        String[] excelHeader = {"部門編號", "部門名稱", "上級部門編號", "部門主管編號", "部門主管姓名"};
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
        for (int i=1; i<list.size(); i++) {
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
        }
        return wb;
    }

}
