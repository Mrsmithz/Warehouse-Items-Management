package controller;
import views.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftPanelController implements ActionListener {
    private LeftPanel leftPanel;
    private MainController mc;
    private JButton profileBtn, dashboardBtn, tableBtn, addItemBtn, settingBtn;
    public LeftPanelController(MainController mc){
        this.mc = mc;
        this.leftPanel = new LeftPanel(this);
        setComponents();
    }
    private void setComponents(){
        this.profileBtn = leftPanel.getBtn1();
        this.dashboardBtn = leftPanel.getBtn2();
        this.tableBtn = leftPanel.getBtn3();
        this.addItemBtn = leftPanel.getBtn4();
        this.settingBtn = leftPanel.getBtn5();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(profileBtn)){
            mc.getSettingsController().getSettingsGUI().getMainFrame().setVisible(false);
            mc.getTableController().getTableGUI().getMainFrame().setVisible(false);
            mc.getAddItemController().getAddItemGUI().getMainFrame().setVisible(false);
            mc.getDashboardController().getDashboardGUI().getMainFrame().setVisible(false);
            mc.getProfileController().getProfileGUI().getMainFrame().setVisible(true);
        }
        else if (e.getSource().equals(tableBtn)){
            mc.getSettingsController().getSettingsGUI().getMainFrame().setVisible(false);
            mc.getProfileController().getProfileGUI().getMainFrame().setVisible(false);
            mc.getAddItemController().getAddItemGUI().getMainFrame().setVisible(false);
            mc.getDashboardController().getDashboardGUI().getMainFrame().setVisible(false);
            mc.getTableController().getTableGUI().getMainFrame().setVisible(true);
        }
        else if (e.getSource().equals(addItemBtn)){
            mc.getSettingsController().getSettingsGUI().getMainFrame().setVisible(false);
            mc.getProfileController().getProfileGUI().getMainFrame().setVisible(false);
            mc.getTableController().getTableGUI().getMainFrame().setVisible(false);
            mc.getDashboardController().getDashboardGUI().getMainFrame().setVisible(false);
            mc.getAddItemController().getAddItemGUI().getMainFrame().setVisible(true);
        }
        else if (e.getSource().equals(dashboardBtn)){
            mc.getSettingsController().getSettingsGUI().getMainFrame().setVisible(false);
            mc.getProfileController().getProfileGUI().getMainFrame().setVisible(false);
            mc.getTableController().getTableGUI().getMainFrame().setVisible(false);
            mc.getAddItemController().getAddItemGUI().getMainFrame().setVisible(false);
            mc.getDashboardController().getDashboardGUI().getMainFrame().setVisible(true);
        }
        else if (e.getSource().equals(settingBtn)){
            mc.getProfileController().getProfileGUI().getMainFrame().setVisible(false);
            mc.getTableController().getTableGUI().getMainFrame().setVisible(false);
            mc.getAddItemController().getAddItemGUI().getMainFrame().setVisible(false);
            mc.getDashboardController().getDashboardGUI().getMainFrame().setVisible(false);
            mc.getSettingsController().getSettingsGUI().getMainFrame().setVisible(true);
        }
    }

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }

    public void setLeftPanel(LeftPanel leftPanel) {
        this.leftPanel = leftPanel;
    }

    public MainController getMc() {
        return mc;
    }

    public void setMc(MainController mc) {
        this.mc = mc;
    }

    public JButton getProfileBtn() {
        return profileBtn;
    }

    public void setProfileBtn(JButton profileBtn) {
        this.profileBtn = profileBtn;
    }

    public JButton getDashboardBtn() {
        return dashboardBtn;
    }

    public void setDashboardBtn(JButton dashboardBtn) {
        this.dashboardBtn = dashboardBtn;
    }

    public JButton getTableBtn() {
        return tableBtn;
    }

    public void setTableBtn(JButton tableBtn) {
        this.tableBtn = tableBtn;
    }

    public JButton getAddItemBtn() {
        return addItemBtn;
    }

    public void setAddItemBtn(JButton addItemBtn) {
        this.addItemBtn = addItemBtn;
    }

    public JButton getSettingBtn() {
        return settingBtn;
    }

    public void setSettingBtn(JButton settingBtn) {
        this.settingBtn = settingBtn;
    }
}
