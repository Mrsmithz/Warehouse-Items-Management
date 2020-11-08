package controller;
import javax.swing.*;
import java.awt.*;
import views.*;
import model.*;
public class MainController {
    private MainGUI mainGUI;
    private LeftPanelController leftPanelController;
    private TopPanelController topPanelController;
    private ProfileController profileController;
    private TableController tableController;
    private AddItemController addItemController;
    private DashboardController dashboardController;
    private User user;
    private JDesktopPane centerDesktopPane, leftDesktopPane, topDesktopPane;
    private JFrame mainFrame;
    public MainController(User user){
        this.mainGUI = new MainGUI();
        this.user = user;
        this.leftPanelController = new LeftPanelController(this);
        this.topPanelController = new TopPanelController();
        this.profileController = new ProfileController(this);
        this.tableController = new TableController(this);
        this.addItemController = new AddItemController(this);
        this.dashboardController = new DashboardController(this);
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
}
