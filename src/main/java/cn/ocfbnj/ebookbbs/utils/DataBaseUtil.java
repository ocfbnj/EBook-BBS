package cn.ocfbnj.ebookbbs.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseUtil {
    private static final BasicDataSource dataSource;

    private static final String url = "jdbc:mysql://localhost:3306/ebook_bbs?serverTimezone=UTC";
    private static final String name = "root";
    private static final String pwd = "12345678";

    static {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl(url);
        ds.setUsername(name);
        ds.setPassword(pwd);

        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);

        dataSource = ds;
    }

    public static BasicDataSource getDataSource() {
        return dataSource;
    }

    public static void main(String[] args) throws SQLException {
        BasicDataSource ds = DataBaseUtil.getDataSource();
        Connection con = ds.getConnection();

        PreparedStatement statement = con.prepareStatement("select * from user_pwd");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.println("===========");
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
        }
    }
}
