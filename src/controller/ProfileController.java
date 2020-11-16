package controller;
import myutilities.CreateShortcuts;
import views.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ProfileController implements MouseListener {
    private ProfileGUI profileGUI;
    private MainController mc;
    public ProfileController(MainController mc){
        this.mc = mc;
        this.profileGUI = new ProfileGUI(this);
        setComponents();
        updateImgProfile("/imgs/user-icon.png");
        updateProfile();
    }
    private void updateImgProfile(String path){
        try{
            int width = profileGUI.getImageLabel().getPreferredSize().width;
            int height = profileGUI.getImageLabel().getPreferredSize().height;
            profileGUI.getImageLabel().setIcon(new ImageIcon(CreateShortcuts.roundedImage(path,width, height)));
            profileGUI.getMainFrame().validate();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    private void setComponents(){
    }
    private void updateProfile(){
        this.profileGUI.getMainFrame().setVisible(true);
    }

    public ProfileGUI getProfileGUI() {
        return profileGUI;
    }

    public void setProfileGUI(ProfileGUI profileGUI) {
        this.profileGUI = profileGUI;
    }

    public MainController getMc() {
        return mc;
    }

    public void setMc(MainController mc) {
        this.mc = mc;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
