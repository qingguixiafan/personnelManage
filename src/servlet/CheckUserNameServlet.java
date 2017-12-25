package servlet;

import bean.User;
import common.Const;
import dao.UserDao;
import net.sf.json.JSONObject;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/18.
 */
@WebServlet("/checkUserName.action")
public class CheckUserNameServlet extends HttpServlet {

    private UserService userService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username").trim();
        String image = userService.checkUserName(username);
        User showInfo = new User();
        showInfo.setImage(Const.IMAGE_POREFIX+Const.ICON);
        if (image!=null) {
            showInfo.setName(username);
            showInfo.setImage(Const.IMAGE_POREFIX+image);
        }
        JSONObject jsonObject = JSONObject.fromObject(showInfo);
        response.getWriter().write(jsonObject.toString());
    }
}
