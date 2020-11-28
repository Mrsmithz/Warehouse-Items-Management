package controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.io.*;
import views.*;
import model.*;
import org.apache.poi.ss.usermodel.Cell;

public class MainController implements WindowListener, ActionListener {
    private MainGUI mainGUI;
    private LeftPanelController leftPanelController;
    private TopPanelController topPanelController;
    private ProfileController profileController;
    private TableController tableController;
    private AddItemController addItemController;
    private DashboardController dashboardController;
    private SettingsController settingsController;
    private LoginController loginController;
    private User user;
    private JDesktopPane centerDesktopPane, leftDesktopPane, topDesktopPane;
    private JFrame mainFrame;
    public MainController(User user, LoginController lc){
        this.loginController = lc;
        this.mainGUI = new MainGUI(this);
        this.user = user;
        this.leftPanelController = new LeftPanelController(this);
        this.topPanelController = new TopPanelController();
        this.profileController = new ProfileController(this);
        this.tableController = new TableController(this);
        this.addItemController = new AddItemController(this);
        this.dashboardController = new DashboardController(this);
        this.settingsController = new SettingsController(this);
        setComponents();
        this.mainFrame.setVisible(true);
    }
    private void setComponents(){
        this.centerDesktopPane = mainGUI.getCenterDesktopPane();
        this.leftDesktopPane = mainGUI.getLeftDesktopPane();
        this.topDesktopPane = mainGUI.getTopDesktopPane();
        this.mainFrame = mainGUI.getMainFrame();
        this.leftDesktopPane.add(leftPanelController.getLeftPanel().getMainFrame());
        this.topDesktopPane.add(topPanelController.getTopPanel().getMainFrame());
        this.centerDesktopPane.add(profileController.getProfileGUI().getMainFrame());
        this.centerDesktopPane.add(tableController.getTableGUI().getMainFrame());
        this.centerDesktopPane.add(addItemController.getAddItemGUI().getMainFrame());
        this.centerDesktopPane.add(dashboardController.getDashboardGUI().getMainFrame());
        this.centerDesktopPane.add(settingsController.getSettingsGUI().getMainFrame());
        this.tableController.updateTable();
    }

    public MainGUI getMainGUI() {
        return mainGUI;
    }

    public void setMainGUI(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    public LeftPanelController getLeftPanelController() {
        return leftPanelController;
    }

    public void setLeftPanelController(LeftPanelController leftPanelController) {
        this.leftPanelController = leftPanelController;
    }

    public TopPanelController getTopPanelController() {
        return topPanelController;
    }

    public void setTopPanelController(TopPanelController topPanelController) {
        this.topPanelController = topPanelController;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JDesktopPane getCenterDesktopPane() {
        return centerDesktopPane;
    }

    public void setCenterDesktopPane(JDesktopPane centerDesktopPane) {
        this.centerDesktopPane = centerDesktopPane;
    }

    public JDesktopPane getLeftDesktopPane() {
        return leftDesktopPane;
    }

    public void setLeftDesktopPane(JDesktopPane leftDesktopPane) {
        this.leftDesktopPane = leftDesktopPane;
    }

    public JDesktopPane getTopDesktopPane() {
        return topDesktopPane;
    }

    public void setTopDesktopPane(JDesktopPane topDesktopPane) {
        this.topDesktopPane = topDesktopPane;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public ProfileController getProfileController() {
        return profileController;
    }

    public void setProfileController(ProfileController profileController) {
        this.profileController = profileController;
    }

    public TableController getTableController() {
        return tableController;
    }

    public void setTableController(TableController tableController) {
        this.tableController = tableController;
    }

    public AddItemController getAddItemController() {
        return addItemController;
    }

    public void setAddItemController(AddItemController addItemController) {
        this.addItemController = addItemController;
    }

    public DashboardController getDashboardController() {
        return dashboardController;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public SettingsController getSettingsController() {
        return settingsController;
    }

    public void setSettingsController(SettingsController settingsController) {
        this.settingsController = settingsController;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
    public void saveProfileImages(){
        try{
            if (profileController.getImg() == null){
                user.uploadProfileImage("src/imgs/profileimage.jpg");
            }
            else{
                user.updateProfileImage("src/imgs/profileimage.jpg");
            }
        }
        catch (SQLException | IOException ex){
            ex.printStackTrace();
        }
    }
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        saveProfileImages();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(mainGUI.getExcelMenuItem())){

        }
    }
    private boolean exportToExcel(){
        return false;
    }
}
