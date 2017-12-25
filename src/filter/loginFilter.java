package filter;

import bean.User;
import bo.UserBo;
import common.Const;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/18.
 */
public class loginFilter implements Filter {

    private FilterConfig config;

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String path = "/login.jsp";
        String msg = "请先登录";
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        UserBo userBo = (UserBo) session.getAttribute("user");

//        String noLoginServlets = config.getInitParameter("noLoginServlets");

        /*String charset = config.getInitParameter("charset");
        if(charset==null){
            charset = "UTF-8";
        }*/
        /*req.setCharacterEncoding(charset);
        if(noLoginServlets!=null){
            String[] strArray = noLoginServlets.split(";");
            for (int i = 0; i < strArray.length; i++) {
                if(strArray[i]==null || "".equals(strArray[i]))continue;
                if(req.getRequestURI().indexOf(strArray[i])!=-1 ){
                    chain.doFilter(request, response);
                    return;
                }
            }
        }*/

        String ignoreServlet = Const.FILTER_IGNORE;
        String[] strArray = ignoreServlet.split(";");
        for (int i = 0; i < strArray.length; i++) {
            if(strArray[i]==null || "".equals(strArray[i]))continue;
            if(req.getRequestURI().indexOf(strArray[i])!=-1 ){
                chain.doFilter(request, response);
                return;
            }
        }



        if(userBo==null){
            session.setAttribute("msg", msg);
            HttpServletResponse rep = (HttpServletResponse)response;
            rep.sendRedirect(req.getContextPath()+path);
        }
        else{
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
