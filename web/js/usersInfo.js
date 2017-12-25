/**
 * Created by Administrator on 2017/12/19.
 */
function firstPage() {
    var rootPath = getRootPath();
    window.location.href=rootPath+"/manageUserInfo.action?action=first";
}

function lastPage() {
    var rootPath = getRootPath();
    window.location.href=rootPath+"/manageUserInfo.action?action=last";
}

function afterPage() {
    var rootPath = getRootPath();
    window.location.href=rootPath+"/manageUserInfo.action?action=after";
}

function beforePage() {
    var rootPath = getRootPath();
    window.location.href=rootPath+"/manageUserInfo.action?action=before";
}

function directPage(pageSize) {
    var pageNum = document.getElementById("pageNum").value;
    if (pageNum.length==0) {
        alert("不允许为空");
        return;
    }
    var rootPath = getRootPath();
    window.location.href = rootPath+"/manageUserInfo.action?pageSize="+pageSize+"&pageNum="+pageNum;
}

function deleteUser(userId) {
    console.log("删除一个员工"+userId);
    var rootPath = getRootPath();
    window.location.href = rootPath+"/removeUser.action?userId="+userId;

}

function updateUser(userId) {
    var salary_id = userId+"_salary";
    var role_id = userId+"_role";
    var role = document.getElementById(role_id).value;
    var salary = document.getElementById(salary_id).value;
    // 这里写的很难受，input框中的值只能通过js获取到，可是js不能封装java对象
    // 这里不好，发起ajax请求会有回调函数，servlet中的页面跳转就不起作用了,不过问题不大
    // fixme: 代码重构后思考换种方式
    var url = getRootPath()+"/UserUpdateBySelecterServlet.action"
    $.ajax({
        type: 'POST',
        url: url,
        data: {
            id: userId,
            role: role,
            salary: salary
        },
        dataType: 'json',
        contentType: 'application/x-www-form-urlencoded',
        success: function(data){
            console("is success");
        },
        error: function(jqXHR){
            console.log("is fail");
        },
    })
}

function createUser() {
    window.location.href = getRootPath()+"/leader/createUser.jsp";
}

function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}
