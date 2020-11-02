package views;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import controller.*;
import myutilities.*;
public class TableGUI implements KeyListener, ItemListener {
    private JInternalFrame mainFrame;
    private JPanel mainPanel, topPanel, centerPanel, searchPanel, sortPanel;
    private Font tableFont;
    private JTable itemTable;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private String[] tableColumns = {"ID", "Name", "Type", "Price", "Weight", "Quantity", "Added Time"};
    private String[] searchMenu = {"Search By", "ID", "Name", "Type"};
    private String[] sortMenu = {"Sort By", "ID: Low-High", "ID: High-Low", "Name: Alphabetically", "Type: Alphabetically", "Price: Low-High", "Price: High-Low", "Weight: Low-High", "Weight: High-Low"};
    private ArrayList<HashMap<String, Object>> data;
    private JTextField searchField;
    private JComboBox searchComboBox, sortComboBox;
    public TableGUI(){
        createComponents();
        setComponents();
    }
    private void createComponents(){
        tableFont = new Font("Angsana New", Font.PLAIN, 25);
        mainFrame = CreateShortcuts.createMyJInternalFrame("", false, false, false, false);
        mainPanel = new JPanel();
        tableModel = new DefaultTableModel(tableColumns, 0);
        itemTable = new JTable(tableModel);
        scrollPane = new JScrollPane(itemTable);
        topPanel = new JPanel();
        centerPanel = new JPanel();
        searchPanel = new JPanel();
        sortPanel = new JPanel();
        searchField = new JPlaceholderTextField("Type words then press enter");
        searchComboBox = new JComboBox(searchMenu);
        sortComboBox = new JComboBox(sortMenu);


    }
    private void setComponents(){
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(scrollPane);

        topPanel.setPreferredSize(new Dimension(400, 60));
        topPanel.setLayout(new GridLayout(1, 2));

        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        searchPanel.add(searchComboBox);
        searchPanel.add(searchField);
        searchField.setPreferredSize(new Dimension(200, 50));
        searchField.setHorizontalAlignment(JTextField.LEFT);
        searchField.setFont(tableFont);
        searchField.addKeyListener(this);
        topPanel.add(searchPanel);

        sortPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        sortPanel.add(sortComboBox);
        sortComboBox.addItemListener(this);
        JPanel test = new JPanel();
        test.setPreferredSize(new Dimension(200, 50));
        sortPanel.add(test);
        topPanel.add(sortPanel);

        itemTable.setRowHeight(50);
        centerCellValue(itemTable);
        itemTable.setFont(tableFont);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());



        mainFrame.setVisible(false);
    }
    public JInternalFrame getMainFrame(){
        return mainFrame;
    }

    private ArrayList<HashMap<String, Object>> getData(){
        try{
            ResultSet rs = MainGUI.getUser().getAllItem();
            ResultSetMetaData meta = rs.getMetaData();
            int col = meta.getColumnCount();
            ArrayList<HashMap<String, Object>> list = new ArrayList<>();
            while (rs.next()){
                HashMap<String, Object> row = new HashMap<>(col);
                for (int i = 1;i<=col;i++){
                    row.put(meta.getColumnName(i), rs.getObject(i));
                }
                list.add(row);
            }
            return list;
        }
        catch (SQLException error){
            System.out.println("Fetch Data Failed!");
            return null;
        }
    }
    private void addDataToModel(ArrayList<HashMap<String, Object>> data){
        for (int i = 0;i<data.size();i++){
            HashMap<String, Object> map = data.get(i);
            int item_id = (int)map.get("item_id");
            String item_name = (String)map.get("item_name");
            String item_type = (String)map.get("item_type");
            double item_price = (double)map.get("item_price");
            double item_weight = (double)map.get("item_weight");
            int quantity = (int)map.get("quantity");
            Timestamp timeStampObj = (Timestamp)map.get("inserted");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String date = dateFormat.format(timeStampObj);
            Object[] row ={item_id, item_name, item_type, item_price, item_weight, quantity, date};
            tableModel.addRow(row);
        }
    }
    private void centerCellValue(JTable table){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0;i<table.getColumnCount();i++){
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    public void updateTable(){
        tableModel.setRowCount(0);
        data = getData();
        addDataToModel(data);
    }
    private void updateTableBySearchOrSort(ArrayList<HashMap<String, Object>> data){
        tableModel.setRowCount(0);
        addDataToModel(data);
    }
    public void keyTyped(KeyEvent keyEvent){
    }
    public void itemStateChanged(ItemEvent itemEvent){
        if(itemEvent.getStateChange() == ItemEvent.SELECTED){
            String item = (String)itemEvent.getItem();
            updateTableBySearchOrSort(sortedData(item));
        }
    }
    public void keyPressed(KeyEvent keyEvent){
        if (keyEvent.getSource().equals(searchField) && (keyEvent.getKeyCode() == KeyEvent.VK_ENTER)){
            updateTableBySearchOrSort(searchedData());
        }
    }
    public void keyReleased(KeyEvent keyEvent){

    }
    private ArrayList<HashMap<String, Object>> sortedData(String order){
        ArrayList<HashMap<String, Object>> data, result = new ArrayList<>();
        data = getData();
        if (order.equals("Sort By")){
            return null;
        }
        else if (order.equals("ID: Low-High")){
            Collections.sort(data, new MapComparator("item_id"));
            return data;
        }
        else if (order.equals("ID: High-Low")){
            Collections.sort(data, new MapComparator("item_id"));
            Collections.reverse(data);
            return data;
        }
        else if (order.equals("Name: Alphabetically")){
            Collections.sort(data, new MapComparator("item_name"));
            return data;
        }
        else if (order.equals("Type: Alphabetically")){
            Collections.sort(data, new MapComparator("item_type"));
            return data;
        }
        else if (order.equals("Price: Low-High")){
            Collections.sort(data, new MapComparator("item_price"));
            return data;
        }
        else if (order.equals("Price: High-Low")){
            Collections.sort(data, new MapComparator("item_price"));
            Collections.reverse(data);
            return data;
        }
        else if (order.equals("Weight: Low-High")){
            Collections.sort(data, new MapComparator("item_weight"));
            return data;
        }
        else if (order.equals("Weight: High-Low")){
            Collections.sort(data, new MapComparator("item_weight"));
            Collections.reverse(data);
            return data;
        }
        else{
            return null;
        }
    }
    private ArrayList<HashMap<String, Object>> searchedData(){
        ArrayList<HashMap<String, Object>> data, result = new ArrayList<>();
        String s = (String)searchComboBox.getItemAt(searchComboBox.getSelectedIndex());
        if (s.equals("Search By")){
            System.out.println("Please Select");
            return null;
        }
        else if (s.equals("ID")){
            try{
                Integer.parseInt(searchField.getText());
            }
            catch (NumberFormatException e){
                return null;
            }
            data = getData();
            for (HashMap<String, Object> map : data) {
                if ((int) map.get("item_id") == Integer.parseInt(searchField.getText())) {
                    result.add(map);
                }
            }
            return result;
        }
        else if (s.equals("Name")){
            data = getData();
            for (HashMap<String, Object> map : data) {
                if (((String)map.get("item_name")).equals(searchField.getText())) {
                    result.add(map);
                }
            }
            return result;
        }
        else if (s.equals("Type")){
            data = getData();
            for (HashMap<String, Object> map : data) {
                if (((String)map.get("item_type")).equals(searchField.getText())) {
                    result.add(map);
                }
            }
            return result;
        }
        else{
            return null;
        }
    }
}
