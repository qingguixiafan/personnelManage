/**
 * Created by Administrator on 2017/12/18.
 */
$("#phone").blur(function(){
    var phone = $("#phone").val();
    if(!(/^1(3|4|5|7|8)\d{9}$/.test(phone))){
        alert("手机号码有误，请重填");
    }
});

$("#email").blur(function(){
    var emailRegex = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    var email = $("#email").val();
    if (!emailRegex.test(email)) {
        alert("邮箱有误，请重填");
    }
});

