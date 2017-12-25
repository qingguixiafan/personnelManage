/**
 * Created by Administrator on 2017/12/17.
 */
function userShow() {
    var rootPath = getRootPath();
    window.location.href=rootPath+"/userShow.action";
}

function userUpdate() {
    //var local = location.href.substring(0,location.href.lastIndexOf('/'));
    var rootPath = getRootPath();
    console.log(rootPath);
    window.location.href=rootPath+"/userUpdate.action";
}

function logout() {
    // 这里很烦，session在服务端，要清除session必须发ajax请求道后端去清除
    var rootPath = getRootPath();
    window.location.href=rootPath+"/logout.action";
}

function gotoManageUsreInfo() {
    var rootPath = getRootPath();
    window.location.href = rootPath+"/manageUserInfo.action?pageNum=1&pageSize=3";
    // &order_by=salary&sort=asc
}





//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}
