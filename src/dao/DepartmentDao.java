package dao;

import bean.Department;
import bean.User;
import bo.DepartmentBo;
import common.Const;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/15.
 */
public class DepartmentDao {

    private UserDao userDao = new UserDao();

    private String Department_Base_Column_List = "id, name, is_delete, parent_id, host_id, " +
        "create_time, update_time";

    // 数据库异常1 null
    // 成功 0 data
    public Department selectById(int id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select "+Department_Base_Column_List+" from department where id=? and is_delete=0";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        result.next();
        Department department = this.resultSetToDepartment(result);
        return department;
    }

    public List<DepartmentBo> selectDeparListByParentId(int pageNum, int pageSize, int parentId, String order_by, String sort) throws SQLException {
        Connection conn = DBHelper.getConnection();
        StringBuilder sql = new StringBuilder("select ");
        sql.append(Department_Base_Column_List);
        sql.append(" from department where is_delete=0 and parent_id=");
        sql.append(parentId);
        sql.append(" order by ");
        if ("".equals(order_by)||null==order_by) {
            sql.append("id ");
        } else {
            sql.append(order_by+" ");
            sql.append(sort+" ");
        }
        sql.append(" limit ");
        sql.append((pageNum-1)*pageSize+",");
        sql.append(pageSize);
        PreparedStatement statement = conn.prepareStatement(sql.toString());
        ResultSet result = statement.executeQuery();
        List<DepartmentBo> list = new ArrayList<DepartmentBo>();
        while (result.next()) {
            DepartmentBo departmentBo = new DepartmentBo();
            departmentBo.setId(result.getInt("id"));
            departmentBo.setName(result.getString("name"));
            int host_id = result.getInt("host_id");
            departmentBo.setHostId(host_id);
            departmentBo.setParentId(result.getInt("parent_id"));

            // 根據id查該部門的員工總數
            int deparUserTotal = userDao.selectUserTotal(host_id);
            departmentBo.setTotalStaff(deparUserTotal);
            // 獲取部門主管姓名
            User user = userDao.selectById(host_id);
            departmentBo.setHostName(user.getName());
            list.add(departmentBo);
        }
        return list;
    }

    public List<DepartmentBo> selectDeparListByParentId(int parentId) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select "+Department_Base_Column_List+" from department where is_delete=0 and parent_id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, parentId);
        ResultSet result = statement.executeQuery();
        List<DepartmentBo> list = new ArrayList<DepartmentBo>();
        while (result.next()) {
            DepartmentBo departmentBo = new DepartmentBo();
            departmentBo.setId(result.getInt("id"));
            departmentBo.setName(result.getString("name"));
            int host_id = result.getInt("host_id");
            departmentBo.setHostId(host_id);
            departmentBo.setParentId(result.getInt("parent_id"));
            departmentBo.setCreateTime(result.getDate("create_time"));
            departmentBo.setUpdateTime(result.getDate("update_time"));
            // 根據id查該部門的員工總數
            int deparUserTotal = userDao.selectUserTotal(host_id);
            departmentBo.setTotalStaff(deparUserTotal);
            // 獲取部門主管姓名
            User user = userDao.selectById(host_id);
            departmentBo.setHostName(user.getName());
            list.add(departmentBo);
        }
        return list;
    }

    public int selectDeparTotal(int parentId) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select count(1) from department where parent_id=? and is_delete="+ Const.IsDelete.UN_DELETE;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, parentId);
        ResultSet resultSet = statement.executeQuery();
        int count = 0;
        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        return count;
    }

    public int selectIdByHostId(int hostId) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "select id from department where is_delete="+Const.IsDelete.UN_DELETE+" and host_id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, hostId);
        ResultSet resultSet = statement.executeQuery();
        int result = 0;
        if (resultSet.next()) {
            result = resultSet.getInt("id");
        }
        return result;
    }

    public int updateBySelecter(Department depar) throws SQLException {
        Connection conn = DBHelper.getConnection();
        StringBuilder sql = new StringBuilder("update department set ");
        sql.append("id="+depar.getId());
        if (depar.getName()!=null && depar.getName().length()>0) {
            sql.append(",name="+"\'"+depar.getName()+"\'");
        }
        if (depar.getHostId()!=null) {
            sql.append(",host_id="+depar.getHostId());
        }
        if (depar.getIsDelete()!=null) {
            sql.append(", is_delete="+depar.getIsDelete());
        }
        if (depar.getParentId()!=null) {
            sql.append(", parent_id="+depar.getParentId());
        }
        sql.append(" where id="+depar.getId());
        PreparedStatement statement = conn.prepareStatement(sql.toString());
        int updateCount = -1;
        updateCount = statement.executeUpdate();
        return updateCount;
    }

    public int add(Department department) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "insert into department(name,parent_id,host_id,create_time,update_time) values(?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, department.getName());
        statement.setInt(2, department.getParentId());
        statement.setInt(3, department.getHostId());
        statement.setDate(4, new java.sql.Date(new Date().getTime()));
        statement.setDate(5, new java.sql.Date(new Date().getTime()));
        statement.execute();
        ResultSet resultSet = statement.getGeneratedKeys();
        int id = -1;
        if (resultSet.next()) {
            id = (int)resultSet.getLong(1);
        }
        return id;
    }

    private Department resultSetToDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("id"));
        department.setName(resultSet.getString("name"));
        department.setHostId(resultSet.getInt("host_id"));
        department.setParentId(resultSet.getInt("parent_id"));
        department.setIsDelete(resultSet.getInt("is_delete"));
        department.setCreateTime(resultSet.getDate("create_time"));
        department.setUpdateTime(resultSet.getDate("update_time"));
        return department;
    }

    public static void main(String[] args) {
        DepartmentDao dao = new DepartmentDao();
        /*try {
            Department depar = new Department();
            depar.setId(6);
            depar.setParentId(2);
            depar.setHostId(1);
            depar.setName("测试");
            int depar_id = dao.add(depar);
            System.out.println(depar_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
}
