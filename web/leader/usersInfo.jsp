<%@ page import="bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 引入jstl标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<%@include file="leaderHeader.jsp"%>
<%--<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="./../js/leaderHeader.js"></script>
<script type="text/javascript" src="./../js/usersInfo.js"></script>--%>
<head>
    <title>员工信息</title>
    <link rel="stylesheet" type="text/css" href="./../css/common.css" />
</head>

<body>
<div>
    <input type="button" style="float: left;margin-left: 500px;margin-right: -4000px;line-height: 15px;height: 31px;" onclick="exportExcel()" value="导出excel">
    <h2>员工信息页</h2>
</div>
    <c:set var="userList" scope="session" value="${usersPageHelp.getList()}"/>
<div id="divForm">
<form>
    <table>
        <tr>
            <td>照片</td>
            <td>编号</td>
            <td>姓名</td>
            <td>性别</td>
            <td>身份</td>
            <td>薪水</td>
            <td>电话</td>
            <td>邮箱</td>
            <td colspan="2">员工管理</td>
        </tr>
        <c:forEach items="${userList}" var="user" varStatus="status">
            <tr>
                <td>
                     <img src="${user.image}">
                </td>
                <td>${user.id}</td>
                <td><a style="text-decoration: none;" href="./../userShow.action?from=leader&userId=${user.id}">${user.name}</a></td>
                <c:choose>
                    <c:when test="${user.sex>0}">
                        <td><text>男</text></td>
                    </c:when>
                    <c:otherwise>
                        <td><text>女</text></td>
                    </c:otherwise>
                </c:choose>
                <td>
                    <select id="${user.id}_role">
                        <option value="1" <c:if test="${user.role eq 1}">selected</c:if>>部门主管</option>
                        <option value="0" <c:if test="${user.role eq 0}">selected</c:if>> 普通员工</option>
                    </select>
                </td>
                <td><input id="${user.id}_salary" style="width: 60px;" value="${user.salary}" /></td>
                <td>${user.phone}</td>
                <td>${user.email}</td>
                <td><input type="button" value="保存" onclick="updateUser(${user.id})"></td>
                <td><input type="button" value="删除" onclick="deleteUser(${user.id})"></td>
            </tr>
        </c:forEach>
    </table>
</form>
</div>

<tr>
    <td>共${usersPageHelp.total}条&nbsp;&nbsp;${usersPageHelp.pageNum}/${usersPageHelp.totalPage}&nbsp;</td>
    <td>
        <button onclick="firstPage()">首页</button>
        <button onclick="beforePage()">上页</button>
        <button onclick="afterPage()">下页</button>
        <button onclick="lastPage()">尾页</button>
        &nbsp;&nbsp;
        <input type="button" onclick="directPage(${usersPageHelp.getPageSize()})" value="转到">
        <input id="pageNum" style="width:50px;"/>页
        <input  type="button" onclick="createUser()" value="添加员工">
    </td>
</tr>
</body>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="./../js/leaderHeader.js"></script>
<script type="text/javascript" src="./../js/usersInfo.js"></script>
</html>
