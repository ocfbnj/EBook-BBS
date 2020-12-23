package cn.ocfbnj.ebookbbs.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ebook/*")
public class EBookServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        // uri: /ebook/53222565
        String uri = req.getRequestURI();

        // bID: 53222565
        String bID = uri.substring(uri.lastIndexOf('/') + 1);
    }
}
