package controllers;

import services.*;

import java.util.Scanner;

public class Menu {
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        CategoriesManager categoriesManager = new CategoriesManager();
        ProductsManager productsManager = new ProductsManager();
        ComparatorPrice comparatorPrice = new ComparatorPrice();
        ComparatorQuantity comparatorQuantity = new ComparatorQuantity();
        FileManager fileManager = new FileManager();
        categoriesManager.loadClasses(fileManager.importData("D:\\Module2_APJ\\HomeWork\\src\\dataClasses.txt"));
        productsManager.loadProduct(fileManager.importData("D:\\Module2_APJ\\HomeWork\\src\\dataStudents.txt"), categoriesManager);
        do {
            System.out.println("MENU:");
            System.out.println("1. Menu Classes");
            System.out.println("2. Menu Student");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    menuClasses(categoriesManager, scanner);
                    break;
                case 2:
                    menuStudent(productsManager, scanner, categoriesManager, comparatorQuantity, comparatorPrice);
                    break;
                case 0:
                    fileManager.exportData(productsManager.getProductsArrayList(), categoriesManager.getClassesList());
                    System.exit(0);

            }
        } while (true);
    }

    private void menuClasses(CategoriesManager categoriesManager, Scanner scanner) {
        int choice;
        do {
            System.out.println("Menu Classes:");
            System.out.println("1. Create classes");
            System.out.println("2. Edit classes");
            System.out.println("3. Display classes");
            System.out.println("0. Back to menu");
            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    categoriesManager.createClasses();
                    break;
                case 2:
                    categoriesManager.editClasses();
                    break;
                case 3:
                    categoriesManager.displayCategories();
                    break;
            }

        } while (choice != 0);
    }

    private void menuStudent(ProductsManager productsManager,
                             Scanner scanner, CategoriesManager categoriesManager,
                             ComparatorQuantity comparatorQuantity, ComparatorPrice comparatorPrice) {
        int choice;
        do {
            System.out.println("Menu Student");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Edit Student");
            System.out.println("4. Find Student by name");
            System.out.println("5. Student sort by age");
            System.out.println("6. Student sort by point");
            System.out.println("7. Display student by ID");
            System.out.println("8. Display all student");
            System.out.println("0. Back to menu");
            System.out.println("Enter your choice:");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    productsManager.addProducts(categoriesManager);
                    break;
                case 2:
                    productsManager.deleteProducts();
                    break;
                case 3:
                    productsManager.editProducts(categoriesManager);
                    break;
                case 4:
                    productsManager.findProductsByApproximateName();
                    break;
                case 5:
                    productsManager.sortByQuantity(comparatorQuantity);
                    break;
                case 6:
                    productsManager.sortByPrice(comparatorPrice);
                    break;
                case 7:
                    productsManager.displayProductsById();
                    break;
                case 8:
                    productsManager.displayProducts();
                    break;
            }
        } while (choice != 0);
    }
}
