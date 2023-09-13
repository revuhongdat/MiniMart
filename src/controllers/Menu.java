package controllers;

import models.Cart;
import models.Customer;
import models.Item;
import models.Manager;
import services.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Menu {
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        CustomerManager customerManager = new CustomerManager();
        CategoriesManager categoriesManager = new CategoriesManager();
        ProductsManager productsManager = new ProductsManager();
        FileManager fileManager = new FileManager();
        categoriesManager.loadCategories(fileManager.importData("src/dataCategories.txt"));
        productsManager.loadProduct(fileManager.importData("src/dataProducts.txt"), categoriesManager);
        ArrayList<Item> listItems = Menu.loadItems(fileManager.importData("src/dataItems.txt"), productsManager);
        ArrayList<String[]> arrayListCarts = fileManager.importData("src/dataCarts.txt");
        ArrayList<Cart> carts1 = Menu.loadCarts1(arrayListCarts,listItems);
        ArrayList<Cart> carts2 = Menu.loadCarts2(arrayListCarts, carts1, listItems);
        ArrayList<String[]> arrayListCustomers = fileManager.importData("src/dataCustomers.txt");
        ArrayList<Customer> customers1 = Menu.loadCustomers1(arrayListCustomers);
        ArrayList<Customer> customers2 = Menu.loadCustomers2(arrayListCustomers, customers1, carts2);
        customerManager.setCustomers(customers2);
        Manager manager = new Manager();
        do {
            System.out.println("MENU:");
            System.out.println("1. Quản lý");
            System.out.println("2. Khách hàng");
            System.out.println("3. Hiện danh sách Item");
            System.out.println("4. Hiện danh sách Cart");
            System.out.println("5. Hiện danh sách Customer");
            System.out.println("0. Thoát");
            System.out.println("Nhập lựa chọn: ");
            int choice = ExceptionManager.exceptionChoice();
            switch (choice) {
                case 1:
                    do {
                        System.out.println("Nhập tài khoản:");
                        String account = scanner.nextLine();
                        System.out.println("Nhập mật khẩu:");
                        String password = scanner.nextLine();
                        if (manager.getAccount().equals(account) && manager.getPassword().equals(password)) {
                            break;
                        } else {
                            System.err.println("Tài khoản hoặc mật khẩu không đúng. Vui lòng nhập lại!");
                        }
                    } while (true);
                    menuManager(categoriesManager, productsManager, scanner, manager);
                    break;
                case 2:
                    menuSignInRegister(customerManager, productsManager, fileManager, categoriesManager);
                    break;
                case 3:
                    for (Item item : listItems) {
                        System.out.println(item);
                    }
                    break;
                case 4:
                    for (Cart cart : carts2) {
                        System.out.println(cart);
                    }
                    break;
                case 5:
                    for (Customer customer : customerManager.getCustomers()) {
                        System.out.println(customer);
                    }
                    break;
                case 0:
                    fileManager.exportData(productsManager.getProducts(), categoriesManager.getCategories());
                    System.exit(0);
            }
        } while (true);
    }

    private void menuManager(CategoriesManager categoriesManager, ProductsManager productsManager, Scanner scanner, Manager manager) {
        int choice;
        do {
            System.out.println("┌———————————————————————————————————┐");
            System.out.println("⎟           MENU QUẢN LÝ            ⎟");
            System.out.println("⎟———————————————————————————————————⎟");
            System.out.println("⎟1. Tạo danh mục sản phẩm           ⎟");
            System.out.println("⎟2. Sửa danh mục sản phẩm           ⎟");
            System.out.println("⎟3. Hiện danh mục sản phẩm          ⎟");
            System.out.println("⎟4. Thêm sản phẩm mới               ⎟");
            System.out.println("⎟5. Sửa sản phẩm                    ⎟");
            System.out.println("⎟6. Xoá sản phẩm                    ⎟");
            System.out.println("⎟7. Hiện sản phẩm                   ⎟");
            System.out.println("⎟8. Tài khoản                       ⎟");
            System.out.println("⎟0. Quay lại                        ⎟");
            System.out.println("└———————————————————————————————————┘");
            System.out.println("Nhập lựa chọn :");
            choice = ExceptionManager.exceptionChoice();
            switch (choice) {
                case 1:
                    categoriesManager.createCategory();
                    break;
                case 2:
                    categoriesManager.editCategory();
                    break;
                case 3:
                    categoriesManager.displayCategories();
                    break;
                case 4:
                    productsManager.addProduct(categoriesManager);
                    break;
                case 5:
                    productsManager.editProduct(categoriesManager);
                    break;
                case 6:
                    productsManager.deleteProduct();
                    break;
                case 7:
                    productsManager.displayProducts();
                    break;
                case 8:
                    System.out.print("Tài khoản : " + manager.getAccount() + "|" + "Mật khẩu : " + manager.getAccount());
                    System.out.println();
                    System.out.println("┌———————————————————————————————————┐");
                    System.out.println("⎟1. Đổi tài khoản                   ⎟");
                    System.out.println("⎟2. Đổi mật khẩu                    ⎟");
                    System.out.println("└———————————————————————————————————┘");

                    break;
            }
        } while (choice != 0);
    }
    public void menuSignInRegister(CustomerManager customerManager,
                                    ProductsManager productsManager,
                                    FileManager fileManager,
                                    CategoriesManager categoriesManager) {
        int choice1;
        do {
            System.out.println("┌———————————————————————————————————┐");
            System.out.println("⎟          MENU KHÁCH HÀNG          ⎟");
            System.out.println("⎟———————————————————————————————————⎟");
            System.out.println("⎟1.Đăng ký                          ⎟");
            System.out.println("⎟2.Đăng nhập                        ⎟");
            System.out.println("⎟0. Quay lại                        ⎟");
            System.out.println("└———————————————————————————————————┘");
            System.out.println("Nhập lựa chọn :");
            choice1 = ExceptionManager.exceptionChoice();
            switch (choice1) {
                case 1:
                    customerManager.register();
                    fileManager.exportCustomers(customerManager.getCustomers());
                    break;
                case 2:
                    Customer customer = customerManager.signIn();
                    if (customer != null) {
                        menuBuy(customer, productsManager, fileManager, customerManager, categoriesManager);
                    }
                    break;
            }
        } while (choice1 != 0);
    }
    private void menuBuy(Customer customer, ProductsManager productsManager,
                         FileManager fileManager, CustomerManager customerManager,
                         CategoriesManager categoriesManager) {
        int choice;
        do {
            System.out.println("┌———————————————————————————————————┐");
            System.out.println("⎟         MENU KHÁCH HÀNG           ⎟");
            System.out.println("⎟———————————————————————————————————⎟");
            System.out.println("⎟1. Thêm sản phẩm vào giỏ hàng      ⎟");
            System.out.println("⎟2. Xoá sản phẩm trong giỏ hàng     ⎟");
            System.out.println("⎟3. Hiển thị giỏ hàng               ⎟");
            System.out.println("⎟4. Thanh toán                      ⎟");
            System.out.println("⎟5. Lịch sử mua hàng                ⎟");
            System.out.println("⎟6. Tài khoản                       ⎟");
            System.out.println("⎟0. Đăng xuất                       ⎟");
            System.out.println("└———————————————————————————————————┘");
            System.out.println("Nhập lựa chọn :");
            choice = ExceptionManager.exceptionChoice();
            switch (choice) {
                case 1:
                    customer.getCarts().get(customer.getCarts().size()-1).addProductToCart(productsManager);
                    break;
                case 2:
                    customer.getCarts().get(customer.getCarts().size()-1).deleteItemFromCart();
                    break;
                case 3:
                    customer.getCarts().get(customer.getCarts().size()-1).displayCart();
                    break;
                case 4:
                    customer.pay();
                    fileManager.exportCustomers(customerManager.getCustomers());
                    fileManager.exportCarts(customerManager.getCustomers());
                    fileManager.exportItems(customerManager.getCustomers());
                    fileManager.exportData(productsManager.getProducts(), categoriesManager.getCategories());
                    break;
                case 5:
                    customer.displayCarts();
                    break;
                case 6:
                    int choice2;
                    do {
                        System.out.println(customer);

                        System.out.println("Tài khoản:");
                        System.out.println(customer.getAccount());
                        System.out.println("Mật khẩu:");
                        System.out.println(customer.getPassword());
                        System.out.println("┌———————————————————————————————————┐");
                        System.out.println("⎟1.Đổi mật khẩu                     ⎟");
                        System.out.println("⎟2.Đổi tên                          ⎟");
                        System.out.println("⎟0. Quay lại                        ⎟");
                        System.out.println("└———————————————————————————————————┘");
                        System.out.println("Nhập lựa chọn :");
                        choice2 = ExceptionManager.exceptionChoice();
                        switch (choice2) {
                            case 1:
                                System.err.println("Viết cho có thời gian đâu mà làm, nhà bao việc");
                                break;
                            case 2:
                                System.err.println("Chức năng ĐỔI TÊN chưa xong");
                                break;
                        }
                    } while (choice2 != 0);
                    break;
                case 0:
                    fileManager.exportCustomers(customerManager.getCustomers());
                    fileManager.exportCarts(customerManager.getCustomers());
                    fileManager.exportItems(customerManager.getCustomers());
            }
        } while (choice != 0);
    }
    public static ArrayList<Item> loadItems(ArrayList<String[]> arrayList, ProductsManager productsManager) {
        ArrayList<Item> listItems = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            int idItem = Integer.parseInt(arrayList.get(i)[0]);
            if (Item.INDEX < idItem) {
                Item.INDEX = idItem;
            }
            int idProduct = Integer.parseInt(arrayList.get(i)[1]);
            int buyQuantity = Integer.parseInt(arrayList.get(i)[2]);
            Item item = new Item(idItem, productsManager.findProductsById(idProduct),buyQuantity);
            listItems.add(item);
        }
        return listItems;
    }
    public static ArrayList<Cart> loadCarts1(ArrayList<String[]> arrayList, ArrayList<Item> listItems) {
        ArrayList<Cart> listCarts = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        for (int i = 0; i < arrayList.size(); i++) {
            int idCart = Integer.parseInt(arrayList.get(i)[0]);
            if (Cart.INDEX < idCart) {
                Cart.INDEX = idCart;
            }
            double money = Double.parseDouble(arrayList.get(i)[1]);
            boolean status = arrayList.get(i)[2].equals("true");
            Cart cart = new Cart(idCart, money, status);
            try {
                Date date = dateFormat.parse(arrayList.get(i)[3]);
                cart.setDate(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            listCarts.add(cart);
        }
        return listCarts;
    }
    public static ArrayList<Cart> loadCarts2(ArrayList<String[]> arrayList,
                                             ArrayList<Cart> listCarts,
                                             ArrayList<Item> listItems ) {
        for (int i = 0; i < arrayList.size(); i++) {
            ArrayList<Item> items = new ArrayList<>();
            for (int j = 4; j < arrayList.get(i).length; j++) {
                for (Item item : listItems) {
                    if (item.getId() == Integer.parseInt(arrayList.get(i)[j])) {
                        items.add(item);
                    }
                }
            }
            listCarts.get(i).setItems(items);
            items = null;
        }
        return listCarts;
    }
    public static ArrayList<Customer> loadCustomers1(ArrayList<String[]> arrayList) {
        ArrayList<Customer> listCustomers = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            int idCustomer = Integer.parseInt(arrayList.get(i)[0]);
            if (Customer.getINDEX() < idCustomer) {
                Customer.setINDEX(idCustomer + 1);
            }
            String name = arrayList.get(i)[1];
            String account = arrayList.get(i)[2];
            String password = arrayList.get(i)[3];
            Customer customer = new Customer(idCustomer, name, account, password);
            listCustomers.add(customer);
        }
        return listCustomers;
    }
    public static ArrayList<Customer> loadCustomers2(ArrayList<String[]> arrayList,
                                                     ArrayList<Customer> listCustomers,
                                                     ArrayList<Cart> listCarts) {
        for (int i = 0; i < arrayList.size(); i++) {
            ArrayList<Cart> carts = new ArrayList<>();
            for (int j = 4; j < arrayList.get(i).length; j++) {
                for (Cart cart : listCarts) {
                    if (cart.getId() == Integer.parseInt(arrayList.get(i)[j])) {
                        carts.add(cart);
                    }
                }
            }
            listCustomers.get(i).setCarts(carts);
            carts = null;
        }
        return listCustomers;
    }
}
