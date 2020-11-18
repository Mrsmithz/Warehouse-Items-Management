package views;

import controller.ProfileController;
import myutilities.CreateShortcuts;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ProfileGUI {
    private JInternalFrame mainFrame;
    private JPanel mainPanel;
    private Font labelFont;
    private ProfileController pc;
    private JPanel imagePanel, namePanel, descPanel;
    private JLabel imageLabel, nameLabel, emailLabel, telLabel;
    private JButton imageEdit;
    public ProfileGUI(ProfileController pc){
        this.pc = pc;
        createComponents();
        setComponents();
        createDescComponents();
    }
    private void createComponents(){
        labelFont = new Font("Angsana New", Font.BOLD, 20);
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
        imageEdit.setFont(new Font("Angasana New", Font.BOLD, 30));
        imageEdit.setPreferredSize(new Dimension(200, 80));
        imageEdit.setForeground(Color.RED);
        imageEdit.setContentAreaFilled(false);
        imageEdit.setFocusPainted(false);
        imageEdit.addMouseListener(this.pc);

        namePanel.setLayout(new GridBagLayout());
        namePanel.setPreferredSize(new Dimension(1050, 100));
        namePanel.add(nameLabel);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setLayout(new GridBagLayout());
        nameLabel.setFont(new Font("Angasana New", Font.BOLD, 50));
        nameLabel.setForeground(Color.WHITE);

        descPanel.setLayout(new GridBagLayout());
        descPanel.setPreferredSize(new Dimension(1050, 300));
        emailLabel.setFont(new Font("Angsana New", Font.BOLD, 30));
        telLabel.setFont(new Font("Angsana New", Font.BOLD, 30));

    }
    private void createDescComponents(){
        final Insets WEST_INSETS = new Insets(5,0,5,5);
        final Insets EAST_INSETS = new Insets(5,5,5,0);
        LinkedHashMap<String, JLabel> fieldMap = new LinkedHashMap<String, JLabel>();
        fieldMap.put("Email : ", emailLabel);
        fieldMap.put("Telephone : ", telLabel);
        GridBagConstraints gbc;
        int i = 0;
        for (String key : fieldMap.keySet()){
            JLabel title = new JLabel(key, SwingConstants.CENTER);
            title.setFont(new Font("Angsana New", Font.BOLD, 30));
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

    public Font getLabelFont() {
        return labelFont;
    }

    public void setLabelFont(Font labelFont) {
        this.labelFont = labelFont;
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
}
