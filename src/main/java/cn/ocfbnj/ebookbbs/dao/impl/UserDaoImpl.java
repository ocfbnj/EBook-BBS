package cn.ocfbnj.ebookbbs.dao.impl;

import cn.ocfbnj.ebookbbs.dao.UserDao;
import cn.ocfbnj.ebookbbs.domain.User;

import java.sql.*;
import java.util.Date;

public class UserDaoImpl extends BaseDao implements UserDao {
    private User getUser(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            User user = new User();

            user.setUserID(resultSet.getInt("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setName(resultSet.getString("name"));
            user.setSex(resultSet.getString("sex"));
            user.setAge(resultSet.getInt("age"));
            user.setPhone(resultSet.getString("phone"));
            user.setEmail(resultSet.getString("email"));
            user.setCreatTime(new Date(resultSet.getDate("create_time").getTime()));

            return user;
        }

        resultSet.close();

        return null;
    }

    @Override
    public User findByUsername(String username) {
        try {
            Connection connection = getConnection();
            String sql = "select * from user where username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            User user = getUser(preparedStatement);

            preparedStatement.close();
            connection.close();

            if (user != null) {
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        try {
            Connection connection = getConnection();

            String sql = "select * from user where username=? and password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            User user = getUser(preparedStatement);

            preparedStatement.close();
            connection.close();

            if (user != null) {
                return user;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public void registerUser(User user) {
        try {
            Connection connection = getConnection();

            String sql = "call register_user(?, ?, ?, ?, ?, ?, ?)";
            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, user.getUsername());
            callableStatement.setString(2, user.getPassword());
            callableStatement.setString(3, user.getName());
            callableStatement.setString(4, user.getSex());
            callableStatement.setInt(5, user.getAge());
            callableStatement.setString(6, user.getPhone());
            callableStatement.setString(7, user.getEmail());

            callableStatement.execute();

            callableStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
