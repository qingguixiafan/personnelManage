/**
 * Created by Administrator on 2017/12/14.
 */

$("#username").blur(function(){
    var username = $("#username").val();
    if (username.length==0){
        $("p").html("不许为空");
        $("#image").attr("src", "http://localhost:8686/personnelManage/upload/favicon.ico");
        return;
    }
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8686/personnelManage/checkUserName.action',
        data: {
            username: username
        },
        dataType: 'json',
        contentType: 'application/x-www-form-urlencoded',
        success: function(data){
            console.log("成功返回");
            if (data.name.length==0) {
                $("p").html("用户名不存在");
            } else {
                $("p").html("");
            }
            $("#image").attr("src", data.image);
        },
        error: function(jqXHR){
            console.log("fail");
        },
    })
});

function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}
