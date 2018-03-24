$(function () {
    $("#verificationCodeBtn").click(function () {
        $("#verificationCodeImage").attr("src","/verificationCode?v=" + new Date());
    });

    $("#loginBtn").click(function () {
        $.ajax({
            type : "POST", //请求方式：POST/GET
            url : "user/doLogin", //请求地址
            dataType : "json", //服务器返回的数据类型（text/xml/json）
            data : { //传给服务器的参数
                username : $("#username").val(),
                password : $("#password").val(),
                verificationCode : $("#verificationCode").val()
            },
            success : function (result) { //成功回调的方法
                console.log(result);
                //window.location = "/index";
            },
            error : function (error) { //失败回调方法
                console.log(error);
            }
        });
    });
});
