<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/12/12
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.css">
    <style>
        body{
            padding-top: 20px;
            padding-left: 20px;
        }
    </style>
</head>
<body>
<a class="btn btn-primary" href="<%=request.getContextPath()%>/show.jsp">上传文件</a>
<br><br>
<a class="btn btn-primary" href="<%=request.getContextPath()%>/upload.jsp">批量上传文件</a>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.0.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.0.0.js"></script>
</html>
