package services;
import models.Category;

import java.util.ArrayList;
import java.util.Scanner;


public class CategoriesManager {
    private static int INDEX = 0;
    private final ArrayList<Category> categories;
    private final Scanner scanner;
    public CategoriesManager() {
        categories = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    public ArrayList<Category> getCategories() {
        return categories;
    }
    public void createCategory() {
        System.out.print("Enter name of new Categories : ");
        String nameNewCategories = scanner.nextLine();
        Category newCategory = new Category(++INDEX, nameNewCategories);
        categories.add(newCategory);
    }
    public void loadCategories(ArrayList<String[]> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            int id = Integer.parseInt(arrayList.get(i)[0]);
            if (INDEX < id) {
                INDEX = id;
            }
            String name = arrayList.get(i)[1];
            Category newCategory = new Category(id, name);
            categories.add(newCategory);
        }
    }


    public void displayCategories() {
        for (Category c : categories) {
            System.out.println(c);
        }
    }

    public Category findCategoryById(int id) {
        for (Category c : categories) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public void editCategory() {
        System.out.print("Enter the id of category you want edit :");
        int id = Integer.parseInt(scanner.nextLine());
        Category c = findCategoryById(id);
        if (c != null) {
            System.out.println("Enter the new name of category :");
            String newName = scanner.nextLine();
            c.setName(newName);
            System.out.println("Edit successfully!");
        } else {
            System.err.println("Not found!");
        }
    }
    private boolean IdIsExist(int id) {
        boolean check = false;
        for (Category p : categories) {
            if (p.getId() == id) {
                check = true;
                break;
            }
        }
        return check;
    }
}
