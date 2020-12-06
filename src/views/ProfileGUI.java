package views;

import controller.ProfileController;
import myutilities.CreateShortcuts;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ProfileGUI {
    private JInternalFrame mainFrame;
    private JPanel mainPanel;
    private Font nameFont, btnFont;
    private ProfileController pc;
    private JPanel imagePanel, namePanel, descPanel;
    private JLabel imageLabel, nameLabel, emailLabel, telLabel;
    private JButton imageEdit, nameEdit, emailEdit, telEdit;
    public ProfileGUI(ProfileController pc){
        this.pc = pc;
        createComponents();
        setComponents();
        createDescComponents();
    }
    private void createComponents(){
        try (InputStream nameInput = this.getClass().getResourceAsStream("/font/SukhumvitSet-Bold.ttf");
             InputStream btnInput = this.getClass().getResourceAsStream("/font/SukhumvitSet-SemiBold.ttf");){
            nameFont = Font.createFont(Font.TRUETYPE_FONT, nameInput).deriveFont(35f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(nameFont);

            btnFont = Font.createFont(Font.TRUETYPE_FONT, btnInput).deriveFont(35f);
            GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge2.registerFont(btnFont);
        } catch (IOException | FontFormatException e) {
            nameFont = new Font("Angsana New", Font.PLAIN, 12);
            btnFont = new Font("Angsana New", Font.PLAIN, 12);
        }
        mainFrame = CreateShortcuts.createMyJInternalFrame("", false, false, false, false);
        mainPanel = new JPanel();

        imagePanel = new JPanel();
        imageLabel = new JLabel();
        imageEdit = new JButton();
        namePanel = new JPanel();
        descPanel = new JPanel();
        nameLabel = new JLabel();
        emailLabel = new JLabel();
        telLabel = new JLabel();
        nameEdit = new JButton();
        emailEdit = new JButton();
        telEdit = new JButton();
    }
    private void setComponents() {
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(imagePanel, BorderLayout.NORTH);
        mainPanel.add(namePanel, BorderLayout.CENTER);
        mainPanel.add(descPanel, BorderLayout.SOUTH);

        imagePanel.setPreferredSize(new Dimension(1050, 300));
        imagePanel.setLayout(new GridBagLayout());
        imagePanel.add(imageLabel);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(250, 250));
        imageLabel.setLayout(new GridBagLayout());
        imageLabel.add(imageEdit);

        imageEdit.setHorizontalAlignment(SwingConstants.CENTER);
        imageEdit.setFont(btnFont);
        imageEdit.setPreferredSize(new Dimension(200, 80));
        imageEdit.setForeground(new Color(155,30,205));
        imageEdit.setContentAreaFilled(false);
        imageEdit.setFocusPainted(false);
        imageEdit.addMouseListener(this.pc);
        imageEdit.setToolTipText("Click to change profile picture.");

        namePanel.setLayout(new BorderLayout());
        namePanel.setPreferredSize(new Dimension(1050, 100));
        namePanel.add(nameLabel);

        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setLayout(new BorderLayout());
        nameLabel.setFont(nameFont);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.add(nameEdit);

        nameEdit.setHorizontalAlignment(SwingConstants.CENTER);
        nameEdit.setFont(btnFont);
        nameEdit.setForeground(new Color(155,30,205));
        nameEdit.setContentAreaFilled(false);
        nameEdit.setFocusPainted(false);
        nameEdit.addMouseListener(this.pc);
        nameEdit.setToolTipText("Click to change your name.");

        descPanel.setLayout(new GridBagLayout());
        descPanel.setPreferredSize(new Dimension(1050, 300));

        emailLabel.setFont(btnFont);
        emailLabel.setLayout(new BorderLayout());
        emailLabel.setMinimumSize(new Dimension(400, 50));
        emailLabel.setMaximumSize(new Dimension(800, 50));
        emailLabel.setPreferredSize(new Dimension(800, 50));
        emailLabel.add(emailEdit);

        emailEdit.setHorizontalAlignment(SwingConstants.LEFT);
        emailEdit.setFont(btnFont);
        emailEdit.setForeground(new Color(155,30,205));
        emailEdit.setContentAreaFilled(false);
        emailEdit.setFocusPainted(false);
        emailEdit.addMouseListener(this.pc);
        emailEdit.setToolTipText("Click to change your email.");

        telLabel.setFont(btnFont);
        telLabel.setLayout(new BorderLayout());
        telLabel.setMinimumSize(new Dimension(400, 50));
        telLabel.setMaximumSize(new Dimension(800, 50));
        telLabel.setPreferredSize(new Dimension(800, 50));
        telLabel.add(telEdit);

        telEdit.setHorizontalAlignment(SwingConstants.LEFT);
        telEdit.setFont(btnFont);
        telEdit.setForeground(new Color(155,30,205));
        telEdit.setContentAreaFilled(false);
        telEdit.setFocusPainted(false);
        telEdit.addMouseListener(this.pc);
        telEdit.setToolTipText("Click to change your telephone number.");

    }
    private void createDescComponents(){
        final Insets WEST_INSETS = new Insets(5,0,5,5);
        final Insets EAST_INSETS = new Insets(5,5,5,0);
        LinkedHashMap<String, JLabel> fieldMap = new LinkedHashMap<String, JLabel>();
        fieldMap.put("Email : ", emailLabel);
        fieldMap.put("  Telephone : ", telLabel);
        GridBagConstraints gbc;
        int i = 0;
        for (String key : fieldMap.keySet()){
            JLabel title = new JLabel(key, SwingConstants.CENTER);
            title.setFont(nameFont);
            gbc = createGbc(0, i, WEST_INSETS, EAST_INSETS);
            descPanel.add(title,gbc);
            gbc = createGbc(1, i, WEST_INSETS, EAST_INSETS);
            descPanel.add(fieldMap.get(key), gbc);
            i++;
        }
    }
    private GridBagConstraints createGbc(int x, int y, Insets west, Insets east){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.anchor = (x == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
        gbc.fill = (x == 0) ? GridBagConstraints.BOTH : GridBagConstraints.HORIZONTAL;

        gbc.insets = (x == 0) ? west : east;
        gbc.weightx = (x == 0) ? 0.1 : 1.0;
        gbc.weighty = 1.0;
        return gbc;
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



    public JLabel getImageLabel() {
        return imageLabel;
    }

    public void setImageLabel(JLabel imageLabel) {
        this.imageLabel = imageLabel;
    }

    public ProfileController getPc() {
        return pc;
    }

    public void setPc(ProfileController pc) {
        this.pc = pc;
    }

    public JPanel getImagePanel() {
        return imagePanel;
    }

    public void setImagePanel(JPanel imagePanel) {
        this.imagePanel = imagePanel;
    }

    public JButton getImageEdit() {
        return imageEdit;
    }

    public void setImageEdit(JButton imageEdit) {
        this.imageEdit = imageEdit;
    }

    public JPanel getNamePanel() {
        return namePanel;
    }

    public void setNamePanel(JPanel namePanel) {
        this.namePanel = namePanel;
    }

    public JPanel getDescPanel() {
        return descPanel;
    }

    public void setDescPanel(JPanel descPanel) {
        this.descPanel = descPanel;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    public JLabel getEmailLabel() {
        return emailLabel;
    }

    public void setEmailLabel(JLabel emailLabel) {
        this.emailLabel = emailLabel;
    }

    public JLabel getTelLabel() {
        return telLabel;
    }

    public void setTelLabel(JLabel telLabel) {
        this.telLabel = telLabel;
    }

    public JButton getNameEdit() {
        return nameEdit;
    }

    public void setNameEdit(JButton nameEdit) {
        this.nameEdit = nameEdit;
    }

    public Font getNameFont() {
        return nameFont;
    }

    public void setNameFont(Font nameFont) {
        this.nameFont = nameFont;
    }

    public Font getBtnFont() {
        return btnFont;
    }

    public void setBtnFont(Font btnFont) {
        this.btnFont = btnFont;
    }

    public JButton getEmailEdit() {
        return emailEdit;
    }

    public void setEmailEdit(JButton emailEdit) {
        this.emailEdit = emailEdit;
    }

    public JButton getTelEdit() {
        return telEdit;
    }

    public void setTelEdit(JButton telEdit) {
        this.telEdit = telEdit;
    }


}
