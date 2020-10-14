package views;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class MainGUI implements ActionListener{
    private JFrame mainFrame;
    private JPanel mainPanel, leftPanel, topPanel, centerPanel;
    private JDesktopPane centerDesktopPane, leftDesktopPane, topDesktopPane;
    private Font myFont;
    private JInternalFrame frame1, frame2, frame3;
    private JButton changeBtn, changeBtn2, changeBtn3;
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
        myFont = new Font("Angsana New", Font.BOLD, 100);
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

        leftDesktopPane.add(new LeftPanel().getMainFrame());
        topDesktopPane.add(new TopPanel().getMainFrame());
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
    private JInternalFrame createInternalFrame(String title){
        JInternalFrame frame = new JInternalFrame(title, true, true, true, true);
        //frame.setSize(1034,662);
        //frame.setVisible(true);
        try{
            frame.setMaximum(true);
        }
        catch(java.beans.PropertyVetoException error){
            System.out.println(error);
        }
        frame.setBorder(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI)frame.getUI()).setNorthPane(null);
        return frame;
    }
    public void actionPerformed(ActionEvent event){

    }
}
