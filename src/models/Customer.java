package models;

import services.FileManager;
import services.ProductsManager;

import java.util.ArrayList;
import java.util.Calendar;

public class Customer {
    private static int INDEX = 1;
    private int id;
    private String name;
    private String account;
    private String password;
    private ArrayList<Cart> carts;
    public Customer(String name, String account, String password) {
        this.name = name;
        this.account = account;
        this.password = password;
        this.id = INDEX++;
        this.carts = new ArrayList<>();
        Cart cart = new Cart();
        carts.add(cart);
    }

    public Customer(int id, String name, String account, String password) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.carts = new ArrayList<>();
    }

    public void createNewCart() {
        Cart cart = new Cart();
        carts.add(cart);
    }
    public void pay() {
        ArrayList<Item> items = getCurrentCart().getItems();
        double money = 0;
        for (Item item : items) {
            money += item.getBuyQuantity() * item.getProduct().getPrice();
            item.getProduct().setQuantity(item.getProduct().getQuantity() - item.getBuyQuantity());
        }
        getCurrentCart().setMoney(money);
        getCurrentCart().setDate(Calendar.getInstance().getTime());
        getCurrentCart().setStatus(true);
        System.out.println(getCurrentCart());
        System.out.println("Đơn hàng vừa thanh toán thành công");

        createNewCart();
    }
    public Cart getCurrentCart() {
        return carts.get(carts.size()-1);
    }
    public void displayCarts() {
        for (Cart cart : carts) {
            if (cart.isStatus()) {
                System.out.println(cart);
            }
        }
    }
    public String idCartsList() {
        StringBuilder idCartsList = new StringBuilder();
        for (Cart cart : carts) {
            idCartsList.append(cart.getId()).append(",");
        }
        return idCartsList.toString();
    }
    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static int getINDEX() {
        return INDEX;
    }

    public static void setINDEX(int INDEX) {
        Customer.INDEX = INDEX;
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

    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id = " + id +
                ", name = " + name + '}';
    }
}
