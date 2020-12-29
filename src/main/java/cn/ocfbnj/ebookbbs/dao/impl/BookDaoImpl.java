package cn.ocfbnj.ebookbbs.dao.impl;

import cn.ocfbnj.ebookbbs.dao.BookDao;
import cn.ocfbnj.ebookbbs.domain.Book;

import java.util.List;

public class BookDaoImpl extends OutBaseDao implements BookDao {

    //返回总书籍总记录数
    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from ebook";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }


    @Override
    public Integer queryForPageTotalCountClassify(String classify) {
        String sql = "select count(*) from ebook where classify = '" + classify + "'";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }


    //返回当前页显示的书籍信息，存放在List里，第一个问号表示从哪条记录开始，第二个问好表示每页的记录数
    //第一个问号是0，因为要从数据库表中读取，表的第一个是从0开始的
    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select * from ebook limit ?,?";
        return queryForList(sql, Book.class, begin, pageSize);
    }

    //按照价格从低到高查询该页的结果集
    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize) {
        String sql = "select * from ebook order by price limit ?, ?";
        return queryForList(sql, Book.class, begin, pageSize);
    }

    public List<Book> queryForPageItemsByClassify(String classify, int begin, int pageSize) {
        String sql = "select * from ebook where classify = '" + classify + "' limit ? ,?";
        System.out.println(begin + ":" + pageSize);
        return queryForList(sql, Book.class, begin, pageSize);
    }

    @Override
    public Book queryByID(int bid) {
        String sql = "select * from ebook where ebook_id=?";
        return queryForOne(sql, Book.class, bid);
    }
}
