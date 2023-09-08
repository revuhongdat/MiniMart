package services;

import models.Category;
import models.Product;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductsManager {
    private static int INDEX = 0;
    private final ArrayList<Product> products;
    private final Scanner scanner;


    public ProductsManager() {
        products = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(CategoriesManager categoriesManager) {
        System.out.println("Enter the name of product:");
        String name = scanner.nextLine();
        int quantity = ExceptionManager.exceptionQuantity();
        String description = ExceptionManager.exceptionDescription();
        double price = ExceptionManager.exceptionPrice();
        // Check xem danh sách categories có trống ko, nếu trống thì tạo một categories cho product,
        // nếu đã có categories thì hiển thị danh sách categories để cho người dùng chọn
        if (categoriesManager.getCategories().isEmpty()) {
            System.err.println("List of categories is empty, you can create a new categories for this product here!");
            categoriesManager.createCategory();
            Category category = categoriesManager.findCategoryById(1);
            Product product = new Product(INDEX++, name, quantity, description, price, category);
            products.add(product);
        } else {
            System.out.println("Choice categories: ");
            categoriesManager.displayCategories();
            System.out.println("Enter id category your choice: ");
            int idCategories = ExceptionManager.exceptionPositiveInteger();
            Category category = categoriesManager.findCategoryById(idCategories);
            Product product = new Product(++INDEX, name, quantity, description, price, category);
            products.add(product);
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
        Category category = categoriesManager.findCategoryById(idCategories);
        Product product = new Product(id, name, quantity, description, price, category);
        products.add(product);
    }
}
    public void displayProducts(){
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void displayProductById(){
        System.out.println("Enter ID: ");
        int id = ExceptionManager.exceptionPositiveInteger();
        for (Product product : products) {
            if (product.getId() == id) {
                System.out.println(product);
                break;
            } else {
                System.out.println("There are no products matching this ID: " + id);
            }
        }
    }
    public void deleteProduct(){
        System.out.println("Enter ID: ");
        int id = ExceptionManager.exceptionPositiveInteger();
        Product product = findProductsById(id);
        if (product == null) {
            System.err.println("Not found!");
        } else {
            products.remove(product);
            System.out.println("Delete successfully!");
        }
    }
    public void editProduct(CategoriesManager categoriesManager){
        System.out.println("Enter ID: ");
        int id = ExceptionManager.exceptionPositiveInteger();
        Product product = findProductsById(id);
        if (product == null) {
            System.err.println("Not found!");
        } else {
            System.out.println("Enter new name of products : ");
            String name = scanner.nextLine();
            int quantity = ExceptionManager.exceptionQuantity();
            String description = ExceptionManager.exceptionDescription();
            double price = ExceptionManager.exceptionPrice();
            // Hiển thị danh sách categories và cho người dùng nhập id của categories muốn chọn
            // Lấy categories có id tương ứng từ trong categoriesList => add vào student
            System.out.println("Select new categories of products : ");
            categoriesManager.displayCategories();
            System.out.println("Enter id of new categories of products : ");
            int idCategories = ExceptionManager.exceptionPositiveInteger();
            // categories tham chiếu đến một categories trong categoriesList có id = idCategories
            Category category = categoriesManager.findCategoryById(idCategories);
            product.setName(name);
            product.setQuantity(quantity);
            product.setDescription(description);
            product.setPrice(price);
            product.setCategories(category);
            System.out.println("Edit successfully!");
        }
    }

    public Product findProductsById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }


    public void findProductByApproximateName(){
        ArrayList<Product> listFindName = new ArrayList<>();
        System.out.println("Enter name of Product:");
        String nameSearch = scanner.nextLine();
        for (Product product : products) {
            if (product.getName().contains(nameSearch)) {
                listFindName.add(product);
            }
        }
        if (listFindName.isEmpty()) {
            System.out.println("There are no product matching this name: " + nameSearch);
        } else {
            for (Product product : listFindName) {
                System.out.println(product);
            }
        }
    }
    public void findProductByPriceRange() {
        ArrayList<Product> listFindPrice = new ArrayList<>();
        System.out.println("Enter the min price:");
        double minPrice = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter the max price:");
        double maxPrice = Double.parseDouble(scanner.nextLine());
        for(Product product : products) {
            if (product.getPrice() <= maxPrice && product.getPrice() >= minPrice) {
                listFindPrice.add(product);
            }
        }
        if (listFindPrice.isEmpty()) {
            System.err.println("There are no product in price range " + minPrice + " - " + maxPrice);
            } else {
            for (Product product : listFindPrice) {
                System.out.println(product);
            }
        }
    }
    public void displayProductByCategories(CategoriesManager categoriesManager) {
        ArrayList<Product> listProducts = new ArrayList<>();
        System.out.println("List of categories:");
        categoriesManager.displayCategories();
        System.out.println("Select the id of the category you want to display products:");
        int idCategory = Integer.parseInt(scanner.nextLine());
        for (Product product : products) {
            if (product.getCategories().getId() == idCategory) {
                listProducts.add(product);
            }
        }
        if (listProducts.isEmpty()) {
            System.out.println("This category has no products!");
        } else {
            for (Product product : listProducts) {
                System.out.println(product);
            }
        }
    }

    public void sortByQuantity(ComparatorQuantity comparatorQuantity) {
        products.sort(comparatorQuantity);
    }

    public void sortByPriceAscending(ComparatorPriceAscending comparatorPriceAscending) {
        products.sort(comparatorPriceAscending);
    }

    public void sortByPriceDescending(ComparatorPriceDescending comparatorPriceDescending) {
        products.sort(comparatorPriceDescending);
    }
    public boolean IdIsExist(int id) {
        boolean check = false;
        for (Product p : products) {
            if (p.getId() == id) {
                check = true;
                break;
            }
        }
        return check;
    }
}
