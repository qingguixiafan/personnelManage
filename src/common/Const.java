package common;

import org.apache.taglibs.standard.lang.jstl.test.StaticFunctionTests;

import java.beans.Statement;

/**
 * Created by Administrator on 2017/12/14.
 */
public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String IMAGE_POREFIX = "http://localhost:8686/personnelManage/upload/";

    public static final String FILTER_IGNORE = "isLoginServlet.action;login.action;testServlet.action;checkUserName.action";

    public static final String ICON = "favicon.ico";

    public static final String PASSWORD = "admin";

    public interface Role{
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1;//管理员
    }

    public interface IsDelete{
        int DELETE = 1;
        int UN_DELETE = 0;
    }
}
