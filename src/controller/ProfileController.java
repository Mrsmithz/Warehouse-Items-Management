package controller;
import views.*;
import javax.swing.*;
import java.awt.*;
public class ProfileController {
    private ProfileGUI profileGUI;
    private JLabel id, firstname, lastname, username, password, email, tel;
    private MainController mc;
    public ProfileController(MainController mc){
        this.mc = mc;
        this.profileGUI = new ProfileGUI(this);
        setComponents();
        updateProfile();
    }
    private void setComponents(){
        this.id = profileGUI.getId();
        this.firstname = profileGUI.getFirstname();
        this.lastname = profileGUI.getLastname();
        this.username = profileGUI.getUsername();
        this.password = profileGUI.getPassword();
        this.email = profileGUI.getEmail();
        this.tel = profileGUI.getTel();
    }
    private void updateProfile(){
        this.id.setText(String.valueOf(mc.getUser().getId()));
        this.firstname.setText(mc.getUser().getFirstname());
        this.lastname.setText(mc.getUser().getLastname());
        this.username.setText(mc.getUser().getUsername());
        this.password.setText(mc.getUser().getPassword());
        this.email.setText(mc.getUser().getEmail());
        this.tel.setText(mc.getUser().getTel());
        this.profileGUI.getMainFrame().setVisible(true);
    }

    public ProfileGUI getProfileGUI() {
        return profileGUI;
    }

    public void setProfileGUI(ProfileGUI profileGUI) {
        this.profileGUI = profileGUI;
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

    public MainController getMc() {
        return mc;
    }

    public void setMc(MainController mc) {
        this.mc = mc;
    }
}
