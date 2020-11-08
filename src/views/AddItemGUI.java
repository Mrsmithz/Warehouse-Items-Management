package views;
import javax.swing.*;
import java.awt.*;
import controller.*;
import myutilities.CreateShortcuts;
import myutilities.JPlaceholderTextField;

public class AddItemGUI {
    private JInternalFrame mainFrame;
    private JPanel mainPanel, namePanel, typePanel, pricePanel, weightPanel, quantityPanel, btnPanel;
    private AddItemController ac;
    private JPlaceholderTextField itemNameField, itemTypeField, itemPriceField, itemWeightField, quantityField;
    private JButton submitBtn, clearBtn;
    private Font fieldFont;
    public AddItemGUI(AddItemController ac){
        this.ac = ac;
        createComponents();
        setComponents();
    }
    private void createComponents(){
        mainFrame = CreateShortcuts.createMyJInternalFrame("", false, false, false, false);
        mainPanel = new JPanel();
        namePanel = new JPanel();
        typePanel = new JPanel();
        pricePanel = new JPanel();
        weightPanel = new JPanel();
        quantityPanel = new JPanel();
        btnPanel = new JPanel();
        itemNameField = new JPlaceholderTextField("Item Name");
        itemTypeField = new JPlaceholderTextField("Item Type");
        itemPriceField = new JPlaceholderTextField("Item Price");
        itemWeightField = new JPlaceholderTextField("Item Weight");
        quantityField = new JPlaceholderTextField("Quantity");
        fieldFont = new Font("Angsana New", Font.PLAIN, 40);
        submitBtn = new JButton("Submit");
        clearBtn = new JButton("Clear");
    }
    private void setComponents(){
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);

        mainPanel.setLayout(new GridLayout(6, 1));
        mainPanel.add(namePanel);
        mainPanel.add(typePanel);
        mainPanel.add(pricePanel);
        mainPanel.add(weightPanel);
        mainPanel.add(quantityPanel);
        mainPanel.add(btnPanel);

        namePanel.setLayout(new GridBagLayout());
        namePanel.add(itemNameField);

        typePanel.setLayout(new GridBagLayout());
        typePanel.add(itemTypeField);

        pricePanel.setLayout(new GridBagLayout());
        pricePanel.add(itemPriceField);

        weightPanel.setLayout(new GridBagLayout());
        weightPanel.add(itemWeightField);

        quantityPanel.setLayout(new GridBagLayout());
        quantityPanel.add(quantityField);

        btnPanel.setLayout(new GridBagLayout());
        btnPanel.add(submitBtn);
        btnPanel.add(clearBtn);

        itemNameField.setPreferredSize(new Dimension(500, 70));
        itemNameField.setFont(fieldFont);

        itemTypeField.setPreferredSize(new Dimension(500, 70));
        itemTypeField.setFont(fieldFont);

        itemPriceField.setPreferredSize(new Dimension(500, 70));
        itemPriceField.setFont(fieldFont);

        itemWeightField.setPreferredSize(new Dimension(500, 70));
        itemWeightField.setFont(fieldFont);

        quantityField.setPreferredSize(new Dimension(500, 70));
        quantityField.setFont(fieldFont);

        submitBtn.setPreferredSize(new Dimension(250, 100));
        submitBtn.setFont(fieldFont);
        submitBtn.addActionListener(this.ac);

        clearBtn.setPreferredSize(new Dimension(250, 100));
        clearBtn.setFont(fieldFont);
        clearBtn.addActionListener(this.ac);
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

    public AddItemController getAc() {
        return ac;
    }

    public void setAc(AddItemController ac) {
        this.ac = ac;
    }

    public JPlaceholderTextField getItemNameField() {
        return itemNameField;
    }

    public void setItemNameField(JPlaceholderTextField itemNameField) {
        this.itemNameField = itemNameField;
    }

    public JPlaceholderTextField getItemTypeField() {
        return itemTypeField;
    }

    public void setItemTypeField(JPlaceholderTextField itemTypeField) {
        this.itemTypeField = itemTypeField;
    }

    public JPlaceholderTextField getItemPriceField() {
        return itemPriceField;
    }

    public void setItemPriceField(JPlaceholderTextField itemPriceField) {
        this.itemPriceField = itemPriceField;
    }

    public JPlaceholderTextField getItemWeightField() {
        return itemWeightField;
    }

    public void setItemWeightField(JPlaceholderTextField itemWeightField) {
        this.itemWeightField = itemWeightField;
    }

    public JPlaceholderTextField getQuantityField() {
        return quantityField;
    }

    public void setQuantityField(JPlaceholderTextField quantityField) {
        this.quantityField = quantityField;
    }

    public Font getFieldFont() {
        return fieldFont;
    }

    public void setFieldFont(Font fieldFont) {
        this.fieldFont = fieldFont;
    }

    public JPanel getNamePanel() {
        return namePanel;
    }

    public void setNamePanel(JPanel namePanel) {
        this.namePanel = namePanel;
    }

    public JPanel getTypePanel() {
        return typePanel;
    }

    public void setTypePanel(JPanel typePanel) {
        this.typePanel = typePanel;
    }

    public JPanel getPricePanel() {
        return pricePanel;
    }

    public void setPricePanel(JPanel pricePanel) {
        this.pricePanel = pricePanel;
    }

    public JPanel getWeightPanel() {
        return weightPanel;
    }

    public void setWeightPanel(JPanel weightPanel) {
        this.weightPanel = weightPanel;
    }

    public JPanel getQuantityPanel() {
        return quantityPanel;
    }

    public void setQuantityPanel(JPanel quantityPanel) {
        this.quantityPanel = quantityPanel;
    }

    public JPanel getBtnPanel() {
        return btnPanel;
    }

    public void setBtnPanel(JPanel btnPanel) {
        this.btnPanel = btnPanel;
    }

    public JButton getSubmitBtn() {
        return submitBtn;
    }

    public void setSubmitBtn(JButton submitBtn) {
        this.submitBtn = submitBtn;
    }

    public JButton getClearBtn() {
        return clearBtn;
    }

    public void setClearBtn(JButton clearBtn) {
        this.clearBtn = clearBtn;
    }
}
