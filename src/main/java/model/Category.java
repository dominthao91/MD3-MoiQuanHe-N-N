package model;

import java.util.List;

public class Category {
    private int id;
    private String name;
    private String note;
    private List<Book>bookList;

    public Category() {
    }

    public Category(int id, String name, String note) {
        this.id = id;
        this.name = name;
        this.note = note;
    }

    public Category(String name, String note, List<Book> bookList) {
        this.name = name;
        this.note = note;
        this.bookList = bookList;
    }

    public Category(int id, String name, String note, List<Book> bookList) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.bookList = bookList;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
