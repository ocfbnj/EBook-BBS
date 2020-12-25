package cn.ocfbnj.ebookbbs.service.impl;

import cn.ocfbnj.ebookbbs.dao.CommentDao;
import cn.ocfbnj.ebookbbs.dao.impl.CommentDaoImpl;
import cn.ocfbnj.ebookbbs.domain.Comment;
import cn.ocfbnj.ebookbbs.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao = new CommentDaoImpl();

    @Override
    public List<Comment> getComments(int ebookID) {
        return commentDao.findByEBookID(ebookID);
    }

    @Override
    public boolean addComment(int ebookID, int userID, String content) {
        return commentDao.addComment(ebookID, userID, content);
    }
}
