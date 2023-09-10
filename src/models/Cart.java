package models;
import services.CategoriesManager;
import services.ExceptionManager;
import services.ProductsManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;
public class Cart {
    public static int INDEX = 0;
    private int id;
    private ArrayList<Item> items;
    private double money;
    private boolean status;

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;
    private final Scanner scanner;
    public Cart() {
        id = ++INDEX;
        money = 0;
        status = false;
        items = new ArrayList<>();
        date = Calendar.getInstance().getTime();
        scanner = new Scanner(System.in);
    }

    public Cart(int id, double money, boolean status) {
        this.id = id;
        this.money = money;
        items = new ArrayList<>();
        this.status = status;
        this.date = null;
        scanner = new Scanner(System.in);
    }
    public void addProductToCart(ProductsManager productsManager) {
        productsManager.displayProducts();
        System.out.println("Nhập id của item bạn muốn mua:");
        int id = Integer.parseInt(scanner.nextLine());
        Product product = productsManager.findProductsById(id);
        if (product != null) {
            System.out.println("Nhập số lượng item bạn muốn mua:");
            int buyQuantity = ExceptionManager.exceptionPositiveInteger();
            boolean check = false;
            for (Item item : getItems()) {
                if (item.getProduct().getId() == id) {
                    check = true;
                    item.setBuyQuantity(item.getBuyQuantity() + buyQuantity);
                }
            }
            if (!check) {
                getItems().add(new Item(product, buyQuantity));
            }
        }
    }
    public void displayCart() {
        try {
            if(getItems().isEmpty()) {
                System.err.println("Giỏ hàng đang trống!");
            } else {
                for (Item item : getItems()) {
                    System.out.println(item);
                }
            }
        } catch (NullPointerException e) {
            System.err.println("Giỏ hàng chưa được tạo, hãy mua sắm để tạo giỏ hàng!");
        }
    }
    public void deleteItemFromCart() {
        displayCart();
        boolean checkIdItem = false;
        int idItem;
        do {
            System.out.println("Nhập id của item trong giỏ mà bạn muốn xoá:");
            idItem = ExceptionManager.exceptionPositiveInteger();
            if (findItemById(idItem) != null) {
                checkIdItem = true;
            }
        } while (!checkIdItem);
        Item item = findItemById(idItem);
        System.out.println("Nhập số lượng item mà bạn muốn xoá trong giỏ hàng:");
        int delQuantity = ExceptionManager.exceptionPositiveInteger();
        item.setBuyQuantity(item.getBuyQuantity() - delQuantity);
        if (item.getBuyQuantity() <= 0) {
            getItems().remove(item);
        }
        System.err.println("Đã xoá item!");
    }
    public Item findItemById(int id) {
        for (Item item : getItems()) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    public String idItemsList() {
        StringBuilder idItemsList = new StringBuilder();
        for (Item item : items) {
            idItemsList.append(item.getId()).append(",");
        }
        return idItemsList.toString();
    }

    public static int getINDEX() {
        return INDEX;
    }
    public static void setINDEX(int INDEX) {
        Cart.INDEX = INDEX;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    public String displayPayCart() {
        String items = "";
        for (Item item : getItems()) {
            items += item.toString();
        }
        return items;
    }
    public Date getDate() {
        return date;
    }
    @Override
    public String toString() {
        return "-------------------------HOÁ ĐƠN MUA HÀNG-----------------------------" + "\n" +
                "Ngày mua hàng : " + date + "\n" +
                displayPayCart() +
                "Tổng tiền     : " + money + "\n" +
                "----------------------------------------------------------------------";
    }
}
