package views;
import javax.swing.*;
import java.awt.*;
import myutilities.*;
import controller.*;
public class SettingsGUI {
    private JInternalFrame mainFrame;
    private JPanel mainPanel, changePassPanel, logoutPanel, deleteAcctPanel;
    private JButton changePassBtn, logoutBtn, deleteAcctBtn;
    private SettingsController sc;
    public SettingsGUI(SettingsController sc){
        this.sc = sc;
        createComponents();
        setComponents();
    }
    private void createComponents(){
        mainFrame = CreateShortcuts.createMyJInternalFrame("",false,false,false,false);
        mainPanel = new JPanel();
        changePassPanel = new JPanel();
        logoutPanel = new JPanel();
        deleteAcctPanel = new JPanel();
        changePassBtn = new JButton("Change Password");
        logoutBtn = new JButton("Logout");
        deleteAcctBtn = new JButton("Delete Account");

    }
    private void setComponents(){
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);

        mainPanel.setLayout(new GridLayout(3, 1));
        mainPanel.add(changePassPanel);
        mainPanel.add(logoutPanel);
        mainPanel.add(deleteAcctPanel);

        changePassPanel.setLayout(new GridBagLayout());
        changePassPanel.add(changePassBtn);

        logoutPanel.setLayout(new GridBagLayout());
        logoutPanel.add(logoutBtn);

        deleteAcctBtn.setLayout(new GridBagLayout());
        deleteAcctPanel.add(deleteAcctBtn);

        changePassBtn.addActionListener(this.sc);
        logoutBtn.addActionListener(this.sc);
        deleteAcctBtn.addActionListener(this.sc);
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

    public JPanel getChangePassPanel() {
        return changePassPanel;
    }

    public void setChangePassPanel(JPanel changePassPanel) {
        this.changePassPanel = changePassPanel;
    }

    public JPanel getLogoutPanel() {
        return logoutPanel;
    }

    public void setLogoutPanel(JPanel logoutPanel) {
        this.logoutPanel = logoutPanel;
    }

    public JPanel getDeleteAcctPanel() {
        return deleteAcctPanel;
    }

    public void setDeleteAcctPanel(JPanel deleteAcctPanel) {
        this.deleteAcctPanel = deleteAcctPanel;
    }

    public JButton getChangePassBtn() {
        return changePassBtn;
    }

    public void setChangePassBtn(JButton changePassBtn) {
        this.changePassBtn = changePassBtn;
    }

    public JButton getLogoutBtn() {
        return logoutBtn;
    }

    public void setLogoutBtn(JButton logoutBtn) {
        this.logoutBtn = logoutBtn;
    }

    public JButton getDeleteAcctBtn() {
        return deleteAcctBtn;
    }

    public void setDeleteAcctBtn(JButton deleteAcctBtn) {
        this.deleteAcctBtn = deleteAcctBtn;
    }

    public SettingsController getSc() {
        return sc;
    }

    public void setSc(SettingsController sc) {
        this.sc = sc;
    }
}
