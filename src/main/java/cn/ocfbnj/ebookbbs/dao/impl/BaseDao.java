package cn.ocfbnj.ebookbbs.dao.impl;

import cn.ocfbnj.ebookbbs.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

// Database access object
abstract public class BaseDao {
    private final QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    //添加、修改、删除
    public int update(String sql, Object... args) {
        int num = 0;
        try {
            num = queryRunner.update(sql, args);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return num;
    }

    //查询单个，返回对象
    public <T> T queryForOne(String sql, Class<T> type, Object... args) {
        try {
            return queryRunner.query(sql, new BeanHandler<>(type), args);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    //查询多个，返回List集合对象
    public <T> List<T> queryForList(String sql, Class<T> type, Object... args) {
        try {
            return queryRunner.query(sql, new BeanListHandler<>(type), args);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        
        return null;
    }

    public Object queryForSingleValue(String sql, Object... args) {
        try {
            return queryRunner.query(sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
