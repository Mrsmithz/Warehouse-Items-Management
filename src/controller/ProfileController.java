package controller;
import myutilities.CreateShortcuts;
import views.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.*;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class ProfileController implements MouseListener{
    private ProfileGUI profileGUI;
    private MainController mc;
    private File img;
    public ProfileController(MainController mc){
        this.mc = mc;
        this.profileGUI = new ProfileGUI(this);
        setComponents();
        getProfileImage();
        updateProfile();
    }
    private void getProfileImage(){
        try{
            img = mc.getUser().getProfileImage();
            if (img != null){
                updateImgProfile(img.getAbsolutePath());
            }
            else{
                File holder = new File("src/imgs/user-icon.png");
                updateImgProfile(holder.getAbsolutePath());
            }
        }
        catch (SQLException | IOException e){
            e.printStackTrace();
        }
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
        String name = String.format("%s %s", mc.getUser().getFirstname(), mc.getUser().getLastname());
        this.profileGUI.getNameLabel().setText(name);
        this.profileGUI.getEmailLabel().setText(mc.getUser().getEmail());
        this.profileGUI.getTelLabel().setText(mc.getUser().getTel());
        this.profileGUI.getMainFrame().setVisible(true);
    }
    private String openFileChooser(){
        final JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Image Chooser Tester");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg","jpeg","bmp","png");
        File workingdir = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingdir);
        chooser.addChoosableFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        int selectedButton = chooser.showDialog(null, "Open");
        if (selectedButton == chooser.APPROVE_OPTION){
            File currentdir = chooser.getCurrentDirectory();
            File selectedfile = chooser.getSelectedFile();
            File file = new File("src/imgs/profileimage.jpg");
            try(FileOutputStream output = new FileOutputStream(file);
                FileInputStream input = new FileInputStream(selectedfile);){
                byte[] buffer = new byte[1024];
                while(input.read(buffer) > 0){
                    output.write(buffer);
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return selectedfile.getAbsolutePath();
        }
        else{
            return null;
        }
    }
    private void updateName(){
        String firstname = JOptionPane.showInputDialog(mc.getMainFrame(), "Enter Firstname : ", mc.getUser().getFirstname());
        if (firstname == null){
            return;
        }
        String lastname = JOptionPane.showInputDialog(mc.getMainFrame(), "Enter Lastname : ", mc.getUser().getLastname());
        if (lastname == null){
            return;
        }
        if (nameValidator(firstname) && nameValidator(lastname)){
            String s = String.format("Your new name is \"%s %s\" do you want to proceed ?", firstname, lastname);
            int selected = JOptionPane.showConfirmDialog(mc.getMainFrame(), s);
            if (selected == JOptionPane.YES_OPTION){
                try{
                    if (mc.getUser().changeName(firstname, lastname)){
                        mc.getUser().getAccount();
                        String name = String.format("%s %s", mc.getUser().getFirstname(), mc.getUser().getLastname());
                        profileGUI.getNameLabel().setText(name);
                        profileGUI.getMainFrame().validate();
                        JOptionPane.showMessageDialog(mc.getMainFrame(), "Update Successfully.");
                    }
                    else{
                        JOptionPane.showMessageDialog(mc.getMainFrame(), "Please Try Again.");
                    }
                }
                catch (SQLException e){
                    JOptionPane.showMessageDialog(mc.getMainFrame(), "Please Try Again.");
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(mc.getMainFrame(), "Invalid input please try again.");
        }
    }
    private void updateEmail(){
        String email = JOptionPane.showInputDialog(mc.getMainFrame(), "Enter Email : ");
        if (email == null){
            return;
        }
        if (emailValidator(email)){
            String s = String.format("Your new email is \"%s\" do you want to proceed ?", email);
            int selected = JOptionPane.showConfirmDialog(mc.getMainFrame(), s);
            if (selected == JOptionPane.YES_OPTION){
                try{
                    if (mc.getUser().changeEmail(email)){
                        mc.getUser().getAccount();
                        profileGUI.getEmailLabel().setText(mc.getUser().getEmail());
                        profileGUI.getMainFrame().validate();
                        JOptionPane.showMessageDialog(mc.getMainFrame(), "Update Successfully.");
                    }
                    else{
                        JOptionPane.showMessageDialog(mc.getMainFrame(), "Please Try Again.");
                    }
                }
                catch (SQLException e){
                    JOptionPane.showMessageDialog(mc.getMainFrame(), "Please Try Again.");
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(mc.getMainFrame(), "Invalid input please try again.");
        }
    }
    private void updateTel(){
        String tel = JOptionPane.showInputDialog(mc.getMainFrame(), "Enter Telephone Number : ");
        if (tel == null){
            return;
        }
        if (telephoneValidator(tel)){
            String s = String.format("Your new phone number is \"%s\" do you want to proceed ?", tel);
            int selected = JOptionPane.showConfirmDialog(mc.getMainFrame(), s);
            if (selected == JOptionPane.YES_OPTION){
                try{
                    if (mc.getUser().changeTel(tel)){
                        mc.getUser().getAccount();
                        profileGUI.getTelLabel().setText(mc.getUser().getTel());
                        JOptionPane.showMessageDialog(mc.getMainFrame(), "Update successfully.");
                    }
                    else{
                        JOptionPane.showMessageDialog(mc.getMainFrame(), "Please Try Again.");
                    }
                }
                catch (SQLException e){
                    JOptionPane.showMessageDialog(mc.getMainFrame(), "Please Try Again.");
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(mc.getMainFrame(), "Invalid input please try again.");
        }
    }
    private boolean nameValidator(String name){
        return Pattern.matches("^[a-zA-Z]+$", name);
    }
    private boolean emailValidator(String email){
        return Pattern.matches("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)" +
                "|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", email);
    }
    private boolean telephoneValidator(String telephone){
        return Pattern.matches("^[0-9]{10}$", telephone);
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

    public File getImg() {
        return img;
    }

    public void setImg(File img) {
        this.img = img;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(profileGUI.getImageEdit())){
            String path = openFileChooser();
            if (path != null){
                updateImgProfile(path);
            }
        }
        else if (e.getSource().equals(profileGUI.getNameEdit())){
            updateName();
        }
        else if (e.getSource().equals(profileGUI.getEmailEdit())){
            updateEmail();
        }
        else if (e.getSource().equals(profileGUI.getTelEdit())){
            updateTel();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(profileGUI.getImageEdit())){
            profileGUI.getImageEdit().setText("Edit Image");
            profileGUI.getMainFrame().validate();
        }
        else if (e.getSource().equals(profileGUI.getNameEdit())){
            profileGUI.getNameLabel().setText("");
            profileGUI.getNameEdit().setText("Edit Name");
            profileGUI.getMainFrame().validate();

        }
        else if (e.getSource().equals(profileGUI.getEmailEdit())){
            profileGUI.getEmailLabel().setText("");
            profileGUI.getEmailEdit().setText("Edit email");
            profileGUI.getMainFrame().validate();
        }
        else if (e.getSource().equals(profileGUI.getTelEdit())){
            profileGUI.getTelLabel().setText("");
            profileGUI.getTelEdit().setText("Edit Telephone");
            profileGUI.getMainFrame().validate();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(profileGUI.getImageEdit())){
            profileGUI.getImageEdit().setText("");
            profileGUI.getMainFrame().validate();
        }
        else if (e.getSource().equals(profileGUI.getNameEdit())){
            String s = String.format("%s %s", mc.getUser().getFirstname(), mc.getUser().getLastname());
            profileGUI.getNameLabel().setText(s);
            profileGUI.getNameEdit().setText("");
            profileGUI.getMainFrame().validate();
        }
        else if (e.getSource().equals(profileGUI.getEmailEdit())){
            profileGUI.getEmailLabel().setText(mc.getUser().getEmail());
            profileGUI.getEmailEdit().setText("");
            profileGUI.getMainFrame().validate();
        }
        else if (e.getSource().equals(profileGUI.getTelEdit())){
            profileGUI.getTelLabel().setText(mc.getUser().getTel());
            profileGUI.getTelEdit().setText("");
            profileGUI.getMainFrame().validate();
        }
    }
}
