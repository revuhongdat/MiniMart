package models;

import services.ExceptionManager;
import services.ProductsManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart {
    private final ArrayList<Products> itemList;
    private final Scanner scanner;
    public Cart() {
        itemList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    public void displayCart() {
        if (itemList.isEmpty()){
            System.err.println("Cart is empty!!!");
        } else {
            for (Products p : itemList) {
                System.out.println(p);
            }
        }
    }
    public void addItems(ProductsManager productsManager) {
        productsManager.displayProducts();
        System.out.println("Enter the id of the product you want to add to the cart:");
        int idBuy = ExceptionManager.exceptionPositiveInteger();
        if (productsManager.IdIsExist(idBuy)) {
            Products itemInMart = productsManager.findProductsById(idBuy);

//            ArrayList<Products> availableProductsList = productsManager.getProductsArrayList();
            System.out.println("There are " +  itemInMart.getQuantity() + " products left in the store" +
                    ". Enter the quantity of products you want to add to your cart:");
            int quantityBuy = ExceptionManager.exceptionPositiveInteger();
            Products itemBuy = new Products(idBuy,itemInMart.getName(),quantityBuy,itemInMart.getDescription(),itemInMart.getPrice(),itemInMart.getCategories());
            itemList.add(itemBuy);
        }
    }
    public void removeItems() {
        System.out.println("remove item");
    }
}
