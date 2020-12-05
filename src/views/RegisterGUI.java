package views;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.InputStream;

import controller.*;
import myutilities.JPlaceholderPasswordField;
import myutilities.JPlaceholderTextField;

public class RegisterGUI{
    private JFrame mainFrame;
    private JPanel mainPanel, usernamePanel, firstnamePanel, lastnamePanel, passwordPanel, confirmpassPanel, emailPanel, telPanel, btnPanel;
    private JPlaceholderTextField usernameField, firstnameField, lastnameField, emailField, telField;
    private JPlaceholderPasswordField passwordField, confirmPasswordField;
    private Font fieldFont, alertFont;
    private JButton registerBtn, backBtn;
    private JLabel firstnameAlert, lastnameAlert, usernameAlert, passwordAlert, conpassAlert, emailAlert, telAlert;
    private RegisterController rc;

    //Kpun
    private JPanel infoPanel, fieldPanel, imgPanel, backBtnPanel;
    private JLabel imgLabel;
    public RegisterGUI(RegisterController rc){
        this.rc = rc;
        createComponents();
        setComponentskpun();
    }
    private void createComponents(){
        try {
            InputStream fieldinput = this.getClass().getResourceAsStream("/font/SukhumvitSet-Bold.ttf");
            fieldFont = Font.createFont(Font.TRUETYPE_FONT, fieldinput).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fieldFont);

            InputStream alertinput = this.getClass().getResourceAsStream("/font/SukhumvitSet-Medium.ttf");
            alertFont = Font.createFont(Font.TRUETYPE_FONT, alertinput).deriveFont(12f);
            GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge2.registerFont(alertFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        fieldFont = new Font("Angsana New", Font.PLAIN, 35);
//
//        alertFont = new Font("Angsana New", Font.PLAIN, 22);

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

        passwordPanel = panelWrapper(passwordField, passwordAlert, "Invalid password format");

        confirmpassPanel = panelWrapper(confirmPasswordField, conpassAlert, "Password doesn't match.");

        emailPanel = panelWrapper(emailField, emailAlert, "Invalid Email.");

        telPanel = panelWrapper(telField, telAlert, "Telephone Number must contains 10 digits.");

        btnPanel = panelWrapper(registerBtn);

        backBtn = new JButton("Back");


        fieldPanel = new JPanel();
        infoPanel = new JPanel();
        imgPanel = new JPanel();
        backBtnPanel = new JPanel();
        imgLabel = new JLabel();

    }
    private void setComponentskpun(){
        mainFrame.setResizable(false);
        mainFrame.setSize(600,800);
        mainFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        mainFrame.addWindowListener(this.rc);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.add(mainPanel);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(fieldPanel);

        fieldPanel.setLayout(new GridLayout(8, 1));
        fieldPanel.add(firstnamePanel);
        fieldPanel.add(lastnamePanel);
        fieldPanel.add(usernamePanel);
        fieldPanel.add(passwordPanel);
        fieldPanel.add(confirmpassPanel);
        fieldPanel.add(emailPanel);
        fieldPanel.add(telPanel);
        fieldPanel.add(btnPanel);

        btnPanel.add(backBtn);

        registerBtn.setFont(fieldFont.deriveFont(30f));
        registerBtn.setPreferredSize(new Dimension(200, 50));
        registerBtn.addActionListener(this.rc);
        registerBtn.setBorderPainted(false);

        backBtn.setFont(fieldFont.deriveFont(30f));
        backBtn.setPreferredSize(new Dimension(200, 50));
        backBtn.addActionListener(this.rc);
        backBtn.setBorderPainted(false);

        passwordAlert.setToolTipText("Password must contains at least 1 upper 1 lower and 1 special characters and 1 digit.");
    }
//    private void setComponents(){
//        mainFrame.setResizable(false);
//        mainFrame.setSize(800, 800);
//        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        mainFrame.setLocationRelativeTo(null);
//        mainFrame.add(mainPanel);
//
//        mainPanel.setLayout(new GridLayout(8, 1));
//        mainPanel.add(firstnamePanel);
//        mainPanel.add(lastnamePanel);
//        mainPanel.add(usernamePanel);
//        mainPanel.add(passwordPanel);
//        mainPanel.add(confirmpassPanel);
//        mainPanel.add(emailPanel);
//        mainPanel.add(telPanel);
//        mainPanel.add(btnPanel);
//
//        btnPanel.add(backBtn);
//
//        registerBtn.setFont(fieldFont.deriveFont(30f));
//        registerBtn.setPreferredSize(new Dimension(200, 50));
//        registerBtn.addActionListener(this.rc);
//        registerBtn.setBorderPainted(false);
//
//        backBtn.setFont(fieldFont.deriveFont(30f));
//        backBtn.setPreferredSize(new Dimension(200, 50));
//        backBtn.addActionListener(this.rc);
//        backBtn.setBorderPainted(false);
//
//        passwordAlert.setToolTipText("Password must contains at least 1 upper 1 lower and 1 special characters and 1 digit.");
//
//    }
    private JPanel panelWrapper(JPlaceholderTextField field, JLabel alert, String text){
        JPanel panel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());
        panel.add(field, gbc);
        gbc.gridy = 1;
        alert.setText(text);
        alert.setFont(alertFont.deriveFont(15f));
        alert.setForeground(new Color(255,0,0));;
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
        alert.setFont(alertFont.deriveFont(15f));
        alert.setForeground(new Color(255,0,0));;
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
        field.setFont(fieldFont.deriveFont(30f));
        field.setBorder(new MatteBorder(0,0,2,0, Color.GRAY));
        field.setBackground(new Color(0,0,0,0));
        field.setPreferredSize(new Dimension(w, h));
        field.addKeyListener(this.rc);
        field.addFocusListener(this.rc);
        return field;
    }
    private JPlaceholderPasswordField passwordFieldWrapper(String placeholder, int w, int h){
        JPlaceholderPasswordField field = new JPlaceholderPasswordField(placeholder);
        field.setFont(fieldFont.deriveFont(30f));
        field.setBorder(new MatteBorder(0,0,2,0, Color.GRAY));
        field.setBackground(new Color(0,0,0,0));
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
