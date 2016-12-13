package servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by admin on 2016/12/12.
 */
public class UnloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从request中获取流信息
        InputStream fileSource = req.getInputStream();
        String tempFileDir = "e:\\tempFile";
        File tempFile = new File(tempFileDir);
        FileOutputStream out = new FileOutputStream(tempFile);

        byte[] buff = new byte[1024];
        int b;
        while ((b = fileSource.read(buff)) != -1) {
            out.write(buff, 0, b);
        }
        out.close();
        fileSource.close();

        RandomAccessFile raf = new RandomAccessFile(tempFile, "r");
        //移动到文件头
        raf.seek(0);
        long startPos = 0;
        int k = 1;
        while ((b = raf.readByte()) != -1 && k <= 4) {
            if (b == '\n') {//换行符
                startPos = raf.getFilePointer();
                k++;
            }
        }
        raf.seek(raf.length());//获取文件内容的结束位置
        long endPos = raf.getFilePointer();
        int j = 1;
        while (endPos >= 0 && j <= 2) {
            endPos--;
            raf.seek(endPos);//指针移动
            if (raf.readByte() == '\n') {
                j++;
            }
        }
        endPos = endPos - 1;
        //获取文件名
        raf.seek(0);//移动到文件头
        raf.readLine();//读完一行
        String tempStr = raf.readLine();
        int start = tempStr.lastIndexOf("=\"");
        int end = tempStr.lastIndexOf("\"");
        String realFileName = tempStr.substring(start + 2, end);//文件名
        String realDir = getServletContext().getRealPath("/");
        File fileDir = new File(realDir+"\\resources\\images");//文件夹
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File realFile = new File(fileDir, realFileName);//创建文件
        RandomAccessFile randomAccessFile = new RandomAccessFile(realFile, "rw");
        raf.seek(startPos);
        while (startPos < endPos) {
            randomAccessFile.writeByte(raf.readByte());
            startPos = raf.getFilePointer();
        }
        raf.close();
        randomAccessFile.close();
        tempFile.delete();
    }
}
