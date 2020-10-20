package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.regex.*;
import mysql.*;
import model.*;
public class RegisterGUI implements ActionListener, KeyListener, FocusListener{
    private static JFrame mainFrame;
    private JPanel mainPanel, usernamePanel, firstnamePanel, lastnamePanel, passwordPanel, confirmpassPanel, emailPanel, telPanel, btnPanel;
    private JPlaceholderTextField usernameField, firstnameField, lastnameField, emailField, telField;
    private JPlaceholderPasswordField passwordField, confirmPasswordField;
    private Font fieldFont, alertFont;
    private JButton registerBtn, backBtn;
    private JLabel firstnameAlert, lastnameAlert, usernameAlert, passwordAlert, conpassAlert, emailAlert, telAlert;
    public RegisterGUI(){
        createComponents();
        setComponents();
    }
    private void createComponents(){

        fieldFont = new Font("Angsana New", Font.PLAIN, 35);

        alertFont = new Font("Angsana New", Font.PLAIN, 22);

        mainFrame = new JFrame("Register");

        mainPanel = new JPanel();

        firstnameAlert = new JLabel();

        lastnameAlert = new JLabel();

        usernameAlert = new JLabel();

        passwordAlert = new JLabel();

        conpassAlert = new JLabel();

        emailAlert = new JLabel();

        telAlert = new JLabel();

        firstnameField = textFieldWrapper("Firstname", 300, 50);

        lastnameField = textFieldWrapper("Lastname", 300, 50);

        usernameField = textFieldWrapper("Username", 300, 50);

        passwordField = passwordFieldWrapper("Password", 300, 50);

        confirmPasswordField = passwordFieldWrapper("Confirm Password", 300, 50);

        emailField = textFieldWrapper("Email Address", 300, 50);

        telField = textFieldWrapper("Telephone", 300, 50);

        registerBtn = new JButton("Submit");

        firstnamePanel = panelWrapper(firstnameField, firstnameAlert, "Must contains only character.");

        lastnamePanel = panelWrapper(lastnameField, lastnameAlert, "Must contains only character.");

        usernamePanel = panelWrapper(usernameField, usernameAlert, "Must contains only character.");

        passwordPanel = panelWrapper(passwordField, passwordAlert, "Must contains 1 special 1 upper 1 lower character 1 digit and at least 8 characters long.");

        confirmpassPanel = panelWrapper(confirmPasswordField, conpassAlert, "Password doesn't match.");

        emailPanel = panelWrapper(emailField, emailAlert, "Invalid Email.");

        telPanel = panelWrapper(telField, telAlert, "Telephone Number must contains 10 digits.");

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
            if (formValidation()){
                if (createAccount()){
                    LoginGUI.setFrameVisible(true);
                    mainFrame.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(mainFrame, "Register Failed !", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(mainFrame, "ข้อมูลไม่ถูกต้อง", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (event.getSource().equals(backBtn)){
            LoginGUI.setFrameVisible(true);
            mainFrame.setVisible(false);
        }
    }
    public void keyTyped(KeyEvent keyEvent){
        if (keyEvent.getSource().equals(usernameField)){
            if(usernameValidator(usernameField.getText())){
                usernameAlert.setVisible(false);
            }
            else{
                usernameAlert.setVisible(true);
            }
        }
        else if (keyEvent.getSource().equals(firstnameField)){
            if(nameValidator(firstnameField.getText())){
                firstnameAlert.setVisible(false);
            }
            else{
                firstnameAlert.setVisible(true);
            }
        }
        else if (keyEvent.getSource().equals(lastnameField)){
            if (nameValidator(lastnameField.getText())){
                lastnameAlert.setVisible(false);
            }
            else{
                lastnameAlert.setVisible(true);
            }
        }
        else if (keyEvent.getSource().equals(passwordField)){
            String pass = new String(passwordField.getPassword());
            System.out.println(passwordValidator(pass));
            System.out.println(pass);
            if (passwordValidator(pass)){
                passwordAlert.setVisible(false);
            }
            else{
                passwordAlert.setVisible(true);
            }
        }
        else if (keyEvent.getSource().equals(confirmPasswordField)){
            String pass = new String(confirmPasswordField.getPassword());
            String pass2 = new String(passwordField.getPassword());
            if (passwordValidator(pass) && pass.equals(pass2)){
                conpassAlert.setVisible(false);
            }
            else{
                conpassAlert.setVisible(true);
            }
        }
        else if (keyEvent.getSource().equals(emailField)){
            if (emailValidator(emailField.getText())){
                emailAlert.setVisible(false);
            }
            else{
                emailAlert.setVisible(true);
            }
        }
        else if (keyEvent.getSource().equals(telField)){
            if (telephoneValidator(telField.getText())){
                telAlert.setVisible(false);
            }
            else{
                telAlert.setVisible(true);
            }
        }
    }
    public void keyPressed(KeyEvent keyEvent){

    }
    public void keyReleased(KeyEvent keyEvent){

    }
    public void focusGained(FocusEvent focusEvent){

    }
    public void focusLost(FocusEvent focusEvent){
        if (focusEvent.getSource().equals(usernameField)){
            if(usernameValidator(usernameField.getText())){
                usernameAlert.setVisible(false);
            }
            else{
                usernameAlert.setVisible(true);
            }
        }
        else if (focusEvent.getSource().equals(firstnameField)){
            if(nameValidator(firstnameField.getText())){
                firstnameAlert.setVisible(false);
            }
            else{
                firstnameAlert.setVisible(true);
            }
        }
        else if (focusEvent.getSource().equals(lastnameField)){
            if (nameValidator(lastnameField.getText())){
                lastnameAlert.setVisible(false);
            }
            else{
                lastnameAlert.setVisible(true);
            }
        }
        else if (focusEvent.getSource().equals(passwordField)){
            String pass = new String(passwordField.getPassword());
            System.out.println(passwordValidator(pass));
            System.out.println(pass);
            if (passwordValidator(pass)){
                passwordAlert.setVisible(false);
            }
            else{
                passwordAlert.setVisible(true);
            }
        }
        else if (focusEvent.getSource().equals(confirmPasswordField)){
            String pass = new String(confirmPasswordField.getPassword());
            String pass2 = new String(passwordField.getPassword());
            if (passwordValidator(pass) && pass.equals(pass2)){
                conpassAlert.setVisible(false);
            }
            else{
                conpassAlert.setVisible(true);
            }
        }
        else if (focusEvent.getSource().equals(emailField)){
            if (emailValidator(emailField.getText())){
                emailAlert.setVisible(false);
            }
            else{
                emailAlert.setVisible(true);
            }
        }
        else if (focusEvent.getSource().equals(telField)){
            if (telephoneValidator(telField.getText())){
                telAlert.setVisible(false);
            }
            else{
                telAlert.setVisible(true);
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
        String pass1 = new String(passwordField.getPassword());
        String pass2 = new String(confirmPasswordField.getPassword());
        boolean user = usernameValidator(usernameField.getText());
        boolean fname = nameValidator(firstnameField.getText());
        boolean lname = nameValidator(lastnameField.getText());
        boolean pass = (passwordValidator(pass1) && passwordValidator(pass2) && pass1.equals(pass2));
        boolean email = emailValidator(emailField.getText());
        boolean tel = telephoneValidator(telField.getText());
        return user && fname && lname && pass && email && tel;

    }
    private JPanel panelWrapper(JPlaceholderTextField field, JLabel alert, String text){
        JPanel panel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());
        panel.add(field, gbc);
        gbc.gridy = 1;
        alert.setText(text);
        alert.setFont(alertFont);
        panel.add(alert, gbc);
        return panel;
    }
    private JPanel panelWrapper(JPlaceholderPasswordField field, JLabel alert, String text){
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(field, gbc);
        gbc.gridy = 1;
        alert.setText(text);
        alert.setVisible(true);
        alert.setFont(alertFont);
        panel.add(alert, gbc);
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
        field.addKeyListener(this);
        field.addFocusListener(this);
        return field;
    }
    private JPlaceholderPasswordField passwordFieldWrapper(String placeholder, int w, int h){
        JPlaceholderPasswordField field = new JPlaceholderPasswordField(placeholder);
        field.setFont(fieldFont);
        field.setPreferredSize(new Dimension(w, h));
        field.addKeyListener(this);
        field.addFocusListener(this);
        return field;
    }
    private void testFocus(){
        if (!usernameField.isFocusOwner()){
            System.out.println(usernameField.getText());
        }
        else{
            System.out.println("Focus");
        }
    }
    private boolean usernameValidator(String username){
        return Pattern.matches("^[a-zA-Z0-9]+$", username);
    }
    private boolean nameValidator(String name){
        return Pattern.matches("^[a-zA-Z]+$", name);
    }
    private boolean passwordValidator(String password){
        return Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", password);
    }
    private boolean emailValidator(String email){
        return Pattern.matches("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)" +
                "|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", email);
    }
    private boolean telephoneValidator(String telephone){
        return Pattern.matches("^[0-9]{10}$", telephone);
    }
}
