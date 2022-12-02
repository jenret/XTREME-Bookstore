package com.example.xtremebookstore.models;


import java.util.Date;

public class BookModel {
    public String ISBN;
    private String title;
    private int author;

    private String authorName;
    private int edition;
    private String publishDate;
    private double purchasePrice;

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public BookModel(String ISBN, String title, int author, String authorName, int edition, String publishDate , double purchasePrice) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.authorName = authorName;
        this.edition = edition;
        this.publishDate = publishDate;
        this.purchasePrice = purchasePrice;
    }

    public BookModel(String ISBN, String title, int author, String authorName, String publishDate , double purchasePrice) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.authorName = authorName;
        this.publishDate = publishDate;
        this.purchasePrice = purchasePrice;
    }
    public BookModel() {
    }
}
