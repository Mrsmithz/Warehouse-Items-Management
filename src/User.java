import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.*;
public class User extends Account{
    private PreparedStatement prestatement;
    private ResultSetMetaData metaData;
    private List<Map<String, Object>> myData;
    private String username, password, firstname, lastname, email, tel;
    public User(String username, String password, String firstname, String lastname, String email, String tel)throws SQLException{
        super(username, password, firstname, lastname, email, tel);
    }
    public User(){
        super();
    }
    public boolean AddItem(Item item) throws SQLException{
        String stmt = "insert into items (user_id, item_name, item_type, item_price, item_weight, quantity)" +
                "values(?,?,?,?,?,?)";
        prestatement = super.getAccountDB().getConn().prepareStatement(stmt);
        prestatement.setInt(1, item.getUser_id());
        prestatement.setString(2, item.getItem_name());
        prestatement.setString(3, item.getItem_type());
        prestatement.setDouble(4, item.getItem_price());
        prestatement.setDouble(5, item.getItem_weight());
        prestatement.setInt(6, item.getQuantity());
        return prestatement.executeUpdate() == 1;
    }

    @Override
    public boolean DeleteItem(Item item) throws SQLException{
        String stmt = "delete from items where item_name=(?) and user_id=(?)";
        prestatement = super.getAccountDB().getConn().prepareStatement(stmt);
        prestatement.setString(1, item.getItem_name());
        prestatement.setInt(2, item.getUser_id());
        return prestatement.executeUpdate() == 1;
    }

    @Override
    public boolean ModifyItem(Item item) throws SQLException{
        String stmt = "update items set item_name=(?), item_type=(?), item_price=(?), item_weight=(?)," +
                "quantity=(?) where user_id=(?) and item_id=(?)";
        int item_id = getItem_id(item);
        prestatement = super.getAccountDB().getConn().prepareStatement(stmt);
        prestatement.setString(1, item.getItem_name());
        prestatement.setString(2, item.getItem_type());
        prestatement.setDouble(3, item.getItem_price());
        prestatement.setDouble(4, item.getItem_weight());
        prestatement.setDouble(5, item.getQuantity());
        prestatement.setInt(6, item.getUser_id());
        prestatement.setInt(7, item_id);
        return prestatement.executeUpdate() == 1;
    }
    private int getItem_id(Item item)throws SQLException{
        String stmt = "select item_id from items where item_name=(?) and user_id=(?)";
        prestatement = super.getAccountDB().getConn().prepareStatement(stmt);
        prestatement.setString(1, item.getItem_name());
        prestatement.setInt(2, item.getUser_id());
        ResultSet resultSet = prestatement.executeQuery();
        return (int) resultSet.getObject(1);
    }

    @Override
    public boolean changePassword(String password)throws SQLException{
        String stmt = "update account set password=(?) where username=(?) and email=(?)";
        prestatement = super.getAccountDB().getConn().prepareStatement(stmt);
        prestatement.setString(1, password);
        prestatement.setString(2, super.getUsername());
        prestatement.setString(3, super.getEmail());
        return prestatement.executeUpdate() == 1;
    }
    @Override
    public boolean getAccount(String username, String password)throws SQLException{
        String stmt = "select id, username, password, firstname, lastname, email, telephone from account where " +
                "username=(?) and password=(?)";
        prestatement = super.getAccountDB().getConn().prepareStatement(stmt);
        prestatement.setString(1, username);
        prestatement.setString(2, password);
        ResultSet resultSet = prestatement.executeQuery();
        if (resultSet.next()){
            super.setId(resultSet.getInt(1));
            super.setUsername(resultSet.getString(2));
            super.setPassword(resultSet.getString(3));
            super.setFirstname(resultSet.getString(4));
            super.setLastname(resultSet.getString(5));
            super.setEmail(resultSet.getString(6));
            super.setTel(resultSet.getString(7));
            return true;
        }
        else{
            return false;
        }
    }
}
