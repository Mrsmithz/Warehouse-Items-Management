package main;

import views.MainGUI;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        testGUI();
    }
//    public static void test(){
//        try{
//            MySQLConnector.MySQLConnector mysql = new MySQLConnector.MySQLConnector("mydatabase", "root", "031961698");
//            //model.User kuygen = new model.User(mysql, "kuygame", "123456", "GameKuy", "Dog", "Dog@dog.com", "0987654321");
//            model.User kuygen = new model.User(mysql, "kuygame", "123456");
//            //kuygen.deleteAccount();
//            if(kuygen.getAccount()){
//                System.out.println("DONE!!!");
//                System.out.println(kuygen.getId());
//                System.out.println(kuygen.getUsername());
//                System.out.println(kuygen.getPassword());
//                System.out.println(kuygen.getFirstname());
//                System.out.println(kuygen.getLastname());
//                System.out.println(kuygen.getEmail());
//                System.out.println(kuygen.getTel());
//            }
//            else{
//                System.out.println("Failed!!");
//            }
//        }
//        catch(SQLException e){
//            System.out.println(e);
//        }
//    }

    public static void testGUI() {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
