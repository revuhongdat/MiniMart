package models;

public class CartItem {
    private Products products;
    private int quantity;
    private double price;

    public CartItem(Products products, int quantity) {
        this.products = products;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
