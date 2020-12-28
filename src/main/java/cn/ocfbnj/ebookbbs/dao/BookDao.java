package cn.ocfbnj.ebookbbs.dao;

import cn.ocfbnj.ebookbbs.domain.Book;

import java.util.List;

public interface BookDao {
    //查询总记录数
    Integer queryForPageTotalCount();

    Integer queryForPageTotalCountDownload();

    //查询图书信息
    List<Book> queryForPageItems(int begin, int pageSize);

    //按价格查询结果集
    List<Book> queryForPageItemsByPrice(int begin, int pageSize);

    List<Book> queryForPageItemsByDownload(int begin, int pageSize);

    // 查询一本图书
    Book queryByID(int bid);
}
