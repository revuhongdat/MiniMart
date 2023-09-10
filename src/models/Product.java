package models;

public class Product {
    private static int INDEX = 1;
    private int id;
    private String name;
    private int quantity;
    private double price;
    private Category category;

    public Product(String name, int quantity, double price, Category category) {
        this.id = INDEX++;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    public static int getINDEX() {
        return INDEX;
    }

    public static void setINDEX(int INDEX) {
        Product.INDEX = INDEX;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategories() {
        return category;
    }

    public void setCategories(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Products{" + "id=" + id + ", name=" + name + ", quantity=" + quantity +
                ", price=" + price + ", categories=" + category + '}';
    }
}