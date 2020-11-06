package views;

import controller.ProfileController;

import javax.swing.*;
import java.awt.*;
public class ProfileGUI {
    private JInternalFrame mainFrame;
    private JPanel mainPanel;
    private JLabel id, firstname, lastname, username, password, email, tel;
    private Font labelFont;
    private ProfileController pc;
    public ProfileGUI(ProfileController pc){
        this.pc = pc;
        createComponents();
        setComponents();
    }
    private void createComponents(){
        labelFont = new Font("Angsana New", Font.BOLD, 30);
        mainFrame = CreateShortcuts.createMyJInternalFrame("", false, false, false, false);
        mainPanel = new JPanel();
        id = new JLabel();
        firstname = new JLabel();
        lastname = new JLabel();
        username = new JLabel();
        password = new JLabel();
        email = new JLabel();
        tel = new JLabel();
    }
    private void setComponents(){
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);

        mainPanel.setLayout(new GridLayout(7,1));

        id.setHorizontalAlignment(JLabel.CENTER);
        id.setFont(labelFont);


        firstname.setHorizontalAlignment(JLabel.CENTER);
        firstname.setFont(labelFont);


        lastname.setHorizontalAlignment(JLabel.CENTER);
        lastname.setFont(labelFont);

        username.setHorizontalAlignment(JLabel.CENTER);
        username.setFont(labelFont);

        password.setHorizontalAlignment(JLabel.CENTER);
        password.setFont(labelFont);

        email.setHorizontalAlignment(JLabel.CENTER);
        email.setFont(labelFont);

        tel.setHorizontalAlignment(JLabel.CENTER);
        tel.setFont(labelFont);

        mainPanel.add(id);
        mainPanel.add(firstname);
        mainPanel.add(lastname);
        mainPanel.add(username);
        mainPanel.add(password);
        mainPanel.add(email);
        mainPanel.add(tel);


    }

    public JInternalFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JInternalFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JLabel getId() {
        return id;
    }

    public void setId(JLabel id) {
        this.id = id;
    }

    public JLabel getFirstname() {
        return firstname;
    }

    public void setFirstname(JLabel firstname) {
        this.firstname = firstname;
    }

    public JLabel getLastname() {
        return lastname;
    }

    public void setLastname(JLabel lastname) {
        this.lastname = lastname;
    }

    public JLabel getUsername() {
        return username;
    }

    public void setUsername(JLabel username) {
        this.username = username;
    }

    public JLabel getPassword() {
        return password;
    }

    public void setPassword(JLabel password) {
        this.password = password;
    }

    public JLabel getEmail() {
        return email;
    }

    public void setEmail(JLabel email) {
        this.email = email;
    }

    public JLabel getTel() {
        return tel;
    }

    public void setTel(JLabel tel) {
        this.tel = tel;
    }

    public Font getLabelFont() {
        return labelFont;
    }

    public void setLabelFont(Font labelFont) {
        this.labelFont = labelFont;
    }
}
