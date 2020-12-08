package controller;
import myutilities.JPlaceholderPasswordField;
import views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class SettingsController implements ActionListener {
    private MainController mc;
    private SettingsGUI settingsGUI;
    public SettingsController(MainController mc){
        this.mc = mc;
        this.settingsGUI = new SettingsGUI(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(settingsGUI.getChangePassBtn())){
            if (changePassword()){
                JOptionPane.showMessageDialog(mc.getMainFrame(), "Password Changed Successfully", "Alert", JOptionPane.INFORMATION_MESSAGE);
                logout();
            }
            else{
                JOptionPane.showMessageDialog(mc.getMainFrame(), "Password Changed Failed", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource().equals(settingsGUI.getLogoutBtn())){
            logout();
        }
        else if (e.getSource().equals(settingsGUI.getDeleteAcctBtn())){
            if (deleteAcct()){
                JOptionPane.showMessageDialog(mc.getMainFrame(), "Delete Account Successfully");
                logout();
            }
            else{
                JOptionPane.showMessageDialog(mc.getMainFrame(), "Delete Account Failed");
            }
        }
    }
    private boolean changePassword(){
        JPanel jOptionPanel = new JPanel();
        jOptionPanel.setLayout(new GridLayout(2, 1));
        JPlaceholderPasswordField oldPasswordField = new JPlaceholderPasswordField("Current Password");
        JPlaceholderPasswordField newPasswordField = new JPlaceholderPasswordField("New Password");
        int currentPass = JOptionPane.showConfirmDialog(mc.getMainFrame(), oldPasswordField, "Enter Current Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (currentPass == JOptionPane.OK_OPTION){
            int newPass = JOptionPane.showConfirmDialog(mc.getMainFrame(), newPasswordField, "Enter New Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (newPass == JOptionPane.OK_OPTION){
                String oldPass = new String(oldPasswordField.getPassword());
                String newPassword = new String(newPasswordField.getPassword());
                if (oldPass.equals(mc.getUser().getPassword())){
                    try{
                        return mc.getUser().changePassword(newPassword);
                    }
                    catch (SQLException e){
                        e.printStackTrace();
                        return false;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(mc.getMainFrame(), "Password Doesn't Matches", "Alert", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    private void logout(){
        mc.saveProfileImages();
        mc.getLoginController().getMainFrame().setVisible(true);
        mc.getMainFrame().dispose();
    }
    private boolean deleteAcct(){
        JPlaceholderPasswordField passwordField = new JPlaceholderPasswordField("Current Password");
        int dialog = JOptionPane.showConfirmDialog(mc.getMainFrame(), passwordField, "Enter Current Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (dialog == JOptionPane.OK_OPTION){
            int confirmDialog = JOptionPane.showConfirmDialog(mc.getMainFrame(), "Are you sure ?");
            String password = new String(passwordField.getPassword());
            if (confirmDialog == JOptionPane.YES_OPTION && password.equals(mc.getUser().getPassword())){
                try{
                    mc.getUser().deleteAllItem();
                    mc.getUser().deleteImage();
                    return mc.getUser().deleteAccount();
                }
                catch (SQLException e){
                    e.printStackTrace();
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public MainController getMc() {
        return mc;
    }

    public void setMc(MainController mc) {
        this.mc = mc;
    }

    public SettingsGUI getSettingsGUI() {
        return settingsGUI;
    }

    public void setSettingsGUI(SettingsGUI settingsGUI) {
        this.settingsGUI = settingsGUI;
    }
}
