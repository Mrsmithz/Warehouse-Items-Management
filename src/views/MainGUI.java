package views;

import java.awt.*;
import javax.swing.*;

public class MainGUI {
    private JFrame mainFrame;
    private JPanel mainPanel, leftPanel, topPanel, centerPanel;
    private JDesktopPane desktopPane;
    private Font myFont;
    public MainGUI(){
        createComponents();
        mainFrame.setSize(1200,800);
        mainFrame.setLayout(new BorderLayout());
        mainPanel.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);
        leftPanel.setPreferredSize(new Dimension(150, 500));
        topPanel.setPreferredSize(new Dimension(800, 50));
        leftPanel.setBackground(new Color(255,0,0));
        topPanel.setBackground(new Color(123,255,123));
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(desktopPane);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setBackground(new Color(0,0,0));
        desktopPane.add(createInternalFrame("test1"));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

    }
    private void createComponents(){
        mainFrame = new JFrame("views.MainGUI");
        mainPanel = new JPanel();
        leftPanel = new JPanel();
        topPanel = new JPanel();
        centerPanel = new JPanel();
        desktopPane = new JDesktopPane();
        myFont = new Font("Angsana New", Font.BOLD, 50);
    }
    private JInternalFrame createInternalFrame(String title){
        JInternalFrame frame = new JInternalFrame(title, true, true, true, true);
        //frame.setSize(1034,662);
        frame.setBackground(new Color(0, 0, 0));
        frame.setVisible(true);
        try{
            frame.setMaximum(true);
        }
        catch(java.beans.PropertyVetoException error){
            System.out.println(error);
        }
        frame.setBorder(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI)frame.getUI()).setNorthPane(null);
        frame.getContentPane().setLayout(new BorderLayout());
        JLabel test = new JLabel("Hello, World!");
        test.setFont(myFont);
        test.setForeground(new Color(255,0,0));
        test.setHorizontalAlignment(JLabel.CENTER);
        frame.getContentPane().add(test);
        return frame;
    }
}
