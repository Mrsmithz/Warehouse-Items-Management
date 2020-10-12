import java.sql.*;

public class Main {
    public static void main(String[] args) {
        test();
    }
    public static void test(){
        try{
            User kuygen = new User();
            kuygen.setAccountDB(new MySQLConnector("mydatabase", "root", "031961698"));
            kuygen.getAccount("tester", "123456789@Za");
            System.out.println(kuygen.getId());
            System.out.println(kuygen.getUsername());
            System.out.println(kuygen.getPassword());
            System.out.println(kuygen.getFirstname());
            System.out.println(kuygen.getLastname());
            System.out.println(kuygen.getEmail());
            System.out.println(kuygen.getTel());

        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
}
