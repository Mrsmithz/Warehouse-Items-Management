package views;

import controller.TopPanelController;
import myutilities.CreateShortcuts;

import javax.swing.*;
import java.awt.*;

public class TopPanel {
    private JInternalFrame mainFrame;
    private JPanel mainPanel;
    private TopPanelController tc;
    public TopPanel(TopPanelController tc){
        this.tc = tc;
        createComponents();
        setComponents();
    }
    private void createComponents() {
        mainFrame = CreateShortcuts.createMyJInternalFrame("", false, false, true, false);
        mainPanel = CreateShortcuts.createImagePanel("/imgs/main-test.png");
    }
    private void setComponents(){
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);

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

    public TopPanelController getTc() {
        return tc;
    }

    public void setTc(TopPanelController tc) {
        this.tc = tc;
    }
}
