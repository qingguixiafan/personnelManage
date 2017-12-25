/**
 * Created by Administrator on 2017/12/24.
 */
$("input").blur(function(){
    var username = $("#username").val();
    console.log("no null");
    if (username.length==0){
        $("#name_p").html("用户名不许为空");
    } else {
        $("#name_p").html("");
    }
});

$("input").blur(function(){
    var salary = $("#salary").val();
    if (salary.length==0) {
        $("#name_p").html("工资不许为空");
    } else {
        $("#name_p").html("");
    }
});