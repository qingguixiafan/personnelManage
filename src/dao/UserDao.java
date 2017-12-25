package dao;

import bean.User;
import common.Const;
import common.ResponseCode;
import common.ServerResponse;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/14.
 */
public class UserDao {

    private static String Base_Column_List = "id, name, password, sex, role, phone, email, " +
            "salary, image, department_id, leader_id, is_delete, create_time, update_time ";



    // 用户名、密码错误
    // return null
    // 成功
    // return user
    public User login(String username, String password) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select "+Base_Column_List+" from user where name= ? and password = ?" +
                " and is_delete=0";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();
        if (!result.next()){
            return null;
        }
        User user = this.resultSetToUser(result);
        return user;
    }

    public User selectById(int id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select "+ Base_Column_List+" from user where id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        User user = this.resultSetToUser(resultSet);
        return  user;
    }

    private User resultSetToUser(ResultSet result) throws SQLException {
        User user = new User();
        user.setId(result.getInt("id"));
        user.setName(result.getString("name"));
        user.setPassowrd(result.getString("password"));
        user.setSex(result.getInt("sex"));
        user.setRole(result.getInt("role"));
        user.setPhone(result.getString("phone"));
        user.setEmail(result.getString("email"));
        user.setSalary(result.getDouble("salary"));
        user.setImage(result.getString("image"));
        user.setDepartmentId(result.getInt("department_id"));
        user.setLeaderId(result.getInt("leader_id"));
        user.setIsDelete(result.getInt("is_delete"));
        user.setCreateTime(result.getDate("create_time"));
        user.setUpdateTime(result.getDate("update_time"));
        return user;
    }

    public User updateBySelecter(User user) throws SQLException {
        Connection conn = DBHelper.getConnection();
        StringBuilder sql = new StringBuilder("update user " );
        sql.append(" set id="+user.getId());
        if (user.getName()!=null && user.getName().length()!=0){
            sql.append(", name="+"\'"+user.getName()+"\'");
        }
        // todo: 添加检查int类型的sex是否为空
        if (user.getSex()!=null){
            sql.append(", sex="+"\'"+user.getSex()+"\'");
        }
        if (user.getPassowrd()!=null && user.getPassowrd().length()!=0){
            sql.append(", password="+"\'"+user.getPassowrd()+"\'");
        }
        if (user.getImage()!=null && user.getImage().length()!=0){
            sql.append(", image="+"\'"+user.getImage()+"\'");
        }
        if (user.getPhone()!=null && user.getPhone().length()!=0){
            sql.append(", phone="+user.getPhone());
        }
        if (user.getEmail()!=null && user.getEmail().length()!=0){
            sql.append(", email="+"\'"+user.getEmail()+"\'");
        }
        if (user.getRole()!=null) {
            sql.append(", role="+user.getRole());
        }
        if (user.getSalary()!=null) {
            sql.append(", salary="+user.getSalary());
        }
        if (user.getIsDelete()!=null) {
            sql.append(", is_delete="+user.getIsDelete());
        }
        sql.append(" where id="+user.getId());
        PreparedStatement statement = conn.prepareStatement(sql.toString());
        statement.execute();
        return user;
    }

    public String checkUserName(String username) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = new String("select image from user where name="+"\'"+username+"\'");
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        String image = null;
        if (resultSet.next()) {
            image = resultSet.getString("image");
        }
        return image;
    }

    public int selectUserTotal(int leaderId) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select count(1) from user where leader_id=? and is_delete=0";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, leaderId);
        ResultSet resultSet = statement.executeQuery();
        int count = 0;
        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        return count;
    }

    public  List<User> selectUserListByLeaderId(int pageNum, int pageSize, int leaderId, String order_by, String sort) throws SQLException {
        Connection conn = DBHelper.getConnection();
        StringBuilder sql = new StringBuilder("select ");
        sql.append(Base_Column_List);
        sql.append(" from user where is_delete=0 and leader_id=");
        sql.append(leaderId);
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
        List<User> list = new ArrayList<User>();
        while (result.next()) {
            User user = new User();
            user.setId(result.getInt("id"));
            user.setName(result.getString("name"));
            user.setSalary(result.getDouble("salary"));
            user.setRole(result.getInt("role"));
            user.setPhone(result.getString("phone"));
            user.setEmail(result.getString("email"));
            user.setSex(result.getInt("sex"));
            user.setImage(Const.IMAGE_POREFIX+result.getString("image"));
            user.setDepartmentId(result.getInt("department_id"));
            user.setLeaderId(result.getInt("leader_id"));
            list.add(user);
        }
        return list;
    }

    public int updateSelecterByDeparId(int newLeaderId, int oldDeparId, int newDeparId) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "update user set department_id=?, leader_id=? where department_id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, newDeparId);
        statement.setInt(2, newLeaderId);
        statement.setInt(3, oldDeparId);
        int updateCount = statement.executeUpdate();
        return updateCount;
    }

    public int add(User user) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "insert into user(name,password,sex,role,salary,image,department_id," +
                "leader_id,create_time,update_time,email,phone) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassowrd());
        statement.setInt(3, user.getSex());
        statement.setInt(4, user.getRole());
        statement.setDouble(5, user.getSalary());
        statement.setString(6, user.getImage());
        statement.setInt(7, user.getDepartmentId());
        statement.setInt(8, user.getLeaderId());
        statement.setDate(9, new java.sql.Date(new Date().getTime()));
        statement.setDate(10, new java.sql.Date(new Date().getTime()));
        statement.setString(11, user.getEmail());
        statement.setString(12, user.getPhone());
        statement.execute();
        ResultSet resultSet = statement.getGeneratedKeys();
        int id = -1;
        if (resultSet.next()) {
            id = (int)resultSet.getLong(1);
        }
        return id;
    }

    public boolean isExist(int leaderId, int userId) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select count(*) from user where id=? and leader_id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, userId);
        statement.setInt(2, leaderId);
        ResultSet resultSet = statement.executeQuery();
        int count = 0;
        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        if (count>0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDao();
//        User user = userDao.login("root", "21232f297a57a5a743894a0e4a801fc3");
//        userDao.delete(10);
//        System.out.println(user);
        /*User user = new User();
        user.setName("test");
        user.setRole(1);
        user.setSex(1);
        user.setDepartmentId(1);
        user.setLeaderId(1);
        user.setSalary(4399.0);
        user.setPassowrd("admin");
        user.setImage("aaa");
        userDao.add(user);*/
        System.out.println(userDao.isExist(2, 12));
    }
}
