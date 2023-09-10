package models;

public class Category {
    private static int INDEX = 1;
    private int id;
    private String name;

    public Category(String name) {
        this.id = INDEX++;
        this.name = name;
    }

    public static int getINDEX() {
        return INDEX;
    }

    public static void setINDEX(int INDEX) {
        Category.INDEX = INDEX;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name=" + name + '}';
    }
}
