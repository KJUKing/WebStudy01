package kr.or.ddit.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Factory Object Pattern
 *  : 특정 객체의 생성을 전담하는 객체를 별도로 운영하는 설계 방식.
 */
public class ConnectionFactoryCP {

    private static ResourceBundle dbInfo;
    private static DataSource ds;

    static {
        String baseName = "kr.or.ddit.db.DBInfo";
    dbInfo = ResourceBundle.getBundle(baseName);

    HikariConfig config = new HikariConfig();
    config.setDriverClassName(dbInfo.getString("driverClassName"));
        config.setJdbcUrl(dbInfo.getString("url"));
        config.setUsername(dbInfo.getString("user"));
        config.setPassword(dbInfo.getString("password"));

        //pooling
        config.setConnectionTestQuery(dbInfo.getString("testQuery"));
        config.setMinimumIdle(Integer.parseInt(dbInfo.getString("minimulIdle")));
        config.setMaximumPoolSize(Integer.parseInt(dbInfo.getString("maximulSize")));
        config.setConnectionTimeout(Long.parseLong(dbInfo.getString("maxWait")));
        ds = new HikariDataSource(config);
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
