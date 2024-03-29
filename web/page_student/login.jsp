<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/09/27
  Time: 下午 2:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>幻萌教务系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="../lid/layui-v2.5.5/css/layui.css" media="all">
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        html, body {width: 100%;height: 100%;overflow: hidden}
        body {background: #1E9FFF;}
        body:after {content:'';background-repeat:no-repeat;background-size:cover;-webkit-filter:blur(3px);-moz-filter:blur(3px);-o-filter:blur(3px);-ms-filter:blur(3px);filter:blur(3px);position:absolute;top:0;left:0;right:0;bottom:0;z-index:-1;}
        .layui-container {width: 100%;height: 100%;overflow: hidden}
        .admin-login-background {width:360px;height:300px;position:absolute;left:50%;top:40%;margin-left:-180px;margin-top:-100px;}
        .logo-title {text-align:center;letter-spacing:2px;padding:14px 0;}
        .logo-title h1 {color:#1E9FFF;font-size:25px;font-weight:bold;}
        .login-form {background-color:#fff;border:1px solid #fff;border-radius:3px;padding:14px 20px;box-shadow:0 0 8px #eeeeee;}
        .login-form .layui-form-item {position:relative;}
        .login-form .layui-form-item label {position:absolute;left:1px;top:1px;width:38px;line-height:36px;text-align:center;color:#d2d2d2;}
        .login-form .layui-form-item input {padding-left:36px;}
        .captcha {width:60%;display:inline-block;}
        .captcha-img {display:inline-block;width:34%;float:right;}
        .captcha-img img {height:34px;border:1px solid #e6e6e6;height:36px;width:100%;}
    </style>
</head>
<body>
<div class="layui-container">
    <div class="admin-login-background">
        <div class="layui-form login-form">
            <form class="layui-form" action="">
                <div class="layui-form-item logo-title">
                    <h1>幻萌教务系统-学生</h1>
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-username" <%--for="username"--%>></label>
                    <input type="text" id="lname" name="username" lay-verify="required|account" placeholder="用户名或者邮箱" autocomplete="off" class="layui-input" value="admin">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" <%--for="password"--%>></label>
                    <input type="password" id="lpass" name="password" lay-verify="required|password" placeholder="密码" autocomplete="off" class="layui-input" value="123456">
                </div>
                <div class="layui-form-item" id="temp">

                </div>
                <div class="layui-form-item">
                    <input type="checkbox" name="rememberMe" value="true" lay-skin="primary" title="记住密码">
                </div>
                <div class="layui-form-item">
                    <button onclick="login()" class="layui-btn layui-btn layui-btn-normal layui-btn-fluid" lay-submit="" lay-filter="login">登 录</button>
                </div>
                <!-- <div class="layui-form-item">
                    <input class="layui-btn layui-btn layui-btn-normal layui-btn-fluid"  id="weibo" value="扫码登录" >
                </div> -->
            </form>
        </div>
    </div>
</div>
<script src="../lid/jquery-3.4.1/jquery-3.4.1.min.js" charset="utf-8"></script>
<script src="../lid/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="../lid/jq-module/jquery.particleground.min.js" charset="utf-8"></script>
<script src="../js/config.js"></script>
<script>
    function login() {
        let lname = $("#lname").val();
        let lpass = $("#lpass").val();
        let temp = $("#temp");
        $.ajax({
            url:"lg.do",
            method:'post',
            data: {"name":lname,"pass":lpass},
            success:function(res){
                if(res == "ok"){
                    window.location = "student_index.jsp";
                }
                if(res == "error"){
                    temp.html("账号或密码错误");
                    alert(temp.val());
                    return;
                }
            }
        });
    }
</script>
<script>

    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.jquery;


        var client_id = '';
        var redirect_uri = 'https://m30606.jp.cdjxt.net/api';
        // // 登录过期的时候，跳出ifram框架
        // if(top.location != self.location) top.location = self.location;

        // 粒子线条背景
        $(document).ready(function(){
            $('.layui-container').particleground({
                dotColor:'#7ec7fd',
                lineColor:'#7ec7fd'
            });
        });

        // $('#weibo').click(function(){
        //     window.location.href = 'https://api.weibo.com/oauth2/authorize?client_id=' + client_id +
        //              '&redirect_uri=' + redirect_uri + '&response_type=code';
        // })

        // 进行登录操作
        form.on('submit(login)', function (data) {
            data = data.field;

            if (data.username == '') {
                layer.msg('用户名不能为空');
                return false;
            }
            if (data.password == '') {
                layer.msg('密码不能为空');
                return false;
            }

            /*$.ajax({
                url: realm_name + '/login',
                method: "POST",
                data:data,
                success:function(res){
                    if(res.code == "0"){

                        //console.log('取出的token为'+layui.data('table').token);

                        if(res.extend.role == "student" ){
                            //token写入local storage
                            layui.data('table', {
                                key: "token",
                                value: res.extend.token
                            });
                            layui.data('role', {
                                key: "role",
                                value: res.extend.role
                            });
                            layer.msg('登录成功', function () {
                                window.location = '../index-student.html';
                            });
                        }else{
                            layer.msg('账号或密码错误');
                        }
                    }else{
                        layer.msg('账号或密码错误');
                    }

                },
                error:function(res){
                    layer.alert(res.message);
                }
            })
            return false;*/
        });
    });
</script>
</body>
</html>
