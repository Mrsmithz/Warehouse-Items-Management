package controller;
import views.*;
import model.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class AddItemController implements ActionListener, KeyListener{
    private MainController mc;
    private AddItemGUI addItemGUI;
    private Item item;
    public AddItemController(MainController mc){
        this.mc = mc;
        setComponents();
    }
    private void setComponents(){
        this.addItemGUI = new AddItemGUI(this);
    }

    public MainController getMc() {
        return mc;
    }

    public void setMc(MainController mc) {
        this.mc = mc;
    }

    public AddItemGUI getAddItemGUI() {
        return addItemGUI;
    }

    public void setAddItemGUI(AddItemGUI addItemGUI) {
        this.addItemGUI = addItemGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addItemGUI.getClearBtn())){
            clearField();
        }
        else if (e.getSource().equals(addItemGUI.getSubmitBtn())){
            addItem();
        }
    }
    private void addItem(){
        if (numberFieldValidate() && stringFieldValidate()){
            int id = mc.getUser().getId();
            String name = addItemGUI.getItemNameField().getText();
            String type = addItemGUI.getItemTypeField().getText();
            double price = Double.parseDouble(addItemGUI.getItemPriceField().getText());
            double weight = Double.parseDouble(addItemGUI.getItemWeightField().getText());
            int quantity = Integer.parseInt(addItemGUI.getQuantityField().getText());
            item = new Item(id, name, type, price, weight, quantity);
            try{
                if (mc.getUser().addItem(item)){
                    JOptionPane.showMessageDialog(mc.getMainFrame(), "Added Item.");
                    clearField();
                    mc.getTableController().updateTable();
                }
                else{
                    JOptionPane.showMessageDialog(mc.getMainFrame(), "Item maybe exists.");
                }
            }
            catch (SQLException ex){
                JOptionPane.showMessageDialog(mc.getMainFrame(), "Added item failed.");
            }
        }
        else{
            JOptionPane.showMessageDialog(mc.getMainFrame(), "Invalid Input !");
        }
    }
    private boolean numberFieldValidate(){
        try{
            Double.parseDouble(addItemGUI.getItemPriceField().getText());
            Double.parseDouble(addItemGUI.getItemWeightField().getText());
            Integer.parseInt(addItemGUI.getQuantityField().getText());
            return (Integer.parseInt(addItemGUI.getQuantityField().getText()) != 0) && (Double.parseDouble(addItemGUI.getItemWeightField().getText()) != 0);
        }
        catch (NumberFormatException e){
            return false;
        }
    }
    private boolean stringFieldValidate(){
        if (!addItemGUI.getItemNameField().getText().equals("Item Name") && !addItemGUI.getItemTypeField().equals("Item Type")){
            return true;
        }
        else{
            return false;
        }
    }
    private void clearField(){
        addItemGUI.getItemNameField().setText("");
        addItemGUI.getItemTypeField().setText("");
        addItemGUI.getItemPriceField().setText("");
        addItemGUI.getItemWeightField().setText("");
        addItemGUI.getQuantityField().setText("");
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            addItem();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
