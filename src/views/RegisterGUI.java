package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import mysql.*;
import model.*;
public class RegisterGUI implements ActionListener {
    private static JFrame mainFrame;
    private JPanel mainPanel, usernamePanel, firstnamePanel, lastnamePanel, passwordPanel, confirmpassPanel, emailPanel, telPanel, btnPanel;
    private JPlaceholderTextField usernameField, firstnameField, lastnameField, emailField, telField;
    private JPlaceholderPasswordField passwordField, confirmPasswordField;
    private Font fieldFont;
    private JButton registerBtn, backBtn;
    public RegisterGUI(){
        createComponents();
        setComponents();
    }
    private void createComponents(){
        fieldFont = new Font("Angsana New", Font.PLAIN, 35);

        mainFrame = new JFrame("Register");

        mainPanel = new JPanel();

        firstnameField = textFieldWrapper("Firstname", 300, 50);

        lastnameField = textFieldWrapper("Lastname", 300, 50);

        usernameField = textFieldWrapper("Username", 300, 50);

        passwordField = passwordFieldWrapper("Password", 300, 50);

        confirmPasswordField = passwordFieldWrapper("Confirm Password", 300, 50);

        emailField = textFieldWrapper("Email Address", 300, 50);

        telField = textFieldWrapper("Telephone", 300, 50);

        registerBtn = new JButton("Submit");

        firstnamePanel = panelWrapper(firstnameField);

        lastnamePanel = panelWrapper(lastnameField);

        usernamePanel = panelWrapper(usernameField);

        passwordPanel = panelWrapper(passwordField);

        confirmpassPanel = panelWrapper(confirmPasswordField);

        emailPanel = panelWrapper(emailField);

        telPanel = panelWrapper(telField);

        btnPanel = panelWrapper(registerBtn);

        backBtn = new JButton("Back");
    }
    private void setComponents(){
        mainFrame.setResizable(false);
        mainFrame.setSize(600, 800);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.add(mainPanel);

        mainPanel.setLayout(new GridLayout(8, 1));
        mainPanel.add(firstnamePanel);
        mainPanel.add(lastnamePanel);
        mainPanel.add(usernamePanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(confirmpassPanel);
        mainPanel.add(emailPanel);
        mainPanel.add(telPanel);
        mainPanel.add(btnPanel);

        btnPanel.add(backBtn);

        registerBtn.setFont(fieldFont);
        registerBtn.setPreferredSize(new Dimension(200, 50));
        registerBtn.addActionListener(this);

        backBtn.setFont(fieldFont);
        backBtn.setPreferredSize(new Dimension(200, 50));
        backBtn.addActionListener(this);

        mainFrame.setVisible(true);

    }
    public void actionPerformed(ActionEvent event){
        if (event.getSource().equals(registerBtn)){
            if (createAccount()){
                System.out.println("Success");
                LoginGUI.setFrameVisible(true);
                mainFrame.setVisible(false);
            }
            else{
                System.out.println("Fail");
            }
        }
    }
    private boolean createAccount(){
        try{
            MySQLConnector mysql = new MySQLConnector("MyDatabase", "root", "031961698");
            String pass = new String(passwordField.getPassword());
            String con_pass = new String(confirmPasswordField.getPassword());
            if (pass.equals(con_pass)){
                User new_user = new User(mysql, usernameField.getText(),
                        pass, firstnameField.getText(), lastnameField.getText(), emailField.getText(), telField.getText());
                if (new_user.createAccount()){
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
        catch(SQLException e){
            System.out.println(e);
            return false;
        }
    }
    private boolean formValidation(){
        return true;
    }
    private JPanel panelWrapper(JPlaceholderTextField field){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(field);
        return panel;
    }
    private JPanel panelWrapper(JPlaceholderPasswordField field){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(field);
        return panel;
    }
    private JPanel panelWrapper(JButton btn){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(btn);
        return panel;
    }
    private JPlaceholderTextField textFieldWrapper(String placeholder, int w, int h){
        JPlaceholderTextField field = new JPlaceholderTextField(placeholder);
        field.setFont(fieldFont);
        field.setPreferredSize(new Dimension(w, h));
        return field;
    }
    private JPlaceholderPasswordField passwordFieldWrapper(String placeholder, int w, int h){
        JPlaceholderPasswordField field = new JPlaceholderPasswordField(placeholder);
        field.setFont(fieldFont);
        field.setPreferredSize(new Dimension(w, h));
        return field;
    }

}
