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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import views.*;
import model.*;

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
        this.mainFrame.setTitle(user.getFirstname() + " Warehouse's");
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
            exportToExcel();
        }
    }
    private boolean exportToExcel(){
        JFileChooser chooser = new JFileChooser();
        File workingdir = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingdir);
        int selected = chooser.showSaveDialog(this.getMainFrame());
        if (selected == JFileChooser.APPROVE_OPTION){
            File temp = chooser.getSelectedFile();
            File file = new File(temp.toString() + ".xlsx");
            try (FileOutputStream output = new FileOutputStream(file);){
                XSSFWorkbook workbook = createExcel();
                workbook.write(output);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    private XSSFWorkbook createExcel(){
        int row = 0;
        ArrayList<HashMap<String, Object>> data = this.tableController.getData();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet 1");
        XSSFCellStyle headerStyle = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short)20);
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE1.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFillBackgroundColor(IndexedColors.TURQUOISE.getIndex());
        headerStyle.setFont(headerFont);

        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        XSSFFont cellFont = workbook.createFont();
        cellFont.setFontHeightInPoints((short)16);
        cellFont.setColor(IndexedColors.INDIGO.getIndex());
        cellStyle.setFont(cellFont);

        XSSFRow headerRow = sheet.createRow(row++);
        addHeader(headerRow, headerStyle);

        for (HashMap<String, Object> map : data){
            XSSFRow rows = sheet.createRow(row++);
            addCells(map, rows, cellStyle);
        }
        HashMap<String, Object> header = data.get(0);
        for (int i = 0;i < header.size();i++){
            sheet.autoSizeColumn(i);
        }
        return workbook;
    }
    private void addCells(HashMap<String, Object> data, XSSFRow row, XSSFCellStyle style){
        for (String key : data.keySet()){
            if (key.equals("user_id")){
                XSSFCell cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue((int)data.get(key));
                cell.setCellStyle(style);
            }
            else if (key.equals("item_id")) {
                XSSFCell cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue((int)data.get(key));
                cell.setCellStyle(style);
            }
            else if (key.equals("item_name")) {
                XSSFCell cell = row.createCell(2, CellType.STRING);
                cell.setCellValue((String)data.get(key));
                cell.setCellStyle(style);
            }
            else if (key.equals("item_type")) {
                XSSFCell cell = row.createCell(3, CellType.STRING);
                cell.setCellValue((String)data.get(key));
                cell.setCellStyle(style);
            }
            else if (key.equals("item_price")) {
                XSSFCell cell = row.createCell(4, CellType.NUMERIC);
                cell.setCellValue((double)data.get(key));
                cell.setCellStyle(style);
            }
            else if (key.equals("item_weight")) {
                XSSFCell cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue((double)data.get(key));
                cell.setCellStyle(style);
            }
            else if (key.equals("quantity")) {
                XSSFCell cell = row.createCell(6, CellType.NUMERIC);
                cell.setCellValue((int)data.get(key));
                cell.setCellStyle(style);
            }
            else if (key.equals("inserted")) {
                XSSFCell cell = row.createCell(7, CellType.STRING);
                Timestamp timeStampObj = (Timestamp)data.get(key);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String date = dateFormat.format(timeStampObj);
                cell.setCellValue(date);
                cell.setCellStyle(style);
            }
        }
    }
    private void addHeader(XSSFRow row, XSSFCellStyle style){
        int i = 0;
        addValueToHeader(row, "USER_ID", i++, style);
        addValueToHeader(row, "ITEM_ID", i++, style);
        addValueToHeader(row, "ITEM_NAME", i++, style);
        addValueToHeader(row, "ITEM_TYPE", i++, style);
        addValueToHeader(row, "ITEM_PRICE", i++, style);
        addValueToHeader(row, "ITEM_WEIGHT", i++, style);
        addValueToHeader(row, "QUANTITIES", i++, style);
        addValueToHeader(row, "ADDED TIME", i++, style);
    }
    private void addValueToHeader(XSSFRow row, String header, int index, XSSFCellStyle style){
        XSSFCell cell = row.createCell(index, CellType.STRING);
        cell.setCellValue(header);
        cell.setCellStyle(style);
    }
}
