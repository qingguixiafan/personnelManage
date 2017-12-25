<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<%--<script language="JavaScript" src="./js/header.js"></script>--%>
<%
    UserBo user = (UserBo) request.getSession().getAttribute("user");
%>

<head>

</head>
    <button onclick="userShow()">查询信息</button>
    <button onclick="userUpdate()">修改信息</button>
    <%if(user.getRole()==1){%>
        <button onclick="gotoManageUsreInfo()">管理部门</button>
    <%}%>
    <button onclick="logout()">退出系统</button>
</html>
