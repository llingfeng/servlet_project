package servlet;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by admin on 2016/12/14.
 */
public class SmartUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建保存路径
        String filePath = req.getServletContext().getRealPath("/") + "resources//images";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        SmartUpload upload = new SmartUpload();
        //初始化对象
        upload.initialize(getServletConfig(), req, resp);
        //设置单个文件大小
        upload.setMaxFileSize(1024 * 1024 * 10);
        //设置总文件大小
        upload.setTotalMaxFileSize(1024 * 1024 * 10 * 10);
        //设置允许上传的文件类型
        upload.setAllowedFilesList("txt,jpg,png");
        String result = "";
        //设置不允许上传的文件类型
        try {
            upload.setDeniedFilesList("rar,jsp,js");
            //上传
            upload.upload();

            int count = upload.save(filePath);
            result = "上传成功";
            System.out.println("上传成功了" + count + "个文件");
        } catch (Exception e) {
            result = "上传失败";
            if(e.getMessage().indexOf("1010") != -1){
                result = "上传失败，上传文件类型不正确";
            }
            e.printStackTrace();
        }
        for(int i=0;i<upload.getFiles().getCount();i++){
            com.jspsmart.upload.File tempFile = upload.getFiles().getFile(i);
            System.out.println("================================");
            System.out.println("表单当中name的值："+tempFile.getFieldName());
            System.out.println("上传文件名："+tempFile.getFileName());
            System.out.println("上传文件大小："+tempFile.getSize());
            System.out.println("上传文件扩展名："+tempFile.getFileExt());
            System.out.println("上传文件路径："+tempFile.getFilePathName());
            System.out.println("================================");
        }
        req.setAttribute("result",result);
        req.getRequestDispatcher("upload.jsp").forward(req,resp);
    }
}
