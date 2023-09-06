package controllers;

import services.*;

import java.util.Scanner;

public class Menu {
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        CategoriesManager categoriesManager = new CategoriesManager();
        ProductsManager productsManager = new ProductsManager();
        ComparatorPriceAscending comparatorPriceAscending = new ComparatorPriceAscending();
        ComparatorPriceDescending comparatorPriceDescending = new ComparatorPriceDescending();
        FileManager fileManager = new FileManager();
        categoriesManager.loadCategories(fileManager.importData("D:\\Module2_APJ\\HomeWork\\src\\dataCategories.txt"));
        productsManager.loadProduct(fileManager.importData("D:\\Module2_APJ\\HomeWork\\src\\dataProducts.txt"), categoriesManager);
        do {
            System.out.println("MENU:");
            System.out.println("1. Menu Manager");
            System.out.println("2. Menu Customer");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            int choice = ExceptionManager.exceptionPositiveInteger();
            switch (choice) {
                case 1:
                    menuManager(categoriesManager, productsManager, scanner);
                    break;
                case 2:
                    menuCustomer(productsManager, scanner, categoriesManager, comparatorPriceDescending, comparatorPriceAscending);
                    break;
                case 0:
                    fileManager.exportData(productsManager.getProductsArrayList(), categoriesManager.getCategoriesList());
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
            choice = ExceptionManager.exceptionPositiveInteger();
            switch (choice) {
                case 1:
                    categoriesManager.createCategories();
                    break;
                case 2:
                    categoriesManager.editCategories();
                    break;
                case 3:
                    categoriesManager.displayCategories();
                    break;
                case 4:
                    productsManager.addProducts(categoriesManager);
                    break;
                case 5:
                    productsManager.editProducts(categoriesManager);
                    break;
                case 6:
                    productsManager.deleteProducts();
                    break;
                case 7:
                    productsManager.displayProducts();
                    break;
            }

        } while (choice != 0);
    }

    private void menuCustomer(ProductsManager productsManager,
                              Scanner scanner, CategoriesManager categoriesManager,
                              ComparatorPriceDescending comparatorPriceDescending, ComparatorPriceAscending comparatorPriceAscending) {
        int choice;
        do {
            System.out.println("Menu Customer");
            System.out.println("1. Find products by approximate name");
            System.out.println("2. Find products by price range");
            System.out.println("3. Display products by categories");
            System.out.println("4. Sort products by price in ascending order");
            System.out.println("5. Sort products by price in descending order");
            System.out.println("0. Back to menu");
            System.out.println("Enter your choice:");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    productsManager.findProductsByApproximateName();
                    break;
                case 2:
                    productsManager.findProductsByPriceRange();
                    break;
                case 3:
                    productsManager.displayProductsByCategories(categoriesManager);
                    break;
                case 4:
                    productsManager.sortByPriceAscending(comparatorPriceAscending);
                    productsManager.displayProducts();
                    break;
                case 5:
                    productsManager.sortByPriceDescending(comparatorPriceDescending);
                    productsManager.displayProducts();
                    break;
            }
        } while (choice != 0);
    }
}
