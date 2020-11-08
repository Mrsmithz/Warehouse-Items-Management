package views;
import javax.swing.*;
import controller.*;
import myutilities.CreateShortcuts;
import myutilities.ImagePanel;

import java.awt.*;

public class DashboardGUI {
    private JInternalFrame mainFrame;
    private JPanel mainPanel;
    private JPanel pieChartPanel, barChartPanel, lineChartPanel, areaChartPanel;
    private DashboardController dc;
    public DashboardGUI(DashboardController dc){
        this.dc = dc;
        createComponents();
        setComponents();
    }
    private void createComponents(){
        mainFrame = CreateShortcuts.createMyJInternalFrame("", false, false, false, false);
        mainPanel = new JPanel();
        pieChartPanel = new JPanel();
        barChartPanel = new JPanel();
        lineChartPanel = new JPanel();
        areaChartPanel = new JPanel();
    }
    private void setComponents(){
        mainFrame.add(mainPanel);

        mainPanel.setLayout(new GridLayout(2,2));
        mainPanel.add(pieChartPanel);
        mainPanel.add(barChartPanel);
        mainPanel.add(lineChartPanel);
        mainPanel.add(areaChartPanel);

        pieChartPanel.setLayout(new BorderLayout());
        barChartPanel.setLayout(new BorderLayout());
        lineChartPanel.setLayout(new BorderLayout());
        areaChartPanel.setLayout(new BorderLayout());
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

    public JPanel getPieChartPanel() {
        return pieChartPanel;
    }

    public void setPieChartPanel(JPanel pieChartPanel) {
        this.pieChartPanel = pieChartPanel;
    }

    public JPanel getBarChartPanel() {
        return barChartPanel;
    }

    public void setBarChartPanel(JPanel barChartPanel) {
        this.barChartPanel = barChartPanel;
    }

    public JPanel getLineChartPanel() {
        return lineChartPanel;
    }

    public void setLineChartPanel(JPanel lineChartPanel) {
        this.lineChartPanel = lineChartPanel;
    }

    public JPanel getAreaChartPanel() {
        return areaChartPanel;
    }

    public void setAreaChartPanel(JPanel areaChartPanel) {
        this.areaChartPanel = areaChartPanel;
    }

    public DashboardController getDc() {
        return dc;
    }

    public void setDc(DashboardController dc) {
        this.dc = dc;
    }
}
