package model;

import java.sql.SQLException;

public interface UserAction {
    public abstract boolean addItem(Item item)throws SQLException;
    public abstract boolean deleteItem(Item item)throws SQLException;
    public abstract boolean modifyItem(Item item)throws SQLException;
    public abstract boolean changePassword(String password)throws SQLException;
    //public abstract boolean getAccount(String username, String password)throws SQLException;
}
