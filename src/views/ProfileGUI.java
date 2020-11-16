package views;

import controller.ProfileController;
import myutilities.CreateShortcuts;
import myutilities.TranslucentButton;
import myutilities.TranslucentButtonIcon;

import javax.swing.*;
import java.awt.*;

public class ProfileGUI {
    private JInternalFrame mainFrame;
    private JPanel mainPanel;
    private Font labelFont;
    private ProfileController pc;
    private JPanel imagePanel;
    private JLabel imageLabel;
    private JButton imageEdit;
    public ProfileGUI(ProfileController pc){
        this.pc = pc;
        createComponents();
        setComponents();
    }
    private void createComponents(){
        labelFont = new Font("Angsana New", Font.BOLD, 20);
        mainFrame = CreateShortcuts.createMyJInternalFrame("", false, false, false, false);
        mainPanel = new JPanel();

        imagePanel = new JPanel();
        imageLabel = new JLabel();
        imageEdit = new JButton("Edit Image");

    }
    private void setComponents(){
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(imagePanel, BorderLayout.NORTH);

        imagePanel.setPreferredSize(new Dimension(1050, 300));
        imagePanel.setLayout(new GridBagLayout());
        imagePanel.add(imageLabel);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(250, 250));
        imageLabel.addMouseListener(this.pc);
        imageLabel.setLayout(new GridBagLayout());
        imageLabel.add(imageEdit);

        imageEdit.setHorizontalAlignment(SwingConstants.CENTER);
        imageEdit.setFont(labelFont);
        imageEdit.setPreferredSize(new Dimension(200, 80));
        imageEdit.setForeground(Color.RED);
        imageEdit.setContentAreaFilled(false);
        imageEdit.setFocusPainted(false);
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
}
