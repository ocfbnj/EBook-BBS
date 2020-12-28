package cn.ocfbnj.ebookbbs.servlet;

import cn.ocfbnj.ebookbbs.domain.Book;
import cn.ocfbnj.ebookbbs.domain.Page;
import cn.ocfbnj.ebookbbs.service.BookService;
import cn.ocfbnj.ebookbbs.service.impl.BookServiceImpl;
import cn.ocfbnj.ebookbbs.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookServlet/*")
public class BookServlet extends BasicServlet {
    private final BookService bookService = new BookServiceImpl();

    //分页
    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求的参数pageNo 和 pageSize;如果没有传入，第一次访问则默认是展示第一页
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        //如果没有传入页数的参数，则默认显示Page.PAGE_SIZE
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用service.page(pageNo, pageSize)，返回page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("bookServlet/page");
        //保存page对象到request域中
        req.setAttribute("page", page);
        System.out.println("信息是：" + req.getParameter("pageNo"));
        System.out.println("访问了");
        //请求转发到显示页面
        req.getRequestDispatcher("/containBooks.jsp").forward(req, resp);
    }

    //    页面按价格从小到大排序输出
    public void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("信息是："+req.getParameter("pageNo"));
        //获取请求的参数pageNo 和 pageSize;如果没有传入，第一次访问则默认是展示第一页
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        //如果没有传入页数的参数，则默认显示Page.PAGE_SIZE
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用service.page(pageNo, pageSize)，返回page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize);
        page.setUrl("bookServlet/pageByPrice");
        //保存page对象到request域中
        req.setAttribute("page", page);
        //请求转发到显示页面
        req.getRequestDispatcher("/containBooks.jsp").forward(req, resp);
    }

    //    页面按价格从小到大排序输出
    public void pageByDownload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("信息是："+req.getParameter("pageNo"));
        //获取请求的参数pageNo 和 pageSize;如果没有传入，第一次访问则默认是展示第一页
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        //如果没有传入页数的参数，则默认显示Page.PAGE_SIZE
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用service.page(pageNo, pageSize)，返回page对象
        System.out.println("servlet" + pageNo + ":" + pageSize);
        Page<Book> page = bookService.pageByDownload(pageNo, pageSize);
        page.setUrl("bookServlet/pageByDownload");
        //保存page对象到request域中
        req.setAttribute("page", page);
        //请求转发到显示页面
        req.getRequestDispatcher("/containBooks.jsp").forward(req, resp);
    }
}
