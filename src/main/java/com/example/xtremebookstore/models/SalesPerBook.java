package com.example.xtremebookstore.models;

public class SalesPerBook {

    private String title;
    private Double salesTotal;
    private int numberOfSales;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public SalesPerBook() {}

    public SalesPerBook(String title, Double salesTotal, int numberOfSales) {
        this.title = title;
        this.salesTotal = salesTotal;
        this.numberOfSales = numberOfSales;
    }
}
