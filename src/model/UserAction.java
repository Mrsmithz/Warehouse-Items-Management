package model;

import java.sql.SQLException;

public interface UserAction {
    public abstract boolean AddItem(Item item)throws SQLException;
    public abstract boolean DeleteItem(Item item)throws SQLException;
    public abstract boolean ModifyItem(Item item)throws SQLException;
    public abstract boolean changePassword(String password)throws SQLException;
    //public abstract boolean getAccount(String username, String password)throws SQLException;
}
