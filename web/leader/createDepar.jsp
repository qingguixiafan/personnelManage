<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>创建部门</title>
    <link rel="stylesheet" type="text/css" href="./../css/common.css" />
</head>
<body>
<h2>添加部门页</h2>
<form name="createDepar" action="./../addDepartmentServlet.action" method="post">
    <table>
        <tr>
            <td>部门名称：</td>
            <td><input name="depar_name" id="deparName"/></td>
        </tr>
        <tr>
            <td>部门主管：</td>
            <td><input name="host_id" id="hostId"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input onclick="return check();" id="save" type="submit" value="保存">
            </td>
        </tr>
    </table>
</form>
</body>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="./../js/createDepar.js"></script>
</html>
