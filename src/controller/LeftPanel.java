package controller;
import controller.MainGUI;
import views.CreateShortcuts;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class LeftPanel implements ActionListener {
    private JInternalFrame mainFrame;
    private JButton btn1, btn2, btn3, btn4, btn5;
    private JPanel mainPanel;
    public LeftPanel(){
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

        btn1.setText("Profile");
        btn1.addActionListener(this);

        btn2.setText("Dashboard");
        btn2.addActionListener(this);

        btn3.setText("Table");
        btn3.addActionListener(this);

        btn4.setText("History");
        btn4.addActionListener(this);

        btn5.setText("Setting");
        btn5.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event){
        if (event.getSource().equals(btn1)){
            if (MainGUI.getTableGUI().getMainFrame().isVisible()){
                MainGUI.getTableGUI().getMainFrame().setVisible(false);
                MainGUI.getProfileGUI().getMainFrame().setVisible(true);
            }
        }
        else if (event.getSource().equals(btn2)){
            if (MainGUI.getProfileGUI().getMainFrame().isVisible()){
                MainGUI.getProfileGUI().getMainFrame().setVisible(false);
                MainGUI.getTableGUI().updateTable();
                MainGUI.getTableGUI().getMainFrame().setVisible(true);
            }
        }
    }
    public JInternalFrame getMainFrame(){
        return this.mainFrame;
    }
}
