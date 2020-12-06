package views;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import myutilities.*;
import controller.*;
public class SettingsGUI {
    private JInternalFrame mainFrame;
    private JPanel mainPanel, changePassPanel, logoutPanel, deleteAcctPanel;
    private JButton changePassBtn, logoutBtn, deleteAcctBtn;
    private SettingsController sc;
    private JLabel changeLabel, changeLabelIcon, logoutLabel, logoutLabelIcon, deleteLabel, deleteLabelIcon;
    private Font btnFont;
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
        changePassBtn = new JButton();
        logoutBtn = new JButton();
        deleteAcctBtn = new JButton();
        changeLabel = new JLabel();
        changeLabelIcon = new JLabel();
        logoutLabel = new JLabel();
        logoutLabelIcon = new JLabel();
        deleteLabel = new JLabel();
        deleteLabelIcon = new JLabel();
        try (InputStream input = this.getClass().getResourceAsStream("/font/SukhumvitSet-Bold.ttf")){
            btnFont = Font.createFont(Font.TRUETYPE_FONT, input).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(btnFont);
        } catch (IOException | FontFormatException e) {
            btnFont = new Font("Angsana New", Font.PLAIN, 12);
        }
    }
    private void setComponents(){
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);

        mainPanel.setLayout(new GridLayout(3,1));
        mainPanel.add(changePassPanel);
        mainPanel.add(logoutPanel);
        mainPanel.add(deleteAcctPanel);

        changePassPanel.setLayout(new GridBagLayout());
        changePassPanel.add(changePassBtn);
        ImageIcon changeIcon = new ImageIcon(this.getClass().getResource("/imgs/exchange.png"));
        changePassBtn.setLayout(new BorderLayout());
        changeLabel.setText("Change Password");
        changeLabel.setFont(btnFont.deriveFont(25f));
        changeLabel.setForeground(new Color(240,240,240));
        changeLabelIcon.setIcon(changeIcon);
        changeLabelIcon.setHorizontalAlignment(SwingConstants.LEFT);
        changeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        changePassBtn.add(changeLabelIcon, BorderLayout.WEST);
        changePassBtn.add(changeLabel, BorderLayout.CENTER);
        changePassBtn.setPreferredSize(new Dimension(300,100));
        changePassBtn.setBorderPainted(false);

        logoutPanel.setLayout(new GridBagLayout());
        logoutPanel.add(logoutBtn);
        ImageIcon logoutIcon = new ImageIcon(this.getClass().getResource("/imgs/logout.png"));
        logoutBtn.setLayout(new BorderLayout());
        logoutLabel.setText("Log out");
        logoutLabel.setForeground(new Color(240,240,240));
        logoutLabel.setFont(btnFont.deriveFont(25f));
        logoutLabelIcon.setIcon(logoutIcon);
        logoutLabelIcon.setHorizontalAlignment(SwingConstants.LEFT);
        logoutLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoutBtn.add(logoutLabelIcon, BorderLayout.WEST);
        logoutBtn.add(logoutLabel, BorderLayout.CENTER);
        logoutBtn.setPreferredSize(new Dimension(300,100));
        logoutBtn.setBorderPainted(false);

        deleteAcctPanel.setLayout(new GridBagLayout());
        deleteAcctPanel.add(deleteAcctBtn);
        ImageIcon deleteIcon = new ImageIcon(this.getClass().getResource("/imgs/delete.png"));
        deleteAcctBtn.setLayout(new BorderLayout());
        deleteLabel.setText("Delete Account");
        deleteLabel.setForeground(new Color(240,240,240));
        deleteLabel.setFont(btnFont.deriveFont(25f));
        deleteLabelIcon.setIcon(deleteIcon);
        deleteLabelIcon.setHorizontalAlignment(SwingConstants.LEFT);
        deleteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deleteAcctBtn.add(deleteLabelIcon, BorderLayout.WEST);
        deleteAcctBtn.add(deleteLabel, BorderLayout.CENTER);
        deleteAcctBtn.setPreferredSize(new Dimension(300,100));
        deleteAcctBtn.setBorderPainted(false);

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

    public JLabel getChangeLabel() {
        return changeLabel;
    }

    public void setChangeLabel(JLabel changeLabel) {
        this.changeLabel = changeLabel;
    }

    public JLabel getChangeLabelIcon() {
        return changeLabelIcon;
    }

    public void setChangeLabelIcon(JLabel changeLabelIcon) {
        this.changeLabelIcon = changeLabelIcon;
    }

    public JLabel getLogoutLabel() {
        return logoutLabel;
    }

    public void setLogoutLabel(JLabel logoutLabel) {
        this.logoutLabel = logoutLabel;
    }

    public JLabel getLogoutLabelIcon() {
        return logoutLabelIcon;
    }

    public void setLogoutLabelIcon(JLabel logoutLabelIcon) {
        this.logoutLabelIcon = logoutLabelIcon;
    }

    public JLabel getDeleteLabel() {
        return deleteLabel;
    }

    public void setDeleteLabel(JLabel deleteLabel) {
        this.deleteLabel = deleteLabel;
    }

    public JLabel getDeleteLabelIcon() {
        return deleteLabelIcon;
    }

    public void setDeleteLabelIcon(JLabel deleteLabelIcon) {
        this.deleteLabelIcon = deleteLabelIcon;
    }

    public Font getBtnFont() {
        return btnFont;
    }

    public void setBtnFont(Font btnFont) {
        this.btnFont = btnFont;
    }
}
