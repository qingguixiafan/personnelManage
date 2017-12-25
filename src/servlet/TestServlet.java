package servlet;

import bean.User;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/25.
 */
@WebServlet("/testServlet.action")
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        User stu = new User();
        stu.setId(1);
        stu.setName("aa");

        response.setContentType("application/json");
//        System.out.println("before");
        JSONObject jsonUser = JSONObject.fromObject(stu);
//        System.out.println("after");
//        System.out.println(jsonUser);
//        System.out.println(jsonUser.toString());
        response.getWriter().write(jsonUser.toString());
    }
}
