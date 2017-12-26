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
    <title>创建新用户</title>
    <link rel="stylesheet" type="text/css" href="./../css/common.css" />
</head>
<body>
<h2>添加员工页</h2>
<form name="" action="./../addUserServlet.action" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input name="username" type="text"/></td>
            <td><p id="name_p"></p></td>
        </tr>
        <tr>
            <td>性别：</td>
            <td>
                <input type="radio" name="sex" value="1" checked/>男
                <input type="radio" name="sex" value="0"/>女
            </td>
        </tr>
        <tr>
            <td>工资：</td>
            <td><input name="salary"/></td>
            <td><p id="salry_p"></p></td>
        </tr>
        <tr>
            <td>身份：</td>
            <td><input type="radio" name="role" value="1" />部门主管
                <input type="radio" name="role" value="0" checked/>普通员工
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">保存</button>
            </td>
        </tr>
    </table>
</form>
</body>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="./../js/createUser.js "></script>
</html>
