<%@ page import="bo.UserBo" %>
<!-- 引入jstl标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>

<%@include file="./../header.jsp"%>
<script type="text/javascript">
    var msg = "${msg}";
    if(msg){
        alert(msg);
    }
</script>
<head>
    <link rel="stylesheet" type="text/css" href="./../css/common.css" />
</head>
<%
    request.getSession().removeAttribute("msg");
    UserBo userBo = (UserBo) request.getSession().getAttribute("user");
%>
<body>
    <div id="divForm">
        <form action="./../userUpdate.action" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>
                        <div id="image">
                            <img src="<%=userBo.getImage()%>">
                        </div>
                    </td>
                    <td>
                        <input type="file" name="upload_file"/>
                    </td>
                </tr>
                <tr>
                    <%--<td>用户名</td>
                    <td><input name="username" value="<%=userBo.getName()%>"/></td>--%>
                    <td>性别：</td>
                    <td>
                        <input type="radio" name="sex" value="1" <c:if test="${user.sex eq 1}">checked</c:if> />男
                        <input type="radio" name="sex" value="0" <c:if test="${user.sex eq 0}">checked</c:if> />女
                    </td>
                </tr>
                <tr>
                    <td>手机：</td>
                    <td><input id="phone" name="phone" value="<%=userBo.getPhone()%>"/></td>
                </tr>
                <tr>
                    <td>电子邮件：</td>
                    <td><input id="email" name="email" value="<%=userBo.getEmail()%>"/></td>
                </tr>
                <tr>
                    <td colspan=2>
                        <input id="submit" type="submit" value="保存" />
                    </td>
                </tr>
            </table>
        </form>
    </div>


    <%--<h2><%=user.getName()%></h2>--%>
    <%--<form action="./../upload.action" method="post" enctype="multipart/form-data">
        <input type="file" name="upload_file"/><br>
        <input type="submit" value="上传">
    </form>--%>
</body>

<script language="JavaScript" src="./../js/header.js"></script>
<script language="JavaScript" src="./../js/userUpdate.js"></script>
</html>
