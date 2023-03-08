import java.sql.*;

public class DbConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/romantest";
    private static final String USERNAME = "romantest";
    private static final String PASSWORD = "qxtradmin";
    Connection conn = null;

    public Connection dbConnect() throws SQLException {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ConnectionException) {
            ConnectionException.printStackTrace();
        }
        return conn;
    }

    public void dbEndConnect() throws SQLException{
        if (conn != null){
            conn.close();
        }
    }
}