package models;

import java.io.Serializable;

public class Item {
    public static int INDEX = 0;
    private int id;
    private Product product;
    private int buyQuantity;

    public Item(Product product, int buyQuantity) {
        this.id = ++INDEX;
        this.product = product;
        this.buyQuantity = buyQuantity;
    }

    public Item(int id, Product product, int buyQuantity) {
        this.id = id;
        this.product = product;
        this.buyQuantity = buyQuantity;
    }

    public static int getINDEX() {
        return INDEX;
    }

    public static void setINDEX(int INDEX) {
        Item.INDEX = INDEX;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(int buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    @Override
    public String toString() {
        return "Sản phẩm = " + product.getName() +
                ", Số lượng = " + buyQuantity + "\n";
    }

}
