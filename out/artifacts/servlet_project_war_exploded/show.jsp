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
    <title>图片预览</title>
    <link rel="stylesheet" type="text/css" href="resources/css/common.css">
    <script type="text/javascript" src="resources/js/jquery-3.0.0.js"></script>
    <script type="text/javascript">
        $(function () {
            //预览大图片
            $(".thumbs a").on('click', function () {
                var href = $(this).attr("href");
                var title = $(this).attr("title");
                $("#largeImg").attr({
                    "src": href,
                    "alt": title
                });
                return false;
            })

            var large = $("#large");
            large.hide();
            $("#previewImg").mousemove(function (e) {
                large.css({
                    "top": e.pageY,
                    "left": e.pageX
                }).show();
            }).mouseleave(function () {
                large.hide();
            })
        });

        //预览本地图片
        function handlerFiles(files) {
            if (files.length) {
                var file = files[0];
                //如果是图片
                if (/image\/\w+/.test(file.type)) {
                    var reader = new FileReader();
                    reader.readAsDataURL(file);
                    //文件加载成功
                    reader.onload = function () {
                        $("#previewImg").attr("src", reader.result);
                        $("#large").attr("src", reader.result);
                    }
                } else {
                    $("#previewImg").attr("src", "resources/images/preview.jpg");
                }
            }
        }
    </script>
</head>
<body>
<img id="previewImg" src="resources/images/preview.jpg" width="80" height="80">
<br>
<form action="unloadServlet.do" method="post" enctype="multipart/form-data">
    请选择图片：<input type="file" id="myFile" name="myFile" onchange="handlerFiles(this.files)">
    <input type="submit" value="提交">
</form>
<img id="large" src="resources/images/preview.jpg">
<br>
<h2>图片预览</h2>
<p><img id="largeImg" src="resources/images/img1-lg.jpg" alt="Large Image"/></p>
<p class="thumbs">
    <a href="resources/images/img2-lg.jpg" title="Image2"><img src="resources/images/img2-thumb.jpg"></a>
    <a href="resources/images/img3-lg.jpg" title="Image3"><img src="resources/images/img3-thumb.jpg"></a>
    <a href="resources/images/img4-lg.jpg" title="Image4"><img src="resources/images/img4-thumb.jpg"></a>
    <a href="resources/images/img5-lg.jpg" title="Image5"><img src="resources/images/img5-thumb.jpg"></a>
    <a href="resources/images/img6-lg.jpg" title="Image6"><img src="resources/images/img6-thumb.jpg"></a>
</p>
</body>
</html>
