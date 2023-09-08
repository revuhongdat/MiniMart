package models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
public class Cart implements Serializable {
    private static final long serialUID = 123456789;
    private static int INDEX = 0;
    private int id;
    private final ArrayList<Item> listItem;
    private double money;
    private boolean status;
    private Date date;
    private final Scanner scanner;
    public Cart() {
        id = ++INDEX;
        money = 0;
        status = false;
        listItem = new ArrayList<>();
//        date = Calendar.getInstance().getTime();
        date = null;
        scanner = new Scanner(System.in);
    }
    public static int getINDEX() {
        return INDEX;
    }
    private static void setINDEX(int INDEX) {
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
    public ArrayList<Item> getListItem() {
        return listItem;
    }
    public Scanner getScanner() {
        return scanner;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", items=" + listItem +
                '}';
    }
}
