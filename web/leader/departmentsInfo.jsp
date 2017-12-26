<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="leaderHeader.jsp"%>
<script type="text/javascript">
    var msg = "${msg}";
    if(msg){
        alert(msg);
    }
</script>
<%
    request.getSession().removeAttribute("msg");
%>
<%--<script type="text/javascript" src="./../js/leaderHeader.js"></script>
<script type="text/javascript" src="./../js/departmentsInfo.js"></script>--%>
<head>
    <title>部门信息</title>
    <link rel="stylesheet" type="text/css" href="./../css/common.css" />
</head>

<body>
<div>
    <input type="button" style="float: left;margin-left: 500px;margin-right: -4000px;line-height: 15px;height: 31px;" onclick="exportExcel()" value="导出excel">
    <h2>部门信息显示页</h2>
</div>
    <c:set var="deparList" scope="session" value="${deparPageHelp.getList()}"/>
<div id="divForm">
<form>
    <table>
        <tr>
            <td>部门编号</td>
            <td>部门名称</td>
            <td>部门主管</td>
            <td>员工总数</td>
            <td>部门管理</td>
        </tr>
        <c:forEach items="${deparList}" var="department" varStatus="status">
            <tr>
                <td>${department.id}</td>
                <td><input style="width: 73px;" id="${department.id}_name" value="${department.name}"/></td>
                <td><input style="width: 73px;" id="${department.id}_hostId" value="${department.hostName}" /></td>
                <td>${department.totalStaff}</td>
                <td>
                    <input type="button" onclick="updateDeapr(${department.id})" value="保存"/>
                    <input type="button" onclick="deleteDepar(${department.id}, ${department.parentId})" value="删除" />
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
</div>
<tr>
    <td>
        共${deparPageHelp.total}条&nbsp;&nbsp;${deparPageHelp.pageNum}/${deparPageHelp.totalPage}&nbsp;
    </td>
    <td>
        <button onclick="firstPage()">首页</button>
        <button onclick="beforePage()">上页</button>
        <button onclick="afterPage()">下页</button>
        <button onclick="lastPage()">尾页</button>
        &nbsp;&nbsp;
        <input type="button" onclick="directPage(${deparPageHelp.getPageSize()})" value="转到">
        <input id="pageNum" style="width:50px;">页
        <input type="button" onclick="createDepar()" value="添加部门"/>
    </td>
</tr>
</body>
<script type="text/javascript" src="./../js/leaderHeader.js"></script>
<script type="text/javascript" src="./../js/departmentsInfo.js"></script>
</html>
