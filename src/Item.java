public class Item {
    private String item_name, item_type;
    private double item_price, item_weight;
    private int quantity, user_id;
    public Item(String item_name, String item_type, double item_price, double item_weight, int quantity, int user_id){
        this.item_name = item_name;
        this.item_type = item_type;
        this.item_price = item_price;
        this.item_weight = item_weight;
        this.quantity = quantity;
        this.user_id = user_id;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public void setItem_weight(double item_weight) {
        this.item_weight = item_weight;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getItem_price() {
        return item_price;
    }

    public double getItem_weight() {
        return item_weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_type() {
        return item_type;
    }

}
