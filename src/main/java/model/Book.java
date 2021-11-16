package model;

import java.util.List;

public class Book {
private  int id;
private String name;
private double price;
private String note;
private List<Category> categories;
    public Book() {
    }

    public Book(int id, String name, double price, String note) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.note = note;
    }

    public Book(String name, double price, String note, List<Category> categories) {
        this.name = name;
        this.price = price;
        this.note = note;
        this.categories = categories;
    }

    public Book(int id, String name, double price, String note, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.note = note;
        this.categories = categories;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
