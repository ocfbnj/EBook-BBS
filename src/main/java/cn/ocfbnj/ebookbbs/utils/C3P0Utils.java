package cn.ocfbnj.ebookbbs.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
/*
*工具类
* 创建数据池
* @getDataSource() 获取数据池的连接对象
 */

public class C3P0Utils {
    private static DataSource ds;
    static {
        ds = new ComboPooledDataSource();
    }
    public static DataSource getDataSource(){
        return ds;
    }
}
