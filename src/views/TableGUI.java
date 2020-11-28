package views;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.*;
import controller.TableController;
import myutilities.*;
public class TableGUI{
    private JInternalFrame mainFrame;
    private JPanel mainPanel, topPanel, centerPanel, searchPanel, sortPanel;
    private Font tableFont;
    private JTable itemTable;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private String[] tableColumns = {"ID", "Name", "Type", "Price", "Weight", "Quantity", "Added Time"};
    private String[] searchMenu = {"Search By", "ID", "Name", "Type"};
    private String[] sortMenu = {"Sort By", "ID: Low-High", "ID: High-Low", "Name: Alphabetically", "Type: Alphabetically", "Price: Low-High", "Price: High-Low", "Weight: Low-High", "Weight: High-Low", "Quantity: Low-High", "Quantity: High-Low"};
    private JTextField searchField;
    private JComboBox searchComboBox, sortComboBox;
    private TableController tc;
    public TableGUI(TableController tc){
        this.tc = tc;
        createComponents();
        setComponents();
    }
    private void createComponents(){
        tableFont = new Font("Angsana New", Font.PLAIN, 20);
        mainFrame = CreateShortcuts.createMyJInternalFrame("", false, false, false, false);
        mainPanel = new JPanel();
        tableModel = new DefaultTableModel(tableColumns, 0);
        itemTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column){
                return column == 1 || column == 2 || column == 3 || column == 4 || column == 5;
            }
            };
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
        searchComboBox.setPreferredSize(new Dimension(180, 40));
        searchComboBox.setFont(new Font("Angsana New", Font.BOLD, 20));
        searchComboBox.addItemListener(this.tc);
        searchPanel.add(searchComboBox);
        searchPanel.add(searchField);
        searchField.setPreferredSize(new Dimension(300, 50));
        searchField.setHorizontalAlignment(JTextField.LEFT);
        searchField.setFont(new Font("Angsana New", Font.BOLD, 16));
        searchField.addKeyListener(this.tc);
        topPanel.add(searchPanel);

        sortPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        sortComboBox.setPreferredSize(new Dimension(180, 40));
        sortPanel.add(sortComboBox);
        sortComboBox.addItemListener(this.tc);
        sortComboBox.setFont(new Font("Angsana New", Font.BOLD, 20));
        JPanel test = new JPanel();
        test.setPreferredSize(new Dimension(200, 50));
        sortPanel.add(test);
        topPanel.add(sortPanel);

        itemTable.setRowHeight(50);
        itemTable.getTableHeader().setFont(new Font("Angsana New", Font.BOLD, 25));
        itemTable.getTableHeader().setOpaque(false);
        itemTable.getTableHeader().setBackground(new Color(200, 50, 111));
        itemTable.setSelectionForeground(new Color(50, 100, 200));
        itemTable.setShowGrid(false);
        itemTable.setDefaultRenderer(Object.class, new MyCustomCellRenderer());
        changeCellEditor(itemTable);
        itemTable.setFont(tableFont);
        itemTable.setDragEnabled(false);
        itemTable.setAutoCreateRowSorter(true);
        itemTable.getTableHeader().setReorderingAllowed(false);
        itemTable.setToolTipText("You can edit values in the cell and they will update values in Database automatically.");
        itemTable.addKeyListener(this.tc);
        tableModel.addTableModelListener(this.tc);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
    }
    private void centerCellValue(JTable table){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0;i<table.getColumnCount();i++){
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    private void changeCellEditor(JTable table){
        JTextField textField = new JTextField();
        textField.setForeground(Color.RED);
        textField.setFont(tableFont);
        DefaultCellEditor dce = new DefaultCellEditor(textField){
            @Override
            public boolean isCellEditable(EventObject anEvent) {
                if (anEvent instanceof MouseEvent){
                    return ((MouseEvent)anEvent).getClickCount() >= 2;
                }
                return false;
            }
        };
        for (int i = 0;i<table.getColumnCount();i++){
            table.getColumnModel().getColumn(i).setCellEditor(dce);
        }
    }
    public JInternalFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JInternalFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(JPanel topPanel) {
        this.topPanel = topPanel;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    public JPanel getSearchPanel() {
        return searchPanel;
    }

    public void setSearchPanel(JPanel searchPanel) {
        this.searchPanel = searchPanel;
    }

    public JPanel getSortPanel() {
        return sortPanel;
    }

    public void setSortPanel(JPanel sortPanel) {
        this.sortPanel = sortPanel;
    }

    public Font getTableFont() {
        return tableFont;
    }

    public void setTableFont(Font tableFont) {
        this.tableFont = tableFont;
    }

    public JTable getItemTable() {
        return itemTable;
    }

    public void setItemTable(JTable itemTable) {
        this.itemTable = itemTable;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public String[] getTableColumns() {
        return tableColumns;
    }

    public void setTableColumns(String[] tableColumns) {
        this.tableColumns = tableColumns;
    }

    public String[] getSearchMenu() {
        return searchMenu;
    }

    public void setSearchMenu(String[] searchMenu) {
        this.searchMenu = searchMenu;
    }

    public String[] getSortMenu() {
        return sortMenu;
    }

    public void setSortMenu(String[] sortMenu) {
        this.sortMenu = sortMenu;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public void setSearchField(JTextField searchField) {
        this.searchField = searchField;
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

    public TableController getTc() {
        return tc;
    }

    public void setTc(TableController tc) {
        this.tc = tc;
    }
}
