package views;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
public class TableGUI {
    private JInternalFrame mainFrame;
    private JPanel mainPanel;
    private Font tableFont;
    private JTable itemTable;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private String[] tableColumns = {"item_id", "item_name", "item_type", "item_price", "item_weight", "quantity", "modified date"};
    private ArrayList<HashMap<String, Object>> data;
    //private TableModel tableModel;
    public TableGUI(){
        createComponents();
        setComponents();
    }
    private void createComponents(){
        mainFrame = CreateShortcuts.createMyJInternalFrame("", false, false, false, false);
        mainPanel = new JPanel();
        tableModel = new DefaultTableModel(tableColumns, 0);
        itemTable = new JTable(tableModel);
        scrollPane = new JScrollPane(itemTable);
    }
    private void setComponents(){
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(scrollPane);

        itemTable.setRowHeight(50);
        centerCellValue(itemTable);

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
            Timestamp timeStampObj = (Timestamp)map.get("modifieddate");
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
}
