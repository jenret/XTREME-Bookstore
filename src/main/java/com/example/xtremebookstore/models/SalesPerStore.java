package com.example.xtremebookstore.models;

public class SalesPerStore {

    private String storeName;
    private Double salesTotal;
    private int numberOfSales;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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

    public SalesPerStore() {
    }

    public SalesPerStore(String storeName, Double salesTotal, int numberOfSales) {
        this.storeName = storeName;
        this.salesTotal = salesTotal;
        this.numberOfSales = numberOfSales;
    }
}
