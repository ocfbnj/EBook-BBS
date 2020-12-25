package cn.ocfbnj.ebookbbs.domain;

import java.util.Date;

public class Comment {
    private int commentID;
    private int parentCommentID;
    private String content;
    private int userID;
    private int ebookID;
    private Date date;
    private String username;

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getParentCommentID() {
        return parentCommentID;
    }

    public void setParentCommentID(int parentCommentID) {
        this.parentCommentID = parentCommentID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getEbookID() {
        return ebookID;
    }

    public void setEbookID(int ebookID) {
        this.ebookID = ebookID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentID=" + commentID +
                ", parentCommentID=" + parentCommentID +
                ", content='" + content + '\'' +
                ", userID=" + userID +
                ", ebookID=" + ebookID +
                ", date=" + date +
                ", username='" + username + '\'' +
                '}';
    }
}
