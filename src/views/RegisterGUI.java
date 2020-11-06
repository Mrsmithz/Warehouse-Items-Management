package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.regex.*;
import mysql.*;
import model.*;
import controller.*;
public class RegisterGUI{
    private JFrame mainFrame;
    private JPanel mainPanel, usernamePanel, firstnamePanel, lastnamePanel, passwordPanel, confirmpassPanel, emailPanel, telPanel, btnPanel;
    private JPlaceholderTextField usernameField, firstnameField, lastnameField, emailField, telField;
    private JPlaceholderPasswordField passwordField, confirmPasswordField;
    private Font fieldFont, alertFont;
    private JButton registerBtn, backBtn;
    private JLabel firstnameAlert, lastnameAlert, usernameAlert, passwordAlert, conpassAlert, emailAlert, telAlert;
    private RegisterController rc;
    public RegisterGUI(RegisterController rc){
        this.rc = rc;
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
        registerBtn.addActionListener(this.rc);

        backBtn.setFont(fieldFont);
        backBtn.setPreferredSize(new Dimension(200, 50));
        backBtn.addActionListener(this.rc);

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
        field.addKeyListener(this.rc);
        field.addFocusListener(this.rc);
        return field;
    }
    private JPlaceholderPasswordField passwordFieldWrapper(String placeholder, int w, int h){
        JPlaceholderPasswordField field = new JPlaceholderPasswordField(placeholder);
        field.setFont(fieldFont);
        field.setPreferredSize(new Dimension(w, h));
        field.addKeyListener(this.rc);
        field.addFocusListener(this.rc);
        return field;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JPanel getUsernamePanel() {
        return usernamePanel;
    }

    public void setUsernamePanel(JPanel usernamePanel) {
        this.usernamePanel = usernamePanel;
    }

    public JPanel getFirstnamePanel() {
        return firstnamePanel;
    }

    public void setFirstnamePanel(JPanel firstnamePanel) {
        this.firstnamePanel = firstnamePanel;
    }

    public JPanel getLastnamePanel() {
        return lastnamePanel;
    }

    public void setLastnamePanel(JPanel lastnamePanel) {
        this.lastnamePanel = lastnamePanel;
    }

    public JPanel getPasswordPanel() {
        return passwordPanel;
    }

    public void setPasswordPanel(JPanel passwordPanel) {
        this.passwordPanel = passwordPanel;
    }

    public JPanel getConfirmpassPanel() {
        return confirmpassPanel;
    }

    public void setConfirmpassPanel(JPanel confirmpassPanel) {
        this.confirmpassPanel = confirmpassPanel;
    }

    public JPanel getEmailPanel() {
        return emailPanel;
    }

    public void setEmailPanel(JPanel emailPanel) {
        this.emailPanel = emailPanel;
    }

    public JPanel getTelPanel() {
        return telPanel;
    }

    public void setTelPanel(JPanel telPanel) {
        this.telPanel = telPanel;
    }

    public JPanel getBtnPanel() {
        return btnPanel;
    }

    public void setBtnPanel(JPanel btnPanel) {
        this.btnPanel = btnPanel;
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

    public Font getFieldFont() {
        return fieldFont;
    }

    public void setFieldFont(Font fieldFont) {
        this.fieldFont = fieldFont;
    }

    public Font getAlertFont() {
        return alertFont;
    }

    public void setAlertFont(Font alertFont) {
        this.alertFont = alertFont;
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

    public RegisterController getRc() {
        return rc;
    }

    public void setRc(RegisterController rc) {
        this.rc = rc;
    }
}
