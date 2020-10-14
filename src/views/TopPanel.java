package views;

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
        mainFrame = createInternalFrame();
        mainPanel = new JPanel();
    }
    private void setComponents(){
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(230, 150, 230));
        mainFrame.setVisible(true);

    }
    private JInternalFrame createInternalFrame(){
        JInternalFrame frame = new JInternalFrame("", false, false, true, false);
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
    public JInternalFrame getMainFrame(){
        return this.mainFrame;
    }
}
