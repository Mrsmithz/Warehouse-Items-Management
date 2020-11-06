package controller;
import myutilities.MapComparator;
import views.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
public class TableController implements KeyListener, ItemListener{
    private TableGUI tableGUI;
    private MainController mc;
    private ArrayList<HashMap<String, Object>> data;
    private JTextField searchField;
    private DefaultTableModel tableModel;
    private JComboBox searchComboBox, sortComboBox;
    public TableController(MainController mc){
        this.mc = mc;
        this.tableGUI = new TableGUI(this);
        setComponents();
    }
    private void setComponents(){
        this.searchField = tableGUI.getSearchField();
        this.tableModel = tableGUI.getTableModel();
        this.searchComboBox = tableGUI.getSearchComboBox();
        this.sortComboBox = tableGUI.getSortComboBox();
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
    private ArrayList<HashMap<String, Object>> getData(){
        try{
            ResultSet rs = mc.getUser().getAllItem();
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
    public void updateTable(){
        tableModel.setRowCount(0);
        data = getData();
        addDataToModel(data);
    }
    private void updateTableBySearchOrSort(ArrayList<HashMap<String, Object>> data){
        tableModel.setRowCount(0);
        addDataToModel(data);
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

    public TableGUI getTableGUI() {
        return tableGUI;
    }

    public void setTableGUI(TableGUI tableGUI) {
        this.tableGUI = tableGUI;
    }

    public MainController getMc() {
        return mc;
    }

    public void setMc(MainController mc) {
        this.mc = mc;
    }

    public void setData(ArrayList<HashMap<String, Object>> data) {
        this.data = data;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public void setSearchField(JTextField searchField) {
        this.searchField = searchField;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public JComboBox getSearchComboBox() {
        return searchComboBox;
    }

    public void setSearchComboBox(JComboBox searchComboBox) {
        this.searchComboBox = searchComboBox;
    }

    public JComboBox getSortComboBox() {
        return sortComboBox;
    }

    public void setSortComboBox(JComboBox sortComboBox) {
        this.sortComboBox = sortComboBox;
    }
}
