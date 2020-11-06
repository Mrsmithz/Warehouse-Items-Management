package views;
import javax.swing.*;
import java.awt.*;
import model.*;
import controller.*;
public class LoginGUI{
    private JFrame mainFrame;
    private JPanel mainPanel, textFieldPanel, btnPanel, logoPanel, userPanel, passPanel;
    private JPlaceholderTextField usernameField;
    private JPlaceholderPasswordField passwordField;
    private JButton loginBtn, registerBtn;
    private Font textFieldFont;
    private GridBagConstraints gbc;
    private User user;
    private LoginController lc;
    public LoginGUI(LoginController lc){
        this.lc = lc;
        createComponents();
        setComponents();
    }
    private void createComponents(){
        mainFrame = new JFrame("Login");

        textFieldFont = new Font("Angsana New", Font.PLAIN, 35);

        mainPanel = new JPanel();

        logoPanel = CreateShortcuts.createImagePanel("/imgs/login-test.png");

        textFieldPanel = new JPanel();

        userPanel = new JPanel();

        passPanel = new JPanel();

        btnPanel = new JPanel();

        usernameField = new JPlaceholderTextField("Username");

        passwordField = new JPlaceholderPasswordField("Password");

        loginBtn = new JButton("LOGIN");

        registerBtn = new JButton("REGISTER");

        gbc = new GridBagConstraints();

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
        mainPanel.add(logoPanel, BorderLayout.NORTH);
        mainPanel.add(textFieldPanel, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        logoPanel.setPreferredSize(new Dimension(800, 170));

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
        loginBtn.setPreferredSize(new Dimension(150, 50));
        loginBtn.setFont(textFieldFont);
        loginBtn.addActionListener(this.lc);

        registerBtn.setVerticalAlignment(SwingConstants.CENTER);
        registerBtn.setPreferredSize(new Dimension(150, 50));
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

    public JPanel getLogoPanel() {
        return logoPanel;
    }

    public void setLogoPanel(JPanel logoPanel) {
        this.logoPanel = logoPanel;
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
}
