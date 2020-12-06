package controller;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import mysql.MySQLConnector;
import myutilities.JPlaceholderPasswordField;
import myutilities.JPlaceholderTextField;
import views.*;
import model.*;
import java.io.*;
public class LoginController implements ActionListener, KeyListener {
    private LoginGUI loginGUI;
    private User user;
    private JButton loginBtn, registerBtn;
    private JFrame mainFrame;
    private JPlaceholderTextField usernameField;
    private JPlaceholderPasswordField passwordField;
    private RegisterController rc;
    public LoginController(){
        this.loginGUI = new LoginGUI(this);
        this.rc = new RegisterController(this);
        setComponents();
        loginGUI.getLogoPanel().getImageLabel().setIcon(new ImageIcon(this.getClass().getResource("/imgs/mlg-frog-login.gif")));
        loginGUI.getLogoPanel().getMainPanel().validate();
        try(AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Conan.wav").getAbsoluteFile())){
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-8);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }

    }
    private void setComponents(){
        this.loginBtn = loginGUI.getLoginBtn();
        this.registerBtn = loginGUI.getRegisterBtn();
        this.mainFrame = loginGUI.getMainFrame();
        this.usernameField = loginGUI.getUsernameField();
        this.passwordField = loginGUI.getPasswordField();
    }
    public void actionPerformed(ActionEvent event){
        if (event.getSource().equals(loginBtn)){
            login();
        }
        else if (event.getSource().equals(registerBtn)){
            rc.setRegisterGUIVisible(true);
            mainFrame.setVisible(false);
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
            new MainController(user, this);
            this.loginGUI.getUsernameField().setText("");
            this.loginGUI.getPasswordField().setText("");
        }
        else{
            JOptionPane.showMessageDialog(mainFrame, "Please Try Again.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }
    private boolean checkIfLoginSuccess(){
        try{
            MySQLConnector sql = new MySQLConnector("MyDatabase", "root", "031961698");
            String password = new String(passwordField.getPassword());
            user = new User(sql, usernameField.getText().strip(), password);
            return user.getAccount();
        }
        catch (SQLException e){
            System.out.println(e);
            return false;
        }
    }
    public void setLoginGUIVisible(boolean b){
        mainFrame.setVisible(b);
    }

    public LoginGUI getLoginGUI() {
        return loginGUI;
    }

    public void setLoginGUI(LoginGUI loginGUI) {
        this.loginGUI = loginGUI;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JButton getLoginBtn() {
        return loginBtn;
    }

    public void setLoginBtn(JButton loginBtn) {
        this.loginBtn = loginBtn;
    }

    public JButton getRegisterBtn() {
        return registerBtn;
    }

    public void setRegisterBtn(JButton registerBtn) {
        this.registerBtn = registerBtn;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JPlaceholderTextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(JPlaceholderTextField usernameField) {
        this.usernameField = usernameField;
    }

    public JPlaceholderPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPlaceholderPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public RegisterController getRc() {
        return rc;
    }

    public void setRc(RegisterController rc) {
        this.rc = rc;
    }

}
