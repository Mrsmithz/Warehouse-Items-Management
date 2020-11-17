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
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(profileGUI.getImageEdit())){
            profileGUI.getImageEdit().setText("");
            profileGUI.getMainFrame().validate();
        }
    }
}
