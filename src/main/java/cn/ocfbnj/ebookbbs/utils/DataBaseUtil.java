package cn.ocfbnj.ebookbbs.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class DataBaseUtil {
    private static final BasicDataSource dataSource;

    private static final String url = "jdbc:mysql://172.26.194.124:3306/ebook_bbs";
    private static final String name = "root";
    private static final String pwd = "12345678";

    static {
        BasicDataSource ds = new BasicDataSource();
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

        for (int i = 0; i < 10; i++) {
            CallableStatement callableStatement = con.prepareCall("call register_user('Bob', '123456',\n" +
                    "null, 'M', 20, '12345678901', '1391195421@qq.com');");
            callableStatement.execute();
        }

        PreparedStatement statement = con.prepareStatement("select * from user_pwd");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.println("===========");
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
        }
    }
}
