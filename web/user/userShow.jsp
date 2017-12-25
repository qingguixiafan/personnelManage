<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bo.UserBo" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/14
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<script language="JavaScript" src="./../js/header.js"></script>
<%@include file="./../header.jsp"%>
<head>
    <link rel="stylesheet" type="text/css" href="./../css/common.css" />
</head>
<%
    request.getSession().removeAttribute("showInfo");
    UserBo userBo = null;
    String from = (String) request.getSession().getAttribute("from");
    if ("self".equals(from)) {
        // 自己查看自己的信息
        userBo = (UserBo) request.getSession().getAttribute("user");
    } else {
        // 领导查看下属员工的信息
        userBo = (UserBo) request.getSession().getAttribute("staff");
    }
    request.getSession().setAttribute("current", userBo);
%>
<body>
<div id="divForm">
    <form>
        <table>
            <tr>
                <td>
                    <img src="${current.image}">
                </td>
                <td>

                </td>
            </tr>
            <tr>
                <td>用户名：</td>
                <td><text>${current.name}</text></td>
            </tr>
            <tr>
                <td>性别：</td>
                <c:choose>
                    <c:when test="${current.sex>0}">
                        <td><text>男</text></td>
                    </c:when>
                    <c:otherwise>
                        <td><text>女</text></td>
                    </c:otherwise>
                </c:choose>
            </tr>
            <tr>
                <td>角色：</td>
                <c:choose>
                    <c:when test="${current.role>0}">
                        <td><text>部门领导</text></td>
                    </c:when>
                    <c:otherwise>
                        <td><text>普通员工</text></td>
                    </c:otherwise>
                </c:choose>
            </tr>
            <tr>
                <td>电话：</td>
                <td>${current.phone}</td>
            </tr>
            <tr>
                <td>邮箱：</td>
                <td>${current.email}</td>
            </tr>
            <tr>
                <td>薪水：</td>
                <td>${current.salary}</td>
            </tr>
            <tr>
                <td>部门：</td>
                <td>${current.departmentName}</td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
