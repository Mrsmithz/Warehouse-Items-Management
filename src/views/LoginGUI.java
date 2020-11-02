package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import controller.MainGUI;
import mysql.*;
import model.*;
public class LoginGUI implements ActionListener, KeyListener {
    private static JFrame mainFrame;
    private JPanel mainPanel, textFieldPanel, btnPanel, logoPanel, userPanel, passPanel;
    private JPlaceholderTextField usernameField;
    private JPlaceholderPasswordField passwordField;
    private JButton loginBtn, registerBtn;
    private Font textFieldFont;
    private GridBagConstraints gbc;
    private User user;
    public LoginGUI(){
        createComponents();
        setComponents();
    }
    private void createComponents(){
        mainFrame = new JFrame("Login");

        textFieldFont = new Font("Angsana New", Font.PLAIN, 35);

        mainPanel = new JPanel();

        logoPanel = CreateShortcuts.createImagePanel("/imgs/login-test.png");

        textFieldPanel = new JPanel();

        userPanel = new JPanel();

        passPanel = new JPanel();

        btnPanel = new JPanel();

        usernameField = new JPlaceholderTextField("Username");

        passwordField = new JPlaceholderPasswordField("Password");

        loginBtn = new JButton("LOGIN");

        registerBtn = new JButton("REGISTER");

        gbc = new GridBagConstraints();

    }
    private void setComponents(){
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setSize(800, 600);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.add(mainPanel);
        mainFrame.setLocationRelativeTo(null);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(111, 0, 11));
        mainPanel.add(logoPanel, BorderLayout.NORTH);
        mainPanel.add(textFieldPanel, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        logoPanel.setPreferredSize(new Dimension(800, 170));

        textFieldPanel.setLayout(new GridLayout(2, 1));
        textFieldPanel.add(userPanel);
        textFieldPanel.add(passPanel);

        userPanel.setBackground(new Color(123, 22, 192));
        userPanel.setLayout(new GridBagLayout());
        userPanel.add(usernameField);

        passPanel.setBackground(new Color(55, 88, 99));
        passPanel.setLayout(new GridBagLayout());
        passPanel.add(passwordField);

        btnPanel.setBackground(new Color(25, 99, 185));
        btnPanel.setLayout(new GridBagLayout());
        btnPanel.setPreferredSize(new Dimension(800, 180));
        btnPanel.add(loginBtn, gbc);
        gbc.gridy = 1;
        btnPanel.add(registerBtn, gbc);

        usernameField.setPreferredSize(new Dimension(300, 50));
        usernameField.setFont(textFieldFont);
        usernameField.setHorizontalAlignment(SwingConstants.LEFT);
        ((JTextField)usernameField).addKeyListener(this);

        passwordField.setPreferredSize(new Dimension(300, 50));
        passwordField.setEchoChar('•');
        passwordField.setFont(textFieldFont);
        passwordField.setHorizontalAlignment(SwingConstants.LEFT);
        ((JTextField)passwordField).addKeyListener(this);

        loginBtn.setVerticalAlignment(SwingConstants.CENTER);
        loginBtn.setPreferredSize(new Dimension(150, 50));
        loginBtn.setFont(textFieldFont);
        loginBtn.addActionListener(this);

        registerBtn.setVerticalAlignment(SwingConstants.CENTER);
        registerBtn.setPreferredSize(new Dimension(150, 50));
        registerBtn.setFont(textFieldFont);
        registerBtn.addActionListener(this);

        mainFrame.setVisible(true);
    }
    public void actionPerformed(ActionEvent event){
        if (event.getSource().equals(loginBtn)){
            login();
        }
        else if (event.getSource().equals(registerBtn)){
            mainFrame.setVisible(false);
            new RegisterGUI();
        }
    }
    public void keyPressed(KeyEvent key){
        if (key.getKeyCode() == KeyEvent.VK_ENTER){
            login();
        }
    }
    public void keyTyped(KeyEvent key){

    }
    public void keyReleased(KeyEvent key){

    }
    private void login(){
        if (checkIfLoginSuccess()){
            mainFrame.setVisible(false);
            MainGUI.setUser(user);
            new MainGUI();
        }
        else{
            JOptionPane.showMessageDialog(mainFrame, "Please Try Again.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }
    private boolean checkIfLoginSuccess(){
        try{
            MySQLConnector sql = new MySQLConnector("MyDatabase", "root", "031961698");
            String password = new String(passwordField.getPassword());
            user = new User(sql, usernameField.getText(), password);
            return user.getAccount();
        }
        catch (SQLException e){
            System.out.println(e);
            return false;
        }
    }
    public static void setFrameVisible(boolean b){
        mainFrame.setVisible(b);
    }
}
