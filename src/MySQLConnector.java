import java.sql.*;
public class MySQLConnector {
    private Connection conn;
    private String dbname, username, password;
    public MySQLConnector(String dbname, String username, String password) throws SQLException{
        this.dbname = dbname;
        this.username = username;
        this.password = password;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+this.dbname, this.username, this.password);
    }
    public Connection getConn(){
        return this.conn;
    }
}
