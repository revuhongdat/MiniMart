package services;
import models.Products;
import java.util.Comparator;
public class ComparatorQuantity implements Comparator<Products> {
    @Override
    public int compare(Products o1, Products o2) {
        return o1.getQuantity() - o2.getQuantity();
    }
}
