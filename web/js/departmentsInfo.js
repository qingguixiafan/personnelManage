/**
 * Created by Administrator on 2017/12/20.
 */
function firstPage() {
    var rootPath = getRootPath();
    window.location.href=rootPath+"/manageDeaprInfo.action?action=first";
}

function lastPage() {
    var rootPath = getRootPath();
    window.location.href=rootPath+"/manageDeaprInfo.action?action=last";
}

function afterPage() {
    var rootPath = getRootPath();
    window.location.href=rootPath+"/manageDeaprInfo.action?action=after";
}

function beforePage() {
    var rootPath = getRootPath();
    window.location.href=rootPath+"/manageDeaprInfo.action?action=before";
}

function directPage(pageSize) {
    var pageNum = document.getElementById("pageNum").value;
    if (pageNum.length==0) {
        alert("不允许为空");
        return;
    }
    var rootPath = getRootPath();
    window.location.href = rootPath+"/manageDeaprInfo.action?pageSize="+pageSize+"&pageNum="+pageNum;
}

function updateDeapr(deparId) {
    var deparName = document.getElementById(deparId+"_name").value;
    var hostId = document.getElementById(deparId+"_hostId").value;
    var path = getRootPath();
    if (isNaN(parseInt(hostId))) {
        path = path+"/updateDeparServlet.action?deparId="
            +deparId+"&deparName="+deparName;
    } else {
        path = path+"/updateDeparServlet.action?deparId="
            +deparId+"&deparName="+deparName+"&hostId="+hostId;
    }
    window.location.href = path;
}

function deleteDepar(deparId, parent_id) {
    var r=confirm("Are you sure delete a department? No kid me");
    if (r==true) {
        var rootPath = getRootPath();
        window.location.href = rootPath+"/removeDeparServlet.action?deparId="+deparId+"&parentId="+parent_id;
    }
}

function createDepar() {
    window.location.href = getRootPath()+"/leader/createDepar.jsp";
}

function exportExcel() {
    window.location.href = getRootPath()+"/exportDeparServlet.action";
}

function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}
