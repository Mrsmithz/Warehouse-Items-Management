package views;
import controller.MainGUI;

import javax.swing.*;
import java.awt.*;
public class ProfileGUI {
    private JInternalFrame mainFrame;
    private JPanel mainPanel;
    private JLabel id, firstname, lastname, username, password, email, tel;
    private Font labelFont;
    public ProfileGUI(){
        createComponents();
        setComponents();
    }
    private void createComponents(){
        labelFont = new Font("Angsana New", Font.BOLD, 30);
        mainFrame = CreateShortcuts.createMyJInternalFrame("", false, false, false, false);
        mainPanel = new JPanel();

        id = new JLabel();
        firstname = new JLabel();
        lastname = new JLabel();
        username = new JLabel();
        password = new JLabel();
        email = new JLabel();
        tel = new JLabel();
    }
    private void setComponents(){
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);

        mainPanel.setLayout(new GridLayout(7,1));

        id.setText(String.valueOf(MainGUI.getUser().getId()));
        id.setHorizontalAlignment(JLabel.CENTER);
        id.setFont(labelFont);

        firstname.setText(MainGUI.getUser().getFirstname());
        firstname.setHorizontalAlignment(JLabel.CENTER);
        firstname.setFont(labelFont);

        lastname.setText(MainGUI.getUser().getLastname());
        lastname.setHorizontalAlignment(JLabel.CENTER);
        lastname.setFont(labelFont);

        username.setText(MainGUI.getUser().getUsername());
        username.setHorizontalAlignment(JLabel.CENTER);
        username.setFont(labelFont);

        password.setText(MainGUI.getUser().getPassword());
        password.setHorizontalAlignment(JLabel.CENTER);
        password.setFont(labelFont);

        email.setText(MainGUI.getUser().getEmail());
        email.setHorizontalAlignment(JLabel.CENTER);
        email.setFont(labelFont);

        tel.setText(MainGUI.getUser().getTel());
        tel.setHorizontalAlignment(JLabel.CENTER);
        tel.setFont(labelFont);

        mainPanel.add(id);
        mainPanel.add(firstname);
        mainPanel.add(lastname);
        mainPanel.add(username);
        mainPanel.add(password);
        mainPanel.add(email);
        mainPanel.add(tel);

        mainFrame.setVisible(true);

    }

    public JInternalFrame getMainFrame(){
        return this.mainFrame;
    }
}
