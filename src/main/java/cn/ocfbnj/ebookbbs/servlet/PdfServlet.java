package cn.ocfbnj.ebookbbs.servlet;

import cn.ocfbnj.ebookbbs.domain.Book;
import cn.ocfbnj.ebookbbs.service.impl.BookServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@WebServlet("/pdf/*")
public class PdfServlet extends BasicServlet {

    public void read(HttpServletRequest req, HttpServletResponse resp) {
        int bid = Integer.parseInt(req.getParameter("bid"));
        System.out.println("success:" + bid);
        String s0 = "E:/file/eff.pdf";
        Book book = new BookServiceImpl().getBook(bid);
        String bookPath = book.getDownloadPath();
        if (bookPath != null) {
            s0 = bookPath;
        }
        System.out.println(s0);
        //跨域请求

        resp.setHeader("Access-Control-Allow-Origin", "*");
        File file = new File(s0);
        resp.setContentLength((int) file.length());
        try {
            resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
            // 设置在下载框默认显示的文件名
            resp.setContentType("application/octet-stream");// 指明response的返回对象是文件流
            // 读出文件到response
            // 这里是先需要把要把文件内容先读到缓冲区
            // 再把缓冲区的内容写到response的输出流供用户下载
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            OutputStream outputStream = resp.getOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = bufferedInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            // 人走带门
            bufferedInputStream.close();
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
