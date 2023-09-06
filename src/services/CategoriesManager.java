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

    public ArrayList<Categories> getClassesList() {
        return categoriesList;
    }

    public void createClasses() {
        System.out.print("Enter name of new class : ");
        String nameNewClass = scanner.nextLine();
        Categories newClass = new Categories(++INDEX, nameNewClass);
        categoriesList.add(newClass);
    }
    public void loadClasses(ArrayList<String[]> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            int id = Integer.parseInt(arrayList.get(i)[0]);
            if (INDEX < id) {
                INDEX = id;
            }
            String name = arrayList.get(i)[1];
            Categories newClass = new Categories(id, name);
            categoriesList.add(newClass);
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

    public void editClasses() {
        System.out.print("Enter the id of class you want edit :");
        int id = Integer.parseInt(scanner.nextLine());
        Categories c = findCategoriesById(id);
        if (c != null) {
            System.out.println("Enter the new name of class :");
            String newName = scanner.nextLine();
            c.setName(newName);
            System.out.println("Edit name successfully!");
        } else {
            System.out.println("Not found!!!");
        }
    }
}
