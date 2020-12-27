package cn.ocfbnj.ebookbbs.servlet;

import cn.ocfbnj.ebookbbs.domain.Book;
import cn.ocfbnj.ebookbbs.domain.Comment;
import cn.ocfbnj.ebookbbs.domain.ResultInfo;
import cn.ocfbnj.ebookbbs.service.BookService;
import cn.ocfbnj.ebookbbs.service.CommentService;
import cn.ocfbnj.ebookbbs.service.impl.BookServiceImpl;
import cn.ocfbnj.ebookbbs.service.impl.CommentServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ebook/*")
public class EBookServlet extends BasicServlet {
    private static final long serialVersionUID = -6587895130609371116L;

    private final CommentService commentService = new CommentServiceImpl();
    private final BookService bookService = new BookServiceImpl();
    private final ResultInfo resultInfo = new ResultInfo();

    public void getComments(HttpServletRequest req, HttpServletResponse resp) {
        String ebookID = req.getParameter("bid");
        System.out.println(ebookID);

        List<Comment> comments = commentService.getComments(Integer.parseInt(ebookID));
        System.out.println(comments);
        try {
            writeValue(resp, comments);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addComment(HttpServletRequest req, HttpServletResponse resp) {
        String content = req.getParameter("content");
        int bid = Integer.parseInt(req.getParameter("bid"));
        int uid = Integer.parseInt(req.getParameter("uid"));

        boolean ok = commentService.addComment(bid, uid, content);
        resultInfo.setFlag(ok);

        try {
            writeValue(resp, resultInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getBook(HttpServletRequest req, HttpServletResponse resp) {
        int bid = Integer.parseInt(req.getParameter("bid"));
        Book book = bookService.getBook(bid);
        try {
            writeValue(resp, book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
