package mysql;

import java.sql.*;
public class MySQLConnector {
    private final Connection conn;
    private final String dbname, username, password;
    public MySQLConnector(String dbname, String username, String password) throws SQLException{
        this.dbname = dbname;
        this.username = username;
        this.password = password;
        conn = DriverManager.getConnection("jdbc:mysql://34.87.91.113:3306/"+this.dbname, this.username, this.password);
    }

    public String getDbname() {
        return this.dbname;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Connection getConn(){
        return this.conn;
    }
}
