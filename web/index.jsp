<%@ page import="util.CommonUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/11/20
  Time: 3:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //生产随机数
    String randomStr = CommonUtil.getRandomStr(10);
    request.getSession().setAttribute("randomStr", randomStr);
%>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
    <script src="resources/js/jquery-3.0.0.js"></script>
    <script src="resources/js/bootstrap.js"></script>
    <script src="resources/js/md5.js"></script>
    <style>
        .container {
            margin-top: 100px;
        }

        .container h2 {
            text-align: center;
        }

        .container .panel-body {
            margin: 30px auto;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading"><h2>登录界面</h2></div>
        <div class="panel-body">
            <form id="loginForm" class="form-horizontal" role="form" action="<%=request.getContextPath()%>/login.do"
                  method="post">
                <div class="form-group">
                    <label for="userName" class="col-md-5 control-label">用户名</label>
                    <div class="col-md-3">
                        <input class="form-control" name="userName" id="userName" placeholder="请输入邮箱"
                               oninput="checkNameBychange()" onblur="checkNameBychange()">
                    </div>
                    <label class="col-md-4" id="userNameWaring"></label>
                </div>
                <div class="form-group">
                    <label for="userPassword" class="col-md-5 control-label">密码</label>
                    <div class="col-md-3">
                        <input type="password" class="form-control" id="userPassword"
                               placeholder="请输入秘密" onblur="checkPasswordByChange()" oninput="checkPasswordByChange()">
                        <input type="hidden" id="text_password" name="text_password">
                    </div>
                    <label class="col-md-4" id="passwordWarning"></label>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5 col-md-3">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" id="remember" name="remember" value="rememberMe">记住我
                            </label>
                        </div>
                    </div>
                </div>
                <div class="from-group">
                    <div class="col-md-offset-5 col-md-1">
                        <button type="button" onclick="checkLogin()" class="btn btn-primary">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</button>
                    </div>
                    <label class="col-md-6" id="errorInfo"></label>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        remUserInfo();
    })

    //记住用户信息
    function remUserInfo() {
        var userCookie = getCookie("user");
        if (userCookie) {
            userCookie = userCookie.replace(/^(user=)(\w+)/, '$2');
            var userCookieArr = userCookie.split("^");
            $("#userName").val(userCookieArr[0]);
            $("#userPassword").val("######");
            $("#text_password").val(userCookieArr[1]);
            $("#remember").attr("checked", "checked");
        }
    }

    //登录验证
    function checkLogin() {
        var userPassword = $("#userPassword").val();
        if (!checkNameBychange() || !checkPasswordByChange()) {
            return false;
        }
        <%--var text_password = hex_md5(userPassword+'${randomStr}');--%>
        if (userPassword != '######') {
            var text_password = hex_md5(userPassword);
            $("#text_password").val(text_password);
        }
        //提交数据
        $.ajax({
            type: 'post',
            dataType: 'html',
            data: $("#loginForm").serialize(),
            async: false,
            url: $("#loginForm").attr('action'),
            success: function (data) {
                var errorInfo = JSON.parse(data).error;
                if('pass' == errorInfo){
                    window.location.href = '${pageContext.request.contextPath}'+"/jumpToFirstPage.do";
                }else {
                    $("#errorInfo").html(errorInfo).css({color:'red'});
                }
            }
        });
    }

    //用户输入框信息改变提示用户
    function checkNameBychange() {
        var userName = $("#userName").val();
        if ($.trim(userName) == "") {
            $("#userNameWaring").html("* 用户不能为空").css({color: "red"});
        } else if (!checkUserName(userName)) {
            $("#userNameWaring").html("* 用户名不合法").css({color: "red"});
        } else {
            $("#userNameWaring").html("* 合法").css({color: "green"});
        }
        return checkUserName(userName);
    }

    //秘密输入框信息改变提示用户
    function checkPasswordByChange() {
        var userPassword = $("#userPassword").val();
        //如果是默认记住密码，过虐掉验证
        if (userPassword == '######') {
            return true;
        }
        if ($.trim(userPassword) == "") {
            $("#passwordWarning").html("* 密码不能为空").css({color: 'red'});
        } else if (!checkPassword(userPassword)) {
            $("#passwordWarning").html("* 密码只能包含字母，数字，下划线，长度为6到30").css({color: 'red'});
        } else {
            $("#passwordWarning").html("");
        }
        return checkPassword(userPassword);
    }

    //验证密码
    function checkPassword(password) {
        var reg = /^\w{6,30}$/;
        if (reg.test(password)) {
            return true;
        }
        return false;
    }

    //验证用户名
    function checkUserName(userName) {
        var reg = /^[a-zA-Z0-9]+([._-][a-zA-Z0-9]+)*@([a-zA-Z0-9]+\.)+[a-zA-Z0-9]{2,5}$/;
        if (reg.test(userName)) {
            return true;
        }
        return false;
    }

    //获得cookie
    function getCookie(name) {
        var cookies = document.cookie;
        var cookieArr = cookies.split(";");
        if (cookieArr != null && cookieArr.length > 0) {
            for (var i = 0; i < cookieArr.length; i++) {
                var cookie = cookieArr[i];
                var reg = new RegExp("^" + name + "=\\w+");
                if (reg.test(cookie)) {
                    return cookie;
                }
            }
        }
        return null;
    }
</script>
</html>
