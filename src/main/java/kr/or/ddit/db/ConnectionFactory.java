package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Factory Object Pattern
 *  : 특정 객체의 생성을 전담하는 객체를 별도로 운영하는 설계 방식.
 */
public class ConnectionFactory {

    private static Properties props = new Properties();

    static {
        String qn = "/kr/or/ddit/db/DBInfo.properties";
        try (InputStream is = ConnectionFactory.class.getResourceAsStream(qn)){
            props.load(is);
            Class.forName(props.getProperty("driverClassName"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws SQLException {
        String url = props.getProperty("url");
        String user = props.getProperty("user");
        String password = props.getProperty("password");

        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

}
