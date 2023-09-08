package models;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private String description;
    private double price;
    private Category category;

    public Product(int id, String name, int quantity, String description, double price, Category category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                ", description=" + description + ", price=" + price + ", categories=" + category + '}';
    }
}