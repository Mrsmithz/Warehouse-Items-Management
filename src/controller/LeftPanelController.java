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
            mc.getTableController().getTableGUI().getMainFrame().setVisible(false);
            mc.getAddItemController().getAddItemGUI().getMainFrame().setVisible(false);
            mc.getDashboardController().getDashboardGUI().getMainFrame().setVisible(false);
            mc.getProfileController().getProfileGUI().getMainFrame().setVisible(true);
        }
        else if (e.getSource().equals(tableBtn)){
            mc.getProfileController().getProfileGUI().getMainFrame().setVisible(false);
            mc.getAddItemController().getAddItemGUI().getMainFrame().setVisible(false);
            mc.getDashboardController().getDashboardGUI().getMainFrame().setVisible(false);
            mc.getTableController().updateTable();
            mc.getTableController().getTableGUI().getMainFrame().setVisible(true);
        }
        else if (e.getSource().equals(addItemBtn)){
            mc.getProfileController().getProfileGUI().getMainFrame().setVisible(false);
            mc.getTableController().getTableGUI().getMainFrame().setVisible(false);
            mc.getDashboardController().getDashboardGUI().getMainFrame().setVisible(false);
            mc.getAddItemController().getAddItemGUI().getMainFrame().setVisible(true);
        }
        else if (e.getSource().equals(dashboardBtn)){
            mc.getProfileController().getProfileGUI().getMainFrame().setVisible(false);
            mc.getTableController().getTableGUI().getMainFrame().setVisible(false);
            mc.getAddItemController().getAddItemGUI().getMainFrame().setVisible(false);
            mc.getDashboardController().getDashboardGUI().getMainFrame().setVisible(true);
        }
    }

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }

    public void setLeftPanel(LeftPanel leftPanel) {
        this.leftPanel = leftPanel;
    }
}
