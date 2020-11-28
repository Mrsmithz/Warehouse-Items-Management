package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mysql.*;
public class User extends Account{
    private PreparedStatement prestatement;
    public User(MySQLConnector mysql, String username, String password, String firstname, String lastname, String email, String tel)throws SQLException{
        super(mysql, username, password, firstname, lastname, email, tel);
    }
    public User(MySQLConnector mysql, String username, String password)throws SQLException{
        super(mysql, username, password);
    }
    public boolean addItem(Item item) throws SQLException{
        if (!checkIfItemExist(item)){
            String stmt = "insert into items (user_id, item_name, item_type, item_price, item_weight, quantity)" +
                    "values(?,?,?,?,?,?)";
            this.prestatement = super.getAccountDB().getConn().prepareStatement(stmt);
            this.prestatement.setInt(1, item.getUser_id());
            this.prestatement.setString(2, item.getItem_name());
            this.prestatement.setString(3, item.getItem_type());
            this.prestatement.setDouble(4, item.getItem_price());
            this.prestatement.setDouble(5, item.getItem_weight());
            this.prestatement.setInt(6, item.getQuantity());
            return this.prestatement.executeUpdate() == 1;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean deleteItem(Item item, int id) throws SQLException{
        String stmt = "delete from items where item_name=(?) and user_id=(?) and item_type=(?) and item_id=(?)";
        this.prestatement = super.getAccountDB().getConn().prepareStatement(stmt);
        this.prestatement.setString(1, item.getItem_name());
        this.prestatement.setInt(2, item.getUser_id());
        this.prestatement.setString(3, item.getItem_type());
        this.prestatement.setInt(4, id);
        return this.prestatement.executeUpdate() == 1;
    }

    @Override
    public boolean modifyItem(Item item, int id) throws SQLException{
        String stmt = "update items set item_name=(?), item_type=(?), item_price=(?), item_weight=(?)," +
                "quantity=(?) where user_id=(?) and item_id=(?)";
        this.prestatement = super.getAccountDB().getConn().prepareStatement(stmt);
        this.prestatement.setString(1, item.getItem_name());
        this.prestatement.setString(2, item.getItem_type());
        this.prestatement.setDouble(3, item.getItem_price());
        this.prestatement.setDouble(4, item.getItem_weight());
        this.prestatement.setDouble(5, item.getQuantity());
        this.prestatement.setInt(6, item.getUser_id());
        this.prestatement.setInt(7, id);
        return this.prestatement.executeUpdate() == 1;
    }
    private int getItem_id(Item item)throws SQLException{
        String stmt = "select item_id from items where item_name=(?) and user_id=(?)";
        this.prestatement = super.getAccountDB().getConn().prepareStatement(stmt);
        this.prestatement.setString(1, item.getItem_name());
        this.prestatement.setInt(2, item.getUser_id());
        ResultSet resultSet = this.prestatement.executeQuery();
        resultSet.next();
        return (int) resultSet.getObject(1);
    }

    private boolean checkIfItemExist(Item item)throws SQLException{
        boolean exist = false;
        String stmt = "select item_name, item_type from items where user_id=(?)";
        this.prestatement = super.getAccountDB().getConn().prepareStatement(stmt);
        this.prestatement.setInt(1, item.getUser_id());
        ResultSet resultSet = this.prestatement.executeQuery();
        while (resultSet.next()){
            if (resultSet.getString(1).toLowerCase().equals(item.getItem_name().toLowerCase()) &&
                    resultSet.getString(2).toLowerCase().equals(item.getItem_type().toLowerCase())){
                exist = true;
                break;
            }
            else{
                exist = false;
            }
        }
        return exist;
    }
    public ResultSet getAllItem()throws SQLException{
        String stmt = "select * from items where user_id=(?)";
        this.prestatement = super.getAccountDB().getConn().prepareStatement(stmt);
        this.prestatement.setInt(1, getId());
        return this.prestatement.executeQuery();
    }
    public ResultSet getItem(int item_id)throws SQLException{
        String stmt = "select item_name, item_type, item_price, item_weight, quantity from items where item_id=(?) and user_id=(?)";
        this.prestatement = super.getAccountDB().getConn().prepareStatement(stmt);
        this.prestatement.setInt(1, item_id);
        this.prestatement.setInt(2, getId());
        ResultSet rs = this.prestatement.executeQuery();
        rs.next();
        return rs;

    }

    public PreparedStatement getPrestatement() {
        return prestatement;
    }

    public void setPrestatement(PreparedStatement prestatement) {
        this.prestatement = prestatement;
    }
}
