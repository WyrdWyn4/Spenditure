package com.spenditure;

import java.util.List;

public class Receipt {
    private List<Item> items;
    private double total;
    private String currency;
    private String location;

    public Receipt(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}