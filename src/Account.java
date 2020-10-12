import java.sql.*;
public abstract class Account implements UserAction{
    private MySQLConnector AccountDB;
    private PreparedStatement prestmt;
    private ResultSet resultSet;
    private String username, password, firstname, lastname, email, tel;
    private int id;
    public Account(String username, String password, String firstname, String lastname, String email, String tel){
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.tel = tel;
    }
    public Account(){
        this("", "", "", "", "", "");
    }
    protected boolean createAccount()throws SQLException{
        if (!checkIfAccountExist(this.username) && !checkIfEmailExist(this.email)){
            String stmt = "insert into account(username, password, firstname, lastname, email, telephone) " +
                    "values(?,?,?,?,?,?)";
            this.prestmt = this.AccountDB.getConn().prepareStatement(stmt);
            this.prestmt.setString(1, this.username);
            this.prestmt.setString(2, this.password);
            this.prestmt.setString(3, this.firstname);
            this.prestmt.setString(4, this.lastname);
            this.prestmt.setString(5, this.email);
            this.prestmt.setString(6, this.tel);
            if (this.prestmt.executeUpdate() == 1){
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
        this.prestmt = this.AccountDB.getConn().prepareStatement(stmt);
        this.resultSet = this.prestmt.executeQuery();
        while (this.resultSet.next()){
            if (this.resultSet.getString(1).toLowerCase().equals(email)){
                exist = true;
                break;
            }
        }
        return exist;
    }
    private boolean checkIfAccountExist(String username)throws SQLException{
        String stmt = "select username from account";
        boolean exist = false;
        this.prestmt = this.AccountDB.getConn().prepareStatement(stmt);
        this.resultSet = this.prestmt.executeQuery();
        while (this.resultSet.next()){
            if (this.resultSet.getString(1).toLowerCase().equals(username)){
                exist = true;
                break;
            }
        }
        return exist;
    }
    protected boolean deleteAccount()throws SQLException{
        String stmt = "delete from account where username=(?)";
        this.prestmt = this.AccountDB.getConn().prepareStatement(stmt);
        this.prestmt.setString(1, this.username);
        return this.prestmt.executeUpdate() == 1;
    }
    protected int getUser_id()throws SQLException {
        String stmt = "select id from account where username=(?) and email=(?)";
        this.prestmt = this.AccountDB.getConn().prepareStatement(stmt);
        this.prestmt.setString(1, this.username);
        this.prestmt.setString(2, this.email);
        ResultSet resultSet = prestmt.executeQuery();
        return (int) resultSet.getObject(1);
    }
    protected MySQLConnector getAccountDB(){
        return this.AccountDB;
    }
    protected PreparedStatement getPrestmt(){
        return this.prestmt;
    }
    protected ResultSet getResultSet(){
        return this.resultSet;
    }

    public void setAccountDB(MySQLConnector accountDB) {
        this.AccountDB = accountDB;
    }

    public void setPrestmt(PreparedStatement prestmt) {
        this.prestmt = prestmt;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
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
