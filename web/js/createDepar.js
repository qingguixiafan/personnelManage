/**
 * Created by Administrator on 2017/12/25.
 */

function check() {
    var depar_name = $("#deparName").val();
    var host_id = $("#hostId").val();
    if (depar_name.length==0){
        alert("请输入部门名称");
        return false;
    }
    if (host_id.length==0){
        alert("请输入部门主管");
        return false;
    }
    document.createDepar.submit();
}
