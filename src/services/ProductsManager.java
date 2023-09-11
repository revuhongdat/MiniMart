package services;

import models.Category;
import models.Product;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductsManager {
    private final ArrayList<Product> products;
    private final Scanner scanner;


    public ProductsManager() {
        products = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
    public boolean isDuplicateName(String name) {
        boolean check = false;
        for (Product product : products) {
            if (product.getName().equals(name)) {
                check = true;
                break;
            }
        }
        return check;
    }
    public void addProduct(CategoriesManager categoriesManager) {
        boolean check = false;
        String name = "";
        do {
            System.out.println("Nhập tên của sản phẩm :");
            name = scanner.nextLine();
            check = isDuplicateName(name);
            if (check) {
                System.out.println("Tên sản phẩm đã có sẵn, nhập lại!");
            }
        } while (check);
        int quantity = ExceptionManager.exceptionQuantity();
        double price = ExceptionManager.exceptionPrice();
        // Check xem danh sách categories có trống ko, nếu trống thì tạo một categories cho product,
        // nếu đã có categories thì hiển thị danh sách categories để cho người dùng chọn
        if (categoriesManager.getCategories().isEmpty()) {
            System.err.println("Danh sách nhãn hàng đang trống, tạo nhãn hàng cho sản phẩm mới bằng cách nhập tên!");
            categoriesManager.createCategory();
            Category category = categoriesManager.findCategoryById(1);
            Product product = new Product(name, quantity, price, category);
            products.add(product);
        } else {
            System.out.println("Danh sách nhãn hàng:");
            categoriesManager.displayCategories();
            System.out.println("Nhập id để chọn nhãn hàng cho sản phẩm mới:");
            int idCategory = ExceptionManager.exceptionPositiveInteger();
            Category category = categoriesManager.findCategoryById(idCategory);
            Product product = new Product(name, quantity, price, category);
            products.add(product);
        }
    }
    public void loadProduct(ArrayList<String[]> arrayList, CategoriesManager categoriesManager) {
        for (int i = 0; i < arrayList.size(); i++) {
            int id = Integer.parseInt(arrayList.get(i)[0]);
            if (Product.getINDEX() < id) {
                Product.setINDEX(id);
            }
            String name = arrayList.get(i)[1];
            int quantity = Integer.parseInt(arrayList.get(i)[2]);
            double price = Double.parseDouble(arrayList.get(i)[3]);
            int idCategory = Integer.parseInt(arrayList.get(i)[4]);
            Category category = categoriesManager.findCategoryById(idCategory);
            Product product = new Product(name, quantity, price, category);
            products.add(product);
        }
    }

    private void displayProductFormat(Product product) {
        System.out.printf("ID = %-5s, Tên = %-10s, Số lượng = %-5d, Giá = %-10.0f, Nhãn hàng = %-10s%n",product.getId(),product.getName(),product.getQuantity(),product.getPrice(),product.getCategories().getName());
    }
    public void displayProducts(){
        for (Product product : products) {
            displayProductFormat(product);
        }
    }
    public void displayProductsForCustomer() {
        for (Product product : products) {
            if (product.getQuantity() > 0) {
                displayProductFormat(product);
            }
        }
    }

    public void deleteProduct(){
        displayProducts();
        System.out.println("Nhập id của sản phẩm muốn xoá : ");
        int id = ExceptionManager.exceptionPositiveInteger();
        Product product = findProductsById(id);
        if (product == null) {
            System.err.println("Không tìm thấy sản phẩm!");
        } else {
            products.remove(product);
            System.err.println("Đã xoá thành công!");
        }
    }
    public void editProduct(CategoriesManager categoriesManager){
        displayProducts();
        System.out.println("Nhập id: ");
        int id = ExceptionManager.exceptionPositiveInteger();
        Product product = findProductsById(id);
        if (product == null) {
            System.err.println("Không có sản phẩm nào có id: " + id);
        } else {
            System.out.println("Nhập tên mới của sản phẩm: ");
            String name = scanner.nextLine();
            int quantity = ExceptionManager.exceptionQuantity();
            double price = ExceptionManager.exceptionPrice();
            // Hiển thị danh sách categories và cho người dùng nhập id của categories muốn chọn
            // Lấy categories có id tương ứng từ trong categoriesList => add vào student
            System.out.println("Danh mục sản phẩm:");
            categoriesManager.displayCategories();
            System.out.println("Nhập id để chọn danh mục cho sản phẩm mới:");
            int idCategory = ExceptionManager.exceptionPositiveInteger();
            // category tham chiếu đến một category trong categoriesList có id = idCategories
            Category category = categoriesManager.findCategoryById(idCategory);
            product.setName(name);
            product.setQuantity(quantity);
            product.setPrice(price);
            product.setCategories(category);
            System.err.println("Sửa thành công!");
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
        System.out.println("Nhập tên của sản phẩm:");
        String nameSearch = scanner.nextLine();
        for (Product product : products) {
            if (product.getName().contains(nameSearch)) {
                listFindName.add(product);
            }
        }
        if (listFindName.isEmpty()) {
            System.out.println("Không có sản phẩm có tên gần giống với : " + nameSearch);
        } else {
            for (Product product : listFindName) {
                System.out.println(product);
            }
        }
    }
    public void findProductByPriceRange() {
        ArrayList<Product> listFindPrice = new ArrayList<>();
        System.out.println("Nhập giá nhỏ nhất:");
        double minPrice = Double.parseDouble(scanner.nextLine());
        System.out.println("Nhập giá lớn nhất:");
        double maxPrice = Double.parseDouble(scanner.nextLine());
        for(Product product : products) {
            if (product.getPrice() <= maxPrice && product.getPrice() >= minPrice) {
                listFindPrice.add(product);
            }
        }
        if (listFindPrice.isEmpty()) {
            System.err.println("Không có sản phẩm nào có giá nằm trong khoảng: " + minPrice + " - " + maxPrice);
            } else {
            for (Product product : listFindPrice) {
                System.out.println(product);
            }
        }
    }
    public void displayProductByCategories(CategoriesManager categoriesManager) {
        ArrayList<Product> listProducts = new ArrayList<>();
        System.out.println("Danh sách các nhãn hàng:");
        categoriesManager.displayCategories();
        System.out.println("Nhập id của nhãn hàng bạn muốn hiển thị:");
        int idCategory = Integer.parseInt(scanner.nextLine());
        for (Product product : products) {
            if (product.getCategories().getId() == idCategory) {
                listProducts.add(product);
            }
        }
        if (listProducts.isEmpty()) {
            System.err.println("Nhãn hàng này chưa có sản phẩm nào!");
        } else {
            for (Product product : listProducts) {
                System.out.println(product);
            }
        }
    }
    public void sortByPriceAscending(ComparatorPriceAscending comparatorPriceAscending) {
        products.sort(comparatorPriceAscending);
    }

    public void sortByPriceDescending(ComparatorPriceDescending comparatorPriceDescending) {
        products.sort(comparatorPriceDescending);
    }
}
