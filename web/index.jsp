<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/11/20
  Time: 3:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
    <script type="text/javascript" src="resources/js/jquery-3.0.0.js"></script>
    <script type="text/javascript" src="resources/js/bootstrap.js"></script>
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
            <form class="form-inline" role="form" action="<%=request.getContextPath()%>/login.do" method="post">
                <div class="row">
                    <div class="col-md-5" style="text-align: right">
                        <label for="userName">用户名</label>
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" name="userName" id="userName" placeholder="请输入用户名">
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-5" style="text-align: right">
                        <label for="userPassword">密&nbsp;&nbsp;&nbsp;码</label>
                    </div>
                    <div class="col-md-6">
                        <input type="password" class="form-control" name="userPassword" id="userPassword"
                               placeholder="请输入秘密">
                    </div>
                </div>
                <div style="width: 100%;text-align: center">
                    <button class="btn btn-primary" style="margin-top: 50px;width: 15%;" onclick="checkLogin()">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function checkLogin() {

    }
</script>
</html>
