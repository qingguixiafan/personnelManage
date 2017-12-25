package service;

import bean.Department;
import bean.User;
import bo.UserBo;
import common.Const;
import dao.DepartmentDao;
import dao.UserDao;
import util.MD5Util;
import util.PageHelp;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/12/14.
 */
public class UserService {

    private UserDao userDao = new UserDao();

    private DepartmentDao departmentDao = new DepartmentDao();

    public UserBo login(String name, String password) {
        User user = null;
        try {
            user = userDao.login(name, MD5Util.getMD5(password));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user==null) {
            return null;
        }
        // 把用户信息分装成bo对象
        UserBo userBo = this.packageUserBo(user.getId());
        return userBo;
    }

    public User updateBySelecter(User user) {
        User updateUser = null;
        try {
            updateUser = userDao.updateBySelecter(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateUser;
    }

    public PageHelp selectUserListByLeaderId(int pageNum, int pageSize, int leaderId, String order_by, String sort) {
        List<User> users = null;
        try {
            users = userDao.selectUserListByLeaderId(pageNum, pageSize, leaderId, order_by, sort);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int total = 0;
        try {
            total = userDao.selectUserTotal(leaderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PageHelp pageHelp = new PageHelp();
        pageHelp.setPageNum(pageNum);
        pageHelp.setPageSize(pageSize);
        pageHelp.setTotal(total);
        pageHelp.setTotalPage((int)Math.ceil((double)total/pageSize));
        pageHelp.setList(users);
        return pageHelp;
    }

    public String checkUserName(String username) {
        String image = null;
        try {
            image = userDao.checkUserName(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return image;
    }

    public UserBo packageUserBo(int userId) {
        UserBo userBo = new UserBo();
        userBo.setId(userId);
        User user = new User();
        try {
            user = userDao.selectById(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userBo.setName(user.getName());
        userBo.setPhone(user.getPhone());
        userBo.setSex(user.getSex());
        userBo.setRole(user.getRole());
        userBo.setEmail(user.getEmail());
        userBo.setSalary(user.getSalary());
        userBo.setImage(Const.IMAGE_POREFIX+user.getImage());
        userBo.setDepartmentId(user.getDepartmentId());
        userBo.setLeaderId(user.getLeaderId());
        // 添加用户部门名称
        Department department = null;
        try {
            department = departmentDao.selectById(user.getDepartmentId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userBo.setDepartmentName(department.getName());

        User leader = null;
        try {
            leader = userDao.selectById(user.getLeaderId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (leader==null) {
            userBo.setLeaderName(null);
        } else {
            userBo.setLeaderName(leader.getName());
        }
        return userBo;
    }

    public int add(User user) {
        int id = -1;
        try {
            id = userDao.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean isExist(int leaderId, int userId) {
        boolean result = false;
        try {
            result = userDao.isExist(leaderId, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println((int)Math.ceil((double) 6/3));
        UserService service = new UserService();
        UserBo userBo = service.packageUserBo(1);
        System.out.println(userBo);
    }
}
