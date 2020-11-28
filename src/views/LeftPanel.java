package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import controller.*;
import myutilities.CreateShortcuts;

public class LeftPanel {
    private JInternalFrame mainFrame;
    private JButton btn1, btn2, btn3, btn4, btn5;
    private JPanel mainPanel;
    private JLabel profileLabel, profileIconLabel, dashboardLabel, dashboardIconLabel, tableLabel, tableIconLabel, addLabel, addIconLabel, settingLabel, settingIconLabel;
    private LeftPanelController lc;
    private Font leftFont;
    public LeftPanel(LeftPanelController lc){
        this.lc = lc;
        createComponents();
        setComponents();
    }
    private void createComponents(){
        mainFrame = CreateShortcuts.createMyJInternalFrame("", false, false, true, false);
        mainPanel = new JPanel();
        btn1 = new JButton();
        btn2 = new JButton();
        btn3 = new JButton();
        btn4 = new JButton();
        btn5 = new JButton();
        profileLabel = new JLabel();
        profileIconLabel = new JLabel();
        dashboardLabel = new JLabel();
        dashboardIconLabel = new JLabel();
        leftFont = new Font("Courier new", Font.BOLD, 20);

    }
    private void setComponents(){
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        mainPanel.setLayout(new GridLayout(5, 1));
        mainPanel.add(btn1);
        mainPanel.add(btn2);
        mainPanel.add(btn3);
        mainPanel.add(btn4);
        mainPanel.add(btn5);

        ImageIcon profileIcon = new ImageIcon(this.getClass().getResource("/imgs/man.png"));
        btn1.setLayout(new BorderLayout());
        profileLabel.setText("Profile");
        profileLabel.setFont(leftFont);
        profileIconLabel.setIcon(profileIcon);
        profileIconLabel.setHorizontalAlignment(SwingConstants.LEFT);
        profileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        btn1.add(profileIconLabel, BorderLayout.WEST);
        btn1.add(profileLabel, BorderLayout.CENTER);
        btn1.addActionListener(this.lc);

        ImageIcon dashboardIcon = new ImageIcon(this.getClass().getResource("/imgs/graph.png"));
        btn2.setLayout(new BorderLayout());
        dashboardLabel.setText("Graph");
        dashboardLabel.setFont(leftFont);
        dashboardIconLabel.setIcon(dashboardIcon);
        dashboardIconLabel.setHorizontalAlignment(SwingConstants.LEFT);
        dashboardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        btn2.add(dashboardIconLabel, BorderLayout.WEST);
        btn2.add(dashboardLabel, BorderLayout.CENTER);
        btn2.addActionListener(this.lc);
        btn2.setToolTipText("Hover your mouse in the graph area to pause the graph.");

        btn3.setText("Table");
        btn3.addActionListener(this.lc);

        btn4.setText("Add Item");
        btn4.addActionListener(this.lc);

        btn5.setText("Setting");
        btn5.addActionListener(this.lc);
    }

    public JInternalFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JInternalFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JButton getBtn1() {
        return btn1;
    }

    public void setBtn1(JButton btn1) {
        this.btn1 = btn1;
    }

    public JButton getBtn2() {
        return btn2;
    }

    public void setBtn2(JButton btn2) {
        this.btn2 = btn2;
    }

    public JButton getBtn3() {
        return btn3;
    }

    public void setBtn3(JButton btn3) {
        this.btn3 = btn3;
    }

    public JButton getBtn4() {
        return btn4;
    }

    public void setBtn4(JButton btn4) {
        this.btn4 = btn4;
    }

    public JButton getBtn5() {
        return btn5;
    }

    public void setBtn5(JButton btn5) {
        this.btn5 = btn5;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public LeftPanelController getLc() {
        return lc;
    }

    public void setLc(LeftPanelController lc) {
        this.lc = lc;
    }
}
