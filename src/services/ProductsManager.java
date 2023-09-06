package services;

import models.Categories;
import models.Products;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductsManager {
    private static int INDEX = 0;
    private final ArrayList<Products> productsArrayList;
    private final Scanner scanner;

    public ProductsManager() {
        productsArrayList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public ArrayList<Products> getProductsArrayList() {
        return productsArrayList;
    }

    public void addProducts(CategoriesManager categoriesManager) {
        System.out.println("Enter the name of product:");
        String name = scanner.nextLine();
        int quantity = ExceptionManager.exceptionQuantity();
        String description = ExceptionManager.exceptionDescription();
        double price = ExceptionManager.exceptionPrice();
        // Hien thi danh sach Categories cho nguoi dung,
        // neu danh sach trong thi tao mot categories moi cho Product do
        if (categoriesManager.getCategoriesList().isEmpty()) {
            System.err.println("List of categories is empty, you can create a new categories for this product here!");
            categoriesManager.createCategories();
            Categories categories = categoriesManager.findCategoriesById(1);
            Products products = new Products(INDEX++, name, quantity, description, price, categories);
            productsArrayList.add(products);
        } else {
            System.out.println("Choice categories: ");
            categoriesManager.displayCategories();
            System.out.println("Enter id categories your choice: ");
            int idCategories = ExceptionManager.exceptionPositiveInteger();
            Categories categories = categoriesManager.findCategoriesById(idCategories);
            Products products = new Products(++INDEX, name, quantity, description, price, categories);
            productsArrayList.add(products);
        }
    }
public void loadProduct(ArrayList<String[]> arrayList, CategoriesManager categoriesManager) {
    for (int i = 0; i < arrayList.size(); i++) {
        int id = Integer.parseInt(arrayList.get(i)[0]);
        if (INDEX < id) {
            INDEX = id;
        }
        String name = arrayList.get(i)[1];
        int quantity = Integer.parseInt(arrayList.get(i)[2]);
        String description = arrayList.get(i)[3];
        double price = Double.parseDouble(arrayList.get(i)[4]);
        int idCategories = Integer.parseInt(arrayList.get(i)[5]);
        Categories categories = categoriesManager.findCategoriesById(idCategories);
        Products products = new Products(id, name, quantity, description, price, categories);
        productsArrayList.add(products);
    }
}
    public void displayProducts(){
        for (Products products : productsArrayList) {
            System.out.println(products);
        }
    }

    public void displayProductsById(){
        System.out.println("Enter ID: ");
        int id = ExceptionManager.exceptionPositiveInteger();
        for (Products products : productsArrayList) {
            if (products.getId() == id) {
                System.out.println(products);
                break;
            } else {
                System.out.println("There are no products matching this ID: " + id);
            }
        }
    }
    public void deleteProducts(){
        System.out.println("Enter ID: ");
        int id = ExceptionManager.exceptionPositiveInteger();
        Products products = findProductsById(id);
        if (products == null) {
            System.err.println("Not found!");
        } else {
            productsArrayList.remove(products);
            System.out.println("Delete successfully!");
        }
    }
    public void editProducts(CategoriesManager categoriesManager){
        System.out.println("Enter ID: ");
        int id = ExceptionManager.exceptionPositiveInteger();
        Products products = findProductsById(id);
        if (products == null) {
            System.err.println("Not found!");
        } else {
            System.out.println("Enter new name of products : ");
            String name = scanner.nextLine();
            int quantity = ExceptionManager.exceptionQuantity();
            String description = ExceptionManager.exceptionDescription();
            double price = ExceptionManager.exceptionPrice();
            // hiển thị danh sách categories và cho người dùng nhập id
            // lấy đối tượng id từ manager => thêm vào student
            System.out.println("Select new categories of products : ");
            categoriesManager.displayCategories();
            System.out.println("Enter id of new categories of products : ");
            int idCategories = ExceptionManager.exceptionPositiveInteger();
            // categories tham chiếu đến một categories trong categoriesList có id = idCategories
            Categories categories = categoriesManager.findCategoriesById(idCategories);
            //-------------------------------

            products.setName(name);
            products.setQuantity(quantity);
            products.setDescription(description);
            products.setPrice(price);
            products.setCategories(categories);
            System.out.println("Edit successfully!");
        }
    }

    private Products findProductsById(int id) {
        for (Products products : productsArrayList) {
            if (products.getId() == id) {
                return products;
            }
        }
        return null;
    }


    public void findProductsByApproximateName(){
        ArrayList<Products> listFindName = new ArrayList<>();
        System.out.println("Enter name of Products:");
        String nameSearch = scanner.nextLine();
        for (Products products : productsArrayList) {
            if (products.getName().contains(nameSearch)) {
                listFindName.add(products);
            }
        }
        if (listFindName.isEmpty()) {
            System.out.println("There are no products matching this name: " + nameSearch);
        } else {
            for (Products products : listFindName) {
                System.out.println(products);
            }
        }
    }
    public void findProductsByPriceRange() {
        ArrayList<Products> listFindPrice = new ArrayList<>();
        System.out.println("Enter the min price:");
        double minPrice = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter the max price:");
        double maxPrice = Double.parseDouble(scanner.nextLine());
        for(Products products : productsArrayList) {
            if (products.getPrice() <= maxPrice && products.getPrice() >= minPrice) {
                listFindPrice.add(products);
            }
        }
        if (listFindPrice.isEmpty()) {
            System.out.println("There are no products in price range " + minPrice + " - " + maxPrice);
            } else {
            for (Products products : listFindPrice) {
                System.out.println(products);
            }
        }
    }
    public void displayProductsByCategories(CategoriesManager categoriesManager) {
        ArrayList<Products> listProducts = new ArrayList<>();
        System.out.println("List of categories:");
        categoriesManager.displayCategories();
        System.out.println("Select the id of the category you want to display products:");
        int idCategory = Integer.parseInt(scanner.nextLine());
        for (Products products : productsArrayList) {
            if (products.getCategories().getId() == idCategory) {
                listProducts.add(products);
            }
        }
        if (listProducts.isEmpty()) {
            System.out.println("This category has no products!");
        } else {
            for (Products products : listProducts) {
                System.out.println(products);
            }
        }
    }

    public void sortByQuantity(ComparatorQuantity comparatorQuantity) {
        productsArrayList.sort(comparatorQuantity);
    }

    public void sortByPriceAscending(ComparatorPriceAscending comparatorPriceAscending) {
        productsArrayList.sort(comparatorPriceAscending);
    }

    public void sortByPriceDescending(ComparatorPriceDescending comparatorPriceDescending) {
        productsArrayList.sort(comparatorPriceDescending);
    }
}
