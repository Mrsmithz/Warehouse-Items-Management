package views;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import model.*;

public class MainGUI{
    private JFrame mainFrame;
    private JPanel mainPanel, leftPanel, topPanel, centerPanel;
    private JDesktopPane centerDesktopPane, leftDesktopPane, topDesktopPane;
    public MainGUI(){
        createComponents();
        setComponents();

    }
    private void createComponents(){
        mainFrame = new JFrame("views.MainGUI");
        mainPanel = new JPanel();
        leftPanel = new JPanel();
        topPanel = new JPanel();
        centerPanel = new JPanel();
        centerDesktopPane = new JDesktopPane();
        leftDesktopPane = new JDesktopPane();
        topDesktopPane = new JDesktopPane();
    }
    private void setComponents(){
        mainFrame.setSize(1200,800);
        mainFrame.setResizable(false);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(150, 500));
        leftPanel.setBackground(new Color(11,123,222));
        leftPanel.add(leftDesktopPane);

        topPanel.setLayout(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(800, 80));
        topPanel.setBackground(new Color(111,222,111));
        topPanel.add(topDesktopPane);

        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(centerDesktopPane);
        centerPanel.setBackground(new Color(99, 23, 100));

    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JPanel getLeftPanel() {
        return leftPanel;
    }

    public void setLeftPanel(JPanel leftPanel) {
        this.leftPanel = leftPanel;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(JPanel topPanel) {
        this.topPanel = topPanel;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    public JDesktopPane getCenterDesktopPane() {
        return centerDesktopPane;
    }

    public void setCenterDesktopPane(JDesktopPane centerDesktopPane) {
        this.centerDesktopPane = centerDesktopPane;
    }

    public JDesktopPane getLeftDesktopPane() {
        return leftDesktopPane;
    }

    public void setLeftDesktopPane(JDesktopPane leftDesktopPane) {
        this.leftDesktopPane = leftDesktopPane;
    }

    public JDesktopPane getTopDesktopPane() {
        return topDesktopPane;
    }

    public void setTopDesktopPane(JDesktopPane topDesktopPane) {
        this.topDesktopPane = topDesktopPane;
    }
}
