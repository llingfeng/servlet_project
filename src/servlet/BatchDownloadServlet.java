package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by admin on 2016/12/14.
 */
public class BatchDownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置下载文件的文件类型和头文件
        resp.setContentType("application/x-msdownload");
        resp.setHeader("Content-Disposition", "attachment;filename=test.zip");
        String str = "";
        String rt = "\r\n";
        String path = getServletContext().getRealPath("/") + "resources//images//";
        String[] fileNames = req.getParameterValues("fileName");
        ZipOutputStream zos = new ZipOutputStream(resp.getOutputStream());
        for (String fileName : fileNames) {
            str += fileName + rt;
            File file = new File(path + fileName);
            zos.putNextEntry(new ZipEntry(fileName));
            FileInputStream fis = new FileInputStream(file);
            byte[] buff = new byte[1024*4];
            int b = 0;
            while ((b = fis.read(buff)) != -1) {
                zos.write(buff, 0, b);
            }
            zos.flush();
            fis.close();
        }
        zos.setComment("download success"+str);
        zos.close();
    }
}
