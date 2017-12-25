/**
 * Created by Administrator on 2017/12/19.
 */
function gotoManageUsreInfo() {
    var rootPath = getRootPath();

    // 这里要拼接分页参数
    //pageNum=1&pageSize=3&order_by=salary&sort=asc
    window.location.href = rootPath+"/manageUserInfo.action?pageNum=1&pageSize=3";
}

function gotoManageDeparInfo() {
    var rootPath = getRootPath();
    window.location.href = rootPath+"/manageDeaprInfo.action?pageNum=1&pageSize=3";
}

function goUserInfo() {
    var rootPath = getRootPath();
    window.location.href = rootPath+"/userShow.action";
}

function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}
