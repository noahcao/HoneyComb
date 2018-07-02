package model;

import com.opensymphony.xwork2.ActionSupport;

public class Book extends ActionSupport {
    private int id;

    private String name;
    private String author;
    private String summary;
    private float price;
    private int stock;
    private int year;

    public Book() {
    }

    public float getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getYear() {
        return year;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
