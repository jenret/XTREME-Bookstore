package com.example.xtremebookstore.models;

public class SalesPerAuthor {

    private String authorName;
    private Double salesTotal;
    private int numberOfSales;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Double getSalesTotal() {
        return salesTotal;
    }

    public void setSalesTotal(Double salesTotal) {
        this.salesTotal = salesTotal;
    }

    public int getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(int numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    public SalesPerAuthor() {
    }

    public SalesPerAuthor(String authorName, Double salesTotal, int numberOfSales) {
        this.authorName = authorName;
        this.salesTotal = salesTotal;
        this.numberOfSales = numberOfSales;
    }
}
