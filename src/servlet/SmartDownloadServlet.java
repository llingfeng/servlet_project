package servlet;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2016/12/14.
 */
public class SmartDownloadServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("filename");
        SmartUpload upload = new SmartUpload();
        upload.initialize(getServletConfig(),req,resp);
        try {
            upload.downloadFile(getServletContext().getRealPath("/")+"resources//images//"+fileName);
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("upload.jsp").forward(req,resp);
    }
}
