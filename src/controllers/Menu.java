package controllers;

import models.Cart;
import services.*;

import java.util.Scanner;

public class Menu {
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        Cart cart = new Cart();
        CategoriesManager categoriesManager = new CategoriesManager();
        ProductsManager productsManager = new ProductsManager();
        ComparatorPriceAscending comparatorPriceAscending = new ComparatorPriceAscending();
        ComparatorPriceDescending comparatorPriceDescending = new ComparatorPriceDescending();
        FileManager fileManager = new FileManager();
        categoriesManager.loadCategories(fileManager.importData("D:\\MiniMart\\src\\dataCategories.txt"));
        productsManager.loadProduct(fileManager.importData("D:\\MiniMart\\src\\dataProducts.txt"), categoriesManager);
        do {
            System.out.println("MENU:");
            System.out.println("1. Menu Manager");
            System.out.println("2. Menu Customer");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            int choice = ExceptionManager.exceptionChoice();
            switch (choice) {
                case 1:
                    menuManager(categoriesManager, productsManager, scanner);
                    break;
                case 2:
                    menuCustomer(productsManager, cart, categoriesManager, comparatorPriceDescending, comparatorPriceAscending);
                    break;
                case 0:
                    fileManager.exportData(productsManager.getProducts(), categoriesManager.getCategories());
                    System.exit(0);

            }
        } while (true);
    }

    private void menuManager(CategoriesManager categoriesManager, ProductsManager productsManager, Scanner scanner) {
        int choice;
        do {
            System.out.println("Menu Manager:");
            System.out.println("1. Create categories");
            System.out.println("2. Edit categories");
            System.out.println("3. Display categories");
            System.out.println("4. Create products");
            System.out.println("5. Edit products");
            System.out.println("6. Delete products");
            System.out.println("7. Display products");
            System.out.println("0. Back to menu");
            System.out.println("Enter your choice: ");
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
            }

        } while (choice != 0);
    }

    private void menuCustomer(ProductsManager productsManager,
                              Cart cart,
                              CategoriesManager categoriesManager,
                              ComparatorPriceDescending comparatorPriceDescending,
                              ComparatorPriceAscending comparatorPriceAscending) {
        int choice;
        do {
            System.out.println("Menu Customer");
            System.out.println("1. Find products by approximate name");
            System.out.println("2. Find products by price range");
            System.out.println("3. Display products by categories");
            System.out.println("4. Sort products by price in ascending order");
            System.out.println("5. Sort products by price in descending order");
            System.out.println("6. Buy");
            System.out.println("0. Back to menu");
            System.out.println("Enter your choice:");
            choice = ExceptionManager.exceptionChoice();
            switch (choice) {
                case 1:
                    productsManager.findProductByApproximateName();
                    break;
                case 2:
                    productsManager.findProductByPriceRange();
                    break;
                case 3:
                    productsManager.displayProductByCategories(categoriesManager);
                    break;
                case 4:
                    productsManager.sortByPriceAscending(comparatorPriceAscending);
                    productsManager.displayProducts();
                    break;
                case 5:
                    productsManager.sortByPriceDescending(comparatorPriceDescending);
                    productsManager.displayProducts();
                    break;
                case 6:
                    menuBuy(cart, productsManager);
                    break;
            }
        } while (choice != 0);
    }
    private void menuBuy(Cart cart,ProductsManager productsManager) {
        int choice;
        do {
            System.out.println("Menu Buy");
            System.out.println("1. Add item to cart");
            System.out.println("2. Remove item from cart");
            System.out.println("3. Display cart");
            System.out.println("4. Pay");
            System.out.println("0. Back to menu");
            choice = ExceptionManager.exceptionChoice();
            switch (choice) {
                case 1:
                    cart.addItems(productsManager);
                    break;
                case 2:
                    cart.removeItems();
                case 3:
                    cart.displayCart();
            }
        } while (choice != 0);
    }
}
