<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/12/12
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>批量上传图片</title>
    <link rel="stylesheet" type="text/css" href="resources/css/common.css">
    <script type="text/javascript" src="resources/js/jquery-3.0.0.js"></script>
</head>
<body>
<form action="smartUploadServlet.do" method="post" enctype="multipart/form-data">
    <div style="float: left;width: 20%;">
        请选择文件：
    </div>
    <div style="width: 80%;float: left;text-align: left;">
        <input type="file" id="myFile1" name="myFile1" onchange="handlerFiles(this)"><br><br>
        <input type="file" id="myFile2" name="myFile2" onchange="handlerFiles(this)"><br><br>
        <input type="file" id="myFile3" name="myFile3" onchange="handlerFiles(this)"><br><br>
        <input type="submit" value="提交">${result}
    </div>
</form>
<h1>单个下载</h1>
<a href="smartDownloadServlet.do?filename=img2-lg.jpg">img2-lg.jpg</a>
<h1>批量下载</h1>
<form action="batchDownloadServlet.do" method="post">
    <input type="checkbox" name="fileName" value="img2-lg.jpg">img2-lg.jpg
    <input type="checkbox" name="fileName" value="img3-lg.jpg">img3-lg.jpg
    <input type="checkbox" name="fileName" value="img4-lg.jpg">img4-lg.jpg
    <input type="submit" value="下载">
</form>
</body>
<script type="text/javascript">
    function handlerFiles(desFile) {
        var desId = $(desFile).attr("id");
        var files = desFile.files;
        if (files.length) {
            var file = files[0];
            var reader = new FileReader();
            //文件加载成功
            reader.onload = function () {
                var fileInfo = desId+"-info";
                if($("#"+fileInfo)){
                    $("#"+fileInfo).remove();
                }
                var size = Math.round(file.size/1024*100)/100+"kb";
                $(desFile).after("<span id='"+fileInfo+"'>"+size+"</span>");
            }
            reader.readAsBinaryString(file);
        }
    }
</script>
</html>
