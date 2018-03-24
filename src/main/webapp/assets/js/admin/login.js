$(function () {
    $("#verificationCodeBtn").click(function () {
        $("#verificationCodeImage").attr("src",ctx + "/verificationCode?v=" + new Date());
    });

    $("#loginBtn").click(function () {
        $.ajax({
            type : "POST", //请求方式：POST/GET
            url : ctx + "/doLogin", //请求地址
            dataType : "json", //服务器返回的数据类型（text/xml/json）
            data : { //传给服务器的参数
                username : $("#username").val(),
                password : $("#password").val(),
                verificationCode : $("#verificationCode").val()
            },
            success : function (result) { //成功回调的方法
                console.log(result);
                if(result.status == 0){
                    window.location = "/index";
                }else {
                    var msg = "<span style='color: red;'>" + result.message + "</span>"
                    $(".login-box-msg").html(msg);
                }
            },
            error : function (error) { //失败回调方法
                console.log(error);
            }
        });
    });
});
