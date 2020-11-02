package controller;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import model.*;
import views.*;
public class MainGUI implements ActionListener{
    private JFrame mainFrame;
    private JPanel mainPanel, leftPanel, topPanel, centerPanel;
    private JDesktopPane centerDesktopPane, leftDesktopPane, topDesktopPane;
    private Font myFont;
    private JInternalFrame frame1, frame2, frame3;
    private JButton changeBtn, changeBtn2, changeBtn3;
    private static User user;
    private static ProfileGUI profileGUI;
    private static LeftPanel leftPanelGUI;
    private static TopPanel topPanelGUI;
    private static TableGUI tableGUI;
    public MainGUI(){
        createComponents();
        setComponents();

    }
    private void createComponents(){
        mainFrame = new JFrame("controller.MainGUI");
        mainPanel = new JPanel();
        leftPanel = new JPanel();
        topPanel = new JPanel();
        centerPanel = new JPanel();
        centerDesktopPane = new JDesktopPane();
        leftDesktopPane = new JDesktopPane();
        topDesktopPane = new JDesktopPane();
        myFont = new Font("Angsana New", Font.BOLD, 100);

        profileGUI = new ProfileGUI();
        tableGUI = new TableGUI();
        topPanelGUI = new TopPanel();
        leftPanelGUI = new LeftPanel();
    }
    private void setComponents(){
        mainFrame.setSize(1200,800);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(150, 500));
        leftPanel.setBackground(new Color(255,255,255));
        leftPanel.add(leftDesktopPane);

        topPanel.setLayout(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(800, 80));
        topPanel.setBackground(new Color(255,255,255));
        topPanel.add(topDesktopPane);

        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(centerDesktopPane);
        centerPanel.setBackground(new Color(255,255,255));

        topDesktopPane.add(topPanelGUI.getMainFrame());
        leftDesktopPane.add(leftPanelGUI.getMainFrame());

        centerDesktopPane.add(profileGUI.getMainFrame());
        centerDesktopPane.add(tableGUI.getMainFrame());
        mainFrame.setVisible(true);
    }
    private void setContents(JInternalFrame frame, String content){
        frame.getContentPane().setLayout(new BorderLayout());
        JLabel test = new JLabel(content);
        test.setForeground(new Color(255, 255, 255));
        test.setFont(myFont);
        test.setHorizontalAlignment(JLabel.CENTER);
        frame.getContentPane().add(test);
    }
    public void actionPerformed(ActionEvent event){

    }
    public static User getUser(){
        return user;
    }
    public static void setUser(User u){
        user = u;
    }
    public static ProfileGUI getProfileGUI(){
        return profileGUI;
    }
    public static TableGUI getTableGUI(){
        return tableGUI;
    }
}
