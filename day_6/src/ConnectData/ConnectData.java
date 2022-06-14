package ConnectData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectData {
    static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    static final String user ="vungoc";
    static final String password = "12345";
    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
}
