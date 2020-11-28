package views;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import controller.MainController;
import model.*;

public class MainGUI{
    private JFrame mainFrame;
    private JPanel mainPanel, leftPanel, topPanel, centerPanel;
    private JDesktopPane centerDesktopPane, leftDesktopPane, topDesktopPane;
    private JMenuBar menuBar;
    private JMenu fileMenu, exportMenu;
    private JMenuItem excelMenuItem;
    private MainController mc;
    public MainGUI(MainController mc){
        this.mc = mc;
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
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        exportMenu = new JMenu();
        excelMenuItem = new JMenuItem();
    }
    private void setComponents(){
        mainFrame.setSize(1200,800);
        mainFrame.setResizable(false);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.addWindowListener(this.mc);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(200, 500));
        leftPanel.setBackground(new Color(11,123,222));
        leftPanel.add(leftDesktopPane);

        topPanel.setLayout(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(800, 80));
        topPanel.setBackground(new Color(111,222,111));
        topPanel.add(topDesktopPane);

        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(centerDesktopPane);
        centerPanel.setBackground(new Color(99, 23, 100));

        mainFrame.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        fileMenu.setText("File");
        fileMenu.add(exportMenu);
        exportMenu.setText("Export..");
        exportMenu.add(excelMenuItem);

        excelMenuItem.setText("To Excel");
        excelMenuItem.addActionListener(this.mc);

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

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public JMenu getFileMenu() {
        return fileMenu;
    }

    public void setFileMenu(JMenu fileMenu) {
        this.fileMenu = fileMenu;
    }

    public JMenu getExportMenu() {
        return exportMenu;
    }

    public void setExportMenu(JMenu exportMenu) {
        this.exportMenu = exportMenu;
    }

    public JMenuItem getExcelMenuItem() {
        return excelMenuItem;
    }

    public void setExcelMenuItem(JMenuItem excelMenuItem) {
        this.excelMenuItem = excelMenuItem;
    }

    public MainController getMc() {
        return mc;
    }

    public void setMc(MainController mc) {
        this.mc = mc;
    }
}
