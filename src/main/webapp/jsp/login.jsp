<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" errorPage=""%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<%
    String baseURL = request.getContextPath();
%>
<!-- Bootstrap -->
<link href="<%=baseURL%>/static/css/bootstrap.min.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
.container-login {
    margin-top: 120px;
}

.form-box {
    border-style: solid;
    border-color: gray;
    border-width: 1px;
    padding: 20px;
}

.flushInfo {
    color: red;
    top: 10px;
    right: 10px;
    position: fixed;
    border: solid 1px #B9B0B0;
    background-color: #fff;
    width: 0px;
    height: 0px;
    border-radius: 8px;
    line-height: 40px;
    text-align: center;
    z-index: 10000;
}

.alert-position {
    top: 10px;
    right: 10px;
}

.login-banner {
    position: absolute;
    left: 0;
    top: -40px;
    width: 100%;
    height: 420px;
    background: #57ACE8;
}

.w {
    width: 990px;
    margin: 0 auto;
}

.site-logo {
    width: 50%;
    height: 60px;
    background: url(<%=baseURL%>/static/image/logo-zh.gif) 50% 0px no-repeat;
}

.slider {
    width: 100%;
    height: 420px;
    position: relative;
    margin-top: 0px;
    background: url(<%=baseURL%>/static/image/login.png) 50% 0px no-repeat
        rgb(233, 244, 251);
    border-style: solid;
    border-width: 1px;
    border-color: #57ACE8;
    border-width: 1px;
}

.slider-list {
    width: 100%;
    height: 420px;
}

.login-bg {
    display: none;
}

.customer-form-box {
    position: absolute;
    margin-right: -530px;
    right: -70%;
    overflow: hidden;
    top: -365px;
    z-index: 2;
    background-color: white;
}

.a {
color: #6d6d6d;
text-decoration: none;
}

.login-warn-message {
    width: 90%;
    padding: 2px 0 2px 3px;
    left: 0px;
    top: 0px;
    border: 1px solid #ffbdbf;
    color: #e6393d;
    background: #ffebec;
    float: none;
    line-height: 16px;
    z-index: 100;
    text-align: center;
    display: none;
}

.forget-pw {
position: absolute;
right: 0;
}

.warn-show {
    display: "";
}
</style>

</head>
<body>
    <div class="site-logo"></div>
    <div class="container container-fluid container-login login-banner">
        <div class="w">
            <div class="slider">
                <div class="slider-list"></div>
            </div>
            <div class="row">
                <div class="col-sm-3">
                    <form class="form-box customer-form-box">
                        <div class="form-group login-warn-message"></div>
                        <div class="form-group">
                            <label for="exampleInputID">用户名</label>
                                <input type="text" class="form-control" id="userName" placeholder="输入用户名" autofocus />
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword">密码</label>
                                <input type="password" class="form-control" id="password" placeholder="输入密码" />
                        </div>
                        <div class="checkbox">
                            <label><a href="" target="_blank" id="forget-pw-safe" class="forget-pw" data-spm-anchor-id="a2107.1.1000341.3">忘记登录密码?</a></label>
                        </div>
                        <button type="button" class="btn btn-danger btn-block">登录</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=baseURL%>/webjars/jquery/2.1.3/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=baseURL%>/static/js/bootstrap.min.js"></script>
    <script src="<%=baseURL%>/static/js/base.js"></script>
    <script type="text/javascript">
        var contextURL = '<%= baseURL%>/api';
    </script>
</body>
</html>
