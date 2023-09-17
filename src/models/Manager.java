package models;

import services.ExceptionManager;

import java.util.Scanner;

public class Manager {
    private String account;
    private String password;

    public Manager() {
        this.account = "manager";
        this.password = "manager";
    }
    public void signIn() {
        System.out.println();
    }
    public void editManager() {
        System.out.print("Tài khoản : " + getAccount() + "|" + "Mật khẩu : " + getAccount());
        System.out.println();
//        int choice = 0;
//        do {
//            choice = ExceptionManager.exceptionChoice();
//            System.out.println("┌———————————————————————————————————┐");
//            System.out.println("⎟1. Đổi tài khoản và mật khẩu       ⎟");
//            System.out.println("⎟0. Quay lại                        ⎟");
//            System.out.println("└———————————————————————————————————┘");
//            System.out.println("Nhập lựa chọn:");
//            Scanner scanner = new Scanner(System.in);
//            int choice1 = ExceptionManager.exceptionChoice();
//            switch (choice1) {
//                case 1:
//                    System.out.println("Nhập tài khoản mới");
//                    String account = scanner.nextLine();
//                    System.out.println("Nhập mật khẩu mới");
//                    String password = scanner.nextLine();
//                    setAccount(account);
//                    setPassword(password);
//            }
//        } while (choice != 0);
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
