package cn.ocfbnj.ebookbbs.service;

import cn.ocfbnj.ebookbbs.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getComments(int ebookID);

    boolean addComment(int ebookID, int userID, String content);
}
