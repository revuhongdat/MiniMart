package services;

import models.Categories;
import models.Products;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    public void exportData(ArrayList<Products> productsList, ArrayList<Categories> categoriesList) {
        try {
            File fileOutClasses = new File("D:\\Module2_APJ\\HomeWork\\src\\dataClasses.txt");
            BufferedWriter brClasses = new BufferedWriter(new FileWriter(fileOutClasses));
            for (Categories c : categoriesList) {
                brClasses.write(c.getId() + "," + c.getName() + "\n");
            }
            brClasses.close();
            File fileOutStudents = new File("D:\\Module2_APJ\\HomeWork\\src\\dataStudents.txt");
            BufferedWriter brStudents = new BufferedWriter(new FileWriter(fileOutStudents));
            for (Products s : productsList) {
                brStudents.write(s.getId() + "," + s.getName() + "," + s.getQuantity() + "," + s.getDescription() + "," + s.getPrice() + "," + s.getCategories().getId() + "," + s.getCategories().getName() + "\n");
            }
            brStudents.close();
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
