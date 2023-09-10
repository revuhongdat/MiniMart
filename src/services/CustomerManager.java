package services;

import models.Cart;
import models.Customer;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerManager {
    private ArrayList<Customer> customers;
    private final Scanner scanner;
    public CustomerManager() {
        customers = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    public void register() {
        String account = "";
        do {
            System.out.println("Nhập tài khoản:");
            account = scanner.nextLine();
            if (account.isEmpty()) {
                System.err.println("Tài khoản không được để trống!");
            }
            if (isDuplicateAccount(account)) {
                System.err.println("Tài khoản đã tồn tại!");
                account = "";
            }
        } while (account.isEmpty());
        System.out.println("Nhập mật khẩu:");
        String password = scanner.nextLine();
        System.out.println("Nhập tên của bạn:");
        String name = scanner.nextLine();
        Customer newCustomer = new Customer(name, account, password);
        customers.add(newCustomer);
        System.out.println("Đăng ký thành công!");
    }
    public Customer signIn() {
        Customer customer = null;
        if (customers.isEmpty()) {
            System.err.println("Trong dữ liệu chưa có tài khoản khách hàng nào!");
            return null;
        } else {
            boolean check = false;
            do {
                System.out.println("Nhập tài khoản:");
                String account = scanner.nextLine();
                System.out.println("Nhập mật khẩu:");
                String password = scanner.nextLine();
                for (Customer c : customers) {
                    if (c.getAccount().equals(account) && c.getPassword().equals(password)) {
                        check = true;
                        return c;
                    }
                }
            } while (!check);
        }
        return customer;
    }
    public boolean isDuplicateAccount(String account) {
        for (Customer customer : customers) {
            if (customer.getAccount().equals(account)) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }
}
