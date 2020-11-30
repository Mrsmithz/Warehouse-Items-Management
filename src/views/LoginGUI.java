package views;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.InputStream;
import java.util.Map;

import controller.*;
import myutilities.CreateShortcuts;
import myutilities.ImagePanel;
import myutilities.JPlaceholderPasswordField;
import myutilities.JPlaceholderTextField;

public class LoginGUI{
    private JFrame mainFrame;
    private JPanel mainPanel, textFieldPanel, btnPanel, userPanel, passPanel;
    private JPlaceholderTextField usernameField;
    private JPlaceholderPasswordField passwordField;
    private JButton loginBtn, registerBtn;
    private Font textFieldFont;
    private GridBagConstraints gbc;
    private LoginController lc;
    private ImagePanel logoPanel;

    //Kpun
    private JPanel headPanel, loginPanel, registerPanel;
    private JLabel userLabelIcon, passLabelIcon, imageLabel;
    public LoginGUI(LoginController lc){
        this.lc = lc;
        createComponents();
        setComponentskpun();
    }
    private void createComponents(){
        mainFrame = new JFrame("Login");
        //textFieldFont = new Font("Angsana New", Font.PLAIN, 35);
        mainPanel = new JPanel();
        logoPanel = new ImagePanel("/imgs/login-test.png");
        textFieldPanel = new JPanel();
        userPanel = new JPanel();
        passPanel = new JPanel();
        btnPanel = new JPanel();
        usernameField = new JPlaceholderTextField("Username");
        passwordField = new JPlaceholderPasswordField("Password");
        loginBtn = new JButton("SIGN IN");
        registerBtn = new JButton("SIGN UP");
        gbc = new GridBagConstraints();

        headPanel = new JPanel();
        loginPanel = new JPanel();
        registerPanel = new JPanel();
        userLabelIcon = new JLabel("  ");
        passLabelIcon = new JLabel("  ");
        imageLabel = new JLabel();
        try {
            InputStream input = this.getClass().getResourceAsStream("/font/SukhumvitSet-Bold.ttf");
            textFieldFont = Font.createFont(Font.TRUETYPE_FONT, input).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(textFieldFont);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void setComponentskpun(){
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setSize(500,600);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.add(mainPanel);
        mainFrame.setLocationRelativeTo(null);

        mainPanel.setLayout(new GridLayout(3,1));
        mainPanel.add(headPanel);
        mainPanel.add(textFieldPanel);
        mainPanel.add(btnPanel);
        ImageIcon imageIconn = new ImageIcon(this.getClass().getResource("/imgs/loginhead.png"));
        imageLabel.setIcon(imageIconn);
        headPanel.add(imageLabel);

        textFieldPanel.setLayout(new GridBagLayout());
        textFieldPanel.add(userPanel, gbc);
        gbc.gridy = 1;
        textFieldPanel.add(passPanel, gbc);

        ImageIcon userIcon = new ImageIcon(this.getClass().getResource("/imgs/user.png"));
        userPanel.setLayout(new GridBagLayout());
        userLabelIcon.setIcon(userIcon);
        userPanel.add(userLabelIcon);
        userPanel.add(usernameField);
        usernameField.setPreferredSize(new Dimension(300,50));
        usernameField.setBackground(new Color(40,44,52,0));
        usernameField.setBorder(new MatteBorder(0,0,2,0, new Color(80,84,92,255)));
        usernameField.setFont(textFieldFont.deriveFont(25f));

        ImageIcon passIcon = new ImageIcon(this.getClass().getResource("/imgs/lock.png"));
        passPanel.setLayout(new GridBagLayout());
        passLabelIcon.setIcon(passIcon);
        passPanel.add(passLabelIcon);
        passPanel.add(passwordField);
        passwordField.setPreferredSize(new Dimension(300,50));
        passwordField.setBackground(new Color(40,44,52,0));
        passwordField.setBorder(new MatteBorder(0,0,2,0, new Color(80,84,92,255)));
        passwordField.setFont(textFieldFont.deriveFont(25f));

        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(loginPanel, gbc);
        gbc.gridy = 2;
        btnPanel.add(registerPanel, gbc);

        loginPanel.setLayout(new GridBagLayout());
        loginPanel.add(loginBtn);
        loginBtn.setPreferredSize(new Dimension(300,50));
        loginBtn.setFont(textFieldFont.deriveFont(30f));
        loginBtn.setBackground(new Color(200,200,200));
        loginBtn.setForeground(new Color(40,44,52));
        ((JTextField)usernameField).addKeyListener(this.lc);
        loginBtn.setBorderPainted(false);
        loginBtn.addActionListener(this.lc);

        Map attributes = textFieldFont.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        attributes.put(TextAttribute.SIZE, 30f);
        registerPanel.setLayout(new GridBagLayout());
        registerPanel.add(registerBtn);
        registerBtn.setPreferredSize(new Dimension(300,50));
        registerBtn.setFont(textFieldFont.deriveFont(attributes));
        registerBtn.setBorderPainted(false);
        registerBtn.setOpaque(false);
        //registerBtn.setDefaultCapable(false);
        registerBtn.setContentAreaFilled(false);
        registerBtn.setFocusPainted(false);
        ((JTextField)passwordField).addKeyListener(this.lc);
        registerBtn.addActionListener(this.lc);



        mainFrame.setVisible(true);
    }
    private void setComponents(){
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setSize(800, 600);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.add(mainPanel);
        mainFrame.setLocationRelativeTo(null);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(111, 0, 11));
        mainPanel.add(logoPanel.getMainPanel(), BorderLayout.NORTH);
        mainPanel.add(textFieldPanel, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        logoPanel.getMainPanel().setPreferredSize(new Dimension(800, 170));

        textFieldPanel.setLayout(new GridLayout(2, 1));
        textFieldPanel.add(userPanel);
        textFieldPanel.add(passPanel);

        userPanel.setBackground(new Color(123, 22, 192));
        userPanel.setLayout(new GridBagLayout());
        userPanel.add(usernameField);

        passPanel.setBackground(new Color(55, 88, 99));
        passPanel.setLayout(new GridBagLayout());
        passPanel.add(passwordField);

        btnPanel.setBackground(new Color(25, 99, 185));
        btnPanel.setLayout(new GridBagLayout());
        btnPanel.setPreferredSize(new Dimension(800, 180));
        btnPanel.add(loginBtn, gbc);
        gbc.gridy = 1;
        btnPanel.add(registerBtn, gbc);

        usernameField.setPreferredSize(new Dimension(300, 50));
        usernameField.setFont(textFieldFont);
        usernameField.setHorizontalAlignment(SwingConstants.LEFT);
        ((JTextField)usernameField).addKeyListener(this.lc);

        passwordField.setPreferredSize(new Dimension(300, 50));
        passwordField.setEchoChar('•');
        passwordField.setFont(textFieldFont);
        passwordField.setHorizontalAlignment(SwingConstants.LEFT);
        ((JTextField)passwordField).addKeyListener(this.lc);

        loginBtn.setVerticalAlignment(SwingConstants.CENTER);
        loginBtn.setPreferredSize(new Dimension(200, 50));
        loginBtn.setFont(textFieldFont);
        loginBtn.addActionListener(this.lc);

        registerBtn.setVerticalAlignment(SwingConstants.CENTER);
        registerBtn.setPreferredSize(new Dimension(200, 50));
        registerBtn.setFont(textFieldFont);
        registerBtn.addActionListener(this.lc);

        mainFrame.setVisible(true);
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JPanel getTextFieldPanel() {
        return textFieldPanel;
    }

    public void setTextFieldPanel(JPanel textFieldPanel) {
        this.textFieldPanel = textFieldPanel;
    }

    public JPanel getBtnPanel() {
        return btnPanel;
    }

    public void setBtnPanel(JPanel btnPanel) {
        this.btnPanel = btnPanel;
    }

    public JPanel getUserPanel() {
        return userPanel;
    }

    public void setUserPanel(JPanel userPanel) {
        this.userPanel = userPanel;
    }

    public JPanel getPassPanel() {
        return passPanel;
    }

    public void setPassPanel(JPanel passPanel) {
        this.passPanel = passPanel;
    }

    public JPlaceholderTextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(JPlaceholderTextField usernameField) {
        this.usernameField = usernameField;
    }

    public JPlaceholderPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPlaceholderPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public JButton getLoginBtn() {
        return loginBtn;
    }

    public void setLoginBtn(JButton loginBtn) {
        this.loginBtn = loginBtn;
    }

    public JButton getRegisterBtn() {
        return registerBtn;
    }

    public void setRegisterBtn(JButton registerBtn) {
        this.registerBtn = registerBtn;
    }

    public Font getTextFieldFont() {
        return textFieldFont;
    }

    public void setTextFieldFont(Font textFieldFont) {
        this.textFieldFont = textFieldFont;
    }

    public GridBagConstraints getGbc() {
        return gbc;
    }

    public void setGbc(GridBagConstraints gbc) {
        this.gbc = gbc;
    }

    public LoginController getLc() {
        return lc;
    }

    public void setLc(LoginController lc) {
        this.lc = lc;
    }

    public ImagePanel getLogoPanel() {
        return logoPanel;
    }

    public void setLogoPanel(ImagePanel logoPanel) {
        this.logoPanel = logoPanel;
    }
}
