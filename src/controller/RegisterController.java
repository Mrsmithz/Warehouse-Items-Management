package controller;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.regex.Pattern;

import model.User;
import mysql.MySQLConnector;
import myutilities.JPlaceholderPasswordField;
import myutilities.JPlaceholderTextField;
import views.*;
public class RegisterController implements ActionListener, FocusListener, KeyListener {
    private RegisterGUI registerGUI;
    private JButton registerBtn, backBtn;
    private JPlaceholderTextField usernameField, firstnameField, lastnameField, emailField, telField;
    private JPlaceholderPasswordField passwordField, confirmPasswordField;
    private JFrame mainFrame;
    private JLabel firstnameAlert, lastnameAlert, usernameAlert, passwordAlert, conpassAlert, emailAlert, telAlert;
    private LoginController lc;
    public RegisterController(LoginController lc){
        this.lc = lc;
        this.registerGUI = new RegisterGUI(this);
        setComponents();
    }
    private void setComponents(){
        this.registerBtn =  registerGUI.getRegisterBtn();
        this.backBtn = registerGUI.getBackBtn();
        this.usernameField = registerGUI.getUsernameField();
        this.firstnameField = registerGUI.getFirstnameField();
        this.lastnameField = registerGUI.getLastnameField();
        this.emailField = registerGUI.getEmailField();
        this.telField = registerGUI.getTelField();
        this.passwordField = registerGUI.getPasswordField();
        this.confirmPasswordField = registerGUI.getConfirmPasswordField();
        this.mainFrame = registerGUI.getMainFrame();
        this.firstnameAlert = registerGUI.getFirstnameAlert();
        this.lastnameAlert = registerGUI.getLastnameAlert();
        this.usernameAlert = registerGUI.getUsernameAlert();
        this.passwordAlert = registerGUI.getPasswordAlert();
        this.conpassAlert = registerGUI.getConpassAlert();
        this.emailAlert = registerGUI.getEmailAlert();
        this.telAlert = registerGUI.getTelAlert();
    }
    public void actionPerformed(ActionEvent event){
        if (event.getSource().equals(registerBtn)){
            if (formValidation()){
                if (createAccount()){
                    JOptionPane.showMessageDialog(mainFrame, "Register Successfully !", "Success", JOptionPane.INFORMATION_MESSAGE);
                    lc.setLoginGUIVisible(true);
                    mainFrame.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(mainFrame, "Register Failed !", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(mainFrame, "Invalid input, Please try again.", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (event.getSource().equals(backBtn)){
            lc.setLoginGUIVisible(true);
            mainFrame.setVisible(false);
        }
    }
    public void keyTyped(KeyEvent keyEvent){
        if (keyEvent.getSource().equals(usernameField)){
            if(usernameValidator(usernameField.getText()) && !usernameField.getText().equals("Username")){
                usernameAlert.setVisible(false);
            }
            else{
                usernameAlert.setVisible(true);
            }
        }
        else if (keyEvent.getSource().equals(firstnameField)){
            if(nameValidator(firstnameField.getText()) && !firstnameField.getText().equals("Firstname")){
                firstnameAlert.setVisible(false);
            }
            else{
                firstnameAlert.setVisible(true);
            }
        }
        else if (keyEvent.getSource().equals(lastnameField)){
            if (nameValidator(lastnameField.getText()) && !lastnameField.getText().equals("Lastname")){
                lastnameAlert.setVisible(false);
            }
            else{
                lastnameAlert.setVisible(true);
            }
        }
        else if (keyEvent.getSource().equals(passwordField)){
            String pass = new String(passwordField.getPassword());
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
            if(usernameValidator(usernameField.getText()) && !usernameField.getText().equals("Username")){
                usernameAlert.setVisible(false);
            }
            else{
                usernameAlert.setVisible(true);
            }
        }
        else if (focusEvent.getSource().equals(firstnameField)){
            if(nameValidator(firstnameField.getText()) && !firstnameField.getText().equals("Firstname")){
                firstnameAlert.setVisible(false);
            }
            else{
                firstnameAlert.setVisible(true);
            }
        }
        else if (focusEvent.getSource().equals(lastnameField)){
            if (nameValidator(lastnameField.getText()) && !lastnameField.getText().equals("Lastname")){
                lastnameAlert.setVisible(false);
            }
            else{
                lastnameAlert.setVisible(true);
            }
        }
        else if (focusEvent.getSource().equals(passwordField)){
            String pass = new String(passwordField.getPassword());
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

    public void setRegisterGUIVisible(boolean b){
        mainFrame.setVisible(b);
    }

    public RegisterGUI getRegisterGUI() {
        return registerGUI;
    }

    public void setRegisterGUI(RegisterGUI registerGUI) {
        this.registerGUI = registerGUI;
    }

    public JButton getRegisterBtn() {
        return registerBtn;
    }

    public void setRegisterBtn(JButton registerBtn) {
        this.registerBtn = registerBtn;
    }

    public JButton getBackBtn() {
        return backBtn;
    }

    public void setBackBtn(JButton backBtn) {
        this.backBtn = backBtn;
    }

    public JPlaceholderTextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(JPlaceholderTextField usernameField) {
        this.usernameField = usernameField;
    }

    public JPlaceholderTextField getFirstnameField() {
        return firstnameField;
    }

    public void setFirstnameField(JPlaceholderTextField firstnameField) {
        this.firstnameField = firstnameField;
    }

    public JPlaceholderTextField getLastnameField() {
        return lastnameField;
    }

    public void setLastnameField(JPlaceholderTextField lastnameField) {
        this.lastnameField = lastnameField;
    }

    public JPlaceholderTextField getEmailField() {
        return emailField;
    }

    public void setEmailField(JPlaceholderTextField emailField) {
        this.emailField = emailField;
    }

    public JPlaceholderTextField getTelField() {
        return telField;
    }

    public void setTelField(JPlaceholderTextField telField) {
        this.telField = telField;
    }

    public JPlaceholderPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPlaceholderPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public JPlaceholderPasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public void setConfirmPasswordField(JPlaceholderPasswordField confirmPasswordField) {
        this.confirmPasswordField = confirmPasswordField;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JLabel getFirstnameAlert() {
        return firstnameAlert;
    }

    public void setFirstnameAlert(JLabel firstnameAlert) {
        this.firstnameAlert = firstnameAlert;
    }

    public JLabel getLastnameAlert() {
        return lastnameAlert;
    }

    public void setLastnameAlert(JLabel lastnameAlert) {
        this.lastnameAlert = lastnameAlert;
    }

    public JLabel getUsernameAlert() {
        return usernameAlert;
    }

    public void setUsernameAlert(JLabel usernameAlert) {
        this.usernameAlert = usernameAlert;
    }

    public JLabel getPasswordAlert() {
        return passwordAlert;
    }

    public void setPasswordAlert(JLabel passwordAlert) {
        this.passwordAlert = passwordAlert;
    }

    public JLabel getConpassAlert() {
        return conpassAlert;
    }

    public void setConpassAlert(JLabel conpassAlert) {
        this.conpassAlert = conpassAlert;
    }

    public JLabel getEmailAlert() {
        return emailAlert;
    }

    public void setEmailAlert(JLabel emailAlert) {
        this.emailAlert = emailAlert;
    }

    public JLabel getTelAlert() {
        return telAlert;
    }

    public void setTelAlert(JLabel telAlert) {
        this.telAlert = telAlert;
    }

    public LoginController getLc() {
        return lc;
    }

    public void setLc(LoginController lc) {
        this.lc = lc;
    }
}

