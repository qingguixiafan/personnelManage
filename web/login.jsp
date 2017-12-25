<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="bo.UserBo" %>
<!-- 引入jstl标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<script type="text/javascript">
    var msg = "${msg}";
    if(msg){
        alert(msg);
    }
</script>
<%
    request.getSession().removeAttribute("msg");
%>
<head>
    <title>欢迎登陆</title>
    <link rel="stylesheet" type="text/css" href="./css/login.css" />
</head>

<body>
<div id="divForm">
<form action="login.action" method=post>
    <table>
        <tr>
            <div id="img">
                <img id="image" src="http://localhost:8686/personnelManage/upload/favicon.ico">
            </div>
        </tr>
        <tr id="user_name_tr">
            <td>用户名</td>
            <td>
                <input id="username" name=username type=text>
            </td>
            <td><p></p></td>
        </tr>
        <tr>
            <td>密码</td>
            <td>
                <input id="password" name=password type=password>
            </td>
        </tr>
        <tr>
            <td colspan=2 align=center>
                <input id="submit" type="submit" value="登陆" />
            </td>
        </tr>
    </table>
</form>
</div>
</body>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="./js/login.js"></script>
</html>
