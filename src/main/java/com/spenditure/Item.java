package com.spenditure;

public class Item {
    private String name;
    private double price;
    private String currency;
    private String category;

    public Item(String name, double price, String currency, String category) {
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCategory() {
        return category;
    }

    // Add the setter method
    public void setCategory(String category) {
        this.category = category;
    }
}