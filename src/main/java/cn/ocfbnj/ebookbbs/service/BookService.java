package cn.ocfbnj.ebookbbs.service;

import cn.ocfbnj.ebookbbs.domain.Book;
import cn.ocfbnj.ebookbbs.domain.Page;

public interface BookService {
    //分页
    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize);

    Book getBook(int bid);
}
