package service;

import bean.Department;
import bean.User;
import bo.DepartmentBo;
import common.Const;
import dao.DepartmentDao;
import dao.UserDao;
import util.PageHelp;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/12/20.
 */
public class DepartmentService {

    private DepartmentDao departmentDao = new DepartmentDao();

    private UserDao userDao = new UserDao();

    public PageHelp selectDeparListByParentId(int pageNum, int pageSize, int parentId, String order_by, String sort) {
        PageHelp pageHelp = new PageHelp();
        List<DepartmentBo> departmentBos = null;
        //  得到list
        try {
            departmentBos = departmentDao.selectDeparListByParentId(pageNum, pageSize, parentId, order_by, sort);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //  得到total
        int total = 0;
        try {
            total = departmentDao.selectDeparTotal(parentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pageHelp.setTotal(total);
        pageHelp.setPageNum(pageNum);
        pageHelp.setPageSize(pageSize);
        pageHelp.setTotalPage((int)Math.ceil((double)total/pageSize));
        pageHelp.setList(departmentBos);
        return pageHelp;
    }

    public int selectIdByHostId(int host_id) {
        int deparmentId = 0;
        try {
            deparmentId = departmentDao.selectIdByHostId(host_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deparmentId;
    }

    public void deleteDepartment(int deparId, int parentId, int newLeaderId) {
        // 1 删除这个部门
        Department depar = new Department();
        depar.setId(deparId);
        depar.setIsDelete(Const.IsDelete.DELETE);
        try {
            departmentDao.updateBySelecter(depar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 2 修改被删除部门下的员工的department_id和leader_id
        int updateCount = 0;
        try {
            updateCount = userDao.updateSelecterByDeparId(newLeaderId, deparId, parentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("受影响员工数："+updateCount);

    }

    public void updateBySelecter(Department department) {
        try {
            departmentDao.updateBySelecter(department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int add(Department department) {
        // 创建一个部门
        int id = -1;
        try {
            id = departmentDao.add(department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 修改部门主管身份
        int host_id = department.getHostId();
        User user = new User();
        user.setId(host_id);
        user.setRole(Const.Role.ROLE_ADMIN);
        try {
            userDao.updateBySelecter(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
