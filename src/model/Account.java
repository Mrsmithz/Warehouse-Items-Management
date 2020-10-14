package model;
import mysql.*;
import java.sql.*;
public abstract class Account implements UserAction{
    private MySQLConnector accountDB;
    private PreparedStatement prestmt;
    private String username, password, firstname, lastname, email, tel;
    private int id;
    public Account(MySQLConnector mysql, String username, String password, String firstname, String lastname, String email, String tel){
        this.accountDB = mysql;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.tel = tel;
    }
    public Account(MySQLConnector mysql, String username, String password)throws SQLException{
        this.accountDB = mysql;
        this.username = username;
        this.password = password;
    }
    public boolean createAccount()throws SQLException{
        if (!checkIfAccountExist(this.username) && !checkIfEmailExist(this.email)){
            String stmt = "insert into account(username, password, firstname, lastname, email, telephone) " +
                    "values(?,?,?,?,?,?)";
            this.prestmt = this.accountDB.getConn().prepareStatement(stmt);
            this.prestmt.setString(1, this.username);
            this.prestmt.setString(2, this.password);
            this.prestmt.setString(3, this.firstname);
            this.prestmt.setString(4, this.lastname);
            this.prestmt.setString(5, this.email);
            this.prestmt.setString(6, this.tel);
            if (this.prestmt.executeUpdate() == 1){
                this.prestmt.close();
                this.id = getUser_id();
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    private boolean checkIfEmailExist(String email)throws SQLException{
        String stmt = "select email from account";
        boolean exist = false;
        this.prestmt = this.accountDB.getConn().prepareStatement(stmt);
        ResultSet resultSet = this.prestmt.executeQuery();
        while (resultSet.next()){
            if (resultSet.getString(1).toLowerCase().equals(email.toLowerCase())){
                exist = true;
                break;
            }
        }
        return exist;
    }
    private boolean checkIfAccountExist(String username)throws SQLException{
        String stmt = "select username from account";
        boolean exist = false;
        this.prestmt = this.accountDB.getConn().prepareStatement(stmt);
        ResultSet resultSet = this.prestmt.executeQuery();
        while (resultSet.next()){
            if (resultSet.getString(1).toLowerCase().equals(username.toLowerCase())){
                exist = true;
                break;
            }
        }
        return exist;
    }
    public boolean deleteAccount()throws SQLException{
        String stmt = "delete from account where username=(?)";
        this.prestmt = this.accountDB.getConn().prepareStatement(stmt);
        this.prestmt.setString(1, this.username);
        if (this.prestmt.executeUpdate() == 1){
            this.id = 0;
            this.username = this.password = this.firstname = this.lastname = this.email = this.tel = "";
            return true;
        }
        else{
            return false;
        }
    }
    public int getUser_id()throws SQLException {
        String stmt = "select id from account where username=(?) and email=(?)";
        this.prestmt = this.accountDB.getConn().prepareStatement(stmt);
        this.prestmt.setString(1, this.username);
        this.prestmt.setString(2, this.email);
        ResultSet resultSet = prestmt.executeQuery();
        resultSet.next();
        return (int) resultSet.getObject(1);
    }
    public boolean getAccount()throws SQLException{
        String stmt = "select id, username, password, firstname, lastname, email, telephone from account where " +
                "username=(?) and password=(?)";
        this.prestmt = this.accountDB.getConn().prepareStatement(stmt);
        this.prestmt.setString(1, this.username);
        this.prestmt.setString(2, this.password);
        ResultSet resultSet = this.prestmt.executeQuery();
        if (resultSet.next()){
            this.id = resultSet.getInt(1);
            this.username = resultSet.getString(2);
            this.password = resultSet.getString(3);
            this.firstname = resultSet.getString(4);
            this.lastname = resultSet.getString(5);
            this.email = resultSet.getString(6);
            this.tel = resultSet.getString(7);
            return true;
        }
        else{
            return false;
        }
    }
    public MySQLConnector getAccountDB(){
        return this.accountDB;
    }
    public PreparedStatement getPrestmt(){
        return this.prestmt;
    }

    public void setAccountDB(MySQLConnector accountDB) {
        this.accountDB = accountDB;
    }

    public void setPrestmt(PreparedStatement prestmt) {
        this.prestmt = prestmt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return this.email;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }
}
