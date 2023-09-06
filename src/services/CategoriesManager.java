package services;
import models.Categories;

import java.util.ArrayList;
import java.util.Scanner;


public class CategoriesManager {
    private static int INDEX = 0;
    private final ArrayList<Categories> categoriesList;
    private final Scanner scanner;

    public static void setINDEX(int INDEX) {
        CategoriesManager.INDEX = INDEX;
    }

    public CategoriesManager() {
        categoriesList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public ArrayList<Categories> getCategoriesList() {
        return categoriesList;
    }

    public void createCategories() {
        System.out.print("Enter name of new Categories : ");
        String nameNewCategories = scanner.nextLine();
        Categories newCategories = new Categories(++INDEX, nameNewCategories);
        categoriesList.add(newCategories);
    }
    public void loadCategories(ArrayList<String[]> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            int id = Integer.parseInt(arrayList.get(i)[0]);
            if (INDEX < id) {
                INDEX = id;
            }
            String name = arrayList.get(i)[1];
            Categories newCategories = new Categories(id, name);
            categoriesList.add(newCategories);
        }
    }


    public void displayCategories() {
        for (Categories c : categoriesList) {
            System.out.println(c);
        }
    }

    public Categories findCategoriesById(int id) {
        for (Categories c : categoriesList) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public void editCategories() {
        System.out.print("Enter the id of categories you want edit :");
        int id = Integer.parseInt(scanner.nextLine());
        Categories c = findCategoriesById(id);
        if (c != null) {
            System.out.println("Enter the new name of categories :");
            String newName = scanner.nextLine();
            c.setName(newName);
            System.out.println("Edit successfully!");
        } else {
            System.err.println("Not found!");
        }
    }
}
