package services;

import java.util.Scanner;

public class ExceptionManager {
    private static final Scanner sc = new Scanner(System.in) ;

    public static int exceptionQuantity() {
        int quantity = -1;
        do {
            System.out.println("Enter the quantity of product:");
            try {
                quantity = Integer.parseInt(sc.nextLine());
                if (quantity <= 0) {
                    quantity = -1;
                    System.out.println("Quantity of the product must be greater than zero, please re-enter!!!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Enter numbers only, please re-enter!!!");
            }

        } while (quantity == -1);
        return quantity;
    }
    public static double exceptionPrice() {
        double price = -1.0;
        do {
            System.out.println("Enter the price of product: ");
            try {
                price = Double.parseDouble(sc.nextLine());
                if (price <= 0) {
                    price = -1;
                    System.err.println("Price of the product must be greater than zero, please re-enter!!!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Enter numbers only, please re-enter!!!");
            }

        } while (price == -1);
        return price;
    }
    public static String exceptionDescription() {
        String description;
        do {
            System.out.println("Enter a description for the product (character length from 1 - 30) :");
            description = sc.nextLine();
        } while (description.isEmpty() || description.length() > 30);
        return description;
    }
    public static int exceptionPositiveInteger() {
        int number = -1;
        do {
            try {
                number = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Enter the wrong format, re-enter!!!");
            }
        } while (number == -1);
        return number;
    }
}
