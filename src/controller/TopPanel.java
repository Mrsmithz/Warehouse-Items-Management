package controller;

import views.CreateShortcuts;

import javax.swing.*;
import java.awt.*;

public class TopPanel {
    private JInternalFrame mainFrame;
    private JPanel mainPanel;
    public TopPanel(){
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
        //mainPanel.setLayout(new BorderLayout());
        //mainPanel.setBackground(new Color(230, 150, 230));
        mainFrame.setVisible(true);

    }
    public JInternalFrame getMainFrame(){
        return this.mainFrame;
    }
}
