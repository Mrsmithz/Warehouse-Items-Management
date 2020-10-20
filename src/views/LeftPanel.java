package views;
import java.awt.*;
import javax.swing.*;

public class LeftPanel{
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

    }

    public JInternalFrame getMainFrame(){
        return this.mainFrame;
    }
}
