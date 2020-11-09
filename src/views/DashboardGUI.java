package views;
import javax.swing.*;
import controller.*;
import myutilities.CreateShortcuts;
import myutilities.ImagePanel;

import java.awt.*;

public class DashboardGUI {
    private JInternalFrame mainFrame;
    private JPanel mainPanel;
    private JPanel quantitiesPieChartPanel, typesPieChartPanel, pricesBarChartPanel, weightBarChartPanel;
    private DashboardController dc;
    public DashboardGUI(DashboardController dc){
        this.dc = dc;
        createComponents();
        setComponents();
    }
    private void createComponents(){
        mainFrame = CreateShortcuts.createMyJInternalFrame("", false, false, false, false);
        mainPanel = new JPanel();
        quantitiesPieChartPanel = new JPanel();
        typesPieChartPanel = new JPanel();
        pricesBarChartPanel = new JPanel();
        weightBarChartPanel = new JPanel();
    }
    private void setComponents(){
        mainFrame.add(mainPanel);

        mainPanel.setLayout(new GridLayout(2,2));

        mainPanel.add(quantitiesPieChartPanel);
        mainPanel.add(pricesBarChartPanel);
        mainPanel.add(typesPieChartPanel);
        mainPanel.add(weightBarChartPanel);

        quantitiesPieChartPanel.setLayout(new BorderLayout());
        pricesBarChartPanel.setLayout(new BorderLayout());
        typesPieChartPanel.setLayout(new BorderLayout());
        weightBarChartPanel.setLayout(new BorderLayout());
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

    public DashboardController getDc() {
        return dc;
    }

    public void setDc(DashboardController dc) {
        this.dc = dc;
    }

    public JPanel getQuantitiesPieChartPanel() {
        return quantitiesPieChartPanel;
    }

    public void setQuantitiesPieChartPanel(JPanel quantitiesPieChartPanel) {
        this.quantitiesPieChartPanel = quantitiesPieChartPanel;
    }

    public JPanel getTypesPieChartPanel() {
        return typesPieChartPanel;
    }

    public void setTypesPieChartPanel(JPanel typesPieChartPanel) {
        this.typesPieChartPanel = typesPieChartPanel;
    }

    public JPanel getPricesBarChartPanel() {
        return pricesBarChartPanel;
    }

    public void setPricesBarChartPanel(JPanel pricesBarChartPanel) {
        this.pricesBarChartPanel = pricesBarChartPanel;
    }

    public JPanel getWeightBarChartPanel() {
        return weightBarChartPanel;
    }

    public void setWeightBarChartPanel(JPanel weightBarChartPanel) {
        this.weightBarChartPanel = weightBarChartPanel;
    }
}
