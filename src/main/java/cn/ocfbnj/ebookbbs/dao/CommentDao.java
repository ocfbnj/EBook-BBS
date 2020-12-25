package cn.ocfbnj.ebookbbs.dao;

import cn.ocfbnj.ebookbbs.domain.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> findByEBookID(int ebookID);

    boolean addComment(int ebookID, int userID, String content);
}
