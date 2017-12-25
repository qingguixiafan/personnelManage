package servlet;

import bean.User;
import bo.UserBo;
import common.Const;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * 这个servlet的get请求和post的请求处理的逻辑不同
 */
@WebServlet("/userUpdate.action")
public class UserUpdateServlet extends HttpServlet {

    UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBo userBo = (UserBo) request.getSession().getAttribute("user");
        User user = new User();

        // 放置文件的位置
        String savePath = this.getServletContext().getRealPath("upload");
        File file = new File(savePath);
        // 判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            //创建目录
            file.mkdir();
            System.out.println("创建目录成功");
        }
        // 文件上传工厂实例
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 文件上传解析器，上传文件对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        if(!ServletFileUpload.isMultipartContent(request)) {
            return;
        }
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        for(FileItem item : items){
            // 如果是普通表单域
            if(item.isFormField()){
                String name = item.getFieldName();
                String value = item.getString("UTF-8");
                if ("sex".equals(name)) {
                    user.setSex(Integer.parseInt(value));
                    userBo.setSex(Integer.parseInt(value));
                }
                if ("phone".equals(name)) {
                    user.setPhone(value);
                    userBo.setPhone(value);
                }
                if ("email".equals(name)) {
                    user.setEmail(value);
                    userBo.setEmail(value);
                }
                /*if ("role".equals(name)) {
                    user.setRole(Integer.parseInt(value));
                    userBo.setRole(Integer.parseInt(value));
                }
                if ("salary".equals((name))) {
                    user.setSalary(Double.parseDouble(value));
                    userBo.setSalary(Double.parseDouble(value));
                }*/
            } else {
                // 上传的是文件
                // 得到上传的文件名
                String filename = item.getName();
                //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
//                filename = filename.substring(filename.lastIndexOf("\\")+1);
                if (filename != null && filename.length() != 0) {
                    filename = UUID.randomUUID().toString()+filename.substring(filename.lastIndexOf("."));
                    UUID.randomUUID().toString();
                    // 获取上传文件输入流
                    InputStream in = item.getInputStream();
                    // 创建文件输出流
                    FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                    // 创建缓冲区
                    byte buffer[] = new byte[1024];
                    int len = 0;
                    while((len=in.read(buffer))>0){
                        out.write(buffer, 0, len);
                    }
                    // 删除临时文件
                    item.delete();
                    out.close();
                    in.close();
                    user.setImage(filename);
                    // 删除原来的图片
                    String deleteImage = userBo.getImage().substring(userBo.getImage().lastIndexOf("/"));
                    if (!("/"+Const.ICON).equals(deleteImage)) {
                        File deleteFile = new File(savePath+deleteImage);
                        deleteFile.delete();
//                        System.out.println("file path:"+deleteFile.getPath());
                    }
                    userBo.setImage(Const.IMAGE_POREFIX+filename);
                    System.out.println("上传文件成功: "+filename);
                }
            }
        }

        user.setId(userBo.getId());
        User updateUser = userService.updateBySelecter(user);
        request.getSession().setAttribute("user", userBo);


        String msg = "保存成功";
        request.getSession().setAttribute("msg", msg);
        String path = "/user/userUpdate.jsp";
        response.sendRedirect(request.getContextPath()+path);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/user/userUpdate.jsp";
        response.sendRedirect(request.getContextPath()+path);
    }
}
