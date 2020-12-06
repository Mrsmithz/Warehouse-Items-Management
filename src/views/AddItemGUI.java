package views;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import controller.*;
import myutilities.CreateShortcuts;
import myutilities.JPlaceholderTextField;

public class AddItemGUI {
    private JInternalFrame mainFrame;
    private JPanel mainPanel, namePanel, typePanel, pricePanel, weightPanel, quantityPanel, btnPanel, titlePanel, infoPanel, blankPanel, titleInsidePanel, infoInsidePanel;
    private AddItemController ac;
    private JPlaceholderTextField itemNameField, itemTypeField, itemPriceField, itemWeightField, quantityField;
    private JButton submitBtn, clearBtn;
    private Font headFont, bodyFont;
    private JLabel titleLabel, infoLabel, nameLabel, typeLabel, priceLabel, weightLabel, quantityLabel;
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
        itemNameField = new JPlaceholderTextField("Enter your new item");
        itemTypeField = new JPlaceholderTextField("Enter item type");
        itemPriceField = new JPlaceholderTextField("Enter item price");
        itemWeightField = new JPlaceholderTextField("Enter item weight");
        quantityField = new JPlaceholderTextField("Enter quantity of item");
        submitBtn = new JButton("Submit");
        clearBtn = new JButton("Clear");
        titlePanel = new JPanel();
        infoPanel = new JPanel();
        titleInsidePanel = new JPanel();
        infoInsidePanel = new JPanel();
        titleLabel = new JLabel();
        infoLabel = new JLabel();
        blankPanel = new JPanel();
        nameLabel = new JLabel();
        priceLabel = new JLabel();
        typeLabel = new JLabel();
        weightLabel = new JLabel();
        quantityLabel = new JLabel();
        try (InputStream headInput = this.getClass().getResourceAsStream("/font/SukhumvitSet-Bold.ttf");
             InputStream bodyInput = this.getClass().getResourceAsStream("/font/SukhumvitSet-SemiBold.ttf")){
            headFont = Font.createFont(Font.TRUETYPE_FONT, headInput).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(headFont);

            bodyFont = Font.createFont(Font.TRUETYPE_FONT, bodyInput).deriveFont(12f);
            GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge2.registerFont(bodyFont);
        } catch (IOException | FontFormatException e) {
            headFont = new Font("Angsana New", Font.PLAIN, 12);
            bodyFont = new Font("Angsana New", Font.PLAIN, 12);
        }
    }
    private void setComponents(){
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(titlePanel, BorderLayout.NORTH);
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.add(btnPanel, BorderLayout.SOUTH);

        titlePanel.setLayout(new GridLayout(2,1));
        titlePanel.add(titleInsidePanel);
        titleInsidePanel.setLayout(new FlowLayout());
        titleInsidePanel.add(titleLabel);
        titleLabel.setFont(headFont.deriveFont(40f));
        titleLabel.setText("ADD ITEM");
        titleLabel.setForeground(new Color(240,240,240));
        titlePanel.add(infoInsidePanel);
        infoInsidePanel.setLayout(new FlowLayout());
        infoInsidePanel.add(infoLabel);
        infoLabel.setFont(bodyFont.deriveFont(15f));
        infoLabel.setText("- Add item to your storage -");
        infoLabel.setForeground(new Color(150,150,150));

        mainPanel.setLayout(new GridLayout(3,2));
        mainPanel.add(namePanel);
        mainPanel.add(typePanel);
        mainPanel.add(pricePanel);
        mainPanel.add(weightPanel);
        mainPanel.add(quantityPanel);

        namePanel.setLayout(new FlowLayout());
        namePanel.add(nameLabel);
        nameLabel.setText("Item Name");
        nameLabel.setForeground(new Color(200,200,200));
        nameLabel.setFont(headFont.deriveFont(25f));
        namePanel.add(itemNameField);

        typePanel.setLayout(new FlowLayout());
        typePanel.add(typeLabel);
        typeLabel.setText("Item Type");
        typeLabel.setForeground(new Color(200,200,200));
        typeLabel.setFont(headFont.deriveFont(25f));
        typePanel.add(itemTypeField);

        pricePanel.setLayout(new FlowLayout());
        pricePanel.add(priceLabel);
        priceLabel.setText("Item Price");
        priceLabel.setForeground(new Color(200,200,200));
        priceLabel.setFont(headFont.deriveFont(25f));
        pricePanel.add(itemPriceField);

        weightPanel.setLayout(new FlowLayout());
        weightPanel.add(weightLabel);
        weightLabel.setText("Item Weight");
        weightLabel.setForeground(new Color(200,200,200));
        weightLabel.setFont(headFont.deriveFont(25f));
        weightPanel.add(itemWeightField);

        quantityPanel.setLayout(new FlowLayout());
        quantityPanel.add(quantityLabel);
        quantityLabel.setText("Item Quantity");
        quantityLabel.setForeground(new Color(200,200,200));
        quantityLabel.setFont(headFont.deriveFont(25f));
        quantityPanel.add(quantityField);

        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(submitBtn);
        btnPanel.add(clearBtn);

        itemNameField.setPreferredSize(new Dimension(400, 70));
        itemNameField.setFont(bodyFont.deriveFont(25f));
        itemNameField.setSelectionColor(new Color(0,150,255, 50));
        itemNameField.addKeyListener(this.ac);

        itemTypeField.setPreferredSize(new Dimension(400, 70));
        itemTypeField.setFont(bodyFont.deriveFont(25f));
        itemTypeField.setSelectionColor(new Color(0,150,255, 70));
        itemTypeField.addKeyListener(this.ac);

        itemPriceField.setPreferredSize(new Dimension(400, 70));
        itemPriceField.setFont(bodyFont.deriveFont(25f));
        itemPriceField.setSelectionColor(new Color(0,150,255, 70));
        itemPriceField.addKeyListener(this.ac);

        itemWeightField.setPreferredSize(new Dimension(400, 70));
        itemWeightField.setFont(bodyFont.deriveFont(25f));
        itemWeightField.setSelectionColor(new Color(0,150,255, 70));
        itemWeightField.addKeyListener(this.ac);

        quantityField.setPreferredSize(new Dimension(400, 70));
        quantityField.setFont(bodyFont.deriveFont(25f));
        quantityField.setSelectionColor(new Color(0,150,255, 70));
        quantityField.addKeyListener(this.ac);

        submitBtn.setPreferredSize(new Dimension(250, 80));
        submitBtn.setFont(bodyFont.deriveFont(20f));
        submitBtn.addActionListener(this.ac);

        clearBtn.setPreferredSize(new Dimension(250, 80));
        clearBtn.setFont(bodyFont.deriveFont(20f));
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

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(JPanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public JPanel getInfoPanel() {
        return infoPanel;
    }

    public void setInfoPanel(JPanel infoPanel) {
        this.infoPanel = infoPanel;
    }

    public JPanel getBlankPanel() {
        return blankPanel;
    }

    public void setBlankPanel(JPanel blankPanel) {
        this.blankPanel = blankPanel;
    }

    public JPanel getTitleInsidePanel() {
        return titleInsidePanel;
    }

    public void setTitleInsidePanel(JPanel titleInsidePanel) {
        this.titleInsidePanel = titleInsidePanel;
    }

    public JPanel getInfoInsidePanel() {
        return infoInsidePanel;
    }

    public void setInfoInsidePanel(JPanel infoInsidePanel) {
        this.infoInsidePanel = infoInsidePanel;
    }

    public Font getHeadFont() {
        return headFont;
    }

    public void setHeadFont(Font headFont) {
        this.headFont = headFont;
    }

    public Font getBodyFont() {
        return bodyFont;
    }

    public void setBodyFont(Font bodyFont) {
        this.bodyFont = bodyFont;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(JLabel titleLabel) {
        this.titleLabel = titleLabel;
    }

    public JLabel getInfoLabel() {
        return infoLabel;
    }

    public void setInfoLabel(JLabel infoLabel) {
        this.infoLabel = infoLabel;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    public JLabel getTypeLabel() {
        return typeLabel;
    }

    public void setTypeLabel(JLabel typeLabel) {
        this.typeLabel = typeLabel;
    }

    public JLabel getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(JLabel priceLabel) {
        this.priceLabel = priceLabel;
    }

    public JLabel getWeightLabel() {
        return weightLabel;
    }

    public void setWeightLabel(JLabel weightLabel) {
        this.weightLabel = weightLabel;
    }

    public JLabel getQuantityLabel() {
        return quantityLabel;
    }

    public void setQuantityLabel(JLabel quantityLabel) {
        this.quantityLabel = quantityLabel;
    }
}
