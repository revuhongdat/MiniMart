package services;
import models.*;

import java.io.*;
import java.util.ArrayList;
public class FileManager {
    public void exportData(ArrayList<Product> productList, ArrayList<Category> categoryList) {
        try {
            File fileOutClasses = new File("src/dataCategories.txt");
            BufferedWriter brCategories = new BufferedWriter(new FileWriter(fileOutClasses));
            for (Category c : categoryList) {
                brCategories.write(c.getId() + "," + c.getName() + "\n");
            }
            brCategories.close();
            File fileOutStudents = new File("src/dataProducts.txt");
            BufferedWriter brProducts = new BufferedWriter(new FileWriter(fileOutStudents));
            for (Product p : productList) {
                brProducts.write(p.getId() + "," +
                        p.getName() + "," +
                        p.getQuantity() + "," +
                        p.getPrice() + "," +
                        p.getCategories().getId() + "," +
                        p.getCategories().getName() + "\n");
            }
            brProducts.close();
        } catch (IOException e) {
            String message = e.getMessage();
            System.err.println(message);
        }
    }
    public void exportItems(ArrayList<Customer> customers) {
        try {
            File file = new File("src/dataItems.txt");
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            for (Customer customer : customers) {
                for (Cart cart : customer.getCarts()) {
                    for (Item item : cart.getItems()) {
                        br.write(item.getId() + "," +
                                item.getProduct().getId() + "," +
                                item.getBuyQuantity() + "\n");
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            String message = e.getMessage();
            System.err.println(message);
        }
    }
    public void exportCarts(ArrayList<Customer> customers) {
        try {
            File file = new File("src/dataCarts.txt");
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            for (Customer customer : customers) {
                for (Cart cart : customer.getCarts()) {
                        br.write(cart.getId() + "," +
                                cart.getMoney() + "," +
                                cart.isStatus() + "," +
                                cart.getDate() + "," +
                                cart.idItemsList() + "\n");
                }
            }
            br.close();
        } catch (IOException e) {
            String message = e.getMessage();
            System.err.println(message);
        }
    }
    public void exportCustomers(ArrayList<Customer> customers) {
        try {
            File file = new File("src/dataCustomers.txt");
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            for (Customer c : customers) {
                br.write(c.getId() + "," +
                        c.getName() + "," +
                        c.getAccount() + "," +
                        c.getPassword() + "," +
                        c.idCartsList() + "\n");
            }
            br.close();
        } catch (IOException e) {
            String message = e.getMessage();
            System.err.println(message);
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
            System.err.println(message);
        }
        return listData;
    }
}
