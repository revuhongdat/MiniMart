package services;

import models.Cart;
import models.Item;
import models.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class CartUser {
    private ProductsManager productManager;
    private static int INDEX;
    private int id;
    Cart cart;
    ArrayList<Cart> listCart;
    Scanner scanner;

    public CartUser(ProductsManager productsManager) {
        this.productManager = productsManager;
        id = ++INDEX;
        listCart = new ArrayList<>();
        cart = null;
        scanner = new Scanner(System.in);
    }
    public void addProductToCart() {
        productManager.displayProducts();
        System.out.println("Enter the id of product you want to buy:");
        int id = Integer.parseInt(scanner.nextLine());
        Product product = productManager.findProductsById(id);
        if (product != null) {
            System.out.println("Enter the number of products you need to buy:");
            int buyQuantity = Integer.parseInt(scanner.nextLine());
            if (cart == null) {
                cart = new Cart();
                Item item = new Item(product, buyQuantity);
                cart.getListItem().add(item);
            } else {
                boolean check = false;
                for (Item item : cart.getListItem()) {
                    if (item.getProduct().getId() == id) {
                        check = true;
                        item.setBuyQuantity(item.getBuyQuantity() + buyQuantity);
                    }
                }
                if (!check) {
                    cart.getListItem().add(new Item(product, buyQuantity));
                }
            }
        }
    }

    public void creat
    public static int getINDEX() {
        return INDEX;
    }

    public static void setINDEX(int INDEX) {
        CartUser.INDEX = INDEX;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public ArrayList<Cart> getListCart() {
        return listCart;
    }

    public void setListCart(ArrayList<Cart> listCart) {
        this.listCart = listCart;
    }
}
