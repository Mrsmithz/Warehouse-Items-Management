package main;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import controller.LoginController;
import controller.RegisterController;
import model.*;
import java.sql.*;
import javax.swing.*;
import mysql.*;
import views.RegisterGUI;

public class Main {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(new FlatArcOrangeIJTheme());
        }
        catch (Exception e){
            System.out.println(e);
        }
        //testGUI();
        //test();
        testLoginGUI();
        //testRegisterGUI();

    }
    public static void test(){
        try{
            MySQLConnector mysql = new MySQLConnector("MyDatabase", "root", "031961698");
            //User kuygen = new User(mysql, "kuygame", "123456", "GameKuy", "Dog", "Dog@dog.com", "0987654321");
            User kuygen = new User(mysql, "kuygame", "123456");
            //kuygen.deleteAccount();
            if(kuygen.getAccount()){
                Item testitem = new Item(kuygen.getId(), "Banana", "fruit", 12.5, 0.5, 1);
                String idInformation = String.format("%d %s %s %s %s %s %s", kuygen.getId(),
                        kuygen.getUsername(), kuygen.getPassword(), kuygen.getFirstname(), kuygen.getLastname(),
                kuygen.getEmail(), kuygen.getTel());
                String itemInformation = String.format("%d %s %s %f %f %d", testitem.getUser_id(), testitem.getItem_name(),
                testitem.getItem_type(), testitem.getItem_price(), testitem.getItem_weight(), testitem.getQuantity());
                System.out.println(idInformation);
                System.out.println(itemInformation);
                System.out.println(kuygen.addItem(testitem));
                //System.out.println(kuygen.deleteItem(testitem));
                //System.out.println(kuygen.changePassword("123456"));
            }
            else{
                System.out.println("Failed!!");
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    public static void testLoginGUI(){
        try{
            UIManager.setLookAndFeel(new FlatArcDarkOrangeIJTheme());
            SwingUtilities.invokeLater(LoginController::new);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
