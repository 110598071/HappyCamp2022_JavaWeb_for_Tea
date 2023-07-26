package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String url = "jdbc:postgresql://140.124.184.222:5432/postgres";
    private final String schema = "islab";
    private final String user = "postgres";
    private final String password = "islab1221";

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, this.user, this.password);
            conn.setSchema(schema);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return conn;
    }
}