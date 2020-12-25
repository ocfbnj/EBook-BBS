package cn.ocfbnj.ebookbbs.dao.impl;

import cn.ocfbnj.ebookbbs.dao.CommentDao;
import cn.ocfbnj.ebookbbs.domain.Comment;
import cn.ocfbnj.ebookbbs.utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl extends BaseDao implements CommentDao {
    private final DataSource dataSource = DataBaseUtil.getDataSource();

    @Override
    public List<Comment> findByEBookID(int ebookID) {
        List<Comment> comments = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            String sql = "select * from ebook_comment where ebook_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, ebookID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Comment comment = new Comment();

                comment.setCommentID(resultSet.getInt("comment_id"));
                comment.setContent(resultSet.getString("content"));
                comment.setParentCommentID(resultSet.getInt("parent_comment_id"));
                comment.setDate(resultSet.getDate("date"));
                comment.setEbookID(resultSet.getInt("ebook_id"));
                comment.setUserID(resultSet.getInt("user_id"));
                comment.setUsername(resultSet.getString("username"));

                comments.add(comment);
            }

            return comments;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean addComment(int ebookID, int userID, String content) {
        Connection connection = null;
        try {
            connection = getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        assert connection != null;

        try {
            String sql = "call add_comment(?, ?, ?, ?)";
            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, null);
            callableStatement.setString(2, content);
            callableStatement.setInt(3, userID);
            callableStatement.setInt(4, ebookID);

            callableStatement.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

}
