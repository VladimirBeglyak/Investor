package by.broker.http.util;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL_KEY="db.url";
    private static final String USER_KEY="db.user";
    private static final String PASSWORD_KEY="db.password";
//    private static final String DRIVER_KEY="db.driver";
    private static DataSource dataSource;

    static {

//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setInitialSize(5);

        poolProperties.setDriverClassName("org.postgresql.Driver");
        poolProperties.setUrl(PropertiesUtil.get(URL_KEY));
        poolProperties.setUsername(PropertiesUtil.get(USER_KEY));
        poolProperties.setPassword(PropertiesUtil.get(PASSWORD_KEY));
        dataSource=new DataSource(poolProperties);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
