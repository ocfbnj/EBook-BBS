package cn.ocfbnj.ebookbbs.service.impl;

import cn.ocfbnj.ebookbbs.dao.BookDao;
import cn.ocfbnj.ebookbbs.dao.impl.BookDaoImpl;
import cn.ocfbnj.ebookbbs.domain.Book;
import cn.ocfbnj.ebookbbs.domain.Page;
import cn.ocfbnj.ebookbbs.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao = new BookDaoImpl();

    //分页
    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();
        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录数和设置总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //求总页码并设置总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }
        page.setPageTotal(pageTotal);

        //设置当前页码
        page.setPageNo(pageNo);
        //求当前页的数据集
        //求当前页的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //获取当前页数据
        List<Book> item = bookDao.queryForPageItems(begin, pageSize);
        //保存当前页数据
        page.setItems(item);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();
        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录数和设置总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //求总页码并设置总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }
        page.setPageTotal(pageTotal);

        //设置当前页码
        page.setPageNo(pageNo);
        //求当前页的数据集
        //求当前页的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //获取当前页数据
        List<Book> item = bookDao.queryForPageItemsByPrice(begin, pageSize);
        //保存当前页数据
        page.setItems(item);
        return page;
    }

}
