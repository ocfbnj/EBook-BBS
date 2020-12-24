package cn.ocfbnj.ebookbbs.dao.impl;

import cn.ocfbnj.ebookbbs.utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

// Database access object
abstract public class BaseDao {
    private final DataSource dataSource = DataBaseUtil.getDataSource();

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
