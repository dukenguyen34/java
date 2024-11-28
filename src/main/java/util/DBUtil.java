// src/main/java/util/DBUtil.java
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=player_evaluation;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";
//private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=player_evaluation;trustServerCertificate=true;";
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("sql JDBC Driver not found", e);
        }
    }
}