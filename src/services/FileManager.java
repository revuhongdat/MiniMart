package services;

import models.Cart;
import models.Category;
import models.Product;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    public void exportData(ArrayList<Product> productList, ArrayList<Category> categoryList) {
        try {
            File fileOutClasses = new File("D:\\MiniMart\\src\\dataCategories.txt");
            BufferedWriter brCategories = new BufferedWriter(new FileWriter(fileOutClasses));
            for (Category c : categoryList) {
                brCategories.write(c.getId() + "," + c.getName() + "\n");
            }
            brCategories.close();
            File fileOutStudents = new File("D:\\MiniMart\\src\\dataProducts.txt");
            BufferedWriter brProducts = new BufferedWriter(new FileWriter(fileOutStudents));
            for (Product p : productList) {
                brProducts.write(p.getId() + "," + p.getName() + "," + p.getQuantity() + "," + p.getDescription() + "," + p.getPrice() + "," + p.getCategories().getId() + "," + p.getCategories().getName() + "\n");
            }
            brProducts.close();
        } catch (IOException e) {
            String message = e.getMessage();
            System.out.println(message);
        }
    }
    public ArrayList<String[]> importData(String linkFile) {
        ArrayList<String[]> listData = new ArrayList<>();
        try {
            File file = new File(linkFile);
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                listData.add(line.split(","));
            }
        } catch (IOException e) {
            String message = e.getMessage();
            System.out.println(message);
        }
        return listData;
    }
}
