package models;

public class CartItem {
    private Products products;
    private int buyQuantity;

    public CartItem(Products products, int buyQuantity) {
        this.products = products;
        this.buyQuantity = buyQuantity;
    }

    public int getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(int buyQuantity) {
        this.buyQuantity = buyQuantity;
    }
}
